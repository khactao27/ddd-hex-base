package tech.ibrave.metabucket.application.user.restful.facade;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.ibrave.metabucket.application.auth.base.SecurityContext;
import tech.ibrave.metabucket.application.user.model.CreateUserResult;
import tech.ibrave.metabucket.application.user.model.ExportedUser;
import tech.ibrave.metabucket.application.user.model.ImportedUser;
import tech.ibrave.metabucket.application.user.model.ImportedUserResult;
import tech.ibrave.metabucket.application.user.restful.mapper.UserMapper;
import tech.ibrave.metabucket.application.user.restful.request.ChangePasswordReq;
import tech.ibrave.metabucket.application.user.restful.request.ExportUserReq;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.application.user.restful.request.UpdateBulkUserReq;
import tech.ibrave.metabucket.application.user.restful.response.GetUserExportFieldsResp;
import tech.ibrave.metabucket.application.user.restful.response.ImportUserResp;
import tech.ibrave.metabucket.application.user.restful.response.ResetPasswordResp;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.dto.UserAuditingObject;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;
import tech.ibrave.metabucket.shared.utils.ExcelUtils;
import tech.ibrave.metabucket.shared.utils.ObjectUtils;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserFacade {
    private final Validator validator;
    private final UserMapper userMapper;
    private final UserUseCase userUseCase;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContext securityContext;
    @Value("${file.dir.import-result}")
    private String importResultDir;
    private static final String FILE_NAME_PREFIX = "import_result";

    @Value("${security.default-pwd}")
    private String defaultPwd;

    public SuccessResponse createUser(PersistUserReq req) {
        validateExistEmail(req.getEmail());
        validateExistUsername(req.getUsername());
        var passwordEncoded = passwordEncoder.encode(defaultPwd);
        var user = userMapper.toUser(req, passwordEncoded);
        return new SuccessResponse(userUseCase.save(user).getId(),
                messageSource.getMessage("mb.users.create.success"));
    }

    public SuccessResponse updateUser(String userId, PersistUserReq req) {
        var user = userUseCase.getOrElseThrow(userId);
        if (!StringUtils.equals(user.getEmail(), req.getEmail())) {
            validateExistEmail(req.getEmail());
        }
        if (!StringUtils.equals(user.getUsername(), req.getUsername())) {
            validateExistUsername(req.getUsername());
        }

        userMapper.updateUser(user, req);
        var updatedUser = userUseCase.save(user);

        return new SuccessResponse(updatedUser.getId(),
                messageSource.getMessage("mb.users.update.success"));
    }

    public SuccessResponse updateBulkUser(UpdateBulkUserReq req) {
        userUseCase.updateStatusBulkUser(req.getUserIds(), req.isEnable());
        return SuccessResponse.ofMessage("mb.users.bulk-update.success");
    }

    public Page<UserAuditingObject> searchUser(SearchUserReq req) {
        return userUseCase.searchUser(req);
    }

    public UserAuditingObject getUser(String userId) {
        return userUseCase.findByIdUseDto(userId);
    }

    public ResetPasswordResp resetPassword(String userId) {
        // todo Check if use can edit

        var newPassword = RandomStringUtils.randomAlphabetic(8);
        var updatedUser = userUseCase.update(userId, updateUser -> updateUser.setPassword(passwordEncoder.encode(newPassword)));

        return new ResetPasswordResp(updatedUser.getId(),
                messageSource.getMessage("mb.users.reset_pw.success"),
                newPassword);
    }

    @SneakyThrows
    public ImportUserResp importUser(MultipartFile file) {
        var importedUsers = readExcelFile(file);
        var invalidUsers = new ArrayList<ImportedUserResult>();
        for (var user : importedUsers) {
            var result = saveAndHandle(user);
            if (!result.isSuccess()) {
                invalidUsers.add(userMapper.toImportedResult(user, result.getMessage()));
            }
        }
        var numOfSuccess = importedUsers.size() - invalidUsers.size();
        var importId = UUID.randomUUID().toString();
        var fileName = FILE_NAME_PREFIX + "_" + importId + ".xlsx";
        var outputStream = new FileOutputStream(fileName);
        var importResultFile = generateExcelFile(invalidUsers);
        importResultFile.write(outputStream);
        outputStream.close();
        return new ImportUserResp(numOfSuccess, invalidUsers.size(), importId);
    }

    public void getImportResult(String importId, HttpServletResponse response) {
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=importedResults.xlsx";
        response.setHeader(headerKey, headerValue);
        try {
            var fileName = FILE_NAME_PREFIX + "_" + importId + ".xlsx";
            var outputStream = response.getOutputStream();
            outputStream.write(Files.readAllBytes(Path.of(fileName)));
            outputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private List<ImportedUser> readExcelFile(MultipartFile file) {
        try {

            var workbook = new XSSFWorkbook(file.getInputStream());
            var firstSheet = workbook.getSheetAt(0);
            var users = new ArrayList<ImportedUser>();
            var numberOfRow = firstSheet.getPhysicalNumberOfRows();
            if (numberOfRow > 1000) {
                throw new ErrorCodeException(ErrorCodes.TOO_LARGE_FILE);
            }
            for (var i = 1; i < numberOfRow; i++) {
                var row = firstSheet.getRow(i);
                var cellIterator = row.cellIterator();
                cellIterator.next(); //ignore STT cell
                var user = ImportedUser.builder()
                        .username(cellIterator.next().getStringCellValue())
                        .lastName(cellIterator.next().getStringCellValue())
                        .firstName(cellIterator.next().getStringCellValue())
                        .email(cellIterator.next().getStringCellValue())
                        .title(cellIterator.next().getStringCellValue())
                        .location(cellIterator.next().getStringCellValue())
                        .phone(cellIterator.next().getStringCellValue())
                        .status(cellIterator.next().getStringCellValue())
                        .build();
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorCodeException(ErrorCodes.READ_FILE_ERROR);
        }
    }

    public GetUserExportFieldsResp getUserExportFields() {
        return new GetUserExportFieldsResp(ObjectUtils.getFieldsString(ExportedUser.class));
    }

    private CreateUserResult saveAndHandle(ImportedUser importedUser) {
        try {
            var violations = validator.validate(importedUser, Default.class);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            validateExistEmail(importedUser.getEmail());
            validateExistUsername(importedUser.getUsername());
            var defaultPassword = passwordEncoder.encode(defaultPwd);
            userUseCase.save(userMapper.toUser(importedUser, defaultPassword));
            return new CreateUserResult(true, "");
        } catch (ConstraintViolationException e) {
            log.error(e.getMessage());
            return new CreateUserResult(false, e.getMessage());
        } catch (ErrorCodeException e) {
            log.error(e.getMessage());
            return new CreateUserResult(false, e.getErrorCode().messageCode());
        }
    }

    private void writeHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        ExcelUtils.createCell(sheet, row, 0, "username", style);
        ExcelUtils.createCell(sheet, row, 1, "firstName", style);
        ExcelUtils.createCell(sheet, row, 2, "lastName", style);
        ExcelUtils.createCell(sheet, row, 3, "email", style);
        ExcelUtils.createCell(sheet, row, 4, "phone", style);
        ExcelUtils.createCell(sheet, row, 5, "location", style);
        ExcelUtils.createCell(sheet, row, 6, "status", style);
        ExcelUtils.createCell(sheet, row, 7, "title", style);
        ExcelUtils.createCell(sheet, row, 8, "message", style);
    }

    private void write(XSSFWorkbook workbook,
                       XSSFSheet sheet,
                       List<ImportedUserResult> importedUserResults) {
        int rowCount = 1;
        var style = workbook.createCellStyle();
        var font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (var user : importedUserResults) {
            var row = sheet.createRow(rowCount++);
            int columnCount = 0;
            ExcelUtils.createCell(sheet, row, columnCount++, user.getUsername(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getFirstName(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getLastName(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getEmail(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getPhone(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getLocation(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getEmail(), style);
            ExcelUtils.createCell(sheet, row, columnCount++, user.getTitle(), style);
            ExcelUtils.createCell(sheet, row, columnCount, user.getMessage(), style);
        }
    }

    public XSSFWorkbook generateExcelFile(List<ImportedUserResult> importedUserResults) {
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("User");
        writeHeader(workbook, sheet);
        write(workbook, sheet, importedUserResults);
        return workbook;
    }

    public void exportUser(ExportUserReq req, HttpServletResponse response) {
        validateExportFields(req.getFields());
        var usersToExport = req.getUsers();
        if (usersToExport.isEmpty()) {
            usersToExport = userUseCase.searchUser(req).getData();
        }
        try {
            var workbook = new XSSFWorkbook();
            var sheet = workbook.createSheet("User");
            ExcelUtils.createHeader(workbook, sheet, req.getFields());
            var style = workbook.createCellStyle();
            var font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);
            int rowCount = 1;
            for (var user : usersToExport) {
                var row = sheet.createRow(rowCount++);
                int columnCount = 0;
                for (var field : req.getFields()) {
                    var fieldValue = ObjectUtils.invokeGetMethod(field, user, UserAuditingObject.class);
                    ExcelUtils.createCell(sheet, row, columnCount++, fieldValue, style);
                }
            }
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=export_users.xlsx";
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorCodeException(ErrorCodes.EXPORT_ERROR);
        }
    }

    public UserAuditingObject getUserProfile() {
        var user = securityContext.getUser();
        return userMapper.toDto(user);
    }

    public SuccessResponse changePassword(ChangePasswordReq req) {
        var user = securityContext.getUser();
        if (!passwordEncoder.matches(req.getCurrentPassword(), user.getPassword())) {
            throw new ErrorCodeException(ErrorCodes.INCORRECT_PASSWORD);
        }
        if (req.getNewPassword().equals(req.getCurrentPassword())) {
            throw new ErrorCodeException(ErrorCodes.DUPLICATE_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        var userId = userUseCase.save(user).getId();
        return new SuccessResponse(userId, messageSource.getMessage("mb.user.update.success"));
    }

    private void validateExportFields(List<String> fields) {
        var listExportFields = ObjectUtils.getFieldsString(ExportedUser.class);
        for (var field : fields) {
            if (!listExportFields.contains(field)) {
                throw new ErrorCodeException(ErrorCodes.INVALID_FIELD);
            }
        }
    }

    private void validateExistUsername(String username) {
        if (userUseCase.existByUsername(username)) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_USERNAME);
        }
    }

    private void validateExistEmail(String email) {
        if (userUseCase.existByEmail(email)) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_EMAIL);
        }
    }
}

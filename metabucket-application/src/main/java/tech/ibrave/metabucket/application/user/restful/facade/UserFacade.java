package tech.ibrave.metabucket.application.user.restful.facade;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.ibrave.metabucket.application.user.model.CreateUserResult;
import tech.ibrave.metabucket.application.user.model.ImportedUser;
import tech.ibrave.metabucket.application.user.model.ImportedUserResult;
import tech.ibrave.metabucket.application.user.restful.mapper.UserMapper;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.application.user.restful.request.UpdateBulkUserReq;
import tech.ibrave.metabucket.application.user.restful.response.ImportUserResp;
import tech.ibrave.metabucket.application.user.restful.response.ResetPasswordResp;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private static final String DEFAULT_PASSWORD = "abcd@1234";

    public SuccessResponse createUser(PersistUserReq req) {
        validateExistedValue(req.getUsername(), req.getEmail());
        var passwordEncoded = passwordEncoder.encode(DEFAULT_PASSWORD);
        var user = userMapper.toUser(req, passwordEncoded);
        return new SuccessResponse(userUseCase.save(user).getId(),
                messageSource.getMessage("mb.users.create.success"));
    }

    public SuccessResponse updateUser(String userId, PersistUserReq req) {
        var user = userUseCase.getOrElseThrow(userId);
        userMapper.updateUser(user, req);
        var updatedUser = userUseCase.save(user);

        return new SuccessResponse(updatedUser.getId(),
                messageSource.getMessage("mb.users.update.success"));
    }

    public SuccessResponse updateBulkUser(UpdateBulkUserReq req) {
        userUseCase.updateStatusBulkUser(req.getUserIds(), req.isEnable());
        return SuccessResponse.ofMessage("mb.users.bulk-update.success");
    }

    public Page<UserDto> searchUser(SearchUserReq req) {
        return userUseCase.searchUser(req);
    }

    public UserDto getUser(String userId) {
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

    public ImportUserResp importUser(MultipartFile file, HttpServletResponse response) {
        var importedUsers = readExcelFile(file);
        var invalidUsers = new ArrayList<ImportedUserResult>();
        for (var user : importedUsers) {
            var result = saveAndHandle(user);
            if (!result.isSuccess()) {
                invalidUsers.add(userMapper.toImportedResult(user, result.getMessage()));
            }
        }
        var numOfSuccess = importedUsers.size() - invalidUsers.size();
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=importedResults.xlsx";
        response.setHeader(headerKey, headerValue);
        try {
            generateExcelFile(invalidUsers, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ImportUserResp(numOfSuccess, invalidUsers.size());
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
                var cell = cellIterator.next();
                var user = ImportedUser.builder()
                        .username(cell.getStringCellValue())
                        .firstName(cellIterator.next().getStringCellValue())
                        .lastName(cellIterator.next().getStringCellValue())
                        .email(cellIterator.next().getStringCellValue())
                        .phone(cellIterator.next().getStringCellValue())
                        .location(cellIterator.next().getStringCellValue())
                        .enable(cellIterator.next().getBooleanCellValue())
                        .title(cellIterator.next().getStringCellValue())
                        .build();
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorCodeException(ErrorCodes.READ_FILE_ERROR);
        }
    }

    private CreateUserResult saveAndHandle(ImportedUser importedUser) {
        try {
            var violations = validator.validate(importedUser, ImportedUser.class);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            validateExistedValue(importedUser.getUsername(), importedUser.getEmail());
            var defaultPassword = passwordEncoder.encode(DEFAULT_PASSWORD);
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
        createCell(sheet, row, 0, "username", style);
        createCell(sheet, row, 1, "firstName", style);
        createCell(sheet, row, 2, "lastName", style);
        createCell(sheet, row, 3, "email", style);
        createCell(sheet, row, 4, "phone", style);
        createCell(sheet, row, 5, "location", style);
        createCell(sheet, row, 6, "enable", style);
        createCell(sheet, row, 7, "title", style);
        createCell(sheet, row, 8, "message", style);
    }

    private void write(XSSFWorkbook workbook,
                       XSSFSheet sheet,
                       List<ImportedUserResult> importedUserResults) {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (var record: importedUserResults) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(sheet, row, columnCount++, record.getUsername(), style);
            createCell(sheet, row, columnCount++, record.getFirstName(), style);
            createCell(sheet, row, columnCount++, record.getLastName(), style);
            createCell(sheet, row, columnCount++, record.getEmail(), style);
            createCell(sheet, row, columnCount++, record.getPhone(), style);
            createCell(sheet, row, columnCount++, record.getLocation(), style);
            createCell(sheet, row, columnCount++, record.getEmail(), style);
            createCell(sheet, row, columnCount++, record.getTitle(), style);
            createCell(sheet, row, columnCount, record.getMessage(), style);
        }
    }
    private void createCell(XSSFSheet sheet,
                            Row row,
                            int columnCount,
                            Object valueOfCell,
                            CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    public void generateExcelFile(List<ImportedUserResult> importedUserResults, HttpServletResponse response) throws IOException {
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("User");
        writeHeader(workbook, sheet);
        write(workbook, sheet, importedUserResults);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void validateExistedValue(String username, String email) {
        if (userUseCase.existByEmail(email)) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_EMAIL);
        }
        if (userUseCase.existByUsername(username)) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_USERNAME);
        }
    }
}

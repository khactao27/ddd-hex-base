package tech.ibrave.metabucket.application.user.restful.api;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.ibrave.metabucket.application.user.restful.facade.UserFacade;
import tech.ibrave.metabucket.application.user.restful.request.user.ChangePasswordReq;
import tech.ibrave.metabucket.application.user.restful.request.user.ExportUserReq;
import tech.ibrave.metabucket.application.user.restful.request.user.PersistUserReq;
import tech.ibrave.metabucket.application.user.restful.request.user.UpdateBulkUserReq;
import tech.ibrave.metabucket.application.user.restful.request.user.UpdateProfileReq;
import tech.ibrave.metabucket.application.user.restful.response.GetUserExportFieldsResp;
import tech.ibrave.metabucket.application.user.restful.response.ImportUserResp;
import tech.ibrave.metabucket.application.user.restful.response.ResetPasswordResp;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;
import tech.ibrave.metabucket.shared.validation.ValidationSequence;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserApi {
    private final UserFacade userFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('mb.users.create')")
    public SuccessResponse create(@Validated(ValidationSequence.class) @RequestBody PersistUserReq req) {
        return userFacade.createUser(req);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('mb.users.update')")
    public SuccessResponse update(@PathVariable String id,
                                  @Validated(ValidationSequence.class) @RequestBody PersistUserReq req) {
        return userFacade.updateUser(id, req);
    }

    @PostMapping("/update-bulk")
    @PreAuthorize("hasAnyAuthority('mb.users.update')")
    public SuccessResponse updateBulk(@RequestBody UpdateBulkUserReq req) {
        return userFacade.updateBulkUser(req);
    }

    @PostMapping("/{id}/reset-password")
    @PreAuthorize("hasAnyAuthority('mb.users.resetpassword')")
    public ResetPasswordResp resetPassword(@PathVariable String id) {
        return userFacade.resetPassword(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('mb.users.view')")
    public Page<UserDto> getListUser(@ModelAttribute SearchUserReq req) {
        return userFacade.searchUser(req);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('mb.users.view')")
    public UserDto getUser(@PathVariable("userId") String userId) {
        return userFacade.getUser(userId);
    }

    @PostMapping("/import")
    @PreAuthorize("hasAnyAuthority('mb.users.update')")
    public ImportUserResp importUser(@RequestParam("file") MultipartFile file) {
        return userFacade.importUser(file);
    }

    @GetMapping("/import/result")
    @PreAuthorize("hasAnyAuthority('mb.users.update')")
    public void getImportResult(@RequestParam String importId, HttpServletResponse response) {
        userFacade.getImportResult(importId, response);
    }

    @GetMapping("/export/fields")
    @PreAuthorize("hasAnyAuthority('mb.users.view')")
    public GetUserExportFieldsResp getExportedFields() {
        return userFacade.getUserExportFields();
    }

    @PostMapping("/export")
    @PreAuthorize("hasAnyAuthority('mb.users.view')")
    public void exportUser(@RequestBody ExportUserReq req, HttpServletResponse response) {
        userFacade.exportUser(req, response);
    }

    @GetMapping("/profile")
    public UserDto getUserProfile() {
        return userFacade.getUserProfile();
    }

    @PutMapping("/profile")
    public SuccessResponse updateUserProfile (@Valid @RequestBody UpdateProfileReq req) {
        return userFacade.updateProfile(req);
    }

    @PutMapping("/change-password")
    public SuccessResponse changePassword(@RequestBody ChangePasswordReq req) {
        return userFacade.changePassword(req);
    }
}

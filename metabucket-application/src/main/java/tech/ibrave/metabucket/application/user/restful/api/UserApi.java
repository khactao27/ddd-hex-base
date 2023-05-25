package tech.ibrave.metabucket.application.user.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.user.restful.facade.UserFacade;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.application.user.restful.response.ResetPasswordResp;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

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
    public SuccessResponse create(@Valid @RequestBody PersistUserReq req) {
        return userFacade.createUser(req);
    }

    @PutMapping("/{id}")
    public SuccessResponse update(@PathVariable String id, @Valid @RequestBody PersistUserReq req) {
        return userFacade.updateUser(id, req);
    }

    @PostMapping("/{id}/reset-password")
    public ResetPasswordResp resetPassword(@PathVariable String id) {
        return userFacade.resetPassword(id);
    }
}

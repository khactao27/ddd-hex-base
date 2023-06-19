package tech.ibrave.metabucket.application.auth.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.auth.restful.facade.AuthFacade;
import tech.ibrave.metabucket.application.auth.restful.request.ConfirmRegisterReq;
import tech.ibrave.metabucket.application.auth.restful.request.ForgotPasswordReq;
import tech.ibrave.metabucket.application.auth.restful.request.LoginReq;
import tech.ibrave.metabucket.application.auth.restful.request.RecoverPasswordReq;
import tech.ibrave.metabucket.application.auth.restful.request.RegisterReq;
import tech.ibrave.metabucket.application.auth.restful.response.ForgotPasswordSuccessResp;
import tech.ibrave.metabucket.application.auth.restful.response.LoginSuccessResp;
import tech.ibrave.metabucket.application.auth.restful.response.RegisterSuccessResp;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;
import tech.ibrave.metabucket.shared.validation.ValidationSequence;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthApi {
    private final AuthFacade facade;

    @PostMapping("/login")
    public LoginSuccessResp login(@Validated(ValidationSequence.class) @RequestBody LoginReq req) {
        return facade.login(req);
    }

    @PostMapping("/register")
    public RegisterSuccessResp register(@Validated(ValidationSequence.class) @RequestBody RegisterReq req) {
        return facade.register(req);
    }

    @PostMapping("/confirm-register/{token}")
    public LoginSuccessResp confirmRegister(@Validated(ValidationSequence.class) @RequestBody ConfirmRegisterReq req,
                                           @PathVariable String token) {
        return facade.confirmRegister(req, token);
    }

    @PostMapping("/forgot-password")
    public ForgotPasswordSuccessResp forgotPassword(@Valid @RequestBody ForgotPasswordReq req) {
        return facade.forgotPassword(req);
    }

    @PostMapping("/recover-password/{token}")
    public SuccessResponse recoverPassword(@Validated(ValidationSequence.class) @RequestBody RecoverPasswordReq req,
                                           @PathVariable String token) {
        return facade.recoverPassword(req, token);
    }
}

package tech.ibrave.metabucket.application.auth.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.auth.restful.facade.AuthFacade;
import tech.ibrave.metabucket.application.auth.restful.request.LoginReq;
import tech.ibrave.metabucket.application.auth.restful.response.LoginSuccessResp;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthFacade facade;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginSuccessResp login(@Valid @RequestBody LoginReq req) {
        return facade.login(req);
    }
}

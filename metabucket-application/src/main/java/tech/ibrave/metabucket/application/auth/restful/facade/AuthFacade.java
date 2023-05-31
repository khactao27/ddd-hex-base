package tech.ibrave.metabucket.application.auth.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.auth.base.AuthErrorCodes;
import tech.ibrave.metabucket.application.auth.base.UserRepoDetails;
import tech.ibrave.metabucket.application.auth.restful.mapper.PrincipalMapper;
import tech.ibrave.metabucket.application.auth.restful.request.ConfirmRegisterReq;
import tech.ibrave.metabucket.application.auth.restful.request.ForgotPasswordReq;
import tech.ibrave.metabucket.application.auth.restful.request.LoginReq;
import tech.ibrave.metabucket.application.auth.restful.request.RecoverPasswordReq;
import tech.ibrave.metabucket.application.auth.restful.request.RegisterReq;
import tech.ibrave.metabucket.application.auth.restful.response.ForgotPasswordSuccessResp;
import tech.ibrave.metabucket.application.auth.restful.response.LoginSuccessResp;
import tech.ibrave.metabucket.application.auth.restful.response.RegisterSuccessResp;
import tech.ibrave.metabucket.application.user.restful.mapper.UserMapper;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.domain.shared.mail.Email;
import tech.ibrave.metabucket.domain.shared.mail.EmailSender;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.constant.JwtTarget;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;
import tech.ibrave.metabucket.shared.utils.JwtUtils;

/**
 * Author: anct
 * Date: 29/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final JwtUtils jwtUtils;
    private final PrincipalMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final UserUseCase userUseCase;
    private final EmailSender emailSender;
    private final UserMapper userMapper;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    @Value("${base-url.confirm-register}")
    private String baseRegisterPasswordUrl;
    @Value("${base-url.recover-password}")
    private String baseRecoverPasswordUrl;

    private static final String CREATE_USER_SUBJECT = "Create user confirm email";

    public LoginSuccessResp login(LoginReq req) {
        if (req.getSource() == UserSource.SELF_REGISTER) {
            try {
                var basicAuth = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
                var authentication =  authenticationManager.authenticate(basicAuth);
                return map(authentication);
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
                throw new ErrorCodeException(AuthErrorCodes.INVALID_USERNAME_OR_PW);
            }
        }

        return null;
    }

    private LoginSuccessResp map(Authentication authentication) {
        var userDetails = (UserRepoDetails) authentication.getPrincipal();
        return new LoginSuccessResp(jwtUtils.generate(authentication), mapper.toDto(userDetails.getUser()));
    }

    public RegisterSuccessResp register(RegisterReq req) {
        validateExistedEmail(req.getEmail());
        var jwtToken = jwtUtils.generateRegisterUserJwt(req.getEmail());
        var registerUrl = baseRegisterPasswordUrl + jwtToken;
        var email = new Email();
        email.setBody(registerUrl);
        email.setTo(req.getEmail());
        email.setSubject(CREATE_USER_SUBJECT);
        emailSender.send(email);
        return new RegisterSuccessResp(registerUrl);
    }

    public SuccessResponse confirmRegister(ConfirmRegisterReq req, String token) {
        var jws = jwtUtils.validateTokenAndGetJws(token);
        if (jws.isEmpty() || !jws.get().getHeader().get("target").equals(JwtTarget.CREATE_USER.name())) {
            throw new ErrorCodeException(AuthErrorCodes.TOKEN_INVALID);
        }
        var email = jws.get().getBody().getSubject();
        validateExistedEmail(email);
        validateExistedUsername(req.getUsername());
        var encodedPassword = passwordEncoder.encode(req.getPassword());
        var user = userMapper.toUser(req, encodedPassword);
        user.setEmail(email);
        user.setEnable(true);
        var userId = userUseCase.save(user).getId();
        return new SuccessResponse(userId, messageSource.getMessage("mb.users.create.success"));
    }

    public ForgotPasswordSuccessResp forgotPassword(ForgotPasswordReq req) {
        if (!userUseCase.existByEmail(req.getEmail())) {
            throw new ErrorCodeException(ErrorCodes.NOT_FOUND);
        }
        var jwtToken = jwtUtils.generateForgotPasswordJwt(req.getEmail());
        var recoverUrl = baseRecoverPasswordUrl + jwtToken;
        var email = new Email();
        email.setBody(recoverUrl);
        email.setTo(req.getEmail());
        email.setSubject(CREATE_USER_SUBJECT);
        emailSender.send(email);
        return new ForgotPasswordSuccessResp(recoverUrl);
    }

    public SuccessResponse recoverPassword(RecoverPasswordReq req, String token) {
        var jws = jwtUtils.validateTokenAndGetJws(token);

        if (jws.isEmpty() || !jws.get().getHeader().get("target").equals(JwtTarget.FORGOT_PASSWORD.name())) {
            throw new ErrorCodeException(AuthErrorCodes.TOKEN_INVALID);
        }
        var email = jws.get().getBody().getSubject();
        var user = userUseCase.findByEmail(email);

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        var userId = userUseCase.save(user).getId();
        return new SuccessResponse(userId, messageSource.getMessage("mb.users.recover_password.success"));

    }

    private void validateExistedEmail(String email) {
        if (userUseCase.existByEmail(email)) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_EMAIL);
        }
    }

    private void validateExistedUsername(String username) {
        if (userUseCase.existByUsername(username)) {
            throw new ErrorCodeException(ErrorCodes.EXISTED_USERNAME);
        }
    }
}

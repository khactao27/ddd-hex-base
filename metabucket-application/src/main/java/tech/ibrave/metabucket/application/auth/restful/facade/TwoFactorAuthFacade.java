package tech.ibrave.metabucket.application.auth.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.auth.base.AuthErrorCodes;
import tech.ibrave.metabucket.application.auth.base.SecurityContext;
import tech.ibrave.metabucket.application.auth.restful.request.TurnOff2FAReq;
import tech.ibrave.metabucket.application.auth.restful.request.VerifyEnable2FAReq;
import tech.ibrave.metabucket.application.auth.restful.response.QRCodeResp;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;
import tech.ibrave.metabucket.shared.totp.TOTP;
import tech.ibrave.metabucket.shared.utils.QRCodeUtils;

/**
 * @author an.cantuong
 * created 6/21/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TwoFactorAuthFacade {

    private final UserUseCase userUseCase;
    private final MessageSource messageSource;
    private final SecurityContext securityContext;
    private final AuthenticationManager authenticationManager;

    public QRCodeResp getGoogleAuthenticatorQrCode() {
        try {
            var email = securityContext.getEmail();
            var issuer = "metabucket";
            var secretKey = TOTP.generateSecretKey();
            var qrCode = QRCodeUtils.createGoogleQRCodeAsBase64(issuer, email, secretKey);
            return new QRCodeResp(secretKey, qrCode);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorCodeException(ErrorCodes.INTERNAL_ERROR);
        }
    }

    public SuccessResponse verifyEnable2FA(VerifyEnable2FAReq req) {
        if (securityContext.getAuthentication().getUser().isEnable2FA()) {
            throw new ErrorCodeException(AuthErrorCodes.ALREADY_ENABLE_2FA);
        }

        if (TOTP.validate(req.getSecretKey(), req.getOtp())) {
            var userId = securityContext.getUserId();
            userUseCase.update(userId, user -> {
                user.setSecretKey(req.getSecretKey());
                user.setEnable2FA(true);
            });

            return new SuccessResponse(userId, messageSource.getMessage("mb.users.2fa.enable_success"));
        }
        throw new ErrorCodeException(AuthErrorCodes.INVALID_OTP);
    }

    public SuccessResponse turnOff2FA(TurnOff2FAReq req) {
        try {
            var basicAuth = new UsernamePasswordAuthenticationToken(securityContext.getLoginUsername(), req.getPassword());
            authenticationManager.authenticate(basicAuth);

            userUseCase.update(securityContext.getUserId(), user -> {
                user.setEnable2FA(false);
                user.setSecretKey(null);
            });

            return new SuccessResponse(securityContext.getUserId(), messageSource.getMessage("mb.users.2fa.turnoff_success"));
        } catch (Exception e) {
            throw new ErrorCodeException(AuthErrorCodes.INVALID_PASSWORD);
        }
    }

}

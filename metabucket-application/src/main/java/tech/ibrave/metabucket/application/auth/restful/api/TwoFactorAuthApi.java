package tech.ibrave.metabucket.application.auth.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.auth.restful.facade.TwoFactorAuthFacade;
import tech.ibrave.metabucket.application.auth.restful.request.VerifyEnable2FAReq;
import tech.ibrave.metabucket.application.auth.restful.response.QRCodeResp;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * @author an.cantuong
 * created 6/21/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth-2fa")
public class TwoFactorAuthApi {

    private final TwoFactorAuthFacade facade;

    @GetMapping("/google-authenticator-qrcode")
    public QRCodeResp forgotPassword() {
        return facade.getGoogleAuthenticatorQrCode();
    }

    @PutMapping("/enable-2fa")
    public SuccessResponse enable2FA(@Valid @RequestBody VerifyEnable2FAReq req) {
        return facade.verifyEnable2FA(req);
    }
}

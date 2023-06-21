package tech.ibrave.metabucket.application.auth.restful.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author an.cantuong
 * created 6/21/2023
 */
@Getter
@Setter
public class VerifyEnable2FAReq {

    @NotBlank
    private String otp;

    @NotBlank
    private String secretKey;
}

package tech.ibrave.metabucket.application.auth.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author an.cantuong
 * created 6/21/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeResp {

    private String secretKey;

    private String qrCode;
}

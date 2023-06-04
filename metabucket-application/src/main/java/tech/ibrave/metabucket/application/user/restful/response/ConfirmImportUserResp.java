package tech.ibrave.metabucket.application.user.restful.response;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 02/06/2023
 */
@Getter
@Setter
public class ConfirmImportUserResp extends SuccessResponse {
    private int numberOfSuccess;
    private int numberOfFail;

}

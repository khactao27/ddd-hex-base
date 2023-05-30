package tech.ibrave.metabucket.application.auth.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: hungnm
 * Date: 31/05/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSuccessResp extends SuccessResponse {
    private String registerUr;
}

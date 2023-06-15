package tech.ibrave.metabucket.application.auth.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.dto.UserAuditingObject;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessResp extends SuccessResponse {

    private String jwtToken;
    private UserAuditingObject user;
}

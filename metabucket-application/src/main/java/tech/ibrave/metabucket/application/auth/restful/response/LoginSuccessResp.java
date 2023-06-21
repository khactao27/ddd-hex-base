package tech.ibrave.metabucket.application.auth.restful.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.shared.message.Message;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginSuccessResp extends SuccessResponse {

    private String jwtToken;
    private UserDto user;
    private Boolean enable2FA;

    public static LoginSuccessResp to2FAResponse(String jwtToken) {
        var resp = new LoginSuccessResp();
        resp.setEnable2FA(true);
        resp.setJwtToken(jwtToken);
        return resp;
    }

    public LoginSuccessResp(Object id,
                            Message message,
                            String jwtToken,
                            UserDto user) {
        super(id, message);
        this.jwtToken = jwtToken;
        this.user = user;
    }
}

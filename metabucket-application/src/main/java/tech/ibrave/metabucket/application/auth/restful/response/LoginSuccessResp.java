package tech.ibrave.metabucket.application.auth.restful.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.dto.UserDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessResp {

    private String jwtToken;
    private UserDto user;
}

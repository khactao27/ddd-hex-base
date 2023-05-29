package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.UserSource;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReq {

    @NotBlank
    @Size(max = 32)
    private String username;

    @NotBlank
    @Size(max = 32)
    private String password;

    private UserSource source = UserSource.SELF_REGISTER;
}
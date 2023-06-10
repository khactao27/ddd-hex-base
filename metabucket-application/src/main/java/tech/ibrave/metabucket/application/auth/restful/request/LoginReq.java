package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.shared.validation.FirstOrder;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReq {

    @NotBlank(message = "{mb.users.login.required_email_or_username}")
    @Size(min = 4, max = 80,
            message = "{mb.users.login.invalid_email}",
            groups = FirstOrder.class)
    private String username;

    @NotBlank(message = "{mb.users.login.required_password}")
    @Size(min = 8, max = 32,
            message = "{mb.users.login.invalid_password}",
            groups = FirstOrder.class)
    private String password;

    private UserSource source = UserSource.SELF_REGISTER;
}
package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Author: hungnm
 * Date: 29/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmRegisterReq {
    @NotBlank(message = "{mb.users.verify.required_firstname}")
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{0,32}", message = "{mb.users.verify.invalid_firstname}")
    private String firstName;

    @NotBlank(message = "{mb.users.verify.required_lastname}")
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{0,32}", message = "{mb.users.verify.invalid_lastname}")
    private String lastName;

    @NotBlank(message = "{mb.users.verify.required_password}")
    @Length(min = 8, max = 32, message = "{mb.users.verify.invalid_password}")
    private String password;

    @NotBlank(message = "{mb.users.verify.required_password}")
    @Length(min = 8, max = 32, message = "{mb.users.verify.invalid_password}")
    private String confirmPassword;
    public String getFullName() {
        return firstName + " " + lastName;
    }
}

package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @NotEmpty(message = "{mb.users.create.required_firstName}")
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{0,32}", message = "{mb.users.create.invalid_firstName}")
    private String firstName;
    @NotEmpty(message = "{mb.users.create.required_lastname}")
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{0,32}", message = "{mb.users.create.invalid_lastName}")
    private String lastName;
    @Length(min = 8, max = 32, message = "{mb.auth.invalid_password}")
    private String password;
    public String getFullName() {
        return firstName + " " + lastName;
    }
}

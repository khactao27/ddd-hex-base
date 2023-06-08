package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import tech.ibrave.metabucket.shared.validation.FirstOrder;

/**
 * Author: hungnm
 * Date: 29/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmRegisterReq {
    @NotBlank(message = "{mb.users.verify.required_firstname}")
    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.verify.invalid_firstname}",
            groups = FirstOrder.class)
    private String firstName;

    @NotBlank(message = "{mb.users.verify.required_lastname}")
    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.verify.invalid_lastname}",
            groups = FirstOrder.class)
    private String lastName;

    @NotBlank(message = "{mb.users.verify.required_password}")
    @Length(min = 8, max = 32, message = "{mb.users.verify.invalid_password}", groups = FirstOrder.class)
    private String password;

    @NotBlank(message = "{mb.users.verify.required_password}")
    @Length(min = 8, max = 32, message = "{mb.users.verify.invalid_password}", groups = FirstOrder.class)
    private String confirmPassword;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Author: hungnm
 * Date: 31/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecoverPasswordReq {
    @NotBlank(message = "{mb.users.recoverpassword.required_password}")
    @Length(min = 8, max = 32, message = "{mb.users.recoverpassword.invalid_password}")
    private String newPassword;
}

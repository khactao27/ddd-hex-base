package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 31/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForgotPasswordReq {
    @Email(message = "mb.user.invalid_email")
    private String email;
}

package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.validation.FirstOrder;

/**
 * Author: hungnm
 * Date: 29/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterReq {
    @NotBlank(message = "{mb.users.create.required_email}")
    @Email(message = "{mb.users.create.invalid_email}", groups = FirstOrder.class)
    private String email;

}

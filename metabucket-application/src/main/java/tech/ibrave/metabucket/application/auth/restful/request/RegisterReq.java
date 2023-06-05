package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.UserGroup;

import java.util.List;

/**
 * Author: hungnm
 * Date: 29/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterReq {
    @NotEmpty(message = "{mb.users.create.required_email}")
    @Email(message = "{mb.users.create.invalid_email}")
    private String email;

}

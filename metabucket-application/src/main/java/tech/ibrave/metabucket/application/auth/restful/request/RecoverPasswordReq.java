package tech.ibrave.metabucket.application.auth.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Length(min = 8, max = 32, message = "{mb.auth.invalid_password}")
    private String newPassword;
}

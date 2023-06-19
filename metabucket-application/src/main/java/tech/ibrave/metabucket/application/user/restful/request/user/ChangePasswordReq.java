package tech.ibrave.metabucket.application.user.restful.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 13/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordReq {
    @NotBlank(message = "{mb.users.update.require_password}")
    @Size(min = 8, max = 32, message = "{mb.users.update.invalid_password}")
    private String currentPassword;

    @NotBlank(message = "{mb.users.update.require_password}")
    @Size(min = 8, max = 32, message = "{mb.users.update.invalid_password}")
    private String newPassword;
}

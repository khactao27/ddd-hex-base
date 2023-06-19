package tech.ibrave.metabucket.application.user.restful.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.application.user.validator.Phone;

/**
 * Author: hungnm
 * Date: 19/06/2023
 */
@Getter
@Setter
public class UpdateProfileReq {
    @NotBlank(message = "{mb.users.update.required_firstname}")
    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.update.invalid_firstname}")
    private String firstName;

    @NotBlank(message = "{mb.users.update.required_lastname}")
    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.update.invalid_lastName}")
    private String lastName;

    @Size(max = 200, message = "{mb.users.update.invalid_tittle}")
    private String title;

    @Phone(ignoreIfEmpty = true)
    private String phone;

    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{0,32}$",
            message = "{mb.users.update.invalid_location}")
    private String location;

    @NotNull
    private boolean enable2FA;
}

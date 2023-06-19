package tech.ibrave.metabucket.application.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: hungnm
 * Date: 02/06/2023
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportedUser {
    @NotBlank(message = "{mb.users.create.required_username}", groups = Default.class)
    @Pattern(regexp = "[a-zA-Z0-9\\\\]{6,32}",
            message = "{mb.users.create.invalid_username}", groups = Default.class)
    private String username;
    @NotBlank(message = "{mb.users.create.required_firstName}", groups = Default.class)
    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.create.invalid_firstName}",
            groups = Default.class)
    private String firstName;
    @NotBlank(message = "{mb.users.create.required_lastname}", groups = Default.class)
    @Pattern(regexp = "[^`0-9˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.create.invalid_lastName}",
            groups = Default.class)
    private String lastName;
    @NotBlank(message = "{mb.users.create.required_email}", groups = Default.class)
    @Email(message = "{mb.users.create.invalid_email}", groups = Default.class)
    private String email;
    private String title;
    private String location;
    private String phone;
    private String status;
}

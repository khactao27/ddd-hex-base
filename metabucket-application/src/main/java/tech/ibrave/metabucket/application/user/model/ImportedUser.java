package tech.ibrave.metabucket.application.user.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
    @NotEmpty(message = "{mb.users.create.required_username}", groups = Default.class)
    @Length(min = 6, max = 32, message = "{mb.users.create.invalid_username}", groups = Default.class)
    private String username;
    @NotEmpty(message = "{mb.users.create.required_firstname}", groups = Default.class)
    @Length(max = 32, message = "{mb.users.create.invalid_firstname}", groups = Default.class)
    private String firstName;
    @NotEmpty(message = "{mb.users.create.required_lastname}", groups = Default.class)
    @Length(max = 32, message = "{mb.users.create.invalid_lastname}", groups = Default.class)
    private String lastName;
    @NotEmpty(message = "{mb.users.create.required_email}", groups = Default.class)
    private String email;
    private String title;
    private String location;
    private String phone;
    private String status;
}

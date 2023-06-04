package tech.ibrave.metabucket.application.user.model;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "mb.users.create.required_username")
    @Length(min = 6, max = 32, message = "mb.users.create.invalid_username")
    private String username;
    @NotEmpty(message = "mb.users.create.required_firstname")
    @Length(max = 32, message = "mb.users.create.invalid_firstname")
    private String firstName;
    @NotEmpty(message = "mb.users.create.required_lastname")
    @Length( max = 32, message = "mb.users.create.invalid_lastname")
    private String lastName;
    @NotEmpty(message = "mb.users.create.required_email")
    private String email;
    private String title;
    private String location;
    private String phone;
    private boolean enable;
}

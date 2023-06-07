package tech.ibrave.metabucket.application.user.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.UserGroup;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistUserReq {
    @NotBlank(message = "{mb.users.create.required_username}")
    @Pattern(regexp = "[a-zA-Z0-9@\\\\]{6,32}", message = "{mb.users.create.invalid_username}")
    private String username;
    @NotBlank(message = "{mb.users.create.required_firstName}")
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{0,32}", message = "{mb.users.create.invalid_firstName}")
    private String firstName;
    @NotBlank(message = "{mb.users.create.required_lastname}")
    @Pattern(regexp = "[a-zA-Z0-9 \\\\]{0,32}", message = "{mb.users.create.invalid_lastName}")
    private String lastName;
    private String fullName;
    private String title;
    private String phone;

    @Size(max = 32, message = "{mb.users.update.invalid_location}")
    private String location;
    @NotBlank(message = "{mb.users.create.required_email}")
    @Email(message = "{mb.users.create.invalid_email}")
    private String email;
    @JsonIgnore
    private UserSource source = UserSource.SELF_REGISTER;
    private List<Role> roles;
    private List<UserGroup> groups;
    private boolean enable;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
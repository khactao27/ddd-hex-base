package tech.ibrave.metabucket.application.user.restful.request.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.application.user.validator.Phone;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.shared.validation.FirstOrder;

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
    @Pattern(regexp = "[a-zA-Z0-9\\\\]{6,32}",
            message = "{mb.users.create.invalid_username}",
            groups = FirstOrder.class)
    private String username;
    @NotBlank(message = "{mb.users.create.required_firstName}")
    @Pattern(regexp = "[^`0-9˜!`#$@%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.create.invalid_firstName}",
            groups = FirstOrder.class)
    private String firstName;
    @NotBlank(message = "{mb.users.create.required_lastname}")
    @Pattern(regexp = "[^`0-9˜!`#$@%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{1,32}$",
            message = "{mb.users.create.invalid_lastName}",
            groups = FirstOrder.class)
    private String lastName;
    private String fullName;
    private String title;
    @Phone(ignoreIfEmpty = true)
    private String phone;

    @Pattern(regexp = "[^`0-9˜!`#$@%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{0,32}$",
            message = "{mb.users.update.invalid_location}",
            groups = FirstOrder.class)
    private String location;
    @NotBlank(message = "{mb.users.create.required_email}")
    @Email(message = "{mb.users.create.invalid_email}",
            groups = FirstOrder.class)
    private String email;
    @JsonIgnore
    private UserSource source = UserSource.SELF_REGISTER;
    private List<Role> roles;
    private List<UserGroup> groups;

    @NotNull(message = "{mb.users.create.required_enable}",
            groups = FirstOrder.class)
    private Boolean enable;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
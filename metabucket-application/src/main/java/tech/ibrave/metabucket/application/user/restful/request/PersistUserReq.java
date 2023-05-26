package tech.ibrave.metabucket.application.user.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * Date: 25/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistUserReq {
    @NotEmpty(message = "mb.users.create.required_username")
    @Length(min = 6, max = 32, message = "mb.users.create.invalid_username")
    private String username;
    @NotEmpty(message = "mb.users.create.required_firstname")
    @Length(max = 32, message = "mb.users.create.invalid_firstname")
    private String firstName;
    @NotEmpty(message = "mb.users.create.required_lastname")
    @Length( max = 32, message = "mb.users.create.invalid_lastname")
    private String lastName;
    private String title;
    private String phone;
    private String location;
    @NotEmpty(message = "mb.users.create.required_email")
    private String email;
    @JsonIgnore
    private UserSource source = UserSource.SELF_REGISTER;
    private List<Role> roles;
    private List<UserGroup> groups;
    private boolean enable;
}
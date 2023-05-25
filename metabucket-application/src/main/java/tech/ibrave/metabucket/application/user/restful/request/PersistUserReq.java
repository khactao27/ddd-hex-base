package tech.ibrave.metabucket.application.user.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
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
    private String username;
    @NotEmpty(message = "mb.users.create.required_firstname")
    private String firstName;
    @NotEmpty(message = "mb.users.create.required_lastname")
    private String lastName;
    private String title;
    private String phone;
    private String location;
    @NotEmpty(message = "mb.users.create.required_email")
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    private List<Role> roles;
    private List<UserGroup> groupIds;
    private boolean enable;
}
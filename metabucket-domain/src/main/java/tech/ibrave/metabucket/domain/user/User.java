package tech.ibrave.metabucket.domain.user;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.UserSource;

import java.util.List;
import java.util.Set;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
public class User {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String location;
    private String phone;
    private String email;
    private UserSource source = UserSource.SELF_REGISTER;
    private Set<Role> roles;
    private Set<UserGroup> groups;
    private boolean enable;
}

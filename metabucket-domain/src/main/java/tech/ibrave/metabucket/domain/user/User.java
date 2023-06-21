package tech.ibrave.metabucket.domain.user;

import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.UserSource;
import tech.ibrave.metabucket.shared.architecture.annotation.SortableField;
import tech.ibrave.metabucket.shared.model.BaseAuditingObject;

import java.util.Objects;
import java.util.Set;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
public class User extends BaseAuditingObject {
    private String id;
    @SortableField
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
    private boolean enable2FA;
    private String secretKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

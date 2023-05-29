package tech.ibrave.metabucket.application.auth.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode
public class UserGrantedAuthority implements GrantedAuthority {

    private String permission;

    public UserGrantedAuthority( String permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}

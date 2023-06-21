package tech.ibrave.metabucket.application.auth.base;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import tech.ibrave.metabucket.domain.shared.Permission;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class UserRepoDetails implements UserDetails {

    @Getter
    private final transient User user;
    private final Set<UserGrantedAuthority> authorities;

    public UserRepoDetails(User user) {
        this.user = user;
        this.authorities = new HashSet<>();
        initAuthority();
    }

    private void initAuthority() {
        if (CollectionUtils.isEmpty(user.getRoles())) {
            this.authorities.add(new UserGrantedAuthority(Permission.NONE.getValue()));
        } else {
            for (var role : user.getRoles()) {
                for (var per : role.getPermissions()) {
                    this.authorities.add(new UserGrantedAuthority(per.getValue()));
                }
            }
        }
    }

    public boolean isEnable2FA() {
        return user.isEnable2FA();
    }

    @Override
    public Set<UserGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnable();
    }
}

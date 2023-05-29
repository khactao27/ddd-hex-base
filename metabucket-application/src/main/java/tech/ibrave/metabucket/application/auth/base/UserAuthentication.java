package tech.ibrave.metabucket.application.auth.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import tech.ibrave.metabucket.domain.user.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserAuthentication implements Authentication {

    private String ip;
    private String userAgent;
    private Set<UserGrantedAuthority> authorities;
    private boolean authenticated;
    private transient User user;

    public UserAuthentication(User user, Set<UserGrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public UserAuthentication(User user) {
        this.user = user;
        this.authorities = new HashSet<>();
    }

    @Override
    public String getCredentials() {
        return user.getPassword();
    }

    @Override
    public String getDetails() {
        return null;
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

}
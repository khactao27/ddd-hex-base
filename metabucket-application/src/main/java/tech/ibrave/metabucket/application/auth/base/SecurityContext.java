package tech.ibrave.metabucket.application.auth.base;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * Author: anct
 * Date: 04/06/2023
 * #YWNA
 */
@Component
public class SecurityContext {

    /**
     * Get user authentication.
     */
    public UserAuthentication getAuthentication() {
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated();
    }

    /**
     * Get current login user.
     *
     * @return simple user.
     */
    public User getUser() {
        return getAuthentication().getUser();
    }

    /**
     * Get current login user name.
     */
    public String getLoginUsername() {
        return getAuthentication().getUser().getUsername();
    }

    /**
     * Get current login user id.
     */
    public String getUserId() {
        return getAuthentication().getUser().getId();
    }

    /**
     * Get permission of current login user.
     */
    public Set<UserGrantedAuthority> getAuthorities() {
        return getAuthentication().getAuthorities();
    }

    /**
     * Get permission of current login user.
     */
    public List<String> getPermissions() {
        return CollectionUtils.toList(getAuthorities(), UserGrantedAuthority::getAuthority);
    }

    /**
     * Get active group of current login user.
     */
    public List<UserGroup> getGroups() {
        return getUser().getGroups() == null
                ? Collections.emptyList()
                : getUser().getGroups();
    }

    /**
     * Get active group of current login user.
     */
    public List<String> getGroupIds() {
        return getGroups().stream().map(UserGroup::getId).toList();
    }
}
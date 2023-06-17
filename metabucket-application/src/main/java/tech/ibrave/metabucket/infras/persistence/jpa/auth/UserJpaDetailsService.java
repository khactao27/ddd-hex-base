package tech.ibrave.metabucket.infras.persistence.jpa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.ibrave.metabucket.application.auth.base.AuthErrorCodes;
import tech.ibrave.metabucket.application.auth.base.UserRepoDetails;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.UserEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.UserJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.UserEntityMapper;

import java.util.regex.Pattern;

/**
 * Author: anct
 * Date: 29/05/2023
 * #YWNA
 */

@SuppressWarnings("all")
public class UserJpaDetailsService implements UserDetailsService {

    private UserJpaRepository repository;
    private UserEntityMapper userEntityMapper;

    @Override
    public UserRepoDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user;
        if (isEmail(username)) {
            user = repository.findByEmailIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException(AuthErrorCodes.EMAIL_NOT_FOUND.messageCode()));
        } else {
            user = repository.findByUsernameIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException(AuthErrorCodes.USERNAME_NOT_FOUND.messageCode()));
        }
        return new UserRepoDetails(userEntityMapper.toDomainModel(user));
    }

    private boolean isEmail(String username) {
        var emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        var matcher = emailPattern.matcher(username);
        return matcher.find();
    }

    @Autowired
    public void setRepository(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setUserEntityMapper(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }
}

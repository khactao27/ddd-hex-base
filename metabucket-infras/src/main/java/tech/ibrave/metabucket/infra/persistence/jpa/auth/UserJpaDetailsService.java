package tech.ibrave.metabucket.infra.persistence.jpa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.ibrave.metabucket.infra.persistence.jpa.repository.UserJpaRepository;
import tech.ibrave.metabucket.infra.persistence.mapper.UserEntityMapper;
import tech.ibrave.metabucket.application.auth.base.UserRepoDetails;

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
        var user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new UserRepoDetails(userEntityMapper.toDomainModel(user));
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

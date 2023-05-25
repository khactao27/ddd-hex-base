package tech.ibrave.metabucket.infra.peristence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.persistence.UserPersistence;
import tech.ibrave.metabucket.infra.peristence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.UserEntity;
import tech.ibrave.metabucket.infra.peristence.jpa.repository.UserJpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.UserEntityMapper;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Component
public class UserPersistenceAdapter extends BaseJpaRepository<UserEntity, User, String> implements UserPersistence {

    protected UserPersistenceAdapter(UserJpaRepository repo, UserEntityMapper mapper) {
        super(repo, mapper);
    }
}
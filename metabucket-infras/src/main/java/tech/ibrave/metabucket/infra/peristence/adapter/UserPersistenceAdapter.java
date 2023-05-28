package tech.ibrave.metabucket.infra.peristence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.persistence.UserPersistence;
import tech.ibrave.metabucket.infra.peristence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.UserEntity;
import tech.ibrave.metabucket.infra.peristence.jpa.repository.UserJpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.UserEntityMapper;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Component
@SuppressWarnings("all")
public class UserPersistenceAdapter extends BaseJpaRepository<UserEntity, User, String> implements UserPersistence {

    protected UserPersistenceAdapter(UserJpaRepository repo, UserEntityMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public boolean existByUsername(String username) {
        return repo().existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return repo().existsByEmail(email);
    }

    @Override
    public List<User> findByIdsOrElseThrow(List<String> ids) {
        if (!repo().existsAllByIdIn(ids)) {
            throw new ErrorCodeException(ErrorCodes.NOT_FOUND);
        }

        var userEntities = repo().findByIdIn(ids);
        return CollectionUtils.toList(userEntities, mapper::toDomainModel);
    }

    @Override
    public UserJpaRepository repo() {
        return super.repo();
    }

    @Override
    public UserEntityMapper mapper() {
        return super.mapper();
    }
}
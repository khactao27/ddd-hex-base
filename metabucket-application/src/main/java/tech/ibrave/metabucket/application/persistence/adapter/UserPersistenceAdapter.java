package tech.ibrave.metabucket.application.persistence.adapter;

import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.persistence.jpa.BaseDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.QUserEntity;
import tech.ibrave.metabucket.application.persistence.jpa.entity.UserEntity;
import tech.ibrave.metabucket.application.persistence.jpa.repository.UserJpaRepository;
import tech.ibrave.metabucket.application.persistence.mapper.UserEntityMapper;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.persistence.UserPersistence;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Component
@SuppressWarnings("all")
public class UserPersistenceAdapter extends BaseDslRepository<UserEntity, User, String> implements UserPersistence {

    protected UserPersistenceAdapter(UserJpaRepository repo,
                                     UserEntityMapper mapper,
                                     EntityManager em) {
        super(repo, mapper, em);
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
    public List<UserDto> findByIdsOrElseThrow(List<String> ids) {
        if (!repo().existsAllByIdIn(ids)) {
            throw new ErrorCodeException(ErrorCodes.NOT_FOUND);
        }

        var userEntities = repo().findByIdIn(ids);
        return CollectionUtils.toList(userEntities, mapper()::toDto);
    }

    @Override
    public Optional<UserDto> findByIdUseDto(String id) {
        return Optional.ofNullable(mapper().toDto(repo().findById(id).orElse(null)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(mapper.toDomainModel(repo().findByEmail(email).orElse(null)));
    }

    @Override
    public void updateStatusBulkUser(List<String> userIds, boolean enable) {
        repo().updateStatusBulkUser(userIds, enable);
    }

    public Page<UserDto> searchUser(SearchUserReq req) {
        var query = queryFactory
                .select(QUserEntity.userEntity)
                .from(QUserEntity.userEntity);
        var whereBuilder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(req.getQuery())) {
            whereBuilder.and(QUserEntity.userEntity.fullName.likeIgnoreCase("%" + req.getQuery() + "%"))
                    .or(QUserEntity.userEntity.username.likeIgnoreCase("%" + req.getQuery() + "%"));
        }
        if (req.getEnable() != null) {
            whereBuilder.and(QUserEntity.userEntity.enable.eq(req.getEnable()));
        }
        query.where(whereBuilder);
        if (StringUtils.isNotEmpty(req.getSort())) {
            query.orderBy(getSortSpecifiers(req));
        }
        return new Page(getDomainResultAsPage(query, mapper()::toDto, req));
    }


    @Override
    @SuppressWarnings("all")
    public UserJpaRepository repo() {
        return super.repo();
    }

    @Override
    @SuppressWarnings("all")
    public UserEntityMapper mapper() {
        return super.mapper();
    }

    @Override
    public QUserEntity entityPath() {
        return QUserEntity.userEntity;
    }
}
package tech.ibrave.metabucket.infras.persistence.adapter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.persistence.UserPersistence;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseDslRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.QRoleEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.QUserEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.QUserGroupEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.UserEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.UserJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.UserEntityMapper;
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
        return Optional.ofNullable(mapper.toDomainModel(repo().findByEmailIgnoreCase(email).orElse(null)));
    }

    @Override
    public void updateStatusBulkUser(List<String> userIds, boolean enable) {
        repo().updateStatusBulkUser(userIds, enable);
    }

    public Page<UserDto> searchUser(SearchUserReq req) {
        var query = buildBasicQuery();
        if (StringUtils.isNotEmpty(req.getUserGroupId())) {
            buildQueryWithJoinGroup(query, req.getUserGroupId());
        }
        if (req.getRoleId() != null) {
            buildQueryWithJoinRole(query, req.getRoleId());
        }
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
        var defaultSort = new OrderSpecifier<>(Order.DESC, QUserEntity.userEntity.createdTime);
        query.orderBy(defaultSort);
        return new Page(getDomainResultAsPage(query, mapper()::toDto, req));
    }

    @Override
    public List<UserDto> findAllByIdIn(List<String> ids) {
        var userEntities = repo().findByIdIn(ids);
        return CollectionUtils.toList(userEntities, mapper()::toDto);

    }

    public JPAQuery<UserEntity> buildBasicQuery() {
        return queryFactory
                .select(QUserEntity.userEntity)
                .from(QUserEntity.userEntity);
    }

    public void buildQueryWithJoinGroup(JPAQuery<UserEntity> query, String userGroupId) {
        query.innerJoin(QUserEntity.userEntity.groups, QUserGroupEntity.userGroupEntity)
                .where(QUserGroupEntity.userGroupEntity.id.eq(userGroupId));
    }

    public void buildQueryWithJoinRole(JPAQuery<UserEntity> query, Long roleId) {
        query.innerJoin(QUserEntity.userEntity.roles, QRoleEntity.roleEntity)
                .where(QRoleEntity.roleEntity.id.eq(roleId));
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
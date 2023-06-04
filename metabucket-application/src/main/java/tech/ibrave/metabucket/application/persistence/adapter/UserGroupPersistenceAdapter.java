package tech.ibrave.metabucket.application.persistence.adapter;

import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.persistence.jpa.BaseDslRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.QUserGroupEntity;
import tech.ibrave.metabucket.application.persistence.jpa.entity.UserGroupEntity;
import tech.ibrave.metabucket.application.persistence.jpa.repository.UserGroupJpaRepository;
import tech.ibrave.metabucket.application.persistence.mapper.UserGroupEntityMapper;
import tech.ibrave.metabucket.domain.shared.request.UserGroupSearchReq;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.domain.user.persistence.UserGroupPersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
import java.util.Optional;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Component
@SuppressWarnings("all")
public class UserGroupPersistenceAdapter extends BaseDslRepository<UserGroupEntity, UserGroup, String> implements UserGroupPersistence {

    protected UserGroupPersistenceAdapter(UserGroupJpaRepository repo,
                                          UserGroupEntityMapper mapper,
                                          EntityManager em) {
        super(repo, mapper, em);
    }

    @Override
    public Page<UserGroupDto> search(UserGroupSearchReq req) {
        var query = queryFactory
                .select(QUserGroupEntity.userGroupEntity)
                .from(QUserGroupEntity.userGroupEntity);
        var whereBuilder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(req.getQuery())) {
            whereBuilder.and(QUserGroupEntity.userGroupEntity.name.likeIgnoreCase("%" + req.getQuery() + "%"));
        }
        if (req.getEnable() != null) {
            whereBuilder.and(QUserGroupEntity.userGroupEntity.enable.eq(req.getEnable()));
        }
        query.where(whereBuilder);
        if (StringUtils.isNotEmpty(req.getSort())) {
            query.orderBy(getSortSpecifiers(req));
        }
        return new Page(getDomainResultAsPage(query, mapper()::toDto, req));
    }

    @Override
    public Optional<UserGroupDto> findUserGroupDtoById(String id) {
        return Optional.ofNullable(mapper().toDto(repo().findById(id).orElse(null)));
    }

    @Override
    public void deleteByIds(List<String> ids) {
        repo().deleteAllByIdIn(ids);
    }

    @Override
    public void updateStatus(List<String> ids, boolean enable) {
        repo().updateStatus(ids, enable);
    }

    @Override
    public boolean existsByName(String name) {
        return repo().existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, String id) {
        return repo().existsByNameAndIdNot(name, id);
    }

    @Override
    public UserGroupJpaRepository repo() {
        return super.repo();
    }

    @Override
    public UserGroupEntityMapper mapper() {
        return super.mapper();
    }

    @Override
    public QUserGroupEntity entityPath() {
        return QUserGroupEntity.userGroupEntity;
    }
}
package tech.ibrave.metabucket.infra.peristence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.infra.peristence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.infra.peristence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.RoleEntityMapper;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Component
public class RolePersistenceAdapter extends BaseJpaRepository<RoleEntity, Role, Long> implements RolePersistence {

    protected RolePersistenceAdapter(RoleJpaRepository repo, RoleEntityMapper mapper) {
        super(repo, mapper);
    }

    public List<Role> findAllByIds(List<Long> ids) {
        var entities = repo.findAllById(ids);
        return CollectionUtils.toList(entities, mapper::toDomainModel);
    }
}

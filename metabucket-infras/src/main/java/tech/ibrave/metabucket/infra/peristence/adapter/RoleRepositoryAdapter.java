package tech.ibrave.metabucket.infra.peristence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.role.Role;
import tech.ibrave.metabucket.domain.role.port.RolePersistence;
import tech.ibrave.metabucket.infra.peristence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.infra.peristence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.RoleMapper;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Component
public class RoleRepositoryAdapter extends BaseJpaRepository<RoleEntity, Role, Long> implements RolePersistence {

    protected RoleRepositoryAdapter(RoleJpaRepository repo, RoleMapper mapper) {
        super(repo, mapper);
    }
}

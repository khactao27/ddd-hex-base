package tech.ibrave.metabucket.infra.peristence.adapter;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.infra.peristence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.infra.peristence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.RoleEntityMapper;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.Optional;

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

    @Override
    public boolean existsByName(String name) {
        return repo().existsByName(name);
    }

    @Override
    public Page<Role> search(String name, int pageIndex, int pageSize) {
        return findAllByName(name, pageIndex, pageSize); // fixme: upgrade
    }

    @Override
    public Page<Role> findAllByName(String name, int pageIndex, int pageSize) {
        if (StringUtils.isEmpty(name)) {
            return toPage(repo().findAll(), mapper::toDomainModel, pageIndex, pageSize);
        } else {
            return toPage(repo().findAllByNameContaining(name), mapper::toDomainModel, pageIndex, pageSize);
        }
    }

    @Override
    public Optional<RoleDto> findByIdUseDto(Long id) {
        return Optional.ofNullable(mapper().toDto(repo().findById(id).orElse(null)));
    }

    @Override
    @SuppressWarnings("all")
    public RoleJpaRepository repo() {
        return super.repo();
    }

    @Override
    @SuppressWarnings("all")
    public RoleEntityMapper mapper() {
        return super.mapper();
    }
}

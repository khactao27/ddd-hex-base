package tech.ibrave.metabucket.infra.persistence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.infra.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.infra.persistence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.infra.persistence.mapper.RoleEntityMapper;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
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
//        if (StringUtils.isEmpty(name)) {
//            return toPage(repo().findAll(), mapper::toDomainModel, pageIndex, pageSize);
//        } else {
//            return toPage(repo().findAllByNameContaining(name), mapper::toDomainModel, pageIndex, pageSize);
//        }
        return null; // fixme: use pageable
    }

    @Override
    public Optional<RoleDto> findByIdUseDto(Long id) {
        return Optional.ofNullable(mapper().toDto(repo().findById(id).orElse(null)));
    }
    @Override
    public void deleteByIds(List<Long> ids) {
        repo().deleteAllByIdIn(ids);
    }

    @Override
    public void updateStatus(List<Long> ids, boolean enable) {
        repo().updateStatus(ids, enable);
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

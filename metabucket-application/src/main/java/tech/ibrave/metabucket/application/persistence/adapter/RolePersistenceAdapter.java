package tech.ibrave.metabucket.application.persistence.adapter;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.application.persistence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.application.persistence.mapper.RoleEntityMapper;
import tech.ibrave.metabucket.application.persistence.mapper.UserEntityMapper;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Component
public class RolePersistenceAdapter extends BaseJpaRepository<RoleEntity, Role, Long> implements RolePersistence {

    private final UserEntityMapper userEntityMapper;
    protected RolePersistenceAdapter(RoleJpaRepository repo, RoleEntityMapper mapper, UserEntityMapper userEntityMapper) {
        super(repo, mapper);
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public boolean existsByName(String name) {
        return repo().existsByName(name);
    }

    @Override
    public Page<RoleDto> search(String name, Pageable pageable) {
        return findAllByName(name, pageable); // fixme: upgrade
    }

    @Override
    public Page<RoleDto> findAllByName(String name, Pageable pageable) {
        if (StringUtils.isEmpty(name)) {
            var roleDtos = repo.findAll().stream().map(t -> {
                var roleDto = mapper().toDto(t);
                roleDto.setUsers(userEntityMapper.toUserRoleLazy(t.getUsers()));
                return roleDto;
            }).collect(Collectors.toList());
            return toPage(roleDtos, pageable);
        } else {
            var roleDtos = repo().findAllByNameContaining(name).stream().map(t -> {
                var roleDto = mapper().toDto(t);
                roleDto.setUsers(userEntityMapper.toUserRoleLazy(t.getUsers()));
                return roleDto;
            }).collect(Collectors.toList());
            return toPage(roleDtos, pageable);
        }
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
    public boolean existsByNameAndIdNot(String name, Long id) {
        return repo().existsByNameAndIdNot(name, id);
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

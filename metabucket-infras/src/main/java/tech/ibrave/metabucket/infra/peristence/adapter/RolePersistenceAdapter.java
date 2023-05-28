package tech.ibrave.metabucket.infra.peristence.adapter;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.infra.peristence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.infra.peristence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.infra.peristence.mapper.RoleEntityMapper;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;
import java.util.function.Function;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Component
public class RolePersistenceAdapter extends BaseJpaRepository<RoleEntity, Role, Long> implements RolePersistence {

    private final RoleJpaRepository repo;
    private final RoleEntityMapper mapper;

    protected RolePersistenceAdapter(RoleJpaRepository repo, RoleEntityMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public Page<Role> search(String name, int pageIndex, int pageSize) {
        return findAllByName(name, pageIndex, pageSize); // fixme: upgrade
    }

    @Override
    public Page<Role> findAllByName(String name, int pageIndex, int pageSize) {
        if (StringUtils.isEmpty(name)) {
            return toPage(repo.findAll(), mapper::toDomainModel, pageIndex, pageSize);
        } else {
            return toPage(repo.findAllByNameContaining(name), mapper::toDomainModel, pageIndex, pageSize);
        }
    }

    private static <T, R> Page<R> toPage(List<T> list,
                                         Function<T, R> function,
                                         int pageIndex,
                                         int pageSize) {
        var result = CollectionUtils.toList(list, function);
        var page = new PageImpl<>(result);
        return new Page<>(
                pageIndex,
                pageSize,
                page.getTotalElements(),
                page.getTotalPages(),
                result
        );
    }
}

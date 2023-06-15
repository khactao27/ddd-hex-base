package tech.ibrave.metabucket.application.persistence.adapter;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.application.persistence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.application.persistence.jpa.repository.RoleJpaRepository;
import tech.ibrave.metabucket.application.persistence.mapper.RoleEntityMapper;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleAuditingObject;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.request.PageReq;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Component
public class RolePersistenceAdapter extends BaseJpaRepository<RoleEntity, Role, Long> implements RolePersistence {

    protected RolePersistenceAdapter(RoleJpaRepository repo,
                                     RoleEntityMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public boolean existsByName(String name) {
        return repo().existsByName(name);
    }

    @Override
    public Page<RoleLiteDto> search(String name, PageReq pageReq) {
        var pageable = getPageable(pageReq);

        var page = StringUtils.isEmpty(name)
                ? repo().findAll(pageable)
                : repo().findAllByNameContaining(name, pageable);

        return toPage(page.getContent(), mapper()::toLiteDto, pageable);
    }

    @Override
    public Optional<RoleAuditingObject> findByIdUseDto(Long id) {
        return Optional.ofNullable(mapper().toDto(repo().findById(id).orElse(null)));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        repo.deleteAllById(ids);
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

package tech.ibrave.metabucket.application.user.service;

import org.springframework.data.domain.Pageable;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@ApplicationService
public class RoleService extends BaseApplicationService<Role, Long, RolePersistence> implements RoleUseCase {

    protected RoleService(RolePersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }

    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public Page<Role> search(String name, Pageable pageable) {
        return repo.search(name, pageable);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        repo.deleteByIds(ids);
    }

    @Override
    public void updateStatus(List<Long> ids, boolean enable) {
        repo.updateStatus(ids, enable);
    }

    @Override
    public RoleDto findByIdUseDto(Long id) {
        return repo.findByIdUseDto(id).orElseThrow(() -> new ErrorCodeException(notFound()));
    }
}

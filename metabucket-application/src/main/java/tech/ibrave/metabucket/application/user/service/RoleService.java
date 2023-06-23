package tech.ibrave.metabucket.application.user.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.model.request.PageReq;

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
        return ErrorCodes.ROLE_NOT_FOUND;
    }

    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public Page<RoleLiteDto> search(String name, PageReq pageReq) {
        return repo.search(name, pageReq);
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

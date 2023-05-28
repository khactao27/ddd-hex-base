package tech.ibrave.metabucket.application.user.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

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
    public Page<Role> search(String name,
                             int pageIndex,
                             int pageSize) {
        return repo.search(name, pageIndex, pageSize);
    }

    @Override
    public Page<Role> findAllByName(String name, int pageIndex, int pageSize) {
        return repo.findAllByName(name, pageIndex, pageSize);
    }

    @Override
    public Role findByName(String name) {
        return repo.findByName(name);
    }
}

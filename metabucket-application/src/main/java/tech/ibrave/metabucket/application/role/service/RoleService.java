package tech.ibrave.metabucket.application.role.service;

import tech.ibrave.metabucket.domain.role.Role;
import tech.ibrave.metabucket.domain.role.persistence.RolePersistence;
import tech.ibrave.metabucket.domain.role.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.domain.BaseUseCase;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@ApplicationService
public class RoleService extends BaseUseCase<Role, Long> implements RoleUseCase {

    protected RoleService(RolePersistence repo) {
        super(repo);
    }

    @Override
    public Long createRole(Role role) {
        return repo.save(role).getId();
    }
}

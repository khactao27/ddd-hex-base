package tech.ibrave.metabucket.application.user.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.persistence.RolePersistence;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@ApplicationService
public class RoleService extends BaseApplicationService<Role, Long> implements RoleUseCase {

    protected RoleService(RolePersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }
}

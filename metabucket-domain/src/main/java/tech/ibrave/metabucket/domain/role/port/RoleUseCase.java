package tech.ibrave.metabucket.domain.role.port;

import tech.ibrave.metabucket.domain.role.Role;
import tech.ibrave.metabucket.domain.role.service.RoleService;
import tech.ibrave.metabucket.shared.annotation.UseCase;
import tech.ibrave.metabucket.shared.domain.BaseUseCase;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@UseCase
public class RoleUseCase extends BaseUseCase<Role, Long> implements RoleService {

    protected RoleUseCase(RolePersistence repo) {
        super(repo);
    }

    @Override
    public Long createRole(Role role) {
        return repo.save(role).getId();
    }
}

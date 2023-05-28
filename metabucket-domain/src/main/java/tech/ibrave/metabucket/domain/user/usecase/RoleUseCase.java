package tech.ibrave.metabucket.domain.user.usecase;

import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RoleUseCase extends BaseUseCase<Role, Long> {

    boolean existsByName(String name);

    Page<Role> search(String name, int pageIndex, int pageSize);

    Page<Role> findAllByName(String name, int pageIndex, int pageSize);

    Role findByName(String name);
}

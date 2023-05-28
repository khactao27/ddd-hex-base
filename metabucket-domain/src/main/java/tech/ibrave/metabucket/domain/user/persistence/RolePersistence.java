package tech.ibrave.metabucket.domain.user.persistence;

import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RolePersistence extends BasePersistence<Role, Long> {

    boolean existsByName(String name);

    Page<Role> search(String name,
                      int pageIndex,
                      int pageSize);

    Page<Role> findAllByName(String name, int pageIndex, int pageSize);
}

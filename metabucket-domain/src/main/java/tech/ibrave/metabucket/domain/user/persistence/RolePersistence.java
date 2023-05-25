package tech.ibrave.metabucket.domain.user.persistence;

import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface RolePersistence extends BasePersistence<Role, Long> {
    List<Role> findAllByIds(List<Long> ids);
}

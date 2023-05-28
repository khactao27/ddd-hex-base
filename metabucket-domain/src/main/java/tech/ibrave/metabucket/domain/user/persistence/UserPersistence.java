package tech.ibrave.metabucket.domain.user.persistence;

import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
public interface UserPersistence extends BasePersistence<User, String> {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    List<User> findByIdsOrElseThrow(List<String> ids);
}

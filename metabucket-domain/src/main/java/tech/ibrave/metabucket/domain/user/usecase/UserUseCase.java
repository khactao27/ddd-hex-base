package tech.ibrave.metabucket.domain.user.usecase;

import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserAuditingObject;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
public interface UserUseCase extends BaseUseCase<User, String> {
    boolean existByUsername(String username);

    boolean existByEmail(String email);

    List<UserAuditingObject> findByIdsOrElseThrow(List<String> ids);

    UserAuditingObject findByIdUseDto(String id);

    User findByEmail(String email);

    void updateStatusBulkUser(List<String> userIds, boolean enable);

    Page<UserAuditingObject> searchUser(SearchUserReq req);
}

package tech.ibrave.metabucket.domain.user.persistence;

import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.shared.architecture.BasePersistence;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
public interface UserPersistence extends BasePersistence<User, String> {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    void updateStatusBulkUser(List<String> userIds, boolean enable);
    Optional<UserDto> findByIdUseDto(String id);
    List<UserDto> findByIdsOrElseThrow(List<String> ids);
    Page<UserDto> searchUser(SearchUserReq req);
}

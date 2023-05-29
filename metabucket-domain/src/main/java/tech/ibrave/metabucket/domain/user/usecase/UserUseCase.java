package tech.ibrave.metabucket.domain.user.usecase;

import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.shared.architecture.BaseUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
public interface UserUseCase extends BaseUseCase<User, String> {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    List<User> findByIdsOrElseThrow(List<String> ids);
    UserDto findByIdUseDto(String id);
    void updateStatusBulkUser(List<String> userIds, boolean enable);
    Page<UserDto> searchUser(SearchUserReq req);
}

package tech.ibrave.metabucket.application.user.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.persistence.UserPersistence;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;

import java.util.List;
import java.util.Optional;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@ApplicationService
public class UserService extends BaseApplicationService<User, String, UserPersistence> implements UserUseCase {

    protected UserService(UserPersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.NOT_FOUND;
    }

    @Override
    public boolean existByUsername(String username) {
        return repo.existByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return repo.existByEmail(email);
    }

    @Override
    public List<UserDto> findByIdsOrElseThrow(List<String> ids) {
        return repo.findByIdsOrElseThrow(ids);
    }

    public UserDto findByIdUseDto(String id) {
        return repo.findByIdUseDto(id).orElseThrow(() -> new ErrorCodeException(notFound()));

    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ErrorCodeException(notFound()));
    }

    @Override
    public void updateStatusBulkUser(List<String> userIds, boolean enable) {
        repo.updateStatusBulkUser(userIds, enable);
    }

    @Override
    public Page<UserDto> searchUser(SearchUserReq req) {
        return repo.searchUser(req);
    }
}

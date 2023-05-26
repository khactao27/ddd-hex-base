package tech.ibrave.metabucket.application.user.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.persistence.UserPersistence;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@ApplicationService
public class UserService extends BaseApplicationService<User, String> implements UserUseCase {
    protected UserPersistence repo;

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
}

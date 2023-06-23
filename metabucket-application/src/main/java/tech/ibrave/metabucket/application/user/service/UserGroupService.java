package tech.ibrave.metabucket.application.user.service;

import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.UserGroupSearchReq;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.domain.user.persistence.UserGroupPersistence;
import tech.ibrave.metabucket.domain.user.usecase.UserGroupUseCase;
import tech.ibrave.metabucket.shared.architecture.BaseApplicationService;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.architecture.annotation.ApplicationService;
import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@ApplicationService
public class UserGroupService extends BaseApplicationService<UserGroup, String, UserGroupPersistence> implements UserGroupUseCase {

    protected UserGroupService(UserGroupPersistence repo) {
        super(repo);
    }

    @Override
    public ErrorCode notFound() {
        return ErrorCodes.USER_GROUP_NOT_FOUND;
    }

    @Override
    public Page<UserGroupDto> search(UserGroupSearchReq req) {
        return repo.search(req);
    }

    public UserGroupDto findUserGroupDtoById(String id) {
        return repo.findUserGroupDtoById(id).orElseThrow(() -> new ErrorCodeException(notFound()));
    }

    @Override
    public void deleteByIds(List<String> ids) {
        repo.deleteByIds(ids);
    }

    @Override
    public void updateStatus(List<String> ids, boolean status) {
        repo.updateStatus(ids, status);
    }

    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, String id) {
        return repo.existsByNameAndIdNot(name, id);
    }
}

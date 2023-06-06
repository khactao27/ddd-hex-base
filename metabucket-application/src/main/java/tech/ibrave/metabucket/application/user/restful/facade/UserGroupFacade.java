package tech.ibrave.metabucket.application.user.restful.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.UserGroupMapper;
import tech.ibrave.metabucket.application.user.restful.mapper.UserMapper;
import tech.ibrave.metabucket.application.user.restful.request.group.AddUserToGroupReq;
import tech.ibrave.metabucket.application.user.restful.request.group.DeleteUserGroupIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.group.PersistUserGroupReq;
import tech.ibrave.metabucket.application.user.restful.request.group.UserGroupStatusBulkReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.shared.request.UserGroupSearchReq;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.domain.user.dto.UserGroupLiteDto;
import tech.ibrave.metabucket.domain.user.usecase.UserGroupUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserGroupFacade {

    private final UserGroupMapper userGroupMapper;
    private final UserMapper userMapper;
    private final UserGroupUseCase userGroupUseCase;
    private final MessageSource messageSource;

    public SuccessResponse createUserGroup(PersistUserGroupReq req) {
        validateName(req.getName());
        var userGroup = userGroupMapper.toUserGroup(req);
        var id = userGroupUseCase.save(userGroup).getId();
        return new SuccessResponse(id, messageSource.getMessage("mb.groups.create.success"));
    }

    public SuccessResponse updateUserGroup(String id, PersistUserGroupReq req) {
        var userGroup = userGroupUseCase.getOrElseThrow(id);
        validateName(userGroup.getName(), req.getName());
        userGroupMapper.toUserGroup(userGroup, req);
        userGroupUseCase.save(userGroup);
        return new SuccessResponse(id, messageSource.getMessage("mb.groups.update.success"));
    }

    public SuccessResponse updateUserGroupStatus(UserGroupStatusBulkReq req) {
        userGroupUseCase.updateStatus(req.getIds(), req.isEnable());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.groups.update.success"));
    }

    public SuccessResponse deleteUserGroup(String id) {
        userGroupUseCase.deleteIfExist(id);
        return new SuccessResponse(id, messageSource.getMessage("mb.groups.delete.success"));
    }

    public SuccessResponse deleteUserGroups(DeleteUserGroupIdBulkReq req) {
        userGroupUseCase.deleteByIds(req.getIds());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.groups.delete.success"));
    }

    public Page<UserGroupDto> getAllUserGroup(UserGroupSearchReq req) {
        return userGroupUseCase.search(req);
    }

    public UserGroupDto getUserGroupById(String id) {
        return userGroupUseCase.findUserGroupDtoById(id);
    }

    public Page<UserGroupLiteDto> getUserGroupShortInfo(UserGroupSearchReq req) {
        var roles = userGroupUseCase.search(req);
        var result = CollectionUtils.toList(roles.getData(), userGroupMapper::toUserGroupLiteResp);
        return new Page<>(
                req.getPageIndex(),
                req.getPageSize(),
                roles.getTotalElement(),
                roles.getTotalPage(),
                result
        );
    }

    public SuccessResponse addUser(String groupId,
                                   AddUserToGroupReq req) {
        var group = userGroupUseCase.getOrElseThrow(groupId);
        var usersToAdd = CollectionUtils.toList(req.getUsers(), userMapper::toUser);
        var totalUsers = group.getUsers();
        totalUsers.addAll(usersToAdd);
        group.setUsers(totalUsers);
        userGroupUseCase.save(group);
        return new SuccessResponse(groupId, messageSource.getMessage("mb.groups.update.success"));
    }

    private void validateName(String name) {
        if (userGroupUseCase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.GROUP_NAME_EXISTED);
        }
    }

    private void validateName(String oldName, String newName) {
        if (!oldName.equals(newName) && userGroupUseCase.existsByName(newName)) {
            throw new ErrorCodeException(ErrorCodes.GROUP_NAME_EXISTED);
        }
    }
}

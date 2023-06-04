package tech.ibrave.metabucket.application.user.restful.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.UserGroupMapper;
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

    private final UserGroupMapper mapper;
    private final UserGroupUseCase useCase;
    private final MessageSource messageSource;

    public SuccessResponse createUserGroup(PersistUserGroupReq req) {
        validateName(req.getName());
        var userGroup = mapper.toUserGroup(req);
        var id = useCase.save(userGroup).getId();
        return new SuccessResponse(id, messageSource.getMessage("mb.groups.create.success"));
    }

    public SuccessResponse updateUserGroup(String id, PersistUserGroupReq req) {
        var userGroup = useCase.getOrElseThrow(id);
        validateName(userGroup.getName(), req.getName(), id);
        mapper.toUserGroup(userGroup, req);
        useCase.save(userGroup);
        return new SuccessResponse(id, messageSource.getMessage("mb.groups.update.success"));
    }

    public SuccessResponse updateUserGroupStatus(UserGroupStatusBulkReq req) {
        useCase.updateStatus(req.getIds(), req.isEnable());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.groups.update.success"));
    }

    public SuccessResponse deleteUserGroup(String id) {
        useCase.deleteIfExist(id);
        return new SuccessResponse(id, messageSource.getMessage("mb.groups.delete.success"));
    }

    public SuccessResponse deleteUserGroups(DeleteUserGroupIdBulkReq req) {
        useCase.deleteByIds(req.getIds());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.groups.delete.success"));
    }

    public Page<UserGroupDto> getAllUserGroup(UserGroupSearchReq req) {
        return useCase.search(req);
    }

    public UserGroupDto getUserGroupById(String id) {
        return useCase.findUserGroupDtoById(id);
    }

    public Page<UserGroupLiteDto> getUserGroupShortInfo(UserGroupSearchReq req) {
        var roles = useCase.search(req);
        var result = CollectionUtils.toList(roles.getData(), mapper::toUserGroupLiteResp);
        return new Page<>(
                req.getPageIndex(),
                req.getPageSize(),
                roles.getTotalElement(),
                roles.getTotalPage(),
                result
        );
    }

    private void validateName(String name) {
        if (useCase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }
    }

    private void validateName(String oldName, String newName, String id) {
        if (!oldName.equals(newName)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }

        if (useCase.existsByNameAndIdNot(newName, id)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }
    }
}

package tech.ibrave.metabucket.application.user.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.RoleMapper;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleLiteReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleSearchReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleStatusReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleMapper mapper;
    private final UserUseCase userUseCase;
    private final RoleUseCase roleUsecase;
    private final MessageSource messageSource;

    public SuccessResponse createRole(PersistRoleReq req) {
        validateName(req.getName());
        var users = userUseCase.findByIdsOrElseThrow(req.getUserIds());
        var role = mapper.toRole(req, users);
        var roleId = roleUsecase.save(role).getId();
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.create.success"));
    }

    public SuccessResponse updateRole(Long roleId, PersistRoleReq req) {
        var role = roleUsecase.getOrElseThrow(roleId);
        validateName(role.getName(), req.getName());
        role.setUsers(null);
        var users = userUseCase.findByIdsOrElseThrow(req.getUserIds());
        mapper.toRole(role, req, users);
        roleUsecase.save(role);
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.update.success"));
    }

    public SuccessResponse updateRoleStatus(RoleStatusReq req) {
        roleUsecase.updateStatus(req.getIds(), req.isEnable());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.roles.update.success"));
    }

    public SuccessResponse deleteRole(Long roleId) {
        roleUsecase.deleteIfExist(roleId);
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.delete.success"));
    }

    public SuccessResponse deleteRoles(RoleIdBulkReq req) {
        roleUsecase.deleteByIds(req.getIds());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.roles.delete.success"));
    }

    public Page<RoleDto> getAllRole(RoleSearchReq req) {
        var pageable = PageRequest.of(req.getPageIndex(), req.getPageSize());
        var roles = roleUsecase.search(req.getName(), pageable);
        var roleResponses = CollectionUtils.toList(roles.getData(), mapper::toRoleResp);
        return new Page<>(
                req.getPageIndex(),
                req.getPageSize(),
                roles.getTotalElement(),
                roles.getPageSize(),
                roleResponses);
    }

    public RoleDto getRoleById(Long roleId) {
        return roleUsecase.findByIdUseDto(roleId);
    }

    public Page<RoleLiteDto> getRoleShortInfo(RoleLiteReq req) {
        var pageable = PageRequest.of(req.getPageIndex(), req.getPageSize());
        var roles = roleUsecase.search(req.getQuery(), pageable);
        var result = CollectionUtils.toList(roles.getData(), mapper::toRoleLiteResp);
        return new Page<>(
                req.getPageIndex(),
                req.getPageSize(),
                roles.getTotalElement(),
                roles.getTotalPage(),
                result
        );
    }

    private void validateName(String name) {
        if (roleUsecase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }
    }

    private void validateName(String oldName, String newName) {
        if (!oldName.equals(newName)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }

        if (roleUsecase.existsByName(newName)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }
    }
}

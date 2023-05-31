package tech.ibrave.metabucket.application.user.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.RoleMapper;
import tech.ibrave.metabucket.application.user.restful.request.role.DeleteRoleIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleLiteReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleSearchReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleStatusBulkReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
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
    private final RoleUseCase roleUsecase;
    private final MessageSource messageSource;

    public SuccessResponse createRole(PersistRoleReq req) {
        validateName(req.getName());
        var role = mapper.toRole(req);
        var roleId = roleUsecase.save(role).getId();
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.create.success"));
    }

    public SuccessResponse updateRole(Long roleId, PersistRoleReq req) {
        var role = roleUsecase.getOrElseThrow(roleId);
        validateName(role.getName(), req.getName());
        role.setUsers(null);
        mapper.toRole(role, req);
        roleUsecase.save(role);
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.update.success"));
    }

    public SuccessResponse updateRoleStatus(RoleStatusBulkReq req) {
        roleUsecase.updateStatus(req.getIds(), req.isEnable());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.roles.update.success"));
    }

    public SuccessResponse deleteRole(Long roleId) {
        roleUsecase.deleteIfExist(roleId);
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.delete.success"));
    }

    public SuccessResponse deleteRoles(DeleteRoleIdBulkReq req) {
        roleUsecase.deleteByIds(req.getIds());
        return new SuccessResponse(
                req.getIds(),
                messageSource.getMessage("mb.roles.delete.success"));
    }

    public Page<RoleDto> getAllRole(RoleSearchReq req) {
        var pageable = PageRequest.of(req.getPageIndex(), req.getPageSize());
        return roleUsecase.search(req.getName(), pageable);
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

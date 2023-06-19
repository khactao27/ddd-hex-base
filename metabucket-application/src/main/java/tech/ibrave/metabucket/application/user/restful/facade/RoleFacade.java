package tech.ibrave.metabucket.application.user.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.RoleMapper;
import tech.ibrave.metabucket.application.user.restful.request.role.DeleteRoleIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleSearchReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleStatusBulkReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.dto.RoleSlimDto;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;
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
        if (roleUsecase.existsByName(req.getName())) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }

        var role = mapper.toRole(req);
        var roleId = roleUsecase.save(role).getId();
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.create.success"));
    }

    public SuccessResponse updateRole(Long roleId, PersistRoleReq req) {
        var role = roleUsecase.getOrElseThrow(roleId);

        if (!role.getName().equals(req.getName() ) && roleUsecase.existsByName(req.getName())) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }

        mapper.updateRole(role, req);
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

    public Page<RoleLiteDto> getAllRole(RoleSearchReq req) {
        return roleUsecase.search(req.getName(), req);
    }

    public RoleDto getRoleById(Long roleId) {
        return roleUsecase.findByIdUseDto(roleId);
    }

    public Page<RoleSlimDto> getRoleShortInfo(RoleSearchReq req) {
        var page = roleUsecase.search(req.getName(), req);
        return new Page<>(page, CollectionUtils.toList(page.getData(), mapper::toSlimDto));
    }
}

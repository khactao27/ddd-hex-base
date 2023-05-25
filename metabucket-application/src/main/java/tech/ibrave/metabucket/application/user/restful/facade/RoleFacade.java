package tech.ibrave.metabucket.application.user.restful.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.RoleMapper;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistListRoleStatusReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleIdsReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleLiteReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistSearchRoleReq;
import tech.ibrave.metabucket.application.user.restful.response.role.multi.PageRoleLiteResp;
import tech.ibrave.metabucket.application.user.restful.response.role.multi.PageRoleResp;
import tech.ibrave.metabucket.application.user.restful.response.role.single.RoleResp;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.domain.user.usecase.UserUseCase;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessListResp;
import tech.ibrave.metabucket.shared.response.SuccessResponse;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;
import tech.ibrave.metabucket.shared.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

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
        validateCreateRole(req.getName(), req.getUserIds());
        var users = CollectionUtils.toList(req.getUserIds(), userUseCase::getOrElseThrow);
        var roleId = roleUsecase.save(mapper.toRole(req, users)).getId();
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.create.success"));
    }

    public SuccessResponse updateRole(Long roleId, PersistRoleReq req) {
        var role = roleUsecase.getOrElseThrow(roleId);
        validateName(role.getName(), req.getName());
        role.setUsers(null);
        var users = CollectionUtils.toList(req.getUserIds(), userUseCase::getOrElseThrow);
        mapper.toRole(role, req, users);
        roleUsecase.save(role);
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.update.success"));
    }

    public SuccessListResp updateRoleStatus(PersistListRoleStatusReq req) {
        var numberOfSuccesses = 0;
        var numberOfFailures = 0;
        var idFailure = 0L;
        List<SuccessResponse> successResponses = new ArrayList<>();
        try {
            for (int i = 0; i < req.getRoleStatusReqs().size(); i++) {
                var statusReq = req.getRoleStatusReqs().get(i);
                idFailure = statusReq.getId();
                var role = roleUsecase.getOrElseThrow(statusReq.getId());
                role.setStatus(statusReq.isStatus());
                roleUsecase.save(role);
                numberOfSuccesses += 1;
                successResponses.add(new SuccessResponse(
                        idFailure,
                        messageSource.getMessage("mb.roles.update.success"))
                );
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            numberOfFailures += 1;
            successResponses.add(
                    new SuccessResponse(idFailure,
                    messageSource.getMessage("mb.roles.delete.failure"))
            );
        }
        return new SuccessListResp(numberOfSuccesses, numberOfFailures, successResponses);
    }

    public SuccessResponse deleteRole(Long roleId) {
        validateDeleteRole(roleId);
        roleUsecase.deleteIfExist(roleId);
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.delete.success"));
    }

    public SuccessListResp deleteRoles(PersistRoleIdsReq req) {
        var numberOfSuccesses = 0;
        var numberOfFailures = 0;
        var idFailure = 0L;
        List<SuccessResponse> successResponses = new ArrayList<>();
        try {
            for (int i = 0; i < req.getIds().size(); i++) {
                idFailure = req.getIds().get(i);
                var successResponse = deleteRole(idFailure);
                numberOfSuccesses += 1;
                successResponses.add(successResponse);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            numberOfFailures += 1;
            successResponses.add(new SuccessResponse(idFailure, messageSource.getMessage("mb.roles.delete.failure")));
        }
        return new SuccessListResp(numberOfSuccesses, numberOfFailures, successResponses);
    }

    public PageRoleResp getAllRole(PersistSearchRoleReq req) {
        var roles = roleUsecase.search(req.getName(), req.getPageIndex(), req.getPageSize());
        var roleResponses = CollectionUtils.toList(roles.getData(), mapper::toRoleResp);
        return new PageRoleResp(
                req.getPageIndex(),
                req.getPageSize(),
                roles.getTotalElement(),
                roles.getPageSize(),
                roleResponses);
    }


    public RoleResp getRoleById(Long roleId) {
        var role = roleUsecase.getOrElseThrow(roleId);
        return mapper.toRoleResp(role);
    }

    public RoleResp getRoleByName(String roleName) {
        var role = roleUsecase.findByName(roleName);
        return ObjectUtils.isNullOrEmpty(role) ? null : mapper.toRoleResp(role);
    }


    public PageRoleLiteResp getRoleShortInfo(PersistRoleLiteReq req) {
        var roles = roleUsecase.findAllByName(req.getQuery(), req.getPageIndex(), req.getPageSize());
        var result = CollectionUtils.toList(roles.getData(), mapper::toRoleLiteResp);
        return new PageRoleLiteResp(
                req.getPageIndex(),
                req.getPageSize(),
                roles.getTotalElement(),
                (int) roles.getTotalPage(),
                result
        );
    }

    private void validateName(String oldName, String newName) {
        if (!oldName.equals(newName) || roleUsecase.existsByName(newName)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }
    }

    private void validateCreateRole(String name, List<String> userIds) {
        if (roleUsecase.existsByName(name)) {
            throw new ErrorCodeException(ErrorCodes.ROLE_NAME_EXISTED);
        }

        userIds.forEach(i -> {
            if (userUseCase.existById(i)) {
                throw new ErrorCodeException(ErrorCodes.NOT_FOUND);
            }
        });
    }

    private void validateDeleteRole(Long roleId) {
        roleUsecase.getOrElseThrow(roleId);
    }
}

package tech.ibrave.metabucket.application.user.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.user.restful.facade.RoleFacade;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleLiteReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleSearchReq;
import tech.ibrave.metabucket.application.user.restful.request.role.RoleStatusBulkReq;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.response.SuccessListResp;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: anct
 * Date: 23/05/2023ô
 * #YWNA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
public class RoleApi {

    private final RoleFacade roleFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse createRole(@Valid @RequestBody PersistRoleReq req) {
        return roleFacade.createRole(req);
    }

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse updateRole(@PathVariable("roleId") Long roleId,
                                      @Valid @RequestBody PersistRoleReq req) {
        return roleFacade.updateRole(roleId, req);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessListResp updateRoleStatus(@ModelAttribute RoleStatusBulkReq req) {
        return roleFacade.updateRoleStatus(req);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse deleteRole(@PathVariable("roleId") Long roleId) {
        return roleFacade.deleteRole(roleId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessListResp deleteRoles(@ModelAttribute RoleIdBulkReq ids) {
        return roleFacade.deleteRoles(ids);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleDto> getAllRole(@ModelAttribute RoleSearchReq req) {
        return roleFacade.getAllRole(req);
    }

    @GetMapping("/id/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getRoleById(@PathVariable("roleId") Long roleId) {
        return roleFacade.getRoleById(roleId);
    }

    @GetMapping("/short-info")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleLiteDto> getRoleShortInfo(@ModelAttribute RoleLiteReq req) {
        return roleFacade.getRoleShortInfo(req);
    }
}

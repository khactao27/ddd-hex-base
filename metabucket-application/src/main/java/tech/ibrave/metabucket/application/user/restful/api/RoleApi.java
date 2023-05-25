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
import tech.ibrave.metabucket.application.user.restful.request.role.PersistListRoleStatusReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleIdsReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleLiteReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistSearchRoleReq;
import tech.ibrave.metabucket.application.user.restful.response.role.multi.PageRoleLiteResp;
import tech.ibrave.metabucket.application.user.restful.response.role.multi.PageRoleResp;
import tech.ibrave.metabucket.application.user.restful.response.role.single.RoleResp;
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
                                      @RequestBody @Valid PersistRoleReq req) {
        return roleFacade.updateRole(roleId, req);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public SuccessListResp updateRoleStatus(@ModelAttribute PersistListRoleStatusReq req) {
        return roleFacade.updateRoleStatus(req);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse deleteRole(@PathVariable("roleId") Long roleId) {
        return roleFacade.deleteRole(roleId);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessListResp deleteRoles(@ModelAttribute PersistRoleIdsReq ids) {
        return roleFacade.deleteRoles(ids);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageRoleResp getAllRole(@ModelAttribute PersistSearchRoleReq req) {
        return roleFacade.getAllRole(req);
    }

    @GetMapping("/id/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResp getRoleById(@PathVariable("roleId") Long roleId) {
        return roleFacade.getRoleById(roleId);
    }

    @GetMapping("/name/{roleName}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResp getRoleByName(@PathVariable("roleName") String roleName) {
        return roleFacade.getRoleByName(roleName);
    }

    @GetMapping("/short-info")
    @ResponseStatus(HttpStatus.OK)
    public PageRoleLiteResp getRoleShortInfo(@ModelAttribute PersistRoleLiteReq req) {
        return roleFacade.getRoleShortInfo(req);
    }
}

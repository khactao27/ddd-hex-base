package tech.ibrave.metabucket.application.user.restful.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import tech.ibrave.metabucket.application.user.restful.facade.UserGroupFacade;
import tech.ibrave.metabucket.application.user.restful.request.group.AddOrDeleteUserToGroupReq;
import tech.ibrave.metabucket.application.user.restful.request.group.DeleteUserGroupIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.group.PersistUserGroupReq;
import tech.ibrave.metabucket.application.user.restful.request.group.UserGroupStatusBulkReq;
import tech.ibrave.metabucket.domain.shared.request.UserGroupSearchReq;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.domain.user.dto.UserGroupLiteDto;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.model.response.SuccessResponse;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/groups")
public class UserGroupApi {

    private final UserGroupFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('mb.groups.create')")
    public SuccessResponse createUserGroup(@Valid @RequestBody PersistUserGroupReq req) {
        return facade.createUserGroup(req);
    }

    @PutMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('mb.groups.update')")
    public SuccessResponse updateUserGroup(@PathVariable("groupId") String groupId,
                                           @Valid @RequestBody PersistUserGroupReq req) {
        return facade.updateUserGroup(groupId, req);
    }

    @PutMapping("/status")
    @PreAuthorize("hasAnyAuthority('mb.groups.update')")
    public SuccessResponse updateUserGroupStatus(@RequestBody UserGroupStatusBulkReq req) {
        return facade.updateUserGroupStatus(req);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('mb.groups.delete')")
    public SuccessResponse deleteUserGroup(@PathVariable("groupId") String groupId) {
        return facade.deleteUserGroup(groupId);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('mb.groups.delete')")
    public SuccessResponse deleteUserGroups(@ModelAttribute DeleteUserGroupIdBulkReq ids) {
        return facade.deleteUserGroups(ids);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('mb.groups.view')")
    public Page<UserGroupDto> getAllGroup(@ModelAttribute UserGroupSearchReq req) {
        return facade.getAllUserGroup(req);
    }

    @GetMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('mb.groups.view')")
    public UserGroupDto getUserGroupById(@PathVariable("groupId") String groupId) {
        return facade.getUserGroupById(groupId);
    }

    @GetMapping("/short-info")
    public Page<UserGroupLiteDto> getRoleShortInfo(@ModelAttribute UserGroupSearchReq req) {
        return facade.getUserGroupShortInfo(req);
    }

    @PostMapping("/{groupId}/add-user")
    @PreAuthorize("hasAnyAuthority('mb.groups.update')")
    public SuccessResponse addUser(@PathVariable String groupId,
                                   @RequestBody AddOrDeleteUserToGroupReq req) {
        return facade.addUser(groupId, req);
    }

    @PostMapping("/{groupId}/delete-user")
    @PreAuthorize("hasAnyAuthority('mb.groups.update')")
    public SuccessResponse deleteUsers(@PathVariable String groupId,
                                       @RequestBody AddOrDeleteUserToGroupReq req) {
        return facade.deleteUsers(groupId, req);
    }
}

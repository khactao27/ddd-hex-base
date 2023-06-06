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
import tech.ibrave.metabucket.application.user.restful.facade.UserGroupFacade;
import tech.ibrave.metabucket.application.user.restful.request.group.AddUserToGroupReq;
import tech.ibrave.metabucket.application.user.restful.request.group.DeleteUserGroupIdBulkReq;
import tech.ibrave.metabucket.application.user.restful.request.group.PersistUserGroupReq;
import tech.ibrave.metabucket.application.user.restful.request.group.UserGroupStatusBulkReq;
import tech.ibrave.metabucket.domain.shared.request.UserGroupSearchReq;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.domain.user.dto.UserGroupLiteDto;
import tech.ibrave.metabucket.shared.architecture.Page;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

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
    public SuccessResponse createUserGroup(@Valid @RequestBody PersistUserGroupReq req) {
        return facade.createUserGroup(req);
    }

    @PutMapping("/{groupId}")
    public SuccessResponse updateUserGroup(@PathVariable("groupId") String groupId,
                                           @Valid @RequestBody PersistUserGroupReq req) {
        return facade.updateUserGroup(groupId, req);
    }

    @PutMapping("/status")
    public SuccessResponse updateUserGroupStatus(@RequestBody UserGroupStatusBulkReq req) {
        return facade.updateUserGroupStatus(req);
    }

    @DeleteMapping("/{groupId}")
    public SuccessResponse deleteUserGroup(@PathVariable("groupId") String groupId) {
        return facade.deleteUserGroup(groupId);
    }

    @DeleteMapping
    public SuccessResponse deleteUserGroups(@ModelAttribute DeleteUserGroupIdBulkReq ids) {
        return facade.deleteUserGroups(ids);
    }

    @GetMapping
    public Page<UserGroupDto> getAllGroup(@ModelAttribute UserGroupSearchReq req) {
        return facade.getAllUserGroup(req);
    }

    @GetMapping("/{groupId}")
    public UserGroupDto getRoleById(@PathVariable("groupId") String groupId) {
        return facade.getUserGroupById(groupId);
    }

    @GetMapping("/short-info")
    public Page<UserGroupLiteDto> getRoleShortInfo(@ModelAttribute UserGroupSearchReq req) {
        return facade.getUserGroupShortInfo(req);
    }

    @PostMapping("/{groupId}/add-user")
    public SuccessResponse addUser(@PathVariable String groupId,
                                   @RequestBody AddUserToGroupReq req) {
        return facade.addUser(groupId, req);
    }
}

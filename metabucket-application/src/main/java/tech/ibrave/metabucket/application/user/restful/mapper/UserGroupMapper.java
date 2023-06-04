package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.group.PersistUserGroupReq;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.domain.user.dto.UserGroupLiteDto;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Mapper
public interface UserGroupMapper {

    UserGroup toUserGroup(PersistUserGroupReq req);

    void toUserGroup(@MappingTarget UserGroup role, PersistUserGroupReq req);

    UserGroupLiteDto toUserGroupLiteResp(UserGroupDto role);
}

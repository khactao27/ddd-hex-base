package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.application.user.restful.response.role.single.RoleLiteResp;
import tech.ibrave.metabucket.application.user.restful.response.role.single.RoleResp;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.User;

import java.util.List;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
@Mapper
public interface RoleMapper {

    Role fromRequest(PersistRoleReq req);

    Role toRole(PersistRoleReq req, List<User> users);
    Role toRole(@MappingTarget Role role, PersistRoleReq req, List<User> users);

    RoleResp toRoleResp(Role role);

    RoleLiteResp toRoleLiteResp(Role role);
}

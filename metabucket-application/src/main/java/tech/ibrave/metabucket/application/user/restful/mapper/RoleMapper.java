package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
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

    Role toRole(PersistRoleReq req, List<User> users);
    void toRole(@MappingTarget Role role, PersistRoleReq req, List<User> users);

    RoleDto toRoleResp(Role role);

    RoleLiteDto toRoleLiteResp(Role role);
}

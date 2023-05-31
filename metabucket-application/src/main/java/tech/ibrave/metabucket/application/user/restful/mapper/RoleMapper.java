package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
@Mapper
public interface RoleMapper {

    Role toRole(PersistRoleReq req);

    void toRole(@MappingTarget Role role, PersistRoleReq req);

    RoleDto toRoleResp(Role role);

    RoleLiteDto toRoleLiteResp(RoleDto role);
}

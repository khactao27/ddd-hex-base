package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.domain.shared.Permission;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.dto.RoleSlimDto;

import java.util.List;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
@Mapper
public interface RoleMapper {

    @Mapping(target = "permissions", source = "permissionEnums")
    Role toRole(PersistRoleReq req, List<Permission> permissionEnums);

    RoleSlimDto toSlimDto(RoleLiteDto roleLiteDto);

    @Mapping(target = "permissions", source = "permissionEnums")
    void updateRole(@MappingTarget Role role, PersistRoleReq req, List<Permission> permissionEnums);
}

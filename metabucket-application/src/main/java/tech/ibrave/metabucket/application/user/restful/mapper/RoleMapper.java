package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.role.PersistRoleReq;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.dto.RoleSlimDto;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
@Mapper
public interface RoleMapper {

    Role toRole(PersistRoleReq req);

    RoleSlimDto toSlimDto(RoleLiteDto roleLiteDto);

    void updateRole(@MappingTarget Role role, PersistRoleReq req);
}

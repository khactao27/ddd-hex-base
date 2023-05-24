package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.application.user.restful.request.PersistRoleReq;
import tech.ibrave.metabucket.domain.user.Role;

/**
 * author: anct
 * date: 5/24/2023
 * YNWA
 */
@Mapper
public interface RoleMapper {

    Role fromRequest(PersistRoleReq req);
}

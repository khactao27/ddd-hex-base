package tech.ibrave.metabucket.infra.peristence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.role.Role;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.RoleEntity;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Mapper
public interface RoleMapper extends BaseEntityMapper<RoleEntity, Role> {
}

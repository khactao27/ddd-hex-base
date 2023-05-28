package tech.ibrave.metabucket.infra.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.RoleEntity;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Mapper
public interface RoleEntityMapper extends BaseEntityMapper<RoleEntity, Role> {

    RoleDto toDto(RoleEntity entity);
}

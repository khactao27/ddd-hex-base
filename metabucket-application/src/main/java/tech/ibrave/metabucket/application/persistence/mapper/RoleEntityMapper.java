package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.application.persistence.jpa.entity.RoleEntity;
import tech.ibrave.metabucket.domain.user.Role;
import tech.ibrave.metabucket.domain.user.dto.RoleDto;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Mapper
public interface RoleEntityMapper extends BaseEntityMapper<RoleEntity, Role> {

    @Mapping(target = "users", ignore = true)
    RoleDto toDto(RoleEntity entity);

    @Named("roleLazy")
    @Mapping(target = "users", ignore = true)
    Role toLazyDomain(RoleEntity entity);
}

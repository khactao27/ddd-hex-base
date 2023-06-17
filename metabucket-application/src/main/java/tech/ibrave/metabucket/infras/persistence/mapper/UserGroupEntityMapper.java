package tech.ibrave.metabucket.infras.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.domain.user.dto.UserGroupDto;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.UserGroupEntity;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper(uses = {UserEntityMapper.class})
public interface UserGroupEntityMapper extends BaseEntityMapper<UserGroupEntity, UserGroup> {

    UserGroupDto toDto(UserGroupEntity entity);

    @Override
    @Mapping(target = "users", qualifiedByName = "userLazy")
    UserGroup toDomainModel(UserGroupEntity entity);

    @Named("groupLazy")
    @Mapping(target = "users", ignore = true)
    UserGroup toLazyDomain(UserGroupEntity entity);
}

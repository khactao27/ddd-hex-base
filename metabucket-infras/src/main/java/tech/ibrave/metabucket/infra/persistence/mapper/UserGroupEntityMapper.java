package tech.ibrave.metabucket.infra.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.domain.user.UserGroup;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.UserGroupEntity;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper
public interface UserGroupEntityMapper extends BaseEntityMapper<UserGroupEntity, UserGroup> {

    @Named("groupLazy")
    @Mapping(target = "users", ignore = true)
    UserGroup toLazyDomain(UserGroupEntity entity);
}

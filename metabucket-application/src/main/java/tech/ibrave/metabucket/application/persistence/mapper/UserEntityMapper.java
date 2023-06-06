package tech.ibrave.metabucket.application.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.application.persistence.jpa.entity.UserEntity;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.dto.UserLiteDto;

import java.util.List;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper(uses = {RoleEntityMapper.class, UserGroupEntityMapper.class})
public interface UserEntityMapper extends BaseEntityMapper<UserEntity, User> {
    UserDto toDto(UserEntity user);

    @Override
    @Mapping(target = "roles", qualifiedByName = "roleLazy")
    @Mapping(target = "groups", qualifiedByName = "groupLazy")
    User toDomainModel(UserEntity entity);

    List<UserLiteDto> toUserRoleLazy(List<UserEntity> users);

    @Named("userLazy")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toLazyDomain(UserEntity entity);
}

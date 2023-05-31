package tech.ibrave.metabucket.infra.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.dto.UserLiteDto;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.UserEntity;

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
}

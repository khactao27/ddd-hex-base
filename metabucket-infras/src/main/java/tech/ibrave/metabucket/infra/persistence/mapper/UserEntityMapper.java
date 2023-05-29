package tech.ibrave.metabucket.infra.persistence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.UserEntity;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper
public interface UserEntityMapper extends BaseEntityMapper<UserEntity, User> {
    UserDto toDto(UserEntity user);
}

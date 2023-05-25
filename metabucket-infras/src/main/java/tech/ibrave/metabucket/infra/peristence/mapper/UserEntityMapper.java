package tech.ibrave.metabucket.infra.peristence.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.UserEntity;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper
public interface UserEntityMapper extends BaseEntityMapper<UserEntity, User> {
}

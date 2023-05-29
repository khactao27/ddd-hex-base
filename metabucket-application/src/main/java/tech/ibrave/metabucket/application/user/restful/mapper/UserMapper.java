package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.shared.architecture.Page;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper
public interface UserMapper {
    User toUser(PersistUserReq req);

    @Mapping(target = "password", source = "password")
    User toUser(PersistUserReq req, String password);

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    void updateUser(@MappingTarget User user, PersistUserReq req);

    UserDto toDto(User user);
}

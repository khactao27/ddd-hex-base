package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.domain.user.User;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper
public interface UserMapper {
    User toUser(PersistUserReq req);

    void updateUser(@MappingTarget User user, PersistUserReq req);
}

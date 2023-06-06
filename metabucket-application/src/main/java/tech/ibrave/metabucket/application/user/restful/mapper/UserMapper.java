package tech.ibrave.metabucket.application.user.restful.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import tech.ibrave.metabucket.application.auth.restful.request.ConfirmRegisterReq;
import tech.ibrave.metabucket.application.user.model.ImportedUser;
import tech.ibrave.metabucket.application.user.model.ImportedUserResult;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;

/**
 * Author: hungnm
 * Date: 25/05/2023
 */
@Mapper
public interface UserMapper {

    @Mapping(target = "password", source = "password")
    User toUser(PersistUserReq req, String password);

    User toUser(UserDto userDto);

    @Mapping(target = "password", source = "password")
    User toUser(ConfirmRegisterReq req, String password);

    @Mapping(target = "enable", source = "importedUser.status", qualifiedByName = "mapStatus")
    User toUser(ImportedUser importedUser, String password);

    @Named("mapStatus")
    default boolean mapStatus(String status) {
        return StringUtils.equals("Active", status);
    }

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    void updateUser(@MappingTarget User user, PersistUserReq req);

    UserDto toDto(User user);

    ImportedUserResult toImportedResult(ImportedUser importedUser, String message);
}

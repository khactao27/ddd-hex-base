package tech.ibrave.metabucket.application.user.restful.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import tech.ibrave.metabucket.application.auth.restful.request.ConfirmRegisterReq;
import tech.ibrave.metabucket.application.user.model.ExportedUser;
import tech.ibrave.metabucket.application.user.model.ImportedUser;
import tech.ibrave.metabucket.application.user.model.ImportedUserResult;
import tech.ibrave.metabucket.application.user.restful.request.PersistUserReq;
import tech.ibrave.metabucket.domain.ErrorCodes;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.RoleLiteDto;
import tech.ibrave.metabucket.domain.user.dto.UserDto;
import tech.ibrave.metabucket.domain.user.dto.UserGroupLiteDto;
import tech.ibrave.metabucket.shared.exception.ErrorCodeException;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;

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
        if ("Active".equals(status)) {
            return true;
        }
        if ("Inactive".equals(status)) {
            return false;
        }
        throw new ErrorCodeException(ErrorCodes.INVALID_STATUS);
    }

    void updateUser(@MappingTarget User user, PersistUserReq req);

    UserDto toDto(User user);

    ImportedUserResult toImportedResult(ImportedUser importedUser, String message);

    @Mapping(target = "role", source = "userDto.roles")
    @Mapping(target = "group", source = "userDto.groups")
    ExportedUser toExportedUser(UserDto userDto);

    default String mapRole(List<RoleLiteDto> roles) {
        var listRole = CollectionUtils.toList(roles, RoleLiteDto::getName);
        return String.join(", ", listRole);
    }

    default String mapGroup(List<UserGroupLiteDto> groups) {
        var listGroup = CollectionUtils.toList(groups, UserGroupLiteDto::getName);
        return String.join(", ", listGroup);
    }
}

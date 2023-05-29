package tech.ibrave.metabucket.application.auth.restful.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserDto;

/**
 * Author: anct
 * Date: 29/05/2023
 * #YWNA
 */
@Mapper
public interface PrincipalMapper {

    UserDto toDto(User user);
}

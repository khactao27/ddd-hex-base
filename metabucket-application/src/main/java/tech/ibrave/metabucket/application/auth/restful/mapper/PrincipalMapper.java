package tech.ibrave.metabucket.application.auth.restful.mapper;

import org.mapstruct.Mapper;
import tech.ibrave.metabucket.domain.user.User;
import tech.ibrave.metabucket.domain.user.dto.UserAuditingObject;

/**
 * Author: anct
 * Date: 29/05/2023
 * #YWNA
 */
@Mapper
public interface PrincipalMapper {

    UserAuditingObject toDto(User user);
}

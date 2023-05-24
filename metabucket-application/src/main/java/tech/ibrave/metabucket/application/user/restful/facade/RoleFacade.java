package tech.ibrave.metabucket.application.user.restful.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.user.restful.mapper.RoleMapper;
import tech.ibrave.metabucket.application.user.restful.request.PersistRoleReq;
import tech.ibrave.metabucket.domain.user.usecase.RoleUseCase;
import tech.ibrave.metabucket.shared.message.MessageSource;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleUseCase roleUsecase;
    private final RoleMapper mapper;
    private final MessageSource messageSource;

    public SuccessResponse createRole(PersistRoleReq req) {
        var roleId = roleUsecase.create(mapper.fromRequest(req)).getId();
        return new SuccessResponse(roleId, messageSource.getMessage("mb.roles.create.success"));
    }
}

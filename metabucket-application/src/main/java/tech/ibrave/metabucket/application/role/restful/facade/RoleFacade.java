package tech.ibrave.metabucket.application.role.restful.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.application.role.restful.request.CreateRoleReq;
import tech.ibrave.metabucket.domain.role.usecase.RoleUseCase;
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

    public SuccessResponse<Long> createRole(CreateRoleReq req) {
        var roleId = roleUsecase.create(req.toDomainModel()).getId();
        return new SuccessResponse<>(roleId, "mb.roles.create.success");
    }
}

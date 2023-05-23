package tech.ibrave.metabucket.application.role.restful.api;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.role.restful.facade.RoleFacade;
import tech.ibrave.metabucket.application.role.restful.request.CreateRoleReq;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
public class RoleApi {

    private final RoleFacade roleFacade;

    @PostMapping
    public SuccessResponse<Long> createRole(@Validated @RequestBody CreateRoleReq req) {
        return roleFacade.createRole(req);
    }
}

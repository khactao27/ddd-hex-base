package tech.ibrave.metabucket.application.user.restful.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.user.restful.facade.RoleFacade;
import tech.ibrave.metabucket.application.user.restful.request.PersistRoleReq;
import tech.ibrave.metabucket.shared.response.SuccessResponse;

import javax.validation.Valid;

/**
 * Author: anct
 * Date: 23/05/2023ô
 * #YWNA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
public class RoleApi {

    private final RoleFacade roleFacade;

    @PostMapping
    public SuccessResponse createRole(@Valid @RequestBody PersistRoleReq req) {
        return roleFacade.createRole(req);
    }
}

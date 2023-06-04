package tech.ibrave.metabucket.application.user.restful.api;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ibrave.metabucket.application.user.restful.response.PermissionResp;
import tech.ibrave.metabucket.shared.constant.Permission;
import tech.ibrave.metabucket.shared.message.MessageSource;

import java.util.Arrays;
import java.util.List;

/**
 * author: anct
 * date: 6/4/2023
 * YNWA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/permissions")
public class PermissionApi {

    private final MessageSource permissionsSource;

    @GetMapping
    @Cacheable("default")
    public List<PermissionResp> getAllPermissions() {
        return Arrays.stream(Permission.values())
                .map(permission -> new PermissionResp(permissionsSource.getRawMessage(permission.getValue()), permission.toString()))
                .toList();
    }
}

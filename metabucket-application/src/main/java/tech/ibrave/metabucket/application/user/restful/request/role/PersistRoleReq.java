package tech.ibrave.metabucket.application.user.restful.request.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.constant.Permission;

import java.util.List;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistRoleReq {

    @NotBlank(message = "{mb.roles.create.name_empty}")
    private String name;
    private String description;
    private boolean enable;
    private List<Permission> permissions;
    private List<String> userIds;
}

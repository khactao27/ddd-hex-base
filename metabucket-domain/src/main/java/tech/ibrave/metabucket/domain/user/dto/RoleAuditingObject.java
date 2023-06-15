package tech.ibrave.metabucket.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.BaseAuditingObject;
import tech.ibrave.metabucket.domain.shared.Permission;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 25/05/2023
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleAuditingObject extends BaseAuditingObject {
    private Long id;
    private String name;
    private String description;
    private boolean enable;
    private List<Permission> permissions;
    private List<UserLiteDto> users;
}

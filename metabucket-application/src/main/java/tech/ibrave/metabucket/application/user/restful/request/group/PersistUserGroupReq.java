package tech.ibrave.metabucket.application.user.restful.request.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.dto.UserAuditingObject;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistUserGroupReq {

    @NotBlank(message = "{mb.groups.create.name_empty}")
    private String name;
    private String description;
    private boolean enable;
    private List<UserAuditingObject> users;
}

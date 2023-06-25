package tech.ibrave.metabucket.application.user.restful.request.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.User;

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

    @NotBlank(message = "{mb.roles.create.required_rolename}")
    @Pattern(regexp = "[^`˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{0,32}$",
            message = "{mb.roles.create.invalid_rolename}")
    private String name;
    @Size(max = 500, message = "{mb.roles.create.invalid_description}")
    private String description;
    @NotNull(message = "{mb.roles.create.required_status}")
    private Boolean enable;
    private List<String> permissions;
    private List<User> users;
}

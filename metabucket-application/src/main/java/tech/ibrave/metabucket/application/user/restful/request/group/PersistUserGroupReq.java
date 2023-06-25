package tech.ibrave.metabucket.application.user.restful.request.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.user.dto.UserDto;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistUserGroupReq {
    @NotBlank(message = "{mb.groups.create.required_groupname}")
    @Pattern(regexp = "[^`˜!`#$%ˆ&*()_\\-+=|\\{}\\[\\]?/:;\".,<>]{0,32}$",
            message = "{mb.groups.create.invalid_groupname}")
    private String name;
    @Size(max = 500, message = "{mb.groups.create.invalid_description}")
    private String description;
    @NotNull(message = "{mb.groups.create.required_status}")
    private Boolean enable;
    private List<UserDto> users;
}

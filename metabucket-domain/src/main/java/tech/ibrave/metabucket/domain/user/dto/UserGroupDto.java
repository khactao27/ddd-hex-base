package tech.ibrave.metabucket.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroupDto {

    private String id;
    private String name;
    private String description;
    private boolean enable;
    private List<UserLiteDto> users;
}

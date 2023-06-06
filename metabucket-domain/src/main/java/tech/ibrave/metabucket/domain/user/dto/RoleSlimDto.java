package tech.ibrave.metabucket.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: nguyendinhthi
 * Date: 25/05/2023
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleSlimDto {

    private Long id;
    private String name;
}

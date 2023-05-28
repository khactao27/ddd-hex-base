package tech.ibrave.metabucket.application.user.restful.request.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleStatusReq {

    private Long id;
    private boolean status;
}

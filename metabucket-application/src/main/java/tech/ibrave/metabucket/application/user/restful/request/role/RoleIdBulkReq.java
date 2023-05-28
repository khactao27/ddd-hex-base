package tech.ibrave.metabucket.application.user.restful.request.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleIdBulkReq {

    private List<Long> ids;
}

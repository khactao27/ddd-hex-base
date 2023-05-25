package tech.ibrave.metabucket.application.user.restful.request.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.request.PageReq;

/**
 * Author: nguyendinhthi
 * Date: 25/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistSearchRoleReq extends PageReq {

    private String name;
}

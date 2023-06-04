package tech.ibrave.metabucket.domain.shared.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.request.PageReq;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGroupSearchReq extends PageReq {

    private String query;
    private Boolean enable;
}
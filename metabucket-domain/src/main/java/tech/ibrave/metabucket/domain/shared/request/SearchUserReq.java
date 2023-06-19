package tech.ibrave.metabucket.domain.shared.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.shared.model.request.PageReq;

/**
 * Author: hungnm
 * Date: 28/05/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchUserReq extends PageReq {
    private String query;
    private String userGroupId;
    private Long roleId;
    private Boolean enable;
}

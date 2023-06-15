package tech.ibrave.metabucket.application.user.restful.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import tech.ibrave.metabucket.domain.shared.request.SearchUserReq;

import java.util.List;

/**
 * Author: hungnm
 * Date: 04/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportUserReq extends SearchUserReq {
    private Integer pageIndex = 1;
    private Integer pageSize = 100000; // unlimit records
    private List<String> fields;
    private List<String> userIds;
}

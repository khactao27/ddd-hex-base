package tech.ibrave.metabucket.application.user.restful.request.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 04/06/2023
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteUserGroupIdBulkReq {

    private List<String> ids;
}
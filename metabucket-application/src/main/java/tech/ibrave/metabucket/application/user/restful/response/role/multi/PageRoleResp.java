package tech.ibrave.metabucket.application.user.restful.response.role.multi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.application.user.restful.response.role.single.RoleResp;
import tech.ibrave.metabucket.shared.architecture.Page;

import java.util.List;

/**
 * Author: nguyendinhthi
 * Date: 25/05/2023
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageRoleResp extends Page<RoleResp> {

    public PageRoleResp(Integer pageIndex,
                        Integer pageSize,
                        long totalElements,
                        int totalPages,
                        List<RoleResp> roleResponses) {
        super(pageIndex, pageSize, totalElements, totalPages, roleResponses);
    }
}

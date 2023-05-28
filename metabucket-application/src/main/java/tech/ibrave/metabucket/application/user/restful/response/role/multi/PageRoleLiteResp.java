package tech.ibrave.metabucket.application.user.restful.response.role.multi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ibrave.metabucket.application.user.restful.response.role.single.RoleLiteResp;
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
public class PageRoleLiteResp extends Page<RoleLiteResp> {

    public PageRoleLiteResp(int pageIndex, int pageSize, long totalElement, int totalPage, List<RoleLiteResp> data) {
        super(pageIndex, pageSize, totalElement, totalPage, data);
    }
}

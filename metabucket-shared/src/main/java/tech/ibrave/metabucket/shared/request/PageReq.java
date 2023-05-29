package tech.ibrave.metabucket.shared.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: anct
 * Date: 25/05/2023
 * #YWNA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageReq {

    private Integer pageIndex = 1;
    private Integer pageSize = 20;

    /**
     * field1:asc,field2:desc
     */
    private String sort;

    public PageReq(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public String getFieldOrdered() {
        return sort.split(":")[0];
    }

    public String getOrder() {
        return sort.split(":")[1];
    }
}

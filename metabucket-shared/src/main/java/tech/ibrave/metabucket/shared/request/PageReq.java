package tech.ibrave.metabucket.shared.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Boolean> getSorts() {
        var sorts = new HashMap<String, Boolean>(3);
        try {
            if (StringUtils.hasLength(sort)) {
                var splits = sort.split(",");
                for (var sortStr : splits) {
                    var values = sortStr.split(":");
                    sorts.put(values[0], values[1].equals("asc"));
                }
            }
        } catch (Exception ignored) {}

        return sorts;
    }
}

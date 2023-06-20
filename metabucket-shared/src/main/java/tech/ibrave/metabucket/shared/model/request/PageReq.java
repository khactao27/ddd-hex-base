package tech.ibrave.metabucket.shared.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: anct
 * Date: 25/05/2023
 * #YWNA
 */
@Slf4j
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

    public Map<String, Order> getSorts() {
        var sorts = new HashMap<String, Order>(3);
        try {
            if (StringUtils.hasLength(sort)) {
                var splits = sort.split(",");
                for (var sortStr : splits) {
                    var values = sortStr.split(":");
                    sorts.put(values[0], Order.from(values[1]));
                }
            }
        } catch (Exception e) {
            log.warn("Invalid sort {}", sort);
        }

        return sorts;
    }

    public enum Order {
        ASC,
        DESC,
        NONE;

        public static Order from(String name) {
            if ("asc".equalsIgnoreCase(name)) {
                return ASC;
            } else if ("desc".equalsIgnoreCase(name)) {
                return DESC;
            }
            return NONE;
        }
    }
}

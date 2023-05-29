package tech.ibrave.metabucket.shared.architecture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import tech.ibrave.metabucket.shared.utils.CollectionUtils;

import java.util.List;
import java.util.function.Function;

/**
 * Author: anct
 * Date: 25/05/2023
 * #YWNA
 */
@Getter
@Setter
@NoArgsConstructor
public class Page<DM> {

    private List<DM> data;
    private long totalElement;
    private int totalPage;
    private int pageIndex;
    private int pageSize;

    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Page(int pageIndex,
                int pageSize,
                long totalElement,
                int totalPage) {
        this.totalElement = totalElement;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
    }

    public Page(int pageIndex,
                int pageSize,
                long totalElement,
                int totalPage,
                List<DM> data) {
        this.totalElement = totalElement;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.data = data;
        this.totalPage = totalPage;
    }

    public Page(org.springframework.data.domain.Page<DM> originPage) {
        this.totalElement = originPage.getTotalElements();
        this.pageIndex = originPage.getPageable().getPageNumber();
        this.pageSize = originPage.getPageable().getPageSize();
        this.data = originPage.getContent();
        this.totalPage = originPage.getTotalPages();
    }
}

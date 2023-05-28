package tech.ibrave.metabucket.shared.architecture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
}

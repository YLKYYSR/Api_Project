package com.sparkle.start_project.Common;

import com.sparkle.start_project.Constant.sortFields;
import lombok.Data;

@Data
public class PageFields {
    /**
     * 当前页号
     */
    private int currentPage;
    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 排序字段
     */
    private String pageSortFields;

    /**
     * 排序方式默认为升序
     */
    private String pageSortOrder = sortFields.ORDER_ASC;
}

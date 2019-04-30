package com.sixtyrobbers.GSQ.fourm.service.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:10
 * Version: V1.0
 * </pre>
 */
@Data
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = 7698590339210569253L;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 记录总数
     */
    private long totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页数据
     */
    private List<T> list;

    public PageInfo(int pageNumber, int pageSize, int totalCount, List<T> result) {

        if (pageSize <= 0) {
            throw new IllegalArgumentException("[pageSize] must great than zero");
        }
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("[pageNumber] must great than zero");
        }
        this.pageNum = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = computeTotalPages(totalCount, pageSize);
        this.list = result;
    }

    private int computeTotalPages(int totalCount, int pageSize) {

        int result = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        if (result <= 1) {
            result = 1;
        }
        return result;
    }

}

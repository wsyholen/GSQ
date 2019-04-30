package com.sixtyrobbers.GSQ.fourm.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:21
 * Version: V1.0
 * </pre>
 */
@Data
public class BaseParam implements Serializable {

    private static final long serialVersionUID = -6899826185429075204L;

    /**
     * 分页页码
     */
    private int pageNum;

    /**
     * 分页每页数量
     */
    private int pageSize;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 分页开始行
     */
    private int startRow;

}

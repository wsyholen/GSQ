package com.sixtyrobbers.GSQ.fourm.service.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:03
 * Version: V1.0
 * </pre>
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 5419631781407324063L;

    /**
     * 分页请求页码
     */
    private int pageNum;

    /**
     * 分页请求每页数量
     */
    private int pageSize;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String entTime;

    /**
     * 搜索关键字
     */
    private String keyWords;

}

package com.sixtyrobbers.GSQ.fourm.service.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:08
 * Version: V1.0
 * </pre>
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 4772764374402702168L;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 删除标识
     */
    private String validFlag;

}

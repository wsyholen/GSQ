package com.sixtyrobbers.GSQ.fourm.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:20
 * Version: V1.0
 * </pre>
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 3397019294270724382L;

    /**
     * 入库时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 有效标志
     */
    private int validFlag;

}

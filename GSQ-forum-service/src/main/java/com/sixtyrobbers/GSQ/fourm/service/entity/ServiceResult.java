package com.sixtyrobbers.GSQ.fourm.service.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/10 11:23
 * Version: V1.0
 * </pre>
 */
@Data
public class ServiceResult implements Serializable {

    private static final long serialVersionUID = -6821605654185249358L;

    private Boolean success;

    private Object info;

    public ServiceResult(Boolean success, Object info) {
        this.success = success;
        this.info = info;
    }
}

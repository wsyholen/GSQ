package com.sixtyrobbers.GSQ.fourm.service.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:37
 * Version: V1.0
 * </pre>
 */
@Data
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1291648895477517393L;

    private Boolean success;

    private String errorCode;

    private String errorMessage;

    private Object data;

    public BaseResult(boolean success, String errorCode, String errorMessage, Object data) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.data = data;
    }

}

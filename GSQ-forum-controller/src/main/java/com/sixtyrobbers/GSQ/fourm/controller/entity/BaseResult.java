package com.sixtyrobbers.GSQ.fourm.controller.entity;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:37
 * Version: V1.0
 * </pre>
 */
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

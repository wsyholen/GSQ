package com.sixtyrobbers.GSQ.fourm.controller.entity;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:37
 * Version: V1.0
 * </pre>
 */
public enum ResponseCodeEnum {

    /**
     * 请求成功时错误码、描述
     */
    ERROR_CODE_SUCCESS("", ""),

    /**
     * 请求失败时错误码、描述
     */
    ERROR_CODE_ERROR("ERROR_CODE_ERROR", "请求失败！"),

    /**
     * 入参错误
     */
    ERROR_CODE_PARAM("1001","入参错误"),

    /**
     * 缺少参数
     */
    ERROR_CODE_LACK_PARAM("1002","缺少参数");


    private String code;

    private String value;

    ResponseCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String getValueByCode(String code) {
        for (ResponseCodeEnum platformFree : ResponseCodeEnum.values()) {
            if (code.equals(platformFree.getCode())) {
                return platformFree.getValue();
            }
        }
        return null;
    }
}

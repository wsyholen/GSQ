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
    ERROR_CODE_SUCCESS("1000", "请求成功！"),

    /**
     * 请求失败时错误码、描述
     */
    ERROR_CODE_ERROR("1001", "请求失败！"),

    /**
     * 入参错误
     */
    ERROR_CODE_PARAM("1002","入参错误！"),

    /**
     * 缺少参数
     */
    ERROR_CODE_LACK_PARAM("1003","缺少参数！"),

    /**
     * 登陆成功
     */
    ERROR_CODE_LOGIN_SUCCESS("1004","登陆成功！"),

    /**
     * 登陆失败
     */
    ERROR_CODE_LOGIN_ERROR("1005","登陆失败！"),

    /**
     * 短线发送失败
     */
    ERROR_CODE_SEND_MESSAGE_ERROR("1006","短信发送失败！"),

    /**
     * 短线发送成功
     */
    ERROR_CODE_SEND_MESSAGE_SUCCESS("1007","短信发送成功！"),

    /**
     * 验证码过期
     */
    ERROR_CODE_CODE_DUE("1008","验证码过期！"),

    /**
     * 验证码错误
     */
    ERROR_CODE_CODE_ERROR("1009","验证码错误！"),

    /**
     * 注册成功
     */
    ERROR_CODE_REGISTER_SUCCESS("1010","注册成功！"),

    /**
     * 修改密码成功
     */
    ERROR_CODE_MODIFY_SUCCESS("2010","修改密码成功！"),
    /**
     * 重新输入的新密码与第一次不一致!
     */
    ERROR_CODE_MODIFY_ERROR("2011","两次密码输入不同！"),
    /**
     * 手机号不存在
     */
    ERROR_CODE_PHONE_ERROR("2012","手机号不存在！"),
    /**
     * 密码错误
     */
    ERROR_CODE_PASSWORD_ERROR("2013","手机号不存在！");
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

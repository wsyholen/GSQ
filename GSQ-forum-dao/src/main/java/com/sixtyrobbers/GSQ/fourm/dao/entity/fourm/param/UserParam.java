package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/9 14:32
 * Version: V1.0
 * </pre>
 */
@Data
public class UserParam implements Serializable {

    private static final long serialVersionUID = 2204762209170788087L;

    /**
     * 手机号
     */
    public String loginPhone;

    /**
     * 登陆邮箱
     */
    private String loginEmail;

    /**
     * 密码
     */
    private String loginPassword;

    /**
     * 背景id
     */
    private String backId;

    /**
     * 头像id
     */
    private String imagId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 性别:0:男；1:女
     */
    private Integer userSex;

    /**
     * 个人介绍
     */
    private String userIntroduce;

    /**
     * 爱好
     */
    private String userHobby;

    /**
     * 职业
     */
    private String userOccupation;

    /**
     * 最后登陆时间
     */
    private String lastLogin;

    /**
     * 最后登出时间
     */
    private String lastLogout;

    /**
     * 登陆ip
     */
    private String loginIp;

}

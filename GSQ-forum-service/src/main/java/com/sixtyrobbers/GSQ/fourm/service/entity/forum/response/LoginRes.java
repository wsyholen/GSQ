package com.sixtyrobbers.GSQ.fourm.service.entity.forum.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 09:47
 * Version: V1.0
 * </pre>
 */
@Getter
@Setter
public class LoginRes implements Serializable {

    private static final long serialVersionUID = -5313606301713027760L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 登陆手机号
     */
    private String loginPhone;

    /**
     * 登陆邮箱
     */
    private String loginEmail;

    /**
     * 用户背景图url
     */
    private String backUrl;

    /**
     * 头像url
     */
    private String imagUrl;

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

}

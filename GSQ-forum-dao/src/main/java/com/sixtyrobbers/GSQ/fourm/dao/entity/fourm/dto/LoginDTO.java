package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 10:22
 * Version: V1.0
 * </pre>
 */
@Data
public class LoginDTO implements Serializable {

    private static long serialVersionUID = -6176059697669620415L;

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

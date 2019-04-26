package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 10:11
 * Version: V1.0
 * </pre>
 */
@Getter
@Setter
public class LoginParam implements Serializable {

    private static long serialVersionUID = 164439979081473616L;

    /**
     * 手机
     */
    private String loginPhone;

    /**
     * 邮箱
     */
    private String loginEmail;

    /**
     * 密码
     */
    private String password;

}

package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 09:30
 * Version: V1.0
 * </pre>
 */
@Getter
@Setter
public class LoginReq implements Serializable {

    private static final long serialVersionUID = -1789573111898968288L;

    /**
     * 账号
     */
    public String account;

    /**
     * 密码
     */
    public String password;
}

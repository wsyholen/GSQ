package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 16:22
 * Version: V1.0
 * </pre>
 */
@Getter
@Setter
public class RegisterParam implements Serializable {

    private static long serialVersionUID = 4779531789841433381L;

    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    public RegisterParam() {

    }

    public RegisterParam(String id, String account, String password, String name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
    }
}

package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import lombok.Data;


import java.io.Serializable;

@Data
public class ModifyPasswordParam implements Serializable {

    private static final long serialVersionUID = 2204762209170788087L;

    /**
     * 手机号
     */
    public String loginPhone;

    /**
     * 密码
     */
    private String loginPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 第二次输入新密码
     */
    private String secondPassword;
}

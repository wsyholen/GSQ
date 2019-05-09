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
     * 密码
     */
    private String loginPassword;

}

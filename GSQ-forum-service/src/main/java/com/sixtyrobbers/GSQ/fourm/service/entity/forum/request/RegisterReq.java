package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 16:04
 * Version: V1.0
 * </pre>
 */
@Data
public class RegisterReq implements Serializable {

    private static final long serialVersionUID = -8023277542709842661L;

    /**
     * 账号
     */
    private String account;

    /**
     * 验证码
     */
    private String verificationCode;

}

package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description:    修改密码请求入参
 * @Author:         luoheng
 * @CreateDate:     2019/4/28 18:48
 * @Version:        1.0
 */
@Getter
@Setter
public class ModifyPasswordReq implements Serializable {

    private static final long serialVersionUID = 4157359361431469769L;

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
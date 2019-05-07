package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import java.io.Serializable;
/**
 * @Description:    java类作用描述
 * @Author:         luoheng
 * @CreateDate:     2019/5/5 21:52
 * @Version:        1.0
 */

public class ForgetPasswordParam implements Serializable {

    private static final long serialVersionUID = 6314185502066066995L;
    /**
     * 手机号
     */
    public String loginPhone;

    /**
     * 验证码
     */
    private String verificationCode;
    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 第二次输入新密码
     */
    private String secondPassword;
}

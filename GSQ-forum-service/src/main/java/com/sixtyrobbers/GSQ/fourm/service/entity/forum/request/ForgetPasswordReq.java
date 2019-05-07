package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * @Description:    修改密码请求参数
 * @Author:         luoheng
 * @CreateDate:     2019/5/5 21:09
 * @Version:        1.0
 */
@Data
public class ForgetPasswordReq implements Serializable {
    private static final long serialVersionUID = -9106485237995720463L;
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

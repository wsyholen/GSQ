package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.controller.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.constant.RedisConstant;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ForgetPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.LoginRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.LoginService;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 09:27
 * Version: V1.0
 * </pre>
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Value("120")
    private int validMinute;

    @Value("${redis.valid.second}")
    private int validSecond;

    @Value("${verify.code.pool}")
    private String verifyCodePool;

    /**
     * <pre>
     * Explain: 登陆
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 9:29
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestBody LoginReq loginReq) {
        if (loginReq.getAccount() == null || loginReq.getAccount() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "账号不能为空!");
        }
        if (loginReq.getPassword() == null || loginReq.getPassword() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "密码不能为空！");
        }
        LoginRes result = null;
        try {
            result = loginService.login(loginReq);
            if (result != null) {
                return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_LOGIN_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_LOGIN_SUCCESS.getValue(), result);
            } else {
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LOGIN_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_LOGIN_ERROR.getValue(), "登陆失败,请确定账号或密码!");
            }
        } catch (Exception e) {
            logger.error("登陆--业务异常，param:{},error:{}", JSONObject.toJSONString(loginReq), e.getMessage());
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
        }
    }

    /**
     * @Description: 修改密码
     * @Author: luoheng
     * @CreateDate: 2019/4/28 18:52
     * @Version: 1.0
     */
    @RequestMapping(value = "/V1.0/modifyPasswoed", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult modifyPassword(@RequestBody ModifyPasswordReq modifyPasswordReq) {
        if (modifyPasswordReq.getLoginPhone() == null || modifyPasswordReq.getLoginPhone() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "账号不能为空!");
        }
        if (modifyPasswordReq.getLoginPassword() == null || modifyPasswordReq.getLoginPassword() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "密码不能为空！");
        }
        if (modifyPasswordReq.getNewPassword() == null || modifyPasswordReq.getNewPassword() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "新密码不能为空!");
        }
        if (modifyPasswordReq.getSecondPassword() == null || modifyPasswordReq.getSecondPassword() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "重新输入新密码不能为空!");
        }
        if (!modifyPasswordReq.getNewPassword().equals(modifyPasswordReq.getSecondPassword())) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getValue(), "重新输入的新密码与第一次不一致!");
        }
        String result = null;
        try {
            result = userService.modifyPasswordByLoginPhone(modifyPasswordReq);
            if (result.equals("1")) {
                return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "修改密码成功！");
            } else if (result.equals("0")){
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getValue(), "修改密码失败！");
            }else if (result.equals("请确定账号或密码是否正确！")){
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), result);
            }
            return null;
        } catch (Exception e) {
            logger.error("修改密码--业务异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
        }
    }
   /**
    * @Description:    忘记密码
    * @Author:         luoheng
    * @CreateDate:     2019/5/5 20:55
    * @Version:        1.0
    */
   @RequestMapping(value = "/V1.0/forgetPassword", method = RequestMethod.POST)
   @ResponseBody
   public BaseResult forgetPassword(@RequestBody ForgetPasswordReq forgetPasswordReq) {
       if (forgetPasswordReq.getLoginPhone() == null || forgetPasswordReq.getLoginPhone() == "") {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "手机号不能为空!");
       }
       if (forgetPasswordReq.getVerificationCode() == null || forgetPasswordReq.getVerificationCode() == "") {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "验证码不能为空!");
       }
       if (forgetPasswordReq.getNewPassword() == null || forgetPasswordReq.getNewPassword() == "") {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "新密码不能为空!");
       }
       if (forgetPasswordReq.getSecondPassword() == null || forgetPasswordReq.getSecondPassword() == "") {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "重新输入新密码不能为空!");
       }
       if (!forgetPasswordReq.getNewPassword().equals(forgetPasswordReq.getSecondPassword())) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getValue(), "重新输入的新密码与第一次不一致!");
       }
       String tempVerifyCode = (String) redisTemplate.opsForValue().get(RedisConstant.USER_VERIFY_CODE + forgetPasswordReq.getLoginPhone());
       if (tempVerifyCode == null) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_DUE.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_DUE.getValue(), "验证码过期，请重新获取！");
       }
       if (!forgetPasswordReq.getVerificationCode().equals(tempVerifyCode)) {
           return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getValue(), "验证码错误，请重新输入！");
       } else {
           String result = null;
           try {
               //redisTemplate.delete(RedisConstant.USER_VERIFY_CODE + forgetPasswordReq.getLoginPhone());

               result = userService.forgetPasswordByLoginPhone(forgetPasswordReq);
               if (result.equals("1")){
                   return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "修改密码成功！");
               }else if (result.equals("0")){
                   return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getValue(), "修改密码失败！");
               }else if (result.equals("请确定手机号是否正确!")){
                   return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), result);
               }
               return null;
           } catch (Exception e) {
               logger.error("忘记密码--业务异常，param:{},error:{}", JSONObject.toJSONString(forgetPasswordReq), e.getMessage());
               return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
           }
       }

   }








    /**
     * <pre>
     * Explain: 发送验证码
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 14:07
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/sendVerCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult sendVerCode(@RequestBody LoginReq loginReq) {
        if (loginReq.getAccount() == null || loginReq.getAccount() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "手机号不能为空!");
        }
        String mobile = loginReq.getAccount();
        String code = StringUtil.randomString(verifyCodePool, 6);
        try {
            String result = loginService.sendMessageByMobile(mobile, code);
            if (result.equals("发送失败！") || result == "") {
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_ERROR.getValue(), "短信发送失败！");
            } else {
                redisTemplate.opsForValue().set(RedisConstant.USER_VERIFY_CODE + loginReq.getAccount(), code, validSecond, TimeUnit.SECONDS);
                return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_SUCCESS.getValue(), "短信发送成功," + validMinute + "分钟内有效！");
            }
        } catch (Exception e) {
            logger.error("发送验证码--业务异常，param:{},error:{}", JSONObject.toJSONString(loginReq), e.getMessage());
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
        }
    }

    /**
     * <pre>
     * Explain: 注册
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 16:03
     * Version: V1.0
     * </pre>
     */
    @RequestMapping(value = "/V1.0/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult register(@RequestBody RegisterReq registerReq) {
        if (registerReq.getAccount() == null || registerReq.getAccount() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "手机号不能为空!");
        }
        if (registerReq.getVerificationCode() == null || registerReq.getVerificationCode() == "") {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "验证码不能为空!");
        }
        String tempVerifyCode = (String) redisTemplate.opsForValue().get(RedisConstant.USER_VERIFY_CODE + registerReq.getAccount());
        if (tempVerifyCode == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_DUE.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_DUE.getValue(), "验证码过期，请重新获取！");
        }
        if (!registerReq.getVerificationCode().equals(tempVerifyCode)) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getValue(), "验证码错误，请重新输入！");
        } else {
            try {
                redisTemplate.delete(RedisConstant.USER_VERIFY_CODE + registerReq.getAccount());
                userService.addUser(registerReq);
                return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_REGISTER_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_REGISTER_SUCCESS.getValue(), "注册成功！");
            } catch (Exception e) {
                logger.error("注册--业务异常，param:{},error:{}", JSONObject.toJSONString(registerReq), e.getMessage());
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
            }
        }
    }

}

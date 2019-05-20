package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.constant.RedisConstant;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.LoginService;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



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
    public BaseResult login(@RequestBody LoginReq loginReq) throws Exception {
        BaseResult result = loginService.login(loginReq);
        return result;
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
        String success = null;
        try {
            String param[] = {"loginPhone", "loginPassword", "newPassword", "secondPassword"};
            success = CheckObj.checkObjIsNull(modifyPasswordReq, param);
        } catch (IllegalAccessException e) {
            logger.error("修改密码--判断参数为空异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
        }
        if (success != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), success);
        }
        if (!modifyPasswordReq.getNewPassword().equals(modifyPasswordReq.getSecondPassword())) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getValue(), "重新输入的新密码与第一次不一致!");
        }
        String result = null;
        try {
            result = userService.modifyPasswordByLoginPhone(modifyPasswordReq);
            if (result.equals("1")) {
                return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "修改密码成功！");
            } else if (result.equals("0")) {
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getValue(), "修改密码失败！");
            } else if (result.equals("请确定账号或密码是否正确！")) {
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), result);
            }
            return null;
        } catch (Exception e) {
            logger.error("修改密码--业务异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
        }
    }

    /**
     * @Description: 忘记密码
     * @Author: luoheng
     * @CreateDate: 2019/5/5 20:55
     * @Version: 1.0
     */
    @RequestMapping(value = "/V1.0/forgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult forgetPassword(@RequestBody ModifyPasswordReq modifyPasswordReq) {
        String success = null;
        try {
            String param[] = {"loginPhone", "verificationCode", "newPassword", "secondPassword"};
            //success = CheckObj.checkObjIsNull(modifyPasswordReq,null);
            success = CheckObj.checkObjIsNull(modifyPasswordReq, param);
        } catch (IllegalAccessException e) {
            logger.error("忘记密码--判断参数为空异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
        }
        if (success != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), success);
        }
        if (!modifyPasswordReq.getNewPassword().equals(modifyPasswordReq.getSecondPassword())) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERROR.getValue(), "重新输入的新密码与第一次不一致!");
        }
        String tempVerifyCode = (String) redisTemplate.opsForValue().get(RedisConstant.REGISTER_VERIFY_CODE + modifyPasswordReq.getLoginPhone());
        if (tempVerifyCode == null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_DUE.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_DUE.getValue(), "验证码过期，请重新获取！");
        }
        if (!modifyPasswordReq.getVerificationCode().equals(tempVerifyCode)) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_CODE_ERROR.getValue(), "验证码错误，请重新输入！");
        } else {
            String result = null;
            try {
                redisTemplate.delete(RedisConstant.REGISTER_VERIFY_CODE + modifyPasswordReq.getLoginPhone());
                result = userService.modifyPasswordByLoginPhone(modifyPasswordReq);
                if (result.equals("1")) {
                    return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_SUCCESS.getValue(), "修改密码成功！");
                } else if (result.equals("0")) {
                    return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getCode(), ResponseCodeEnum.ERROR_CODE_MODIFY_ERRORS.getValue(), "修改密码失败！");
                } else if (result.equals("请确定手机号是否正确!")) {
                    return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_PHONE_PASSWORD_ERROR.getValue(), result);
                }
                return null;
            } catch (Exception e) {
                logger.error("忘记密码--业务异常，param:{},error:{}", JSONObject.toJSONString(modifyPasswordReq), e.getMessage());
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
        BaseResult result = loginService.sendMessageByMobile(loginReq);
        return result;
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
        BaseResult result = userService.addUser(registerReq);
        return result;
    }
}


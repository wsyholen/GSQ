package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.ALiSendMessageUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto.LoginDTO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.LoginParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.LoginDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.constant.RedisConstant;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 09:51
 * Version: V1.0
 * </pre>
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginDAO loginDAO;

    @Value("${verify.code.pool}")
    private String verifyCodePool;

    @Value("${redis.valid.minute}")
    private int validMinute;

    @Value("${redis.valid.second}")
    private int validSecond;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * <pre>
     * Explain: 登陆
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 9:52
     * Version: V1.0
     * </pre>
     */
    @Override
    public BaseResult login(LoginReq loginReq) {
        String checkResult = null;
        try {
            checkResult = CheckObj.checkObjIsNull(loginReq, null);
        } catch (IllegalAccessException e) {
            logger.error("登陆--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(loginReq), e.getMessage());
        }
        if (checkResult != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), checkResult);
        }
        LoginParam loginParam = new LoginParam();
        loginParam.setPassword(loginReq.getPassword());
        String account = loginReq.getAccount();
        if (account.indexOf("@") != -1) {
            loginParam.setLoginEmail(account);
        } else {
            loginParam.setLoginPhone(account);
        }
        LoginDTO result = loginDAO.login(loginParam);
        if (result != null) {
            return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SUCCESS.getValue(), result);
        } else {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LOGIN_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_LOGIN_ERROR.getValue(), "登陆失败,请确定账号或密码!");
        }
    }

    /**
     * <pre>
     * Explain: 发送验证码
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 14:24
     * Version: V1.0
     * </pre>
     */
    @Override
    public BaseResult sendMessageByMobile(LoginReq loginReq) {
        String checkResult = null;
        String[] checkParam = {"account"};
        try {
            checkResult = CheckObj.checkObjIsNull(loginReq, checkParam);
        } catch (IllegalAccessException e) {
            logger.error("发送验证码--判断对象为空异常，param:{},error:{}", JSONObject.toJSONString(loginReq), e.getMessage());
        }
        if (checkResult != null) {
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), checkResult);
        }
        //生成验证码
        String code = StringUtil.randomString(verifyCodePool, 6);
        String nodeCode = "SMS_164267594";
        try {
            String data = ALiSendMessageUtil.sendMessage(loginReq.getAccount(), code, nodeCode);
            JSONObject json = JSON.parseObject(data);
            String resultCode = json.getString("Code");
            if (resultCode.equals("OK")) {
                redisTemplate.opsForValue().set(RedisConstant.REGISTER_VERIFY_CODE + loginReq.getAccount(), code, validSecond, TimeUnit.SECONDS);
                return new BaseResult(true, ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_SUCCESS.getCode(), ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_SUCCESS.getValue(), "短信发送成功," + validMinute + "分钟内有效！");
            } else {
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_SEND_MESSAGE_ERROR.getValue(), "短信发送失败！");
            }
        } catch (com.aliyuncs.exceptions.ClientException e) {
            logger.error("发送验证码--业务异常，param:{},error:{}", e.getMessage());
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ERROR.getCode(), ResponseCodeEnum.ERROR_CODE_ERROR.getValue(), "请求失败！");
        }
    }

}

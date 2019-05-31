package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.CheckObj;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.constant.RedisConstant;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;

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

    @Value("${redis.valid.minute}")
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
    public BaseResult modifyPassword(@RequestBody ModifyPasswordReq modifyPasswordReq) throws Exception {
        BaseResult result = userService.modifyPasswordByLoginPhone(modifyPasswordReq);
        return result;
    }

    /**
     * @Description: 忘记密码
     * @Author: luoheng
     * @CreateDate: 2019/5/5 20:55
     * @Version: 1.0
     */
    @RequestMapping(value = "/V1.0/forgetPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult forgetPassword(@RequestBody ModifyPasswordReq modifyPasswordReq) throws Exception {
        redisTemplate.delete(RedisConstant.REGISTER_VERIFY_CODE + modifyPasswordReq.getLoginPhone());
        BaseResult result = userService.modifyPasswordByLoginPhone(modifyPasswordReq);
        return result;
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
    public BaseResult sendVerCode(@RequestBody LoginReq loginReq) throws Exception {
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
    public BaseResult register(@RequestBody RegisterReq registerReq) throws Exception {
        BaseResult result = userService.addUser(registerReq);
        return result;
    }
}


package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sixtyrobbers.GSQ.fourm.common.util.ALiSendMessageUtil;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto.LoginDTO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.LoginParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.LoginDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.LoginRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    /**
     * <pre>
     * Explain: 登陆
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 9:52
     * Version: V1.0
     * </pre>
     */
    @Override
    public LoginRes login(LoginReq loginReq) {
        LoginParam loginParam = new LoginParam();
        loginParam.setPassword(loginReq.getPassword());
        String account = loginReq.getAccount();
        if (account.indexOf("@") != -1){
            loginParam.setLoginEmail(account);
        }else {
            loginParam.setLoginPhone(account);
        }
        LoginDTO loginDTO = loginDAO.login(loginParam);
        LoginRes result = JSON.parseObject(JSON.toJSONString(loginDTO),LoginRes.class);
        return result;
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
    public String sendMessageByMobile(String mobile,String code) {
        String nodeCode = "SMS_164267594";
        String result = null;
        try {
            String data = ALiSendMessageUtil.sendMessage(mobile,code,nodeCode);
            JSONObject json = JSON.parseObject(data);
            String resultCode = json.getString("Code");
            if (resultCode.equals("OK")){
                return "发送成功！";
            }else {
                return "发送失败！";
            }
        } catch (com.aliyuncs.exceptions.ClientException e) {
            logger.error("发送验证码--业务异常，param:{},error:{}", e.getMessage());
        }
        return "";
    }

}

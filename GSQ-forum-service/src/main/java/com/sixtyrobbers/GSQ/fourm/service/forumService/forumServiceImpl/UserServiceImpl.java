package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.oid.OIDGennerator;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ForgetPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.RegisterParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.UserParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.UserDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ForgetPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.UserReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 16:18
 * Version: V1.0
 * </pre>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * <pre>
     * Explain: 添加用户
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 16:18
     * Version: V1.0
     * </pre>
     */
    @Override
    public void addUser(RegisterReq registerReq) {
        String oid = OIDGennerator.getOID();
        String password = registerReq.getAccount().substring(registerReq.getAccount().length() - 6);
        String name = StringUtil.getStringRandom();
        RegisterParam registerParam = new RegisterParam(oid, registerReq.getAccount(), password, name);
        userDAO.addUser(registerParam);
    }

    /**
     * @Description: 修改密码
     * @Author: luoheng
     * @CreateDate: 2019/4/29 18:54
     * @Version: 1.0
     */
    @Override
    public String modifyPasswordByLoginPhone(ModifyPasswordReq modifyPasswordReq) {
        ModifyPasswordParam modifyPasswordParam = JSON.parseObject(JSON.toJSONString(modifyPasswordReq),ModifyPasswordParam.class);
        UserDO userDO = userDAO.findUsersByLoginPhone(modifyPasswordParam);
        if (userDO == null){
            return "请确定账号或密码是否正确！";
        }
        int result = userDAO.modifyPasswordByLoginPhone(modifyPasswordParam);
        return String.valueOf(result);
    }

    /**
     * @Description:    忘记密码
     * @Author:         luoheng
     * @CreateDate:     2019/5/5 21:40
     * @Version:        1.0
     */
    @Override
    public  String forgetPasswordByLoginPhone(ForgetPasswordReq forgetPasswordReq){
        ForgetPasswordParam forgetPasswordParam = JSON.parseObject(JSON.toJSONString(forgetPasswordReq),ForgetPasswordParam.class);
        UserDO userDO = userDAO.forgetFindUsersByLoginPhone(forgetPasswordParam);
        if (userDO == null){
            return "请确定手机号是否正确!";
        }
        int result = userDAO.forgetPasswordByLoginPhone(forgetPasswordParam);
        return String.valueOf(result);
    }

    /**
     * <pre>
     * Explain: 修改背景图片
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 14:24
     * Version: V1.0
     * </pre>
     */
    @Override
    public String updateBackGround(CommonsMultipartFile[] background, UserReq userReq) {
        UserParam userParam = JSON.parseObject(JSON.toJSONString(userReq),UserParam.class);
        UserDO userDO = userDAO.getUser(userParam);
        if (userDO == null){
            return "不存在该用户！";
        }

        return null;
    }


}

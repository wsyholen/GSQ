package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.oid.OIDGennerator;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.RegisterParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.UserDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}

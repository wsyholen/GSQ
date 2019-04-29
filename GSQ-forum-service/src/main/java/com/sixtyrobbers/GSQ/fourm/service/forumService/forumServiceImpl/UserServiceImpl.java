package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.oid.OIDGennerator;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto.LoginDTO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.LoginParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.RegisterParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.LoginDAO;
import com.sixtyrobbers.GSQ.fourm.dao.forum.UserDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.LoginRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.UserService;
import org.apache.ibatis.annotations.Param;
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
     * @Description: 判断用户是否存在
     * @Author: luoheng
     * @CreateDate: 2019/4/29 18:53
     * @Version: 1.0
     */
    @Override
    public boolean isUserDOExist(String loginPhone) {
        UserDO userDO = userDAO.findUsersByLoginPhone(loginPhone);
        if (userDO == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @Description: 根据提供的用户得到密码
     * @Author: luoheng
     * @CreateDate: 2019/4/29 19:04
     * @Version: 1.0
     */
    @Override
    public String getPasswordByLoginPhone(String loginPhone) {
        return userDAO.findUsersByLoginPhone(loginPhone).getLoginPassword();
    }

    /**
     * @Description: 修改密码
     * @Author: luoheng
     * @CreateDate: 2019/4/29 18:54
     * @Version: 1.0
     */

    public void modifyPasswordByLoginPhone(String loginPhone, String newpassword) {
        UserDO userDO = userDAO.findUsersByLoginPhone(loginPhone);
        userDO.setLoginPhone(loginPhone);
        userDO.setLoginPassword(newpassword);
        userDAO.modifyPasswordByLoginPhone(userDO);
    }


}

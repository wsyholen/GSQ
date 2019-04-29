package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 16:16
 * Version: V1.0
 * </pre>
 */
public interface UserService {

    /**
     * <pre>
     * Explain: 添加用户
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 16:15
     * Version: V1.0
     * </pre>
     */
    void addUser(RegisterReq registerReq);

    /**
     * @Description:    判断用户是否存在
     * @Author:         luoheng
     * @CreateDate:     2019/4/29 18:48
     * @Version:        1.0
     */
    boolean isUserDOExist(String loginPhone);

    /**
     * @Description:    根据用户得到密码
     * @Author:         luoheng
     * @CreateDate:     2019/4/29 19:01
     * @Version:        1.0
     */
    String getPasswordByLoginPhone(String loginPhone);

   /**
    * @Description:    修改密码
    * @Author:         luoheng
    * @CreateDate:     2019/4/29 19:14
    * @Version:        1.0
    */
    void modifyPasswordByLoginPhone(String loginPhone,String newpassword);
}

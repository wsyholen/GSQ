package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.RegisterParam;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 16:38
 * Version: V1.0
 * </pre>
 */
public interface UserDAO {

    /**
     * <pre>
     * Explain: 添加用户
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 16:38
     * Version: V1.0
     * </pre>
     */
    void addUser(RegisterParam registerParam);
    /**
     * @Description:    查找用户手机号
     * @Author:         luoheng
     * @CreateDate:     2019/4/29 18:41
     * @Version:        1.0
     */
     UserDO findUsersByLoginPhone(String loginPhone);
    /**
     * @Description:    修改密码
     * @Author:         luoheng
     * @CreateDate:     2019/4/29 18:42
     * @Version:        1.0
     */
     int modifyPasswordByLoginPhone(UserDO userDO);
}

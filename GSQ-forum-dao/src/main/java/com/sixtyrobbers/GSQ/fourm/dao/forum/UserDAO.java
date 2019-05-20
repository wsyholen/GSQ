package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ModifyPasswordParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.RegisterParam;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.UserParam;

import java.util.List;

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
     * <pre>
     * Explain: 查询用户(入参可选：手机号、密码)
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 14:30
     * Version: V1.0
     * </pre>
     */
    UserDO getUser(UserParam userParam);

    /**
     * <pre>
     * Explain: 更新用户信息
     * Author: holennnnnn_
     * Create_Time: 2019/5/10 10:53
     * Version: V1.0
     * </pre>
     */
    void updateUser(UserParam userParam);

    /**
     * @Description:    修改密码查找用户手机号
     * @Author:         luoheng
     * @CreateDate:     2019/4/29 18:41
     * @Version:        1.0
     */
     UserDO findUsersByLoginPhone(ModifyPasswordParam modifyPasswordParam);

    /**
     * @Description:    修改密码
     * @Author:         luoheng
     * @CreateDate:     2019/4/29 18:42
     * @Version:        1.0
     */
     int modifyPasswordByLoginPhone(ModifyPasswordParam modifyPasswordParam);

}

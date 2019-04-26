package com.sixtyrobbers.GSQ.fourm.dao.forum;

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

}

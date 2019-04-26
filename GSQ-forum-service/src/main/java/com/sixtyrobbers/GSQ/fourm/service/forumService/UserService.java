package com.sixtyrobbers.GSQ.fourm.service.forumService;

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

}

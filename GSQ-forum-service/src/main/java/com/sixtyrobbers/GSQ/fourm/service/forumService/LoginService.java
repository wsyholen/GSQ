package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.BaseRequest;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.LoginReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.LoginRes;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 09:47
 * Version: V1.0
 * </pre>
 */
public interface LoginService {

    /**
     * <pre>
     * Explain: 登陆
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 9:51
     * Version: V1.0
     * </pre>
     */
    BaseResult login(LoginReq loginReq);

    /**
     * <pre>
     * Explain: 发送验证码
     * Author: holennnnnn_
     * Create_Time: 2019/4/26 14:23
     * Version: V1.0
     * </pre>
     */
    BaseResult sendMessageByMobile(LoginReq loginReq);

}

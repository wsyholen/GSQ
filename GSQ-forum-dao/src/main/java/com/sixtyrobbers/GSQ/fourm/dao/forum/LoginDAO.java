package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto.LoginDTO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.LoginParam;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 10:21
 * Version: V1.0
 * </pre>
 */
public interface LoginDAO {

    LoginDTO login(LoginParam loginParam);

}

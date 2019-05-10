package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.ServiceResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ModifyPasswordReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.RegisterReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.UserReq;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Map;

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
     * @Description: 修改密码
     * @Author: luoheng
     * @CreateDate: 2019/4/29 19:14
     * @Version: 1.0
     */
    String modifyPasswordByLoginPhone(ModifyPasswordReq modifyPasswordReq);

    /**
     * <pre>
     * Explain: 修改背景图片
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 14:24
     * Version: V1.0
     * </pre>
     */
    ServiceResult updateBackGround(CommonsMultipartFile[] background, UserReq userReq);
}

package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserPictureDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.UserPictureParam;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/10 10:39
 * Version: V1.0
 * </pre>
 */
public interface UserPictureDAO {

    /**
     * <pre>
     * Explain: 添加用户图片
     * Author: holennnnnn_
     * Create_Time: 2019/5/10 10:40
     * Version: V1.0
     * </pre>
     */
    void addUserPicture(UserPictureParam userPictureParam);

    /**
     * <pre>
     * Explain: 查询用户图片
     * Author: holennnnnn_
     * Create_Time: 2019/5/10 13:28
     * Version: V1.0
     * </pre>
     */
    UserPictureDO getUserPicture(UserPictureParam userPictureParam);

    /**
     * <pre>
     * Explain: 更新用户图片
     * Author: holennnnnn_
     * Create_Time: 2019/5/10 13:40
     * Version: V1.0
     * </pre>
     */
    void updateUserPicture(UserPictureParam userPictureParam);

}

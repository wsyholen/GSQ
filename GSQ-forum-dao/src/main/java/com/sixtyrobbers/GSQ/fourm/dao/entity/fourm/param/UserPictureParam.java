package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserPictureDO;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/10 10:35
 * Version: V1.0
 * </pre>
 */
@Data
public class UserPictureParam implements Serializable {

    private static final long serialVersionUID = 3028261831416162200L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 图片分类
     */
    private Integer pictureType;

    /**
     * 图片地址
     */
    private String userPictureUrl;

    public UserPictureParam() {

    }

    public UserPictureParam(String id, String userPictureUrl) {
        this.id = id;
        this.userPictureUrl = userPictureUrl;
    }

    public UserPictureParam(String id, String userId, Integer pictureType, String userPictureUrl) {
        this.id = id;
        this.userId = userId;
        this.pictureType = pictureType;
        this.userPictureUrl = userPictureUrl;
    }
}

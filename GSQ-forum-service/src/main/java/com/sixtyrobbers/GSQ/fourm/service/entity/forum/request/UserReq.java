package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/9 13:50
 * Version: V1.0
 * </pre>
 */
@Data
public class UserReq implements Serializable {

    private static final long serialVersionUID = 1624745017779852895L;

    /**
     * 用户id
     */
    private String userId;

}

package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: luoheng
 * @CreateDate: 2019/5/15 19:55
 * @Version: 1.0
 */
@Data
public class ForeignJurisdictionParam implements Serializable {
    private static final long serialVersionUID = -3244128553897838606L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 私信：0：开放；1：不开放
     */
    private Integer message;

    /**
     * 关注：0：开放；1：不开放
     */
    private Integer attentionliu;

    /**
     * 浏览历史：0：开放；1：不开放
     */
    private Integer browsingHistory;

    /**
     * 我的文章：0：开放；1：不开放
     */
    private Integer myArticle;

    /**
     * 我的关注：0：开放；1：不开放
     */
    private Integer myAttentionliup;

    /**
     * 我的评论：0：开放；1：不开放
     */
    private Integer myComments;

    /**
     * 我的点赞：0：开放；1：不开放
     */
    private Integer myPraise;
}

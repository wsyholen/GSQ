package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户对外权限请求参数
 * @Author: luoheng
 * @CreateDate: 2019/5/9 22:54
 * @Version: 1.0
 */
@Data
public class ForeignJurisdictionReq implements Serializable {

    private static final long serialVersionUID = 1218537313300352771L;

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

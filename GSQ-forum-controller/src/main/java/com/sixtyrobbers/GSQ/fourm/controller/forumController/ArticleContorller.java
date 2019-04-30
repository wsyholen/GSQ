package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.sixtyrobbers.GSQ.fourm.common.util.GSQUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.StringUtil;
import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ArticleReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.Serializable;

/**
 * Created by hekang on 19/4/29.
 */
public class ArticleContorller implements Serializable {
    private static final long serialVersionUID = 3500809046914526540L;
    @RequestMapping(value = "/V1.0/modifyArticle", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult modifyArticle(@RequestBody ArticleReq articleReq) {
        return null;
    }

    /**
     * 校验文章维护接口参数
     * @param articleReq
     * @return
     */
    private BaseResult checkModifyArticle(ArticleReq articleReq){
        String userID = articleReq.getUserID();
        String articleTitle = articleReq.getArticleTitle();
        File picture = articleReq.getPicture();
        String content = articleReq.getContent();
        String typeID = articleReq.getTypeID();
        String jurisdiction = articleReq.getJurisdiction();
        String operateFlag = articleReq.getOperateFlag();
        if (GSQUtil.isNullOrEmpty(userID,true)){

        }
        return null;
    }
}

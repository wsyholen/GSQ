package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.oid.OIDGennerator;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ArticleParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.ArticleDao;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ArticleReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by hekang on 19/5/9.
 */
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void modifyArticle(ArticleReq articleReq) {
        File picture = (File) articleReq.getPicture();
        String pictureID = "";

        ArticleParam articleParam = JSON.parseObject(JSON.toJSONString(articleReq),ArticleParam.class);
        String oid = OIDGennerator.getOID();
        articleParam.setArticleID(oid);
        articleParam.setPictureID(pictureID);
        if ("0".equals(articleReq.getOperateFlag())){
            articleDao.createArticle(articleParam);
        }else {
            articleDao.updateArticle(articleParam);
        }
    }
}

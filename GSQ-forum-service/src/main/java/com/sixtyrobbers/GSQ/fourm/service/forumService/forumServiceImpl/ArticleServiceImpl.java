package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sixtyrobbers.GSQ.fourm.common.util.UploadFileUtil;
import com.sixtyrobbers.GSQ.fourm.common.util.oid.OIDGennerator;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ArticleParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.ArticleDao;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ArticleReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hekang on 19/5/9.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    @Value("${test.user.background.online.path}")
    private String onlinePath;

    @Value("${prd.user.background.path}")
    private String userBackPath;

    @Override
    public void modifyArticle(CommonsMultipartFile[] background,ArticleReq articleReq) {
        ArticleParam articleParam = JSON.parseObject(JSON.toJSONString(articleReq),ArticleParam.class);
        String oid = OIDGennerator.getOID();
        articleParam.setArticleID(oid);
        List<String> pathList = null;
        if ("0".equals(articleReq.getOperateFlag())){
            try {
                pathList = UploadFileUtil.uploadFile(background,userBackPath,onlinePath);
            } catch (IOException e) {
                logger.error("上传图片--业务异常，param:{},error:{}", e.getMessage());
            }
            articleParam.setPictureID(pathList.get(0));
            articleDao.createArticle(articleParam);
        }else {
            try {
                pathList = UploadFileUtil.uploadFile(background,userBackPath,onlinePath);
            } catch (IOException e) {
                logger.error("上传图片--业务异常，param:{},error:{}", e.getMessage());
            }
            articleParam.setPictureID(pathList.get(0));
            articleDao.updateArticle(articleParam);
        }
    }
}

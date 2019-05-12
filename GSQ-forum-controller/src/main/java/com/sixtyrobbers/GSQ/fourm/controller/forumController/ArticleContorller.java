package com.sixtyrobbers.GSQ.fourm.controller.forumController;

import com.sixtyrobbers.GSQ.fourm.common.util.GSQUtil;
import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.controller.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ArticleReq;
import com.sixtyrobbers.GSQ.fourm.service.forumService.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by hekang on 19/4/29.
 */
@RequestMapping("/article")
@Controller
public class ArticleContorller implements Serializable {
    private static final long serialVersionUID = 3500809046914526540L;
    private static final Logger logger = LoggerFactory.getLogger(ArticleContorller.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/V1.0/modifyArticle", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult modifyArticle(@RequestBody ArticleReq articleReq)throws IOException {
        //入参校验
        BaseResult baseResult = checkModifyArticle(articleReq);
        if (baseResult != null){
            return baseResult;
        }
        try {
            //维护文章
            articleService.modifyArticle(articleReq);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            if ("0".equals(articleReq.getOperateFlag())){
                //新增文章失败
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ARTICLE_CREATE.getCode(), ResponseCodeEnum.ERROR_CODE_ARTICLE_CREATE.getValue(), "文章标题不能为空!");
            }else {
                //更新文章失败
                return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_ARTICLE_UPDATE.getCode(), ResponseCodeEnum.ERROR_CODE_ARTICLE_UPDATE.getValue(), "文章标题不能为空!");
            }
        }

        return null;
    }

    /**
     * 校验文章维护接口参数
     * @param articleReq
     * @return
     */
    private BaseResult checkModifyArticle(ArticleReq articleReq) throws IOException{
        String userID = articleReq.getUserID();
        String articleTitle = articleReq.getArticleTitle();
        MultipartFile picture = articleReq.getPicture();
        String content = articleReq.getContent();
        String typeID = articleReq.getTypeID();
        String jurisdiction = articleReq.getJurisdiction();
        String operateFlag = articleReq.getOperateFlag();
        if (GSQUtil.isNullOrEmpty(userID,true)){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "用户ID不能为空!");
        }
        if (GSQUtil.isNullOrEmpty(articleTitle,true)){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "文章标题不能为空!");
        }
        if (GSQUtil.isNullOrEmpty(content,true)){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "文章内容不能为空!");
        }
        if (GSQUtil.isNullOrEmpty(typeID,true)){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "文章分类不能为空!");
        }
        if (GSQUtil.isNullOrEmpty(jurisdiction,true)){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "文章权限不能为空!");
        }
        if (GSQUtil.isNullOrEmpty(operateFlag,true)){
            return new BaseResult(false, ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getCode(), ResponseCodeEnum.ERROR_CODE_LACK_PARAM.getValue(), "操作不能为空!");
        }
        MultipartFile file = (MultipartFile) picture;
        String fileName =  file.getName();
        File file1 = new File(fileName);
        return null;
    }
}

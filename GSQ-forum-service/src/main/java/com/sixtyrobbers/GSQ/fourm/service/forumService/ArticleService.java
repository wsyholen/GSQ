package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ArticleReq;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by hekang on 19/5/9.
 */
public interface ArticleService {
    void modifyArticle(CommonsMultipartFile[] background, ArticleReq articleReq);
}

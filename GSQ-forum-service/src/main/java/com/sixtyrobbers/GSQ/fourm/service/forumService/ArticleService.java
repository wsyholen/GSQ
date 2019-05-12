package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ArticleReq;
import org.springframework.stereotype.Service;

/**
 * Created by hekang on 19/5/9.
 */
@Service
public interface ArticleService {
    void modifyArticle(ArticleReq articleReq);
}

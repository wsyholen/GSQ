package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ArticleParam;

/**
 * Created by hekang on 19/5/11.
 */
public interface ArticleDao {
    void createArticle(ArticleParam articleParam);
    void updateArticle(ArticleParam articleParam);
}

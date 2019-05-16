package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hekang on 19/5/11.
 */
@Data
public class ArticleParam implements Serializable{
    private static final long serialVersionUID = -7841721663923621516L;

    //文章ID
    private String articleID;
    //用户id
    private String userID;
    //文章标题
    private String articleTitle;
    //背景图片
    private String pictureID;
    //文章内容
    private String content;
    //文章分类ID
    private String typeID;
    //文章权限:0：私人；1：开放
    private String jurisdiction;
    //操作区分:0:新增；1：编辑
    private String operateFlag;
}

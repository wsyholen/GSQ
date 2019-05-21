package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;

/**
 * 新增编辑文章请求参数
 * Created by hekang on 19/4/30.
 */
@Getter
@Setter
public class ArticleReq implements Serializable {
    private static final long serialVersionUID = -2539522132892841873L;
    //用户id
    private String userID;
    //文章标题
    private String articleTitle;
    //文章内容
    private String content;
    //文章分类ID
    private String typeID;
    //文章权限:0：私人；1：开放
    private String jurisdiction;
    //操作区分:0:新增；1：编辑
    private String operateFlag;
}

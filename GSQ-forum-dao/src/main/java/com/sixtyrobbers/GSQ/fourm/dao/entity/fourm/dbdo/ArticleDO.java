package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-04-25 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="gsq_forum_article" )
public class ArticleDO  implements Serializable {

	private static final long serialVersionUID =  1096709014802895947L;

	/**
	 * 文章id
	 */
   	@Column(name = "article_id" )
	private String articleId;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 文章背景图id
	 */
   	@Column(name = "article_back_id" )
	private String articleBackId;

	/**
	 * 文章标题
	 */
   	@Column(name = "article_headline" )
	private String articleHeadline;

	/**
	 * 文章内容
	 */
   	@Column(name = "article_content" )
	private String articleContent;

	/**
	 * 文章分类id
	 */
   	@Column(name = "article_type_id" )
	private String articleTypeId;

	/**
	 * 文章权限：0：私人；1：开放
	 */
   	@Column(name = "article_jurisdiction" )
	private Long articleJurisdiction;

	/**
	 * 文章点击次数
	 */
   	@Column(name = "article_trigger" )
	private Long articleTrigger;

	/**
	 * 是否推荐：0：推荐；1：不推荐
	 */
   	@Column(name = "recommend" )
	private Long recommend;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 更新时间
	 */
   	@Column(name = "update_time" )
	private Date updateTime;

	/**
	 * 更新次数
	 */
   	@Column(name = "update_size" )
	private Long updateSize;

	/**
	 * 有效标志位：0：存在；1：删除
	 */
   	@Column(name = "valide_falg" )
	private Long valideFalg;

}

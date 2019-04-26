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
@Table ( name ="gsq_forum_user_jurisdiction" )
public class UserJurisdictionDO  implements Serializable {

	private static final long serialVersionUID =  1022334575796454433L;

   	@Column(name = "id" )
	private String id;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 私信：0：开放；1：不开放
	 */
   	@Column(name = "message" )
	private Integer message;

	/**
	 * 关注：0：开放；1：不开放
	 */
   	@Column(name = "attentionliu" )
	private Integer attentionliu;

	/**
	 * 浏览历史：0：开放；1：不开放
	 */
   	@Column(name = "browsing_history" )
	private Integer browsingHistory;

	/**
	 * 我的文章：0：开放；1：不开放
	 */
   	@Column(name = "my_article" )
	private Integer myArticle;

	/**
	 * 我的关注：0：开放；1：不开放
	 */
   	@Column(name = "my_attentionliup" )
	private Integer myAttentionliup;

	/**
	 * 我的评论：0：开放；1：不开放
	 */
   	@Column(name = "my_comments" )
	private Integer myComments;

	/**
	 * 我的点赞：0：开放；1：不开放
	 */
   	@Column(name = "my_praise" )
	private Integer myPraise;

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

}

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
@Table ( name ="gsq_fourm_article_praise" )
public class ArticlePraiseDO  implements Serializable {

	private static final long serialVersionUID =  8591484117290299155L;

   	@Column(name = "praise_id" )
	private String praiseId;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 文章id
	 */
   	@Column(name = "article_id" )
	private String articleId;

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
	 * 有效标志位：0：有效；1：无效
	 */
   	@Column(name = "valide_flag" )
	private Integer valideFlag;

}

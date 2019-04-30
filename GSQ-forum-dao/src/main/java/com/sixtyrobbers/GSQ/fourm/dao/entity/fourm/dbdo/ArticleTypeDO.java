package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-04-25 
 */

@Data
@ToString
@Entity
@Table ( name ="gsq_forum_article_type" )
public class ArticleTypeDO  implements Serializable {

	private static final long serialVersionUID =  8884636264626718924L;

   	@Column(name = "id" )
	private String id;

	/**
	 * 分类名称
	 */
   	@Column(name = "type_name" )
	private String typeName;

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
	 * 有效标志位：0：存在；1：删除
	 */
   	@Column(name = "valid_flag" )
	private Integer validFlag;

}

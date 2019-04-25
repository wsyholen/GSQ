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
@Table ( name ="gsq_forum_user_picture" )
public class UserPictureDO  implements Serializable {

	private static final long serialVersionUID =  2428609756451171144L;

   	@Column(name = "id" )
	private String id;

	/**
	 * 图片分类
	 */
   	@Column(name = "picture_type" )
	private String pictureType;

	/**
	 * 图片地址
	 */
   	@Column(name = "user_picture_url" )
	private String userPictureUrl;

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
   	@Column(name = "vaild_flag" )
	private Long vaildFlag;

}

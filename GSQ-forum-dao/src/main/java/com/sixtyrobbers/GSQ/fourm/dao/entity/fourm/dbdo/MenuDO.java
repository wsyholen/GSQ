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
@Table ( name ="gsq_forum_menu" )
public class MenuDO  implements Serializable {

	private static final long serialVersionUID =  554185360927060911L;

	/**
	 * 菜单id
	 */
   	@Column(name = "menu_id" )
	private Integer menuId;

	/**
	 * 菜单名称
	 */
   	@Column(name = "menu_name" )
	private String menuName;

	/**
	 * 菜单url
	 */
   	@Column(name = "menu_url" )
	private String menuUrl;

	/**
	 * 图标url
	 */
	@Column(name = "icon_url" )
	private String iconUrl;

	/**
	 * 父级id
	 */
   	@Column(name = "parent_id" )
	private Integer parentId;

	/**
	 * 排序
	 */
	@Column(name = "order" )
	private Integer order;

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

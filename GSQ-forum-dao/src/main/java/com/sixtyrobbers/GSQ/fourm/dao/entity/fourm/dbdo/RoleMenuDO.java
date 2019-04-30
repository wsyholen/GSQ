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
@Table ( name ="gsq_forum_role_menu" )
public class RoleMenuDO  implements Serializable {

	private static final long serialVersionUID =  8250872642594720998L;

   	@Column(name = "id" )
	private String id;

	/**
	 * 角色id
	 */
   	@Column(name = "role_id" )
	private String roleId;

	/**
	 * 菜单节点id
	 */
   	@Column(name = "menu_node_numer" )
	private String menuNodeNumer;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

}

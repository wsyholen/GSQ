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
@Table ( name ="gsq_fourm_user" )
public class UserDO  implements Serializable {

	private static final long serialVersionUID =  8866090497433937663L;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 登陆手机号
	 */
   	@Column(name = "login_phone" )
	private String loginPhone;

	/**
	 * 登陆邮箱
	 */
   	@Column(name = "login_email" )
	private String loginEmail;

	/**
	 * 登陆密码
	 */
   	@Column(name = "login_password" )
	private String loginPassword;

	/**
	 * 用户背景图id
	 */
   	@Column(name = "back_id" )
	private String backId;

	/**
	 * 头像id
	 */
   	@Column(name = "imag_id" )
	private String imagId;

	/**
	 * 昵称
	 */
   	@Column(name = "user_name" )
	private String userName;

	/**
	 * 性别:0:男；1:女
	 */
   	@Column(name = "user_sex" )
	private Integer userSex;

	/**
	 * 个人介绍
	 */
   	@Column(name = "user_introduce" )
	private String userIntroduce;

	/**
	 * 爱好
	 */
   	@Column(name = "user_hobby" )
	private String userHobby;

	/**
	 * 职业
	 */
   	@Column(name = "user_occupation" )
	private String userOccupation;

	/**
	 * 最后登陆时间
	 */
   	@Column(name = "last_login" )
	private String lastLogin;

	/**
	 * 最后登出时间
	 */
   	@Column(name = "last_logout" )
	private String lastLogout;

	/**
	 * 登陆ip
	 */
   	@Column(name = "login_ip" )
	private String loginIp;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private String createTime;

	/**
	 * 创建程序名
	 */
   	@Column(name = "create_pgm" )
	private String createPgm;

	/**
	 * 更新时间
	 */
   	@Column(name = "update_time" )
	private String updateTime;

	/**
	 * 更新程序名
	 */
   	@Column(name = "update_pgm" )
	private String updatePgm;

	/**
	 * 有效标志位：0：存在；1：删除
	 */
   	@Column(name = "valid_flag" )
	private Integer validFlag;

}

package com.yf.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_USER",autoIncrement="userId",pkId="userId")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 803195047515343623L;
	
	@Column(columnName="USER_ID")
	private Long userId;
	
	@Column(columnName="USER_NAME")
	private String userName;
	
	@Column(columnName="USER_ORDER")
	private Integer userOrder;
	
	@Column(columnName="USER_TYPE")
	private Integer userType;
	
	@Column(columnName="USER_STATUS")
	private Integer userStatus;
	
	@Column(columnName="USER_PWD")
	private String userPwd;
	
	@Column(columnName="CREATE_TIME")
	private Date createTime;
	
	@Column(columnName="UPDATE_TIME")
	private Date updateTime;
	
	@Column(columnName="REAL_NAME")
	private String realName;
	
	@Column(columnName="EMAIL")
	private String email;
	
	@Column(columnName="MOBILE")
	private String mobile;
	
	@Column(columnName="SEX")
	private Integer sex;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Integer getUserOrder() {
		return this.userOrder;
	}

	public void setUserOrder(Integer userOrder) {
		this.userOrder = userOrder;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd == null ? null : userPwd.trim();
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}

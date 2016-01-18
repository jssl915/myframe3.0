package com.yf.system.entity;

import java.io.Serializable;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_ROLE_USER",autoIncrement="roleUserId",pkId="roleUserId")
public class SysRoleUser implements Serializable {
	
	private static final long serialVersionUID = 1160097577626992986L;
	@Column
	private Long roleUserId;
	@Column
	private Long userId;
	@Column
	private Long roleId;

	public Long getRoleUserId() {
		return this.roleUserId;
	}

	public void setRoleUserId(Long roleUserId) {
		this.roleUserId = roleUserId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}

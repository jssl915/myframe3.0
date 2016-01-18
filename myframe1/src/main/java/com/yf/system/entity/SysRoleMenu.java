package com.yf.system.entity;

import java.io.Serializable;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_ROLE_MENU",autoIncrement="roleMenuId",pkId="roleMenuId")
public class SysRoleMenu implements Serializable {
	
	private static final long serialVersionUID = -2088878484963884499L;
	@Column
	private Long roleMenuId;
	@Column
	private Long roleId;
	@Column
	private Long menuId;

	public Long getRoleMenuId() {
		return this.roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}

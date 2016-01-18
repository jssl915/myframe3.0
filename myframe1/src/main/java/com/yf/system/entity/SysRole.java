package com.yf.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_ROLE",autoIncrement="roleId",pkId="roleId")
public class SysRole implements Serializable {
	
	private static final long serialVersionUID = -2441363411571186053L;
	@Column
	private Long roleId;
	@Column
	private String roleName;
	@Column
	private String roleDesc;
	@Column
	private Integer roleOrder;
	@Column
	private Integer roleType;
	@Column
	private Integer roleStatus;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc == null ? null : roleDesc.trim();
	}

	public Integer getRoleOrder() {
		return this.roleOrder;
	}

	public void setRoleOrder(Integer roleOrder) {
		this.roleOrder = roleOrder;
	}

	public Integer getRoleType() {
		return this.roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getRoleStatus() {
		return this.roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
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
}

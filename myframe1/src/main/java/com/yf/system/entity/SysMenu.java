package com.yf.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_MENU",autoIncrement="menuId",pkId="menuId")
public class SysMenu implements Serializable {
	
	private static final long serialVersionUID = -1477424495390058816L;
	@Column
	private Long menuId;
	@Column
	private String menuName;
	@Column
	private String menuDesc;
	@Column
	private String menuUrl;
	@Column
	private Long menuPid;
	@Column
	private Integer menuType;
	@Column
	private Integer menuStatus;
	@Column
	private Integer menuLevel;
	@Column
	private String menuIcon;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;
	@Column
	private Integer menuOrder;

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc == null ? null : menuDesc.trim();
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Long getMenuPid() {
		return this.menuPid;
	}

	public void setMenuPid(Long menuPid) {
		this.menuPid = menuPid;
	}

	public Integer getMenuType() {
		return this.menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuStatus() {
		return this.menuStatus;
	}

	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Integer getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
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

	public Integer getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
}

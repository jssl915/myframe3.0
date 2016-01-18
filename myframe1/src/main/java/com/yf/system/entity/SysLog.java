package com.yf.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.cykj.grcloud.mybatis.annotation.Column;
import com.cykj.grcloud.mybatis.annotation.Table;

@Table(tabName="T_S_LOG",autoIncrement="logId",pkId="logId")
public class SysLog implements Serializable {
	
	private static final long serialVersionUID = 2216372364220040522L;

	@Column
	private Long logId;

	@Column
	private String actionUrl;

	@Column
	private Date logTime;

	@Column
	private String userIp;

	@Column
	private Long userId;

	@Column
	private String logDesc;

	@Column
	private String userName;
	
	private String controllerName; // 控制器名称 
	private String controllerMethod; // 控制器方法名称 
	private Long processTime; // 响应时间

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl == null ? null : actionUrl.trim();
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp == null ? null : userIp.trim();
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogDesc() {
		return this.logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc == null ? null : logDesc.trim();
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getControllerMethod() {
		return controllerMethod;
	}

	public void setControllerMethod(String controllerMethod) {
		this.controllerMethod = controllerMethod;
	}

	public Long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}
	
	
}

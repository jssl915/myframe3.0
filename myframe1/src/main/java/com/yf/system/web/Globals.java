package com.yf.system.web;
 

public class Globals implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2620073317901561140L;

	private Globals() {
	}

	/**
	 * the instance name in
	 * HttpSession(com.cykj.grcloud.system.entity.SysUser)
	 */
	public static final String USER_INFO = "com.cykj.grcloud.system.entity.SysUser";
	
	/**
	 * 重复提交token
	 */
	public static final String TOKEN = "com_grcloud_token";
	/**
	 * 重复提交跳转页面
	 */
	public static final String DEFAULT_TOKEN_MSG_JSP = "RepeatSubmitInfo.jsp";
	
	
	public static final String OP_MSG_KEY = "com.cykj.request.sysOpLog.msg.key";
	

}


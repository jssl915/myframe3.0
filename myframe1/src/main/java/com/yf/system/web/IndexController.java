package com.yf.system.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yf.system.entity.SysUser;
import com.yf.system.service.SysMenuService;
import com.yf.system.service.SysUserService;
import com.yf.util.MD5Encoder;
import com.yf.web.BaseController;
import com.yf.web.Constant;
@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserService sysUserService;
	@RequestMapping(value = "")
	public String index(HttpServletRequest req, HttpServletResponse res){
		return "index";
	}
	@RequestMapping(value = "init")
	public String init(HttpServletRequest req, HttpServletResponse res){
		return "main";
	}
	@RequestMapping(value = "tree")
	public void tree(HttpServletRequest request, HttpServletResponse response) {
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constant.SESSION_USER);
		String menuTree = sysMenuService.listUserTree(Long.valueOf(sysUser.getUserId()),false);
		writeToPage(menuTree, response);
	}
	@RequestMapping(value = "password")
	public String password(){
		return "password";
	}
	@RequestMapping(value = "checkUserPwd")
	public void checkUserPwd(HttpServletRequest request,PrintWriter out){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constant.SESSION_USER);
		String password = MD5Encoder.encode(request.getParameter("password"));
		String msg = "操作成功";
		boolean result = false;
		if(!password.equals(sysUser.getUserPwd())){
			result =true;
			msg = "原始密码不正确";
		}
		ajaxJsonResponse(out, result, msg);
	}
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request){
		String userPwd = request.getParameter("newPwd");
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constant.SESSION_USER);
		sysUser.setUserPwd(userPwd);
		sysUserService.updateUserPwd(sysUser);
		return "success";
	}
}

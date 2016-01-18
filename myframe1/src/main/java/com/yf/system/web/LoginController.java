package com.yf.system.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yf.system.entity.SysUser;
import com.yf.system.service.SysUserService;
import com.yf.util.JSONUtils;
import com.yf.util.MD5Encoder;
import com.yf.web.BaseController;
import com.yf.web.Constant;
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController{
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "")
	public String login(HttpServletRequest request){
		
		return "login";
	}
	
	@RequestMapping(value = "in")
	public void in(HttpServletRequest request,HttpServletResponse response,SysUser user){
		Map<String,String>resultMap = new HashMap<String,String>();
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("userName", user.getUserName());
		condititon.put("userStatus", 1);
		List<SysUser> userList = sysUserService.findEntitysByCondition(condititon);
		if(userList.size()==0){
			resultMap.put("success", "0");
			resultMap.put("msg", "用户名不存在");
		}else{
			SysUser newUser = userList.get(0);
			if(!newUser.getUserPwd().equals(MD5Encoder.encode(user.getUserPwd()))){
				resultMap.put("success", "0");
				resultMap.put("msg", "用户名密码错误");
			}else{
				resultMap.put("success", "1");
				resultMap.put("msg", "登录成功");
				request.getSession().setAttribute(Constant.SESSION_USER, newUser);
				request.getSession().setMaxInactiveInterval(30*60);
			}
		}
		writeToPage(JSONUtils.toJson(resultMap), response);
	}
	
	@RequestMapping(value = "out")
	public String out(HttpServletRequest request) throws Exception{
		request.getSession().removeAttribute(Constant.SESSION_USER);
		return "login";
	}
}

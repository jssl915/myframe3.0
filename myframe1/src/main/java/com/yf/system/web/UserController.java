package com.yf.system.web;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cykj.grcloud.mybatis.page.PageObject;
import com.yf.system.entity.SysUser;
import com.yf.system.service.SysDictService;
import com.yf.system.service.SysUserService;
import com.yf.util.JSONUtils;
import com.yf.util.SysProperties;
import com.yf.web.BaseController;

@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDictService sysDictService;
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		Map<String,String>sexMap = sysDictService.getDetailValueMap("性别");
		request.setAttribute("initPassword", SysProperties.getProperty("INIT_PWD"));
		request.setAttribute("sexMap", JSONUtils.toJson(sexMap));
		request.setAttribute("sexCombo", JSONUtils.toCombo(sexMap));
		return "system/user/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysUser user) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"USER_ORDER asc");
		po.getCondition().put("userNameLike", user.getUserName());
		po.getCondition().put("realName", user.getRealName());
		po.getCondition().put("userStatus", user.getUserStatus());
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysUserService.getGridDataModelByCondition(po)), response);
	}
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request){
		Map<String,String>sexMap = sysDictService.getDetailValueMap("性别");
		request.setAttribute("sexMap", sexMap);
		request.setAttribute("initPassword", SysProperties.getProperty("INIT_PWD"));
		return "system/user/add";
	}
	@RequestMapping(value = "insert")
	public void insert(HttpServletRequest request,PrintWriter out,SysUser user){
		sysUserService.insert(user);
		ajaxJsonResponse(out, true, "操作成功");
	}
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long userId = Long.valueOf(request.getParameter("userId"));
		SysUser sUser = sysUserService.findById(userId);
		request.setAttribute("sUser", sUser);
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", statusMap);
		Map<String,String>sexMap = sysDictService.getDetailValueMap("性别");
		request.setAttribute("sexMap", sexMap);
		return "system/user/edit";
	}
	
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,PrintWriter out,SysUser user){
		SysUser sysUser = sysUserService.findById(user.getUserId());
		sysUser.setEmail(user.getEmail());
		sysUser.setMobile(user.getMobile());
		sysUser.setRealName(user.getRealName());
		sysUser.setUserName(user.getUserName());
		sysUser.setSex(user.getSex());
		sysUser.setUserOrder(user.getUserOrder());
		sysUser.setUserType(user.getUserType());
		sysUser.setUserStatus(user.getUserStatus());
		sysUserService.updateById(sysUser);
		ajaxJsonResponse(out, true, "操作成功");
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,PrintWriter out){
		log.debug("method: delete() ");
		String ids = request.getParameter("ids");
		String[] aIds = ids.split(",");
		sysUserService.deleteUserByIds(aIds);
		ajaxJsonResponse(out, true, "操作成功");
	}
	@RequestMapping(value = "initPwd")
	public void initPwd(HttpServletRequest request,PrintWriter out){
		log.debug("method: initPwd() ");
		String msg = "操作成功";
		boolean result = true;
		try {
			String ids = request.getParameter("ids");
			String[] aId = ids.split(",");
			for(String id:aId){
				SysUser sUser = sysUserService.findById(Long.valueOf(id));
				sUser.setUserId(Long.valueOf(id));
				sUser.setUserPwd(SysProperties.getProperty("INIT_PWD"));
				sUser.setUpdateTime(new Date());
				sysUserService.updateUserPwd(sUser);
			}
		} catch (Exception e) {
			msg = "系统发生异常！";
			result = false;
		}
		ajaxJsonResponse(out, result, msg);
	}
	
	@RequestMapping(value = "checkUserName")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysUser user) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("userName", user.getUserName());
		condititon.put("notUserId", user.getUserId());
		int flag = sysUserService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
	
}

package com.yf.system.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cykj.grcloud.mybatis.page.PageObject;
import com.yf.system.entity.SysRole;
import com.yf.system.entity.SysRoleMenu;
import com.yf.system.entity.SysRoleUser;
import com.yf.system.entity.SysUser;
import com.yf.system.service.SysDictService;
import com.yf.system.service.SysMenuService;
import com.yf.system.service.SysRoleService;
import com.yf.system.service.SysUserService;
import com.yf.util.HypyUtil;
import com.yf.util.JSONUtils;
import com.yf.web.BaseController;

@Controller
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {
	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDictService sysDictService;
	
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		return "system/role/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysUser user) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"ROLE_ORDER asc");
		po.getCondition().put("roleNameLike", request.getParameter("roleName"));
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysRoleService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request){
		return "system/role/add";
	}
	
	@RequestMapping(value = "insert")
	public void insert(HttpServletRequest request,PrintWriter out,SysRole sRole){
		sRole.setCreateTime(new Date());
		sRole.setUpdateTime(new Date());
		sRole.setRoleStatus(1);
		sysRoleService.insert(sRole);
		ajaxJsonResponse(out, true, "操作成功");
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		SysRole sRole = sysRoleService.findById(roleId);
		request.setAttribute("sRole", sRole);
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		return "system/role/edit";
	}
	
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,PrintWriter out,SysRole sRole){
		sRole.setUpdateTime(new Date());
		sysRoleService.mergeById(sRole);
		ajaxJsonResponse(out, true, "操作成功");
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,PrintWriter out){
		log.debug("method: delete() ");
		String ids = request.getParameter("ids");
		String[] aIds = ids.split(",");
		List<String>idList = Arrays.asList(aIds);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idList", idList);
		sysRoleService.removeByCondition(map);
		ajaxJsonResponse(out, true, "操作成功");
	}
	
	@RequestMapping(value = "checkRoleName")
	public void checkRoleName(HttpServletRequest request,HttpServletResponse response,SysRole role) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleName", role.getRoleName());
		condititon.put("notRoleId", role.getRoleId());
		int flag = sysRoleService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
	
	@RequestMapping(value = "bindPower")
	public String bindPower(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		List<SysRoleMenu> sRoleMenu = sysRoleService.selectRoleMenuByCondition(roleId);
		request.setAttribute("roleId", roleId);
		request.setAttribute("sRoleMenuJson", JSONUtils.toJson(sRoleMenu));
		String resourceTree = sysMenuService.listTree(true,true);
		request.setAttribute("resourceTree", resourceTree);
		
		List<SysRoleUser> sUserRoleList = sysRoleService.selectSUserRoleByCondition(roleId);
		Map<String, Object> condition = new HashMap<String, Object>();
		List<SysUser>sUserList = sysUserService.findEntitysByCondition(condition);
		request.setAttribute("roleId", roleId);
		request.setAttribute("sUserRoleList", JSONUtils.toJson(sUserRoleList));
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String letter[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(int i=0;i<letter.length;i++){
			List<HashMap<String,String>>lUserList = new ArrayList<HashMap<String,String>>();
			for(int j=0;j<sUserList.size();j++){
				SysUser sUser = sUserList.get(j);
				String userName = sUser.getUserName();
				String l = HypyUtil.cn2py(userName.substring(0,1));//用户名首字母
				HashMap<String,String> m = new HashMap<String,String>();
				m.put("id", sUser.getUserId().toString());
				m.put("name", userName+"/"+sUser.getRealName());
				if(letter[i].equals(l)){
					lUserList.add(m);
				}
			}
			if(lUserList.size()>0){
				dataMap.put(letter[i], lUserList);
			}
		}
		request.setAttribute("userData", JSONUtils.toJson(dataMap));
		return "system/role/bindPower";
	}
	
	@RequestMapping(value = "loadResourceTree")
	public void loadResourceTree(HttpServletRequest request, HttpServletResponse response){
		String resourceTree = sysMenuService.listTree(true,true);
		writeToPage(resourceTree, response);
	}
	
	@RequestMapping(value = "bindMenu")
	public void bindMenu(HttpServletRequest request,PrintWriter out){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String menuIds = request.getParameter("menuIds");
		if(menuIds!=null&&menuIds!=""){
			String aMenuIds[] = menuIds.split(",");
			sysRoleService.bindRoleMenu(roleId, aMenuIds);
		}
		ajaxJsonResponse(out, true, "操作成功");
	}
	@RequestMapping(value = "userDialog")
	public String userDialog(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		List<SysRoleUser> sUserRoleList = sysRoleService.selectSUserRoleByCondition(roleId);
		Map<String, Object> condition = new HashMap<String, Object>();
		List<SysUser>sUserList = sysUserService.findEntitysByCondition(condition);
		request.setAttribute("roleId", roleId);
		request.setAttribute("sUserRoleList", JSONUtils.toJson(sUserRoleList));
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String letter[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(int i=0;i<letter.length;i++){
			List<HashMap<String,String>>lUserList = new ArrayList<HashMap<String,String>>();
			for(int j=0;j<sUserList.size();j++){
				SysUser sUser = sUserList.get(j);
				String userName = sUser.getUserName();
				String l = HypyUtil.cn2py(userName.substring(0,1));//用户名首字母
				HashMap<String,String> m = new HashMap<String,String>();
				m.put("id", sUser.getUserId().toString());
				m.put("name", userName+"/"+sUser.getRealName());
				if(letter[i].equals(l)){
					lUserList.add(m);
				}
			}
			if(lUserList.size()>0){
				dataMap.put(letter[i], lUserList);
			}
		}
		request.setAttribute("userData", JSONUtils.toJson(dataMap));
		return "system/role/roleUser";
	}
	
	@RequestMapping(value = "bindUser")
	public void bindUser(HttpServletRequest request,PrintWriter out){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String userIds = request.getParameter("userIds");
		if(userIds!=null&&userIds!=""){
			String aUserIds[] = userIds.split(",");
			sysRoleService.bindRoleUser(roleId, aUserIds);
		}
		ajaxJsonResponse(out, true, "操作成功");
	}
	
}

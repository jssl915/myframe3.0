package com.yf.system.web;

import java.io.PrintWriter;
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
import com.yf.system.entity.SysMenu;
import com.yf.system.service.SysDictService;
import com.yf.system.service.SysMenuService;
import com.yf.util.JSONUtils;
import com.yf.web.BaseController;

@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
	Logger log = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysDictService sysDictService;
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", JSONUtils.toJson(statusMap));
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		request.setAttribute(Globals.OP_MSG_KEY,"菜单管理功能");
		return "system/menu/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysMenu menu) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"MENU_LEVEL asc,MENU_ORDER asc");
		po.getCondition().put("pMenuId", menu.getMenuId());
		po.getCondition().put("menuNameLike", menu.getMenuName());
		po.getCondition().put("menuLevel", menu.getMenuLevel());
		po.getCondition().put("menuStatus", menu.getMenuStatus());
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysMenuService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request,HttpServletResponse response){
		Long menuPid = Long.valueOf(request.getParameter("menuPid"));
		SysMenu sMenuP = sysMenuService.findById(menuPid);
		request.setAttribute("sMenuP", sMenuP);
		return "system/menu/add";
	}
	
	@RequestMapping(value = "insert")
	public void insert(HttpServletRequest request,PrintWriter out,SysMenu menu){
		Long menuPid = Long.valueOf(request.getParameter("menuPid"));
		Integer menuLevel = Integer.valueOf(menu.getMenuLevel())+1;
		menu.setMenuPid(menuPid);
		menu.setMenuLevel(menuLevel);
		menu.setMenuStatus(1);
		menu.setCreateTime(new Date());
		menu.setUpdateTime(new Date());
		sysMenuService.insert(menu);
		ajaxJsonResponse(out, true, "操作成功");
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long menuId = Long.valueOf(request.getParameter("menuId"));
		SysMenu sMenu = sysMenuService.findById(menuId);
		SysMenu sMenuP = sysMenuService.findById(Long.valueOf(sMenu.getMenuPid()));
		request.setAttribute("sMenu", sMenu);
		request.setAttribute("sMenuP", sMenuP);
		Map<String,String>statusMap = sysDictService.getDetailValueMap("状态");
		request.setAttribute("statusCombo", JSONUtils.toCombo(statusMap));
		return "system/menu/edit";
	}
	
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,PrintWriter out,SysMenu sMenu){
		sMenu.setUpdateTime(new Date());
		sysMenuService.mergeById(sMenu);
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
		sysMenuService.removeByCondition(map);
		ajaxJsonResponse(out, true, "操作成功");
	}
	
	@RequestMapping(value = "tree")
	public void tree(HttpServletRequest request, HttpServletResponse response){
		String resourceTree = sysMenuService.listTree(true,false);
		writeToPage(resourceTree, response);
	}
	
	@RequestMapping(value = "checkMenuName")
	public void checkMenuName(HttpServletRequest request,HttpServletResponse response,SysMenu menu) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("menuName", menu.getMenuName());
		condititon.put("notMenuId", menu.getMenuId());
		int flag = sysMenuService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
	
	@RequestMapping(value = "getMenu")
	public void getMenu(HttpServletRequest request, HttpServletResponse response){
		Long menuId = Long.valueOf(request.getParameter("menuId"));
		SysMenu sysMenu = sysMenuService.findById(menuId);
		writeToPage(JSONUtils.toJson(sysMenu), response);
	}
}

package com.yf.system.web;

import java.io.PrintWriter;
import java.util.Arrays;
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
import com.yf.system.entity.SysDict;
import com.yf.system.service.SysDictService;
import com.yf.util.JSONUtils;
import com.yf.web.BaseController;

@Controller
@RequestMapping(value = "/system/dict")
public class DictController extends BaseController {
	Logger log = LoggerFactory.getLogger(DictController.class);
	@Autowired
	private SysDictService sysDictService;
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		request.setAttribute("statusMap", JSONUtils.toJson(sysDictService.getDetailValueMap("状态")));
		return "system/dict/init";
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public void list(HttpServletRequest request,HttpServletResponse response,SysDict sDict) {
		log.debug("method: list() ");
		PageObject po = getPageObject(request,"UPDATE_TIME desc");
		po.getCondition().put("dictNameLike", sDict.getDictName());
		po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
		po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
		po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
		po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
		writeToPage(JSONUtils.toJson(sysDictService.getGridDataModelByCondition(po)), response);
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request,HttpServletResponse response){
		return "system/dict/add";
	}
	
	@RequestMapping(value = "insert")
	public void insert(HttpServletRequest request,PrintWriter out,SysDict sDict){
		sysDictService.insert(sDict);
		ajaxJsonResponse(out, true, "操作成功");
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long dictId = Long.valueOf(request.getParameter("dictId"));
		SysDict sDict = sysDictService.findById(dictId);
		request.setAttribute("sDict", sDict);
		request.setAttribute("statusMap", sysDictService.getDetailValueMap("状态"));
		return "system/dict/edit";
	}
	
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request,PrintWriter out,SysDict sDict){
		sysDictService.mergeById(sDict);
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
		sysDictService.removeByCondition(map);
		ajaxJsonResponse(out, true, "操作成功");
	}
	@RequestMapping(value = "checkDictName")
	public void checkDictName(HttpServletRequest request,HttpServletResponse response,SysDict dict) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictName", dict.getDictName());
		condititon.put("notDictId", dict.getDictId());
		int flag = sysDictService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
}

package com.yf.system.service;

import java.util.Map;

import com.cykj.grcloud.mybatis.GenericBase.BaseService;
import com.cykj.grcloud.remote.service.RemoteService;
import com.yf.system.entity.SysDict;

@RemoteService
public interface SysDictService extends BaseService<SysDict,Long>{
	
	public Map<String, String> getDetailNameMap(String dictName);//得到明细map<name,value>
	
	public Map<String, String> getDetailValueMap(String dictName);//得到明细map<value,name>
	
	public String getDetailValue(String dictName,String detailName);//得到明细value
}

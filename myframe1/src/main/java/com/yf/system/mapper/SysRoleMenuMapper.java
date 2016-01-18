package com.yf.system.mapper;

import java.util.Map;

import com.cykj.grcloud.mybatis.GenericBase.GenericMapper;
import com.yf.system.entity.SysRoleMenu;

public interface SysRoleMenuMapper extends GenericMapper<SysRoleMenu,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}

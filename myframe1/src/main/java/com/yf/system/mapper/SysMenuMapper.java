package com.yf.system.mapper;

import java.util.List;
import java.util.Map;

import com.cykj.grcloud.mybatis.GenericBase.GenericMapper;
import com.yf.system.entity.SysMenu;

public interface SysMenuMapper extends GenericMapper<SysMenu, Long> {
	
	public int deleteByCondition(Map<String, Object> condition);
	
	public List<SysMenu> getAllMenus(Long userId);

}

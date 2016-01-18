package com.yf.system.mapper;

import java.util.Map;

import com.cykj.grcloud.mybatis.GenericBase.GenericMapper;
import com.yf.system.entity.SysRoleUser;

public interface SysRoleUserMapper extends GenericMapper<SysRoleUser,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}

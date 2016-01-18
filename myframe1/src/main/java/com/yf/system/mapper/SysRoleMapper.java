package com.yf.system.mapper;

import java.util.List;

import com.cykj.grcloud.mybatis.GenericBase.GenericMapper;
import com.yf.system.entity.SysRole;

public interface SysRoleMapper extends GenericMapper<SysRole,Long>{

	public List<SysRole>selectRolesByUserId(Long userId);
}

package com.yf.system.service;

import java.util.List;

import com.cykj.grcloud.mybatis.GenericBase.BaseService;
import com.cykj.grcloud.remote.service.RemoteService;
import com.yf.system.entity.SysRole;
import com.yf.system.entity.SysRoleMenu;
import com.yf.system.entity.SysRoleUser;

@RemoteService
public interface SysRoleService extends BaseService<SysRole,Long>{
	
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId);	
	
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId);	
	
	public void  bindRoleMenu(Long roleId,String[] menuIds);
	
	public void  bindRoleUser(Long roleId,String[] userIds);
	
	public  void  deleteRoleByIds(String[] roleIds);
	
	public List<SysRole> selectRolesByUserId(Long userId);
	
}

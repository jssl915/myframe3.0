package com.yf.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cykj.grcloud.mybatis.GenericBase.BaseServiceImpl;
import com.yf.system.entity.SysRole;
import com.yf.system.entity.SysRoleMenu;
import com.yf.system.entity.SysRoleUser;
import com.yf.system.mapper.SysRoleMapper;
import com.yf.system.mapper.SysRoleMenuMapper;
import com.yf.system.mapper.SysRoleUserMapper;

@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long>
		implements SysRoleService {
	
	@Autowired
	private SysRoleMenuMapper sRoleMenuMapper;
	@Autowired
	private SysRoleUserMapper sRoleUserMapper;	
	@Autowired
	private SysRoleMapper sRoleMapper;

	@Autowired
	public void setMapper(SysRoleMapper mapper) {
		setGenericMapper(mapper);
	}

	@Override
	public List<SysRoleMenu> selectRoleMenuByCondition(Long roleId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		return sRoleMenuMapper.findEntitysByCondition(condititon);
	}

	
	@Override
	public List<SysRoleUser> selectSUserRoleByCondition(Long roleId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		return sRoleUserMapper.findEntitysByCondition(condititon);
	}
		
	public List<SysRole> selectRolesByUserId(Long userId){
		return sRoleMapper.selectRolesByUserId(userId);
	}
	
	
    public void  bindRoleMenu(Long roleId,String[] menuIds){
    	Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("roleId", roleId);
		sRoleMenuMapper.removeByCondition(condition);
		for(int i=0;i<menuIds.length;i++){
			SysRoleMenu record = new SysRoleMenu();
			record.setRoleId(roleId);
			record.setMenuId(Long.valueOf(menuIds[i]));
			sRoleMenuMapper.insert(record);
		}		
    }
	
	public void  bindRoleUser(Long roleId,String[] userIds){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("roleId", roleId);
		sRoleUserMapper.removeByCondition(condition);
		for(int i=0;i<userIds.length;i++){
			SysRoleUser record = new SysRoleUser();
			record.setRoleId(roleId);
			record.setUserId(Long.valueOf(userIds[i]));
			sRoleUserMapper.insert(record);
		}		
	}
	
	public  void  deleteRoleByIds(String[] roleIds){
		for(String roleId : roleIds){
			//删除角色
			removeById(Long.parseLong(roleId));	
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("roleId", roleId);					
			//删除角色和菜单的绑定关系
			sRoleMenuMapper.removeByCondition(condition);
			//删除角色和用户的绑定关系
			sRoleUserMapper.removeByCondition(condition);
		}		
	}
}

package com.yf.system.service;

import com.cykj.grcloud.mybatis.GenericBase.BaseService;
import com.cykj.grcloud.remote.service.RemoteService;
import com.yf.system.entity.SysUser;

@RemoteService
public interface SysUserService extends BaseService<SysUser,Long>{
	
	public void updateUserPwd(SysUser record);
	
	public  void deleteUserByIds(String[] userIds);
}

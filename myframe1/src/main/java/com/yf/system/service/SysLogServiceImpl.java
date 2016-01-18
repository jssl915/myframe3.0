package com.yf.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cykj.grcloud.mybatis.GenericBase.BaseServiceImpl;
import com.yf.system.entity.SysLog;
import com.yf.system.mapper.SysLogMapper;

@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements
		SysLogService {

	@Autowired
	public void setMapper(SysLogMapper mapper) {
		setGenericMapper(mapper);
	}
}

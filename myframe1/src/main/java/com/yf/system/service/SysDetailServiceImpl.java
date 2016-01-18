package com.yf.system.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cykj.grcloud.mybatis.GenericBase.BaseServiceImpl;
import com.yf.system.entity.SysDetail;
import com.yf.system.mapper.SysDetailMapper;

@Service
@Transactional
public class SysDetailServiceImpl extends BaseServiceImpl<SysDetail, Long>
		implements SysDetailService {
	@Autowired
	public void setMapper(SysDetailMapper mapper) {
		super.setGenericMapper(mapper);
	}
	
	@Override
	public void insert(SysDetail record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setDetailStatus(1);
		super.insert(record);
	}
	
	@Override
	public int removeById(Long detailId) {
		int flag = super.removeById(detailId);
		return flag;
	}
}

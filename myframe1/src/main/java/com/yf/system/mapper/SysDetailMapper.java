package com.yf.system.mapper;

import java.util.Map;

import com.cykj.grcloud.mybatis.GenericBase.GenericMapper;
import com.yf.system.entity.SysDetail;

public interface SysDetailMapper extends GenericMapper<SysDetail, Long> {

	public void deleteByCondition(Map<String, Object> map);

}

package com.yf.system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cykj.grcloud.mybatis.GenericBase.BaseServiceImpl;
import com.yf.system.entity.SysDetail;
import com.yf.system.entity.SysDict;
import com.yf.system.mapper.SysDetailMapper;
import com.yf.system.mapper.SysDictMapper;

@Service
@Transactional
public class SysDictServiceImpl extends BaseServiceImpl<SysDict, Long>
		implements SysDictService {
	@Autowired
	private SysDictMapper mapper;
	@Autowired
	private SysDetailMapper sysDetailMapper;

	@Autowired
	public void setMapper(SysDictMapper mapper) {
		setGenericMapper(mapper);
	}

	@Override
	public void insert(SysDict record) {
		record.setDictStatus(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		super.insert(record);
	}

	@Override
	public int mergeById(SysDict record) {
		record.setUpdateTime(new Date());
		int flag = super.mergeById(record);
		return flag;

	}

	@Override
	public int removeById(Long dictId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictId", dictId);
		sysDetailMapper.removeByCondition(condititon);
		int flag = mapper.removeById(dictId);
		return flag;
	}
	//根据dictName得到detail对应的明细map<name,value>
	@Override
	public Map<String, String> getDetailNameMap(String dictName) {
		Hashtable<String, Map<String, SysDetail>> dict = loadData();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SysDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SysDetail> e : dictDetailMap.entrySet()) {  
			SysDetail d = e.getValue();
			params.put(d.getDetailName(), d.getDetailValue().toString());
		}  
		return params;
	}
	//根据dictName得到detail对应的明细map<value,name>
	@Override
	public Map<String, String> getDetailValueMap(String dictName) {
		Hashtable<String, Map<String, SysDetail>> dict = loadData();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SysDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SysDetail> e : dictDetailMap.entrySet()) {  
			SysDetail d = e.getValue();
			params.put(d.getDetailValue().toString(),d.getDetailName());
		}  
		return params;
	}
	//根据dictName和detailName得到detail对应的value
	@Override
	public String getDetailValue(String dictName, String detailName) {
		Hashtable<String, Map<String, SysDetail>> dict = loadData();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		SysDetail dictDetail = dict.get(dictName).get(detailName);
		return dictDetail.getDetailValue().toString();
	}
	
	public Hashtable<String, Map<String, SysDetail>>loadData(){
		Hashtable<String, Map<String, SysDetail>> dict = new Hashtable<String, Map<String, SysDetail>>();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("detailStatus", 1);
		List<SysDict>sDictList = mapper.findEntitysByCondition(null);
		List<SysDetail> SysDetailList = sysDetailMapper.findEntitysByCondition(para);
		Map<String, SysDetail> dictDetailMap = null;
		for(SysDict sd:sDictList){
			String dictId = sd.getDictId().toString();
			dictDetailMap = new HashMap<String, SysDetail>();
			for (SysDetail SysDetail : SysDetailList) {
				if(dictId.equals(SysDetail.getDictId().toString())){
					dictDetailMap.put(SysDetail.getDetailName(), SysDetail);
				}
			}
			dict.put(sd.getDictName(), dictDetailMap);
		}
		return dict;
	}
}

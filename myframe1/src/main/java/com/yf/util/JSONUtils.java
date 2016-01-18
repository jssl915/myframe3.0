package com.yf.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cykj.grcloud.util.Ognl;

public class JSONUtils {
	//private static final Logger LOG = LoggerFactory.getLogger(JSONUtils.class);
	
	private static SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat};
	
	public static String toJson(Object target) {
		return JSON.toJSONString(target,features);
	}
	
	public static List<String> toCombo(Map<String,String> map){
		List<String>list = new ArrayList<String>();
		for (Map.Entry<String,String> entry : map.entrySet()) {  
			String str="{\"text\":\""+entry.getValue()+"\",\"value\":\""+entry.getKey()+"\"}";
			list.add(str);
		}  
		return list;
	}
	
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (Ognl.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, clazz);
	}
	
	public static final <T> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}
}

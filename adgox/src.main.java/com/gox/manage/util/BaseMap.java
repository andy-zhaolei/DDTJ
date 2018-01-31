package com.gox.manage.util;

import java.util.Date;
import java.util.Map;

public class BaseMap {
	public static Map<String,String> setMap(Map<String,String> map){
		map.put("requestId", "adgox"+new Date().getTime()+(int)(Math.random()*100));
		map.put("memberChannel", "adgox");
		map.put("timestamp",new Date().getTime()+"");
		map.put("version", "1.0");
		map.put("appKey", "weqre");
		return map;
	}

}

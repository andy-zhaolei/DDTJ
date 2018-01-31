package com.gox.manage.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.fx.marketing.util.MD5;
import com.mongodb.util.JSON;

import net.sf.json.JSONObject;
@SuppressWarnings("all")
public class DataSignCompare {
	public static void main(String[] args) {
		JSONObject data=new JSONObject();
		data.put("name", "zl");
		data.put("password", "234");
		data.put("a", "cn");
		String mi=compare(data);
		System.out.println(mi);
	}
	public static String compare(JSONObject data){
		Map<String,Object> m=(Map<String,Object>)JSON.parse(data.toString());
		Map<String,Object> n=new TreeMap<String,Object>(new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {				
				return o1.toString().compareTo(o2.toString());
			}
		});
		n.putAll(m);
		StringBuilder str=new StringBuilder();
		
		for (Map.Entry<String, Object> s : n.entrySet()) {
				str.append(s.getKey());
				str.append(s.getValue());
		}
		
		return SignUtils.md5(str.toString());		
	}

}

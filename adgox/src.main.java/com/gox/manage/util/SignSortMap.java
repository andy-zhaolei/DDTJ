package com.gox.manage.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class SignSortMap {
	//public static final String appSecret="92UC09XM04SDBAL21N22N3J6FKBODFFX";//正式
	public static final String appSecret="YY731JG5R3GGB9JAI4R95BGHVL1613G1";//CESHI
	//public static final String appKey="CNBV50UY0LRM499MDH1JAQWJ0AFIE207";//正式
	public static final String appKey="106FLIFZX43PO0USH6S9OJQNF2PNRTAY";//CE式

	//分享处的appSecrect********************************************
	 public static Map<String, Object> sort(Map<String, Object> map) {
	        if (map == null) return null;
	        Map<String, Object> result = new TreeMap<String,Object>(new Comparator<Object>() {
	            @Override
	            public int compare(Object o1, Object o2) {
	                return o1.toString().compareTo(o2.toString());
	            }
	        });
	        result.putAll(map);
	        return result;
	    }
	 public static String SignString(String url,Map<String,Object> m,String appSecret){
		 StringBuilder str=new StringBuilder();
		 for (Map.Entry<String, Object> s : m.entrySet()) {
			str.append(s.getKey());
			str.append(s.getValue());
		}
		 String s=str.toString();
		 s=url+s+appSecret;
		 String sign=EncryptUtils.encryptHMAC(appSecret, s, EncryptUtils.ENCODE_TYPE_BASE64);
		 
		 return sign;
	 }

}

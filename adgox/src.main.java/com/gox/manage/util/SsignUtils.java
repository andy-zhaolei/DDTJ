package com.gox.manage.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.util.JSON;


import net.sf.json.JSONObject;
@SuppressWarnings("all")
public class SsignUtils {
	public static void main(String[] args){
		String data="{\"mobile\":\"32423\",\"age\":\"23\",\"gender\":\"男\"}";
		JSONObject s=JSONObject.fromObject(data);
		String result=SortAndEnc(s);
		
	}
	
	public static String SortAndEnc(JSONObject dataparams){
			Map m=(Map)JSON.parse(dataparams.toString());
			Object [] obj=m.keySet().toArray();
			Arrays.sort(obj);
			StringBuilder ssign=new StringBuilder();
			for (int i = 0; i < obj.length; i++) {
				ssign.append(obj[i]);
				ssign.append(m.get(obj[i]));			
			}
			
			String s=SignUtils.md5(ssign.toString());
		return s;
	}

}

package com.gox.manage.util;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.util.JSON;

import net.sf.json.JSONObject;

/*
 * 将data和page重新组合
 */
@SuppressWarnings("all")
public class ParamsGernateMap {
	
	public static Map<String,Object> ParamNew(JSONObject inparams){
		Map<String,Object> d=new HashMap<String,Object>();
		Map<String,Object> p=new HashMap<String,Object>();
		if(inparams.has("data")){
			Object data= inparams.remove("data");
			d=(Map)JSON.parse(data.toString());
		}if(inparams.has("page")){
			Object page=inparams.remove("page");
			p=(Map)JSON.parse(page.toString());		
		}
		if(d!=null){
			for (Map.Entry<String, Object> da: d.entrySet()) {			
				inparams.put(da.getKey(),da.getValue());
			}
		}
		if(p!=null){
			for (Map.Entry<String, Object> pa: p.entrySet()) {
				inparams.put(pa.getKey(), pa.getValue());
			}
		}
		Map<String,Object> m=(Map<String,Object>)JSON.parse(inparams.toString());
		return m;
	}	

}

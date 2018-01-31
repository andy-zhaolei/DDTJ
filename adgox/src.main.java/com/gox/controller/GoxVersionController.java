package com.gox.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.mortbay.util.ajax.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SuppressWarnings("all")
public class GoxVersionController {
	
	@RequestMapping("/validate/version")
	@ResponseBody
	public Map validateVersion(){
		JSONObject inparams=new JSONObject();
		JSONObject data=new JSONObject();
		try {
			inparams.put("code", "SUCCESS");
			data.put("androidVersion", "4");
			data.put("iosVersion", "1");
			inparams.put("data",data);
			Map m=(Map)JSON.parse(inparams.toString());
			return m;
		} catch (Exception e) {
			inparams.put("code", "FAIL");
			inparams.put("data", "");
			Map m=(Map)JSON.parse(inparams.toString());
			return m;
		}
		
	}
	
	
	

}

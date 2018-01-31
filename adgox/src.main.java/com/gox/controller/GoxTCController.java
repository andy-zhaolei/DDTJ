package com.gox.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Juan;
import com.gox.manage.model.User;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.IpConstant;

@Controller
@SuppressWarnings("all")
public class GoxTCController {
	@Autowired
	private UserService userService;
	@Autowired
	private JuanService juanService;
	
	@RequestMapping("/TC")
	@ResponseBody
	public Map TanChuang(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams =JSONObject.fromObject(params);
		//System.out.println("请求参数："+inparams);
		JSONObject outparams=new JSONObject();
		JSONObject data=new JSONObject();
		try{
			String accessToken=null;
			String state="N";
			if(inparams.has("accessToken")&&!inparams.getString("accessToken").isEmpty()){
				accessToken=inparams.getString("accessToken");
				User u=userService.findUser(accessToken);
				Juan j=null;
				if(u!=null){
					j=juanService.findJuan(u.getId());					
				}else{
					outparams.put("code", "FAIL");
					outparams.put("message", "请重新登录");					
				}
				if(j!=null){
					state=j.getState();				
				}else{
					state="Y";
				}						
			}		
			/*data.put("bjImg",IpConstant.getGoxIp()+"/adgox/img/img_bj@3x.png");*/
			data.put("bjImg",IpConstant.getGoxIp()+"/adgox/img/daititu.png");
			data.put("ticketImg",IpConstant.getGoxIp()+"/adgox/img/img_ticket@3x.png");
			data.put("state", state);	
			if(accessToken!=null){				
				data.put("url",IpConstant.getGoxIp()+"/adgox/HD/index/"+accessToken);//IpConstant.getGoxIp()+"/adgox/HD/index"
				//data.put("url", "http://119.23.161.176/activity/buyGuide/index.html");
			}else{				
				data.put("url", "Nojump");
			}
			outparams.put("code", "SUCCESS");
			outparams.put("data", data);
			Map m=new HashMap();
			m=(Map)JSON.parse(outparams.toString());
			return m;		
		}catch (Exception e) {
			e.printStackTrace();
			outparams.put("code", "FAIL");
			outparams.put("message", "请重新登录");
			outparams.put("data", data);
			Map n=new HashMap();
			n=(Map)JSON.parse(outparams.toString());
			return n;
		}		
		
	}

}

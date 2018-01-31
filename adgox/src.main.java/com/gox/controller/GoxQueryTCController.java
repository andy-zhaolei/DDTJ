package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;

@Controller
@SuppressWarnings("all")
public class GoxQueryTCController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JuanService juanService;
	
	@RequestMapping("/QueryTC/ticket")//点击领取卷
	@ResponseBody
	public Map TanChuang(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams =JSONObject.fromObject(params);
		String accessToken=null;
		String state="N";
		accessToken=inparams.getString("accessToken");
		Juan juan=null;
		User u=new User();
		if(accessToken==null){
			state="N";
		}else{
			 u=userService.findUser(accessToken);			
			juan=juanService.update(u.getId());			
		}
		if(juan==null){
			state="Y";
		}else{
			state=juan.getState();
			JSONObject data=new JSONObject();			
			data.put("promotionNumber",juan.getPromotionNumber());
			data.put("userId",u.getUserId());
			inparams.put("data",data);
			if(inparams.has("sign")){
				inparams.remove("sign");
			}
			if(inparams.has("accessToken")){
				inparams.remove("accessToken");
			}
			if(inparams.has("refreshToken")){
				inparams.remove("refreshToken");
			}
			String uri="/cbclient/promotion/sendCoupon";
			String url=IpConstant.getBaseIp()+uri;
			JSONObject json=JSONObject.fromObject(inparams.toString());
			Map<String,Object> n=ParamsGernateMap.ParamNew(json);
			n=SignSortMap.sort(n);
			String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
			sign=URLEncoder.encode(sign,"utf-8");
			inparams.put("sign", sign);			
		//	System.out.println("送卷req"+inparams);
			try {
				JSONObject result = HttpClientUtil.doPost(url, inparams);
				//System.out.println("送卷res"+result);									
			}catch (Exception e){
				e.printStackTrace();
				//System.out.println("送券失败");
			}
		}
		JSONObject outparams=new JSONObject();
		JSONObject data=new JSONObject();		
		data.put("state", state);	
		if(state.equals("Y")){
			data.put("message", "领取成功");
		}else{
			data.put("message", "未领取");
		}		
		outparams.put("data",data);		
		System.out.println("-------TC请求-----------");
		outparams.put("code", "SUCCESS");
		outparams.put("data", data);
		System.out.println(outparams);
		Map m=new HashMap();
		m=(Map)JSON.parse(outparams.toString());
		return m;			
		/*
		 点击领取跳转链接
		(传参必须有accessToken)		 
		 http://192.168.1.146:8080/adgox/QueryTC/ticket
		 {
		 	"code":"SUCCESS",
		 	"data":{
		 		"message":"",//返回消息，领取成功，未领取成功		 				 	
		 	}		 		 
		 }		 
		 */	
	}
}

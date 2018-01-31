package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.util.DataSignCompare;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.gox.manage.util.SignUtils;
import com.gox.manage.util.SsignUtils;
import com.mongodb.util.JSON;

@Controller
@SuppressWarnings("all")
public class AdgoxVerifyCodeController {
	/**
	 * 1.获取短信验证码
	 * @return
	 */
	private HttpClientUtil httpClientUtil;
	
	@RequestMapping(value="/cbclient/api/verifyCode/getVerifyCodeByCenter")
	@ResponseBody
	public Map getVerifyCode(HttpServletRequest request) throws IOException{//model说明是注册，提现，重置密码
		
		InputStream in=request.getInputStream();
		String params= IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		System.out.println(inparams);
	
		if(!inparams.has("appKey")){
			inparams.put("appKey",SignSortMap.appKey);//106FLIFZX43PO0USH6S9OJQNF2PNRTAY
		}	
		String dsign=(String)inparams.remove("sign");
		
				
		JSONObject json=new JSONObject();
		json=JSONObject.fromObject(inparams.toString());
		
		String uri="/cbclient/api/verifyCode/getVerifyCodeByCenter";
		String url=IpConstant.getBaseIp()+uri;
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//重新排序		
		

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);

		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);
		try {	
			System.out.println(inparams);
			JSONObject result=httpClientUtil.doPost(url, inparams);
			
			
			Map m=(Map)JSON.parse(result.toString());	
			return m; 
		} catch (Exception e) {		
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","失败");
			return m1;
		} 
	}
	
	
	/*
	 * 2.校验验证码
	 */
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping("/cbclient/api/verifyCode/validationCode")
	@ResponseBody
	public Map validateCode(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		System.out.println(inparams);
		if(!inparams.has("appKey")){
			inparams.put("appKey",SignSortMap.appKey);//
		}	
		String dsign=(String)inparams.remove("sign");
		if(dsign!=null && dsign.length()!=0 && !data.isEmpty()){
			String s=DataSignCompare.compare(data);
			if(!s.equals(dsign)){
				Map m=new HashMap();
				m.put("code", "FAIL");
				m.put("message", "签名错误");
				return m;
			}
		}
		//生成新的签名
		String uri="/cbclient/api/verifyCode/validationCode";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名


		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message", "失败");
			return m1;
		}	
	}
}

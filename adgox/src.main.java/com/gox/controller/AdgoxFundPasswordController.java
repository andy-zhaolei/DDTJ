package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.service.UserService;
import com.gox.manage.util.Base64;
import com.gox.manage.util.BaseMap;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.HttpClientUtils;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.gox.manage.util.SignUtils;
import com.gox.manage.util.SsignUtils;
import com.mongodb.util.JSON;

@Controller
@SuppressWarnings("all")

public class AdgoxFundPasswordController {
	@Autowired
	private UserService userService;
	
	private HttpClientUtil httpClientUtil;

	/*
	 * 28.首次设置资金账户密码
	 */
	@RequestMapping(value = "/cbclient/api/oauth/fund/password/create")
	@ResponseBody
	public Map apiOauthFundPasswordReset(HttpServletRequest request)
			throws IOException {

		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
	
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/password/create";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url, inparams);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "设置密码失败");
			return m1;
		}
	}

	/*
	 * 29.重置资金密码
	 */
	@RequestMapping(value = "/cbclient/api/oauth/fund/password/reset")
	@ResponseBody
	public Map apiOauthFundPasswordCreate(HttpServletRequest request)
			throws IOException {

		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		JSONObject data=inparams.getJSONObject("data");
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/password/reset";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
			if ("SUCCESS".equals(result.get("code"))) {
				
			}
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "重置密码失败");
			return m1;
		}
	}

	/*
	 * 修改资金密码
	 * 
	
	 */
	@RequestMapping(value = "/cbclient/api/oauth/fund/password/update")
	@ResponseBody
	public Map updateCapitalPassword(HttpServletRequest request)
			throws IOException {

		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/password/update";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "修改密码失败");
			return m1;
		}
	}


	/*
	 * 31.重置登陆密码
	 */
	@RequestMapping(value = "/cbclient/user/resetPassword")
	@ResponseBody
	public Map fundLoginPassword(HttpServletRequest request) throws IOException {

		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		System.out.println(inparams);
		JSONObject data=inparams.getJSONObject("data");
		
		inparams.remove("sign");
		String uri="/cbclient/user/resetPassword";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "重置登陆密码失败");
			return m1;
		}
	}
	//资金密码验证
	@RequestMapping(value = "/cbclient/api/oauth/fund/verifyPwd")
	@ResponseBody
	public Map VerifyPwd(HttpServletRequest request) throws IOException {

		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		System.out.println(inparams);
		JSONObject data=inparams.getJSONObject("data");
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/verifyPwd";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
		
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "失败");
			return m1;
		}
	}
	
}

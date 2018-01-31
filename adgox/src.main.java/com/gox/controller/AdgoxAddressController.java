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

import com.gox.manage.model.Address;
import com.gox.manage.model.Recevinginfo;
import com.gox.manage.service.UserService;
import com.gox.manage.util.BaseMap;
import com.gox.manage.util.DataSignCompare;
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
public class AdgoxAddressController {
	@Autowired
	private UserService userService;

	private HttpClientUtil httpClientUtil;

	/*
	 * 新增修改地址
	 */
	@RequestMapping(value = "/cbclient/api/oauth/address/edit")
	@ResponseBody
	public Map oauthAddressEdit(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		// 转成json
		JSONObject inparams = JSONObject.fromObject(params);		
		JSONObject data=inparams.getJSONObject("data");
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
		String uri="/cbclient/api/oauth/address/edit";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url, inparams);
			JSONObject resultData=result.getJSONObject("data");
			if ("SUCCESS".equals(result.get("code"))) {
				Address address=new Address();
				address.setUserId(resultData.getLong("userId"));
				address.setAddressId(resultData.getLong("id"));
				address.setFlag(resultData.getLong("flag"));
				address.setIsDefault(resultData.getLong("isDefault"));
				address.setPostCode(resultData.getString("postCode"));
				address.setReceivingAddress(resultData.getString("receivingAddress"));
				address.setReceivingName(resultData.getString("receivingName"));
				address.setReceivingMobile(resultData.getString("receivingMobile"));
				address.setRegion(resultData.getString("region"));
				address.setWid(resultData.getString("wid"));
				address.setCreateDate(resultData.getString("createDate"));
				address.setUpdateDate(resultData.getString("updateDate"));
				userService.saveAddress(address);				
			}
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "新增修改地址失败");
			return m1;
		}
	}
	
	/*
	 * 获取收货地址	
	 */
	@RequestMapping(value = "/cbclient/api/oauth/address/query")
	@ResponseBody
	public Map oauthAddressQuery(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		System.out.println(params);// json
		// 转成json
		JSONObject inparams = JSONObject.fromObject(params);
		JSONObject data = inparams.getJSONObject("data");
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
		
		String uri="/cbclient/api/oauth/address/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url, inparams);			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "获取收到地址失败");
			return m1;
		}
	}
	//删除收货地址
	@RequestMapping(value = "/cbclient/api/oauth/address/delete")
	@ResponseBody
	public Map DeleteAddressQuery(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		System.out.println(params);// json
		// 转成json
		JSONObject inparams = JSONObject.fromObject(params);
		JSONObject data = inparams.getJSONObject("data");
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
		String uri="/cbclient/api/oauth/address/delete";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		
		try {
			JSONObject result = httpClientUtil.doPost(url, inparams);			
			if ("SUCCESS".equals(result.get("code"))) {
				Address address=new Address();
				address.setAddressId(Long.parseLong(data.getString("id")));
				userService.delete(address);
			}
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "删除地址失败");
			return m1;
		}
	}
	//cbclient/api/oauth/address/default
	@RequestMapping(value = "/cbclient/api/oauth/address/default")
	@ResponseBody
	public Map DefaultAddressQuery(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
	
		JSONObject inparams = JSONObject.fromObject(params);
		JSONObject data = inparams.getJSONObject("data");
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
		String uri="/cbclient/api/oauth/address/default";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url, inparams);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "删除地址失败");
			return m1;
		}
	}
}

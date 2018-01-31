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
public class AdgoxVolumeController {
	private HttpClientUtil httpClientUtil;
	/*
	 * 26.体验卷查询
	 */
	@RequestMapping(value = "/cbclient/api/oauth/coupon/query")
	@ResponseBody
	public Map queryVolume(HttpServletRequest request) throws IOException {
		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/coupon/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//重新排序		

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);


		
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
			m1.put("message", "查询体验卷失败");
			return m1;
		}
	}
	/*
	 * 27.
	 * 可用体检卷查询
	 */
	@RequestMapping(value = "/cbclient/api/oauth/coupon/use/query")
	@ResponseBody
	public Map queryAvailableVolume(HttpServletRequest request)
			throws IOException {

		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/coupon/use/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//重新排序				

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
			m1.put("message", "查询可用体验卷失败");
			return m1;
		}
	}
}
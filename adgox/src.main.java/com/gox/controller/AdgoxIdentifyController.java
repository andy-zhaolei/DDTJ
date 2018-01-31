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
public class AdgoxIdentifyController {

	private HttpClientUtil httpClientUtil;

	/**
	 * 21.实名认证
	 */
	@RequestMapping(value = " /cbclient/api/oauth/fund/realName/identify")
	@ResponseBody
	public Map apiOauthFundRealNameIdentify(HttpServletRequest request)
			throws IOException {
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/realName/identify";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
	
		try {
			JSONObject result = httpClientUtil.doPost(url, inparams);			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "认证失败");
			return m1;
		}
	}
	//实名认证查询
	///cbclient/api/oauth/fund/realName/query
	@RequestMapping("/cbclient/api/oauth/fund/realName/query")
	@ResponseBody
	public Map BankList(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject dataparams=inparams.getJSONObject("data");
		inparams.remove("sign");
		//生成新的签名
		String uri="/cbclient/api/oauth/fund/realName/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			
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

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

import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.gox.manage.util.SsignUtils;
import com.mongodb.util.JSON;

//拼单
@Controller
@SuppressWarnings("all")
public class CrawdOrdering {
	///
	@RequestMapping("/cbclient/api/crawdOrdering/listProduct")
	@ResponseBody
	public Map CrawdOrder(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject dataparams=inparams.getJSONObject("data");
		String sign=inparams.getString("sign");
		String s=SsignUtils.SortAndEnc(dataparams);//
		if(!sign.equalsIgnoreCase(s)){
			Map m=new HashMap();
			m.put("code","signError");
			m.put("message","签名错误");
			return m;
		}
		//删除多余参数
		inparams.remove("sign");
		//生成新的签名
		String uri="/cbclient/api/crawdOrdering/listProduct";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		//System.out.println("拼单req"+inparams);
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			//System.out.println("拼单res"+result);
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
	//新增拼单
	@RequestMapping("/oauth/crawdOrdering/create")
	@ResponseBody
	public Map NewCrawdOrder(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject dataparams=inparams.getJSONObject("data");
		String sign=inparams.getString("sign");
		String s=SsignUtils.SortAndEnc(dataparams);
		if(!sign.equalsIgnoreCase(s)){
			Map m=new HashMap();
			m.put("code","signError");
			m.put("message","签名错误");
			return m;
		}
		//删除多余参数
		inparams.remove("sign");
		//生成新的签名
		String uri="/oauth/crawdOrdering/create";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		//System.out.println("新增拼单req"+inparams);
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			//System.out.println("新增拼单res"+result);
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

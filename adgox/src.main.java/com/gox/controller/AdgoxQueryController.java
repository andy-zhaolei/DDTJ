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
public class AdgoxQueryController {
	private HttpClientUtil httpClientUtil;
	/*
	 * 8.查询商品排行榜
	 */
	
	@RequestMapping("/cbclient/api/product/ranking/query")
	@ResponseBody
	public Map QueryPro(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		 
		inparams.remove("sign");
		
		String uri="/cbclient/api/product/ranking/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//重新排序

		String sign=SignSortMap.SignString(url, n, SignSortMap.appSecret);

		System.out.println(sign);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);
		
		try {
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
	 * 9. 查询商品最新价
	 */
	@RequestMapping("/cbclient/api/quote/latest/query")
	@ResponseBody
	public Map QueryPrice(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		
		String url=IpConstant.getBaseIp()+"/cbclient/api/quote/latest/query";
		String uri="/cbclient/api/quote/latest/query";
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//签名

		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign",sign);
		
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","注册失败");
			return m1;
		}		
	}
	/*
	 * 10.查询K线图
	 */
	@RequestMapping("/cbclient/api/quote/kline/query")
	@ResponseBody
	public Map QueryK(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String url=IpConstant.getBaseIp()+"/cbclient/api/quote/kline/query";
		String uri="/cbclient/api/quote/kline/query";
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);


		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","查询失败");
			return m1;
		}		
	}
	
	
	//查询商品列表（龙汇）-/cbclient/api/longhui/product/query
	
	@RequestMapping("/cbclient/api/longhui/product/query")
	@ResponseBody
	public Map LhProQuery(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		JSONObject data=inparams.getJSONObject("data");
		String s=DataSignCompare.compare(data);
		
		String dsign=(String)inparams.remove("sign");
		
		String uri="/cbclient/api/longhui/product/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","查询失败");
			return m1;
		}		
	}
	
	//查询商品排行榜-/cbclient/api/longhui/product/ranking/query
	@RequestMapping("/cbclient/api/longhui/product/ranking/query")
	@ResponseBody
	public Map LhRankQuery(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		String s=(String) inparams.remove("sign");
		String uri="/cbclient/api/longhui/product/ranking/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","查询失败");
			return m1;
		}		
	}
	//获取开盘信息-/cbclient/api/quote/open/query
		@RequestMapping("/cbclient/api/quote/open/query")
		@ResponseBody
		public Map QueryKp(HttpServletRequest request) throws IOException{
			InputStream in=request.getInputStream();
			String params=IOUtils.toString(in,"utf-8");
			JSONObject inparams=JSONObject.fromObject(params);
			
			inparams.remove("sign");
			String uri="/cbclient/api/quote/open/query";
			String url=IpConstant.getBaseIp()+uri;
			JSONObject json=JSONObject.fromObject(inparams.toString());
			Map<String,Object> n=ParamsGernateMap.ParamNew(json);
			n=SignSortMap.sort(n);
			String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
			sign=URLEncoder.encode(sign, "utf-8");
			inparams.put("sign", sign);
			try {
				JSONObject result=httpClientUtil.doPost(url, inparams);
				Map m=(Map)JSON.parse(result.toString());
				return m;
			} catch (Exception e) {
				e.printStackTrace();
				Map<String,String> m1=new HashMap<String,String>();
				m1.put("code", "FAIL");
				m1.put("message","查询失败");
				return m1;
			}		
		}

}

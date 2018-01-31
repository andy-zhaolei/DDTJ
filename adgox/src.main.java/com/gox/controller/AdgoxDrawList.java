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
import com.gox.manage.util.SsignUtils;
import com.mongodb.util.JSON;

@Controller
@SuppressWarnings("all")
public class AdgoxDrawList {
	//城市下拉列表
	@RequestMapping("/cbclient/api/oauth/fund/listCity")
	@ResponseBody
	public Map CityList(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		System.out.println(inparams);
		JSONObject dataparams=inparams.getJSONObject("data");
		String dsign=(String)inparams.remove("sign");
		if(dsign!=null && dsign.length()!=0 && !dataparams.isEmpty()){
			String s=DataSignCompare.compare(dataparams);
			if(!s.equals(dsign)){
				Map m=new HashMap();
				m.put("code", "FAIL");
				m.put("message", "签名错误");
				return m;
			}
		}
		//生成新的签名
		String uri="/cbclient/api/oauth/fund/listCity";
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
	
	//省份下拉列表
		@RequestMapping("/cbclient/api/oauth/fund/listProvince")
		@ResponseBody
		public Map ProList(HttpServletRequest request) throws IOException{
			InputStream in=request.getInputStream();
			String params=IOUtils.toString(in,"utf-8");
			JSONObject inparams=JSONObject.fromObject(params);
			
			inparams.remove("sign");
			//生成新的签名
			String uri="/cbclient/api/oauth/fund/listProvince";
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
		//银行列表BLANKS = "cbclient/api/oauth/fund/listBank
		@RequestMapping("/cbclient/api/oauth/fund/listBank")
		@ResponseBody
		public Map BankList(HttpServletRequest request) throws IOException{
			InputStream in=request.getInputStream();
			String params=IOUtils.toString(in,"utf-8");
			JSONObject inparams=JSONObject.fromObject(params);
		
			inparams.remove("sign");
			//生成新的签名
			String uri="/cbclient/api/oauth/fund/listBank";
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
		//银行列表BLANKS H5= "/cbclient/api/oauth/fund/listBankH5
				@RequestMapping("/cbclient/api/oauth/fund/listBankH5")
				@ResponseBody
				public Map BankListH5(HttpServletRequest request) throws IOException{
					InputStream in=request.getInputStream();
					String params=IOUtils.toString(in,"utf-8");
					JSONObject inparams=JSONObject.fromObject(params);				
					inparams.remove("sign");
					
					//生成新的签名
					String uri="/cbclient/api/oauth/fund/listBankH5";
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

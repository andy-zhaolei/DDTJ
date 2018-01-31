package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.OrderRecord;
import com.gox.manage.model.User;
import com.gox.manage.service.OrderRecordService;
import com.gox.manage.service.UserService;
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
public class AdgoxOrderRecordController {

	private HttpClientUtil httpClientUtil;
	@Autowired
	private OrderRecordService orderRecordService;
	@Autowired
	private UserService userService;

	/*
	 * 23.查询订货记录
	 *
	 */
	@RequestMapping(value = "/cbclient/api/oauth/order/queryClosedOrder")
	@ResponseBody
	public Map oauthOrderQueryClosedOrder(HttpServletRequest request)
			throws IOException {
		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		inparams.remove("sign");
		
		String uri="/cbclient/api/oauth/order/queryClosedOrder";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
			JSONArray ResultData=result.getJSONArray("data");
			if ("SUCCESS".equals(result.get("code"))&&!ResultData.isEmpty()) {
				User u=userService.findUser(inparams.getString("accessToken"));
				OrderRecord r=orderRecordService.find(u.getId());
				long stime=0;
				if(r!=null){
					stime=r.getSelltime().getTime();
				}
				for (int i = 0; i <ResultData.size(); i++) {
					JSONObject d=ResultData.getJSONObject(i);
					OrderRecord or=new OrderRecord();
					if(d.getLong("selltime")>stime){
						or.setSelltime(new Date(d.getLong("selltime")));
						or.setToplimit(d.getDouble("toplimit")+"");
						or.setBottomlimit(d.getDouble("bottomlimit")+"");			
						or.setBuymoney(new BigDecimal(d.getDouble("buymoney")+""));
						or.setBuydirection(d.getInt("buydirection"));					
						or.setBuyprice(new BigDecimal(d.getDouble("buyprice")+""));
						or.setArea(d.getString("area"));
						or.setCount(d.getInt("count"));
						or.setOrderid(d.getLong("orderid"));
						or.setOrdernum(d.getString("ordernum"));
						or.setOrdertype(d.getInt("ordertype"));
						or.setProductpic(d.getString("productpic"));
						or.setProductId(d.getString("productid"));					
						or.setPlamount(new BigDecimal(d.getDouble("plamount")+""));					
						or.setFee(new BigDecimal(d.getDouble("fee")+""));
						or.setActualAmount(new BigDecimal(d.getDouble("actualAmount")+""));
						or.setAddtime(new Date(d.getLong("addtime")));						
						or.setName(d.getString("name"));
						or.setSellprice(new BigDecimal(d.getDouble("sellprice")+""));
						or.setCreatedTime(new Date());	
						or.setUserId(u.getId());
						or.setPhone(u.getMobile());
						
						orderRecordService.save(or);
					}
				
				}
			}
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "查询失败");
			return m1;
		}
	}
	
	/*
	 * 24查询补款取货记录
	 * 
	 */
	@RequestMapping(value = "/cbclient/api/oauth/delivery/query")
	@ResponseBody
	public Map apiOauthDeliveryQuery(HttpServletRequest request)
			throws IOException {
		
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/delivery/query";
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
			m1.put("message", "查询失败");
			return m1;
		}
	}
	
	/**
	 *25. 统计订货差价
	 */
	@RequestMapping(value = "/cbclient/api/oauth/order/sumClosedOrder")
	@ResponseBody
	public Map apiOauthOrderSumClosedOrder(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/order/sumClosedOrder";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);

		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		//System.out.println("统计订货差价req"+inparams);
		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
			//System.out.println("统计订货差价res"+result);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "查询失败");
			return m1;
		}
	}
	//订货建议数量-/cbclient/api/suggest/numbers
	@RequestMapping(value = "/cbclient/api/suggest/numbers")
	@ResponseBody
	public Map SuggestNum(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
	
		inparams.remove("sign");
		String uri="/cbclient/api/suggest/numbers";
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
			m1.put("message", "查询失败");
			return m1;
		}
	}
	
	
}

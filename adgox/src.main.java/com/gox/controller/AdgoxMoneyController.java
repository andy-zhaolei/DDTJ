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

import com.gox.manage.model.Record;
import com.gox.manage.model.User;
import com.gox.manage.service.RecevingInfoService;
import com.gox.manage.service.RecordService;
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
public class AdgoxMoneyController {
	@Autowired
	private UserService userService;
	@Autowired
	private RecordService recordService;

	private HttpClientUtil httpClientUtil;

	/*
	 * 查询收支记录
	 */
	@RequestMapping(value = "/cbclient/api/oauth/money/queryMoneyLogList")
	@ResponseBody
	public Map apiOauthFundRealNameIdentify(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");
		
		String uri="/cbclient/api/oauth/money/queryMoneyLogList";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);

		try {
			JSONObject result = httpClientUtil.doPost(url,inparams);
			JSONArray resultData=result.getJSONArray("data");
			if ("SUCCESS".equals(result.get("code"))&&!resultData.isEmpty()) {
				User user=userService.findUser(inparams.getString("accessToken"));				
				Record record=new Record();
				record=recordService.findRecord();//找最新一条记录				
				for (int i = 0; i <resultData.size(); i++) {
					JSONObject obj=resultData.getJSONObject(i);
					Long addTime=obj.getLong("addTime");
					if(record.getAddTime().getTime()<addTime){
						Record r=new Record();
						r.setOrderTypeStr(obj.getString("orderTypeStr"));
						r.setOrderNumber(obj.getString("orderNumber"));
						double balance=obj.getDouble("balance");
						double money=obj.getDouble("money");
						r.setBalance(new BigDecimal(balance));
						r.setMoney(new BigDecimal(money));
						r.setAddTime(new Date(addTime));
						r.setCreatedTime(r.getAddTime());
						r.setUpdatedTime(new Date());
						r.setProDesc(obj.getString("proDesc"));
						r.setUserId(user.getId());
						recordService.saveRecord(r);
					}					
				}								
			}
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "查询成功");
			return m1;
		}
	}
	//出金入金提示信息
	@RequestMapping(value = "/cbclient/api/oauth/fund/getFundTimeInfo")
	@ResponseBody
	public Map GetFund(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");//
		String uri="/cbclient/api/oauth/fund/getFundTimeInfo";
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
			m1.put("message", "查询成功");
			return m1;
		}
	}
	//用户可用余额cbclient/api/oauth/fund/queryBalance
	@RequestMapping(value = "/cbclient/api/oauth/fund/queryBalance")
	@ResponseBody
	public Map GetBalanceFund(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		inparams.remove("sign");//
		String uri="/cbclient/api/oauth/fund/queryBalance";
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
			m1.put("message", "查询成功");
			return m1;
		}
	}

    /**
     * 查询建议充值金额
   		"cbclient/api/suggest/infunds";
     */
	@RequestMapping(value = "/cbclient/api/suggest/infunds")
	@ResponseBody
	public Map SuggestInfund(HttpServletRequest request)
			throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		
		inparams.remove("sign");//
		String uri="/cbclient/api/suggest/infunds";
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
			m1.put("message", "查询成功");
			return m1;
		}
	}
	
	

	
}

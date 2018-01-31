package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Juan;
import com.gox.manage.model.Order;
import com.gox.manage.model.User;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.OrderService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.HDCODE;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.gox.manage.util.SignUtils;
import com.gox.manage.util.SsignUtils;

@Controller
@SuppressWarnings("all")
public class AdgoxOrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private JuanService juanService;		
	/*
	 * 11.现价订货
	 */
	///cbclient/api/oauth/trade/order/current/create
	@RequestMapping("/cbclient/api/oauth/order/current/create")
	@ResponseBody
	public Map OrderCreate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		String amount=(String)data.remove("amount");
		String fee=(String)data.remove("fee");
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/order/current/create";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url,inparams);
			
			if("SUCCESS".equals(result.getString("code"))){
				User user=userService.findUser(inparams.getString("accessToken"));
				List<Order> r=orderService.findOrder(user.getId());//查看之前订单
				Order order=new Order();
				order.setNumber(data.getString("number"));
				order.setCoponType(data.getString("couponType"));
				order.setIsJuan(data.getString("isJuan"));
				order.setBottomlimit(data.getString("bottomlimit"));
				order.setArea(data.getString("area"));
				order.setToplimit(data.getString("toplimit"));
				order.setProductId(data.getString("productId"));
				order.setUserId(user.getId());
				order.setCreatedTime(new Date());
				order.setUpdatedTime(order.getCreatedTime());
				order.setBuydirection("XJ");
				order.setBuymoney(amount);
				order.setFee(fee);
				System.out.println("订单"+order);
				orderService.saveOrder(order);
				
				int i=(int)(Math.random()*100)+1;//1-50，51-95，96-97，98，99，100
				
				if(r.size()!=0){
					Order od=r.get(0);
					Long s=od.getCreatedTime().getTime()+24L*60*60*1000;
					Long dtime=order.getCreatedTime().getTime();
					if(s<dtime){
						Juan j=new Juan();
						j.setSource("黄金宝箱");					
						if(i<=50){//
							j.setFaceValue("8.8");
							j.setPromotionNumber(HDCODE.hjbx1);
						}else if(i>=51&&i<=95){
							j.setFaceValue("9.8");
							j.setPromotionNumber(HDCODE.hjbx2);
						}else if(i<=97&&i>=96){
							j.setFaceValue("32");
							j.setPromotionNumber(HDCODE.hjbx3);
						}else if(i==98){
							j.setFaceValue("58");
							j.setPromotionNumber(HDCODE.hjbx4);
						}else if(i==99){
							j.setFaceValue("68");
							j.setPromotionNumber(HDCODE.hjbx5);
						}else if(i==100){
							j.setFaceValue("138");
							j.setPromotionNumber(HDCODE.hjbx6);
						}
						int i1=(int)(Math.random()*2);
						if(i1==0){
							j.setTreasureMap("N1");
						}
						j.setState("N");
						j.setCreatedTime(new Date());
						j.setUpdatedTime(j.getCreatedTime());
						j.setUserId(user.getId());
						j.setTel(user.getMobile());
						juanService.save(j);
											
					}
				}else{
					Juan j=new Juan();
					j.setSource("黄金宝箱");					
					if(i<=50){//
						j.setFaceValue("8.8");
						j.setPromotionNumber(HDCODE.hjbx1);
					}else if(i>=51&&i<=95){
						j.setFaceValue("9.8");
						j.setPromotionNumber(HDCODE.hjbx2);
					}else if(i<=97&&i>=96){
						j.setFaceValue("32");
						j.setPromotionNumber(HDCODE.hjbx3);
					}else if(i==98){
						j.setFaceValue("58");
						j.setPromotionNumber(HDCODE.hjbx4);
					}else if(i==99){
						j.setFaceValue("68");
						j.setPromotionNumber(HDCODE.hjbx5);
					}else if(i==100){
						j.setFaceValue("138");
						j.setPromotionNumber(HDCODE.hjbx6);
					}
					int i1=(int)(Math.random()*2);
					if(i1==0){
						j.setTreasureMap("N1");
					}
					j.setState("N");
					j.setCreatedTime(new Date());
					j.setUpdatedTime(j.getCreatedTime());
					j.setUserId(user.getId());
					j.setTel(user.getMobile());
					juanService.save(j);					
				}			
			}
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
		
	}
	/*
	 * 12.结算价订货
	 *////cbclient/api/oauth/trade/order/settlement/create
	@RequestMapping("/cbclient/api/oauth/order/settlement/create")
	@ResponseBody
	public Map OrderSettle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		
		String amount=(String)data.remove("amount");//
		String fee=(String)data.remove("fee");	//
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/order/settlement/create";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);

		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url,inparams);
			
			if("SUCCESS".equals(result.getString("code"))){
				User user=userService.findUser(inparams.getString("accessToken"));
				List<Order> r=orderService.findOrder(user.getId());//查看之前订单
				Order order=new Order();
				order.setNumber(data.getString("number"));
				order.setCoponType(data.getString("couponType"));
				order.setIsJuan(data.getString("isJuan"));
				order.setBottomlimit(data.getString("bottomlimit"));
				order.setArea(data.getString("area"));
				order.setToplimit(data.getString("toplimit"));
				order.setProductId(data.getString("productId"));
				order.setUserId(user.getId());
				order.setCreatedTime(new Date());
				order.setUpdatedTime(order.getCreatedTime());
				order.setBuydirection("JSJ");
				order.setBuymoney(amount);
				order.setFee(fee);
				orderService.saveOrder(order);
				
				int i=(int)(Math.random()*100)+1;//70,10,15,5,/10..79,1-9,80-84,85-100
			
				if(r.size()!=0){
					Order od=r.get(0);
					Long s=od.getCreatedTime().getTime()+24L*60*60*1000;
					Long dtime=order.getCreatedTime().getTime();
					if(s<dtime){
						Juan j=new Juan();
						j.setSource("黄金宝箱");					
						if(i<=50){//
							j.setFaceValue("8.8");
							j.setPromotionNumber(HDCODE.hjbx1);
						}else if(i>=51&&i<=95){
							j.setFaceValue("9.8");
							j.setPromotionNumber(HDCODE.hjbx2);
						}else if(i<=97&&i>=96){
							j.setFaceValue("32");
							j.setPromotionNumber(HDCODE.hjbx3);
						}else if(i==98){
							j.setFaceValue("58");
							j.setPromotionNumber(HDCODE.hjbx4);
						}else if(i==99){
							j.setFaceValue("68");
							j.setPromotionNumber(HDCODE.hjbx5);
						}else if(i==100){
							j.setFaceValue("138");
							j.setPromotionNumber(HDCODE.hjbx6);
						}
						int i1=(int)(Math.random()*2);
						if(i1==0){
							j.setTreasureMap("N1");
						}
						j.setState("N");
						j.setCreatedTime(new Date());
						j.setUpdatedTime(j.getCreatedTime());
						j.setUserId(user.getId());
						j.setTel(user.getMobile());
						juanService.save(j);
											
					}
				}else{
					Juan j=new Juan();
					j.setSource("黄金宝箱");					
					if(i<=50){//
						j.setFaceValue("8.8");
						j.setPromotionNumber(HDCODE.hjbx1);
					}else if(i>=51&&i<=95){
						j.setFaceValue("9.8");
						j.setPromotionNumber(HDCODE.hjbx2);
					}else if(i<=97&&i>=96){
						j.setFaceValue("32");
						j.setPromotionNumber(HDCODE.hjbx3);
					}else if(i==98){
						j.setFaceValue("58");
						j.setPromotionNumber(HDCODE.hjbx4);
					}else if(i==99){
						j.setFaceValue("68");
						j.setPromotionNumber(HDCODE.hjbx5);
					}else if(i==100){
						j.setFaceValue("138");
						j.setPromotionNumber(HDCODE.hjbx6);
					}
					int i1=(int)(Math.random()*2);
					if(i1==0){
						j.setTreasureMap("N1");
					}
					j.setState("N");
					j.setCreatedTime(new Date());
					j.setUpdatedTime(j.getCreatedTime());
					j.setUserId(user.getId());
					j.setTel(user.getMobile());
					juanService.save(j);					
										
					
					
				}			
			}
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
	}
	/*
	 * 13. 查询用户订单
	 */
	@RequestMapping("/cbclient/api/oauth/order/query")
	@ResponseBody
	public Map OrderQuery(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/order/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			JSONArray jsonarray=new JSONArray();
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}		
	}
	/*
	 * 14. 退订
	 */
	@RequestMapping("/cbclient/api/oauth/order/close")
	@ResponseBody
	public Map OrderClose(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/order/close";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);

		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url,inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
	}
	
	/*
	 * 15. 补款取货(
	 */
	@RequestMapping("/cbclient/api/oauth/order/delivery")
	@ResponseBody
	public Map OrderDelivery(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/order/delivery";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);

		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
	}
	
	/*
	 * 18. 查询余额
	 */
	@RequestMapping("/cbclient/api/oauth/fund/query")
	@ResponseBody
	public Map FundQuery(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
	}
	/*
	 * 19.充值
	 */
	@RequestMapping("/cbclient/api/oauth/fund/infund")
	@ResponseBody
	public Map FundInfund(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
	
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/infund";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
		
	}
	/*
	 * 20. 提现
	 */
	@RequestMapping("/cbclient/api/oauth/fund/outfund")
	@ResponseBody
	public Map OutFund(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/fund/outfund";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);	
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}
	}
	
}

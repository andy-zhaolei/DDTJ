package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.LimitSome;
import com.gox.manage.model.Order;
import com.gox.manage.model.OrderRecord;
import com.gox.manage.model.User;
import com.gox.manage.service.LimitSomeService;
import com.gox.manage.service.OrderRecordService;
import com.gox.manage.service.OrderService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.mongodb.util.JSON;
import com.mysql.jdbc.StringUtils;
@Controller
@SuppressWarnings("all")
public class AdogxAliPayWxOrder {
	@Autowired
	private OrderRecordService orderRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private LimitSomeService limitSomeService;
	@Autowired
	private OrderService orderService;
	
	

    /**
     * 查询支付渠道
     */
	@RequestMapping(value = "/cbclient/api/oauth/fund/pay/channel/query")
	@ResponseBody
	public Map SuggestInfund(HttpServletRequest request)throws IOException {

		// 从流中获取数据
		InputStream in = request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");
		JSONObject inparams = JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		inparams.remove("sign");//
		String uri="/cbclient/api/oauth/fund/pay/channel/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		/*String d1="{\"id\": 101,\"payCode\":\"UNSPAY\",\"payName\": \"银生宝支付\", \"payLogoUrl\":\"http://cbec-static.oss-cn-shenzhen.aliyuncs.com/trade/product/664bc00feaa94903a394d9c62d9bafcb.png\",\"fundCallbackUrl\":\"http://cbec.100bei.com/\", \"payUrl\":\"http://cbecuat.100bei.com/cbclient/payment/index.html#/unspay_recharge\"}";
	*/
		try {
			
			JSONObject result = HttpClientUtil.doPost(url,inparams);
			if ("SUCCESS".equals(result.get("code"))) {
				JSONArray j=result.getJSONArray("data");
				
				if(data.getString("payType").equals("1")){
					result.remove("data");
					
						JSONArray d=new JSONArray();
						JSONObject dt=new JSONObject();
						dt.put("id", 201);
						dt.put("fundCallbackUrl", "");
						dt.put("payCode", "ALISCANPAY");
						dt.put("payName", "支付宝支付");
						dt.put("payLogoUrl", IpConstant.getGoxIp()+"/adgox/img/zfblogo.jpg");
						dt.put("payUrl", IpConstant.getGoxIp()+"/adgox/cbclient/api/oauth/fund/alipay/infund");
						d.add(dt);
						JSONObject dt1=new JSONObject();
						
						/*dt1.put("id",301);
						dt1.put("fundCallbackUrl","");
						dt1.put("payCode", "ZNPAYH5");
						dt1.put("payName","中南支付");
						dt1.put("payLogoUrl",IpConstant.getGoxIp()+"/adgox/img/zn_logo.png");
						dt1.put("payUrl", IpConstant.getGoxIp()+"/adgox/index.html#/zn_recharge");
						d.add(dt1);*/
						result.put("data", d);
						
				}else{
					JSONArray jsons=result.getJSONArray("data");
					j.remove(0);
					JSONObject d=new JSONObject();
					d.put("payCode", "ZNPAYH5");
					
					d.put("payUrl",IpConstant.getGoxIp()+"/adgox/index.html#/zn_withdraw");
					d.put("payLogoUrl", "");
					d.put("fundCallbackUrl", "");
					j.add(d);
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
	//扫码支付
	@RequestMapping("/cbclient/api/oauth/fund/alipay/infund")
	@ResponseBody
	public Map SanMa(HttpServletRequest request)throws IOException {
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		inparams.remove("sign");
		
		String amount=(String)data.remove("amount");
		double money=Double.parseDouble(amount);
		long moneys=(long)(money*100);
		data.put("amount", moneys);
		data.put("userIp",request.getRemoteAddr());
		data.put("goodsDesc","点点淘金充值订单");
		// 从流中获取数据		
		String uri="/cbclient/api/oauth/fund/alipay/infund";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result = HttpClientUtil.doPost(url,inparams);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "支付失败");
			return m1;
		}
	}
	//中南提现
	@RequestMapping("/cbclient/api/oauth/fund/zn/outfund")
	@ResponseBody
	public Map ZhonNan(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
	
		inparams.remove("sign");
		
		//生成新的签名
		String uri="/cbclient/api/oauth/fund/zn/outfund";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		try {
			LimitSome limits=limitSomeService.findwithdraw();
			User u=userService.findUser(inparams.getString("accessToken"));
			List<Order> list=orderService.findCashOrder(u.getId());
			double b=0;
			if(list.size()!=0){
				for (Order order : list) {
					String m=order.getBuymoney();
					double ordermoney=Double.parseDouble(m);
					b=b+ordermoney;
				}
			}
			double limit=limits.getWithdraw().doubleValue();
			if(limit<=b){				
				JSONObject result=HttpClientUtil.doPost(url, inparams);
				//System.out.println("中南提现res"+result);
				Map m=(Map)JSON.parse(result.toString());
				return m;
			}else{
				Map<String,String> m=new HashMap<String,String>();
				m.put("code", "FAIL");
				m.put("message", "现金交易必须达到"+limit+"元才可以提现!");
				return m;				
			}
	
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message", "失败");
			return m1;
		}	
	}
	////cbclient/api/oauth/fund/zn/infund
	//中南支付
	@RequestMapping("/cbclient/api/oauth/fund/zn/infund")
	@ResponseBody
	public Map ZhonNanRecharge(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
	
		inparams.remove("sign");
		
		//生成新的签名
		String uri="/cbclient/api/oauth/fund/zn/infund";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		//System.out.println("中南支付req"+inparams);
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			//System.out.println("中南支付res"+result);
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
	
	
	
	//获取绑卡信息= "/cbclient/api/oauth/fund/card/query
	@RequestMapping("/cbclient/api/oauth/fund/card/query")
	@ResponseBody
	public Map BankListH5(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
	
		inparams.remove("sign");
		
		//生成新的签名
		String uri="/cbclient/api/oauth/fund/card/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		//System.out.println("获取绑卡信息req"+inparams);
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			//System.out.println("获取绑卡信息es"+result);
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
	
	
	
	
	
	
	//获取提现订单号/cbclient/api/oauth/fund/in/order/num
	@RequestMapping("/cbclient/api/oauth/fund/out/order/num")
	@ResponseBody
	public Map Ordernum(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
	
		inparams.remove("sign");
		
		//生成新的签名
		String uri="/cbclient/api/oauth/fund/out/order/num";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//生成签名
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);//
		//System.out.println("获取提现订单号req"+inparams);
		try {
			JSONObject result=HttpClientUtil.doPost(url, inparams);
			//System.out.println("获取提现订单号res"+result);
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
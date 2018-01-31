package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Device;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.Juan;
import com.gox.manage.model.User;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.DataSignCompare;
import com.gox.manage.util.HDCODE;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.gox.manage.util.SignUtils;
import com.gox.manage.util.SsignUtils;
import com.mongodb.util.JSON;
@Controller
@SuppressWarnings("all")
public class AdgoxUserController {
	
	private HttpClientUtil httpClientUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private JuanService juanService;
	
	/*
	 * 5.用户登录
	 */
	@RequestMapping(value="/cbclient/user/login/mobile")
	@ResponseBody
	public Map goLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();			
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		JSONObject data=inparams.getJSONObject("data");
		
		String dsign=(String)inparams.remove("sign");
		if(dsign!=null && dsign.length()!=0 && !data.isEmpty()){//签名比较
			String s=DataSignCompare.compare(data);			
			if(!s.equals(dsign)){
				Map m=new HashMap();
				m.put("code", "FAIL");
				m.put("message", "签名错误");
				return m;
			}
		}
		String idfv=null;
		String idfa=null;
		if(data.has("idfa")&&data.has("idfv")){			
			idfa=(String)data.remove("idfa");			
			idfv=(String)data.remove("idfv");
			idfa=URLDecoder.decode(idfa,"utf-8");
			idfv=URLDecoder.decode(idfv, "utf-8");
			
		}
	
		String uri="/cbclient/user/login/mobile";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);//签名
		sign=URLEncoder.encode(sign, "utf-8");	
		inparams.put("sign",sign);
		
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
						
			JSONObject H=(JSONObject) result.remove("other");
		
			if("SUCCESS".equalsIgnoreCase(result.getString("code"))){
				//加上响应头
				response.setHeader("Access-Token",H.getString("Access-Token"));
				response.setHeader("Refresh-Token",H.getString("Refresh-Token"));
				response.setHeader("Access-Token-Expire-Time",H.getString("Access-Token-Expire-Time"));
				response.setHeader("Refresh-Token-Expire-Time",H.getString("Refresh-Token-Expire-Time"));
				
				//判断是否存储相应用户信息
				User us=userService.findUserByPhone(data.getString("mobile"));
				User u=new User();
				JSONObject d=result.getJSONObject("data");
				u.setSex(d.getInt("sex"));
				u.setIsRealName(d.getInt("isRealName"));
				u.setMemberChannel(d.getString("memberChannel"));
				u.setWid(d.getString("wid"));
				u.setMemberUnitsId(d.getInt("memberUnitsId"));
				u.setHasFundPassword(d.getInt("hasFundPassword"));
				u.setUserType(d.getInt("userType"));
				u.setFlag(d.getInt("flag"));
				u.setUnitNo(d.getString("unitNo"));
				u.setAccountUserId(d.getString("accountUserId"));
				u.setAccountMemberunitId(d.getString("accountMemberunitId"));
				u.setAccessToken(H.getString("Access-Token"));
				Long t=Long.parseLong(H.getString("Access-Token-Expire-Time"));
				u.setAccessTokenExpireTime(new Date(t));
				u.setRefreshToken(H.getString("Refresh-Token"));
				Long t1=Long.parseLong(H.getString("Refresh-Token-Expire-Time"));
				u.setRefreshTokenExpireTime(new Date(t1));
				u.setUserName(d.getString("userName"));
				u.setInvitationCode(d.getString("invitationCode"));
				u.setPassword(data.getString("password"));
				u.setMobile(data.getString("mobile"));

				Long userId=d.getLong("userId");
				
				Device device=new Device();
				device.setDeviceId(inparams.getString("deviceId"));
				device.setPlatform(inparams.getString("platform"));	
				device.setIdfa(idfa);
				device.setIdfv(idfv);
			
				userService.updateUser(userId,u,device);
				
				Long utime=us.getUpdatedTime().getTime()+24L*60*60*1000;
				Long ntime=new Date().getTime();
				if(us==null||utime<ntime){
					int i=(int)(Math.random()*2); 
					
					Juan j=new Juan();
					j.setSource("白银宝箱");
					if(i==0){
						j.setFaceValue("8.8");	
						j.setPromotionNumber(HDCODE.bybx1);
					}else{
						j.setFaceValue("9.8");
						j.setPromotionNumber(HDCODE.bybx2);
					}					
					j.setState("N");
					j.setCreatedTime(new Date());
					j.setUpdatedTime(j.getCreatedTime());
					j.setUserId(us.getId());
					j.setTel(u.getMobile());
					juanService.save(j);
				}
				
				
			}			
			Map m=(Map)JSON.parse(result.toString());
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","登陆失败");
			return m1;
		}		
	}
	/*
	 *6. 刷新token
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping("/cbclient/user/refresh-token")
	@ResponseBody
	public Map refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		
		//删除多余接口参数
		//System.out.println("删除多余参数前"+inparams);
		inparams.remove("sign");
		String uri="/cbclient/user/oauth/refresh-token";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign", sign);
		try {		
		    JSONObject result=httpClientUtil.doPost(url,inparams);
		    if("SUCCESS".equals(result.getString("code"))){
		    	
		    	JSONObject H=(JSONObject) result.remove("other");
		    	String accessToken=H.getString("Access-Token");
		    	String accessTokenExpireTime=H.getString("Access-Token-Expire-Time");
		    	response.setHeader("Access-Token",accessToken);
		    	response.setHeader("Access-Token-Expire-Time",accessTokenExpireTime);
		    	String OldAccessToken=inparams.getString("accessToken");
		    	userService.updateAccessToken(OldAccessToken,accessToken,accessTokenExpireTime);
		    	
		    }
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
	 * 7.查询船期列表
	 */
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/cbclient/api/shipment/query")
	@ResponseBody
	public Map Shipment(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
	
		//
		inparams.remove("sign");
	
		String uri="/cbclient/api/shipment/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//签名

		sign=URLEncoder.encode(sign, "utf-8");//编码特殊符号
		inparams.put("sign",sign);
		
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
	 * 登出
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestMapping("/cbclient/user/oauth/logout")
	@ResponseBody
	public Map logout(HttpServletRequest request ) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/shipment/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序

		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//签名
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
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
	//  获取用户信息-/cbclient/api/oauth/userInfo/query
	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestMapping("/cbclient/api/oauth/userInfo/query")
	@ResponseBody
	public Map UserInfoQuery(HttpServletRequest request ) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		inparams.remove("sign");
		String uri="/cbclient/api/oauth/userInfo/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);//重整参数
		n=SignSortMap.sort(n);//排序
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);//签名
		sign=URLEncoder.encode(sign,"utf-8");
		inparams.put("sign",sign);
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
	
	

}

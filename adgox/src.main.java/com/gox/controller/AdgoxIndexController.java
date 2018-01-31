package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
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
import org.mortbay.jetty.security.Credential.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Device;
import com.gox.manage.model.Invitation;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.Juan;
import com.gox.manage.model.User;
import com.gox.manage.service.InvitationService;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.EncryptUtils;
import com.gox.manage.util.HDCODE;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.gox.manage.util.SignUtils;
import com.gox.manage.util.SsignUtils;

import com.mongodb.util.JSON;
import com.unionpay.acp.sdk.HttpClient;

@Controller
@SuppressWarnings("all")
public class AdgoxIndexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private InvitationService invitationService;
	@Autowired
	private JuanService juanService;
	
	private HttpClientUtil httpClientUtil;
	
	/*
	 * 3.个人用户注册
	 */	
	@RequestMapping(value="/cbclient/user/personal/register")
	@ResponseBody
	public Map  goRegist(HttpServletRequest request,HttpServletResponse response) throws IOException{	//,String verifyCode,String invitationCode	
		InputStream in=request.getInputStream();
		String params = IOUtils.toString(in, "utf-8");		
		JSONObject inparams=JSONObject.fromObject(params);//inparams全部参数json格式
		JSONObject data=inparams.getJSONObject("data");//data数据字段json格式	
		if(inparams.getString("platform").equals("ios")){			
			String idfv=(String)data.remove("idfv");
			String idfa=(String)data.remove("idfa");
			idfv=URLDecoder.decode(idfv,"utf-8");
			idfa=URLDecoder.decode(idfa, "utf-8");
			inparams.put("platform", "server");
		}
		String idfv=null;
		String idfa=null;
		if(data.has("idfa")&&data.has("idfv")){			
			idfa=(String)data.remove("idfa");			
			idfv=(String)data.remove("idfv");
			idfa=URLDecoder.decode(idfa,"utf-8");
			idfv=URLDecoder.decode(idfv, "utf-8");
			
		}
	
		//try {//正对分享H5注册得	
		if(!inparams.has("appKey")){
			inparams.put("appKey",SignSortMap.appKey);//106FLIFZX43PO0USH6S9OJQNF2PNRTAY
		}	
		String invitationCode=(String)data.remove("invitationCode");
		data.put("invitationCode", "9990");
		
		inparams.remove("sign");	
		String uri="/cbclient/user/personal/register";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		
		Map<String,Object> m=ParamsGernateMap.ParamNew(json);//重整参数
		m=SignSortMap.sort(m);//重新排序
		String sign=SignSortMap.SignString(uri, m, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign", sign);			
		//System.out.println("个人注册req"+inparams);
		try {
			
			JSONObject result=httpClientUtil.doPost(url, inparams);
			//System.out.println("个人注册res"+result);
			if("SUCCESS".equals(result.getString("code"))){					
				User user=new User();
				JSONObject D=result.getJSONObject("data");
				Long userId=Long.parseLong(D.getString("userId"));				
				//Long userId=101032L;							
				user.setUserId(userId);
				user.setMobile(data.getString("mobile"));
				user.setPassword(data.getString("password"));
				user.setInvitationCode(invitationCode);
				user.setCreatedTime(new Date());
				
				user.setUpdatedTime(user.getCreatedTime());			
				userService.saveUser(user);
				
				Device d=new Device();
				d.setDeviceId(inparams.getString("deviceId"));
				d.setPlatform(inparams.getString("platform"));
				d.setCreatedTime(user.getCreatedTime());
				d.setUpdatedTime(user.getUpdatedTime());
				d.setUserId(user.getId());
				d.setIdfa(idfa);
				d.setIdfv(idfv);
				userService.saveDevice(d);
				if(!invitationCode.equals(data.getString("invitationCode"))){//
					Long id=Long.parseLong(invitationCode);					
					Invitation ins=invitationService.find(Long.parseLong(invitationCode));
					
				}
				Juan j=new Juan();
				j.setFaceValue("8.8");
				j.setSource("新手注册");	
				
				j.setPromotionNumber(HDCODE.xszc1);				
				j.setCreatedTime(new Date());
				j.setUpdatedTime(j.getCreatedTime());
				j.setUserId(user.getId());
				j.setTel(user.getMobile());
				juanService.save(j);							
		}						
			Map res=(Map)JSON.parse(result.toString());			
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> res1=new HashMap<String,String>();
			res1.put("code", "FAIL");
			res1.put("message","失败");
			return res1;
		}			
	}
	/**
	 *4. 企业注册
	 * @throws IOException 
	 * 
	 */
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/cbclient/user/enterprise/register")
	@ResponseBody
	public Map goEnterPriseRegist(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		JSONObject data=inparams.getJSONObject("data");
		
		String uri="/cbclient/user/enterprise/register";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign",sign);
		System.out.println("企业注册req"+inparams);
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			System.out.println("企业注册res"+result);
			Map res=(Map)JSON.parse(result.toString());
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","注册失败");
			return m1;
		}		
	}
	//横幅
	///cbclient/api/banner/advertisements
	@RequestMapping(value="/cbclient/api/banner/advertisements")
	@ResponseBody
	public Map BannerAdvertise(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		inparams.remove("sign");
		JSONObject result=new JSONObject();
		result.put("requestId",inparams.getString("requestId"));
		result.put("code", "SUCCESS");
		result.put("message", "");
		JSONArray data=new JSONArray();
		for (int i = 0; i < 2; i++) {
			JSONObject banner=new JSONObject();			
			banner.put("id",i+1);
			banner.put("title","banner");
			banner.put("picUrl", IpConstant.getGoxIp()+"/adgox/img/banner"+(i+1)+".jpg");
			if((i+1)==1){
				banner.put("jumpUrl", "http://119.23.161.176/activity/askTicket/toup.html");
			}else if((i+1)==2){
				banner.put("jumpUrl","http://119.23.161.176/activity/buyGuide/index.html");
			}
			data.add(i,banner);//Nojump
		}
		result.put("data", data);
		Map res=(Map)JSON.parse(result.toString());
		return res;		
	}
	/*
	 *广播
	 */
	@RequestMapping(value="/cbclient/api/banner/SSGB")
	@ResponseBody
	public Map GB(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		List<Juan> list=juanService.findTodayTrank();//最新获取排行版
		JSONObject json=new JSONObject();
		json.put("code", "SUCCESS");
		
		JSONArray data=new JSONArray();
		
		for (int i = 0; i <list.size(); i++) {
			
			JSONObject gb=new JSONObject();
			String str=list.get(i).getTel();
			String s=str.substring(3,7);
			str=str.replace(s,"**");
			String award=list.get(i).getFaceValue();
			gb.put("id",i+1);
			gb.put("text","恭喜"+str+"用户获取"+award+"元体验券" );
			data.add(gb);
			
		}		
		json.put("data",data);
		Map res=(Map)JSON.parse(json.toString());
		response.addHeader("Access-Control-Allow-Origin", "*");//同意跨域请求
		return res;
		
	}
	
	
	
	
	///客服
	@RequestMapping(value="/cbclient/api/oauth/qq/query")
	@ResponseBody
	public Map QQkefu(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		System.out.println(params);
		JSONObject inparams=JSONObject.fromObject(params);
		inparams.remove("sign");
		
		String uri="/cbclient/api/oauth/qq/query";
		String url=IpConstant.getBaseIp()+uri;
		JSONObject json=JSONObject.fromObject(inparams.toString());
		Map<String,Object> n=ParamsGernateMap.ParamNew(json);
		n=SignSortMap.sort(n);
		String sign=SignSortMap.SignString(uri, n, SignSortMap.appSecret);
		sign=URLEncoder.encode(sign, "utf-8");
		inparams.put("sign",sign);
		try {
			JSONObject result=httpClientUtil.doPost(url, inparams);
			Map res=(Map)JSON.parse(result.toString());
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> m1=new HashMap<String,String>();
			m1.put("code", "FAIL");
			m1.put("message","注册失败");
			return m1;
		}		
	}	
		//QQ客服
		@RequestMapping(value = "/cbclient/api/oauth/qq/url/query")
		@ResponseBody
		public Map QQUrl(HttpServletRequest request)throws IOException {

			
			JSONObject outparams=new JSONObject();
			JSONObject data=new JSONObject();
			outparams.put("code", "SUCCESS");
			outparams.put("message", "获取客服QQ成功");
			data.put("qqUrl", "http://q.url.cn/abEN1n?_type=wpa&qidian=true");
			outparams.put("data",data);
			Map m=(Map)JSON.parse(outparams.toString());
			return m;
		}
		//预订时间标记-cbclient/api/open/flag
		@RequestMapping(value = "/cbclient/api/open/flag")
		@ResponseBody
		public Map FlagTime(HttpServletRequest request)throws IOException {

			// 从流中获取数据
			InputStream in = request.getInputStream();
			String params = IOUtils.toString(in, "utf-8");
			JSONObject inparams = JSONObject.fromObject(params);
			
			inparams.remove("sign");//
			String uri="/cbclient/api/open/flag";
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
				m1.put("message", "失败");
				return m1;
			}
		}

	// 分享-
		@RequestMapping(value = "/cbclient/index")
		public String ShareWeb(HttpServletRequest request) throws IOException {

			System.out.println("分享");
		

			String accessToken = request.getParameter("accessToken");
			User u = userService.findUser(accessToken);
			String invitationCode = u.getId() + "";
			String exchange = "CB_GUOXIANG";
			String memberChannel = "999";

			String url = IpConstant.getGoxIp()
				+ "/adgox/ddtj/index.html?invitationCode=" + invitationCode
				+ "&exchange=" + exchange + "&memberChannel=" + memberChannel;
			System.out.println("分享页面" + url);
			return "redirect:" + url;
		}
	
		// 帮助中心
		@RequestMapping(value = "/help/index")
		public String Help(HttpServletRequest request) throws IOException {
		
			String url=IpConstant.getBaseIp()+"/help/index.html";
			return "redirect:" + url;
		}
		
		//个人注册合并后的协议
		@RequestMapping(value = "/cbclient/lhProtocol.html")
		public String regiterProtocol(HttpServletRequest request) throws IOException {
		
			String url=IpConstant.getBaseIp()+ "/cbclient/lhProtocol.html";
			return "redirect:" + url;
		}
		//免责声明
		@RequestMapping("/mzsm/H5")
		public String mzsmH5(){
			String url=IpConstant.getGoxIp()+"/adgox/H5/mzsm.html";
			return "redirect:"+url;
		}
		@RequestMapping("/cbclient/app-keys")
		@ResponseBody
		public Map getAppKey(){
			JSONObject inparams= new JSONObject();
			inparams.put("code", "SUCCESS");
			JSONObject data=new JSONObject();
			data.put("appSecret", SignSortMap.appSecret);
			data.put("appKey", SignSortMap.appKey);
			inparams.put("data", data);
			Map m=(Map)JSON.parse(inparams.toString());
			return m;
		}
}

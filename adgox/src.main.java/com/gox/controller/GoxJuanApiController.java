package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Device;
import com.gox.manage.model.Invitation;
import com.gox.manage.model.Juan;
import com.gox.manage.model.User;
import com.gox.manage.service.InvitationService;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.HDCODE;
import com.gox.manage.util.HttpClientUtil;
import com.gox.manage.util.IpConstant;
import com.gox.manage.util.ParamsGernateMap;
import com.gox.manage.util.SignSortMap;
import com.mongodb.util.JSON;

@Controller
@SuppressWarnings("all")
public class GoxJuanApiController {
	@Autowired	
	private UserService userService;
	@Autowired
	private JuanService juanService;

	@RequestMapping("/bybx/lq/{userId}")//白银宝箱领取
	@ResponseBody
	public Map  Bybxlq(@PathVariable("userId") Long userId) throws Exception{
		JSONObject jsonparams=new JSONObject();
		JSONObject d=new JSONObject();
		
		Juan j=null;
		List<Juan> list=juanService.findByUserId(userId);
		if(list.size()==0){
			jsonparams.put("code", "Y1");//已领取过
		}else{
			j=list.get(0);
			Juan juan=juanService.findLqTime(userId);
			if(juan!=null){//卷不为空根据领取时间判断
				Long utime=juan.getUpdatedTime().getTime()+24L*60*60*1000;
				Long ntime=new Date().getTime();
				if(ntime<utime){
					jsonparams.put("code","Y1");
				}else{
					jsonparams.put("code","Y");
					d.put("facevalue",j.getFaceValue());
					jsonparams.put("data", d);
					j.setUpdatedTime(new Date());
					j.setState("Y");
					juanService.updateBybx(j);				
				}				
			}else{//为空则立即领取
				jsonparams.put("code","Y");
				d.put("facevalue",j.getFaceValue());
				jsonparams.put("data", d);
				j.setUpdatedTime(new Date());
				j.setState("Y");
				juanService.updateBybx(j);	
				
			}			
		}
		if("Y".equals(jsonparams.getString("code"))){
			User u=userService.findUserById(userId);
			Device de=userService.findDevice(u.getId());
			JSONObject inparams=new JSONObject();
			inparams.put("exchange", "CB_GUOXIANG");
			inparams.put("memberChannel", u.getMemberChannel());
			inparams.put("timestamp", new Date().getTime());
			inparams.put("appKey",SignSortMap.appKey);
			inparams.put("platform", "server");
			inparams.put("version", "2.1.0");
			inparams.put("deviceId", de.getDeviceId());
			String requestId=UUID.randomUUID().toString();
			requestId=URLEncoder.encode(requestId,"utf-8");
			inparams.put("requestId",requestId);
			JSONObject data=new JSONObject();
			data.put("promotionNumber",j.getPromotionNumber() );
			data.put("userId", u.getUserId());
			inparams.put("data", data);
			
			String uri="/cbclient/promotion/sendCoupon";
			String url=IpConstant.getBaseIp()+uri;
			JSONObject json=JSONObject.fromObject(inparams.toString());
			Map<String,Object> n=ParamsGernateMap.ParamNew(json);
			n=SignSortMap.sort(n);
			String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
			sign=URLEncoder.encode(sign,"utf-8");
			inparams.put("sign", sign);			
			//System.out.println("送卷req"+inparams);
			try {
				JSONObject result = HttpClientUtil.doPost(url, inparams);
				//System.out.println("送卷res"+result);
				
					
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("送券失败");
			}		
		}		
		Map m=(Map)JSON.parse(jsonparams.toString());		
		return m;
	}
	//黄金宝箱领取
	@RequestMapping("/hjbx/lq/{userId}")
	@ResponseBody
	public Map  hjbxlq(@PathVariable("userId") Long userId) throws Exception{
		JSONObject jsonparams=new JSONObject();
		JSONObject d=new JSONObject();		
		List<Juan> list=juanService.findHjById(userId);
		Juan juan=juanService.findHJnew(userId);//最近领的券
		Long otime=0L;
		if(juan!=null){			
			otime=juan.getUpdatedTime().getTime()+24L*60*60*1000;
		}
		
		Long ntime=new Date().getTime();
		if(list.size()==0){
			jsonparams.put("code","Y1");
			
		}else{			
			if(otime<ntime||otime==0){
				Juan j=list.get(0);
				jsonparams.put("code","Y");				
				d.put("facevalue",j.getFaceValue());
				if(j.getTreasureMap()!=null){
					d.put("treasureMap",j.getTreasureMap() );
					
				}
				j.setState("Y");
				j.setUpdatedTime(new Date());
				jsonparams.put("data", d);
				juanService.updateBybx(j);//发卷				
				User u=userService.findUserById(userId);
				Device de=userService.findDevice(u.getId());
				JSONObject inparams=new JSONObject();
				inparams.put("exchange", "CB_GUOXIANG");
				inparams.put("memberChannel", u.getMemberChannel());
				inparams.put("timestamp", new Date().getTime());
				inparams.put("appKey",SignSortMap.appKey);
				inparams.put("platform", "server");
				inparams.put("version", "2.1.0");
				inparams.put("deviceId", de.getDeviceId());
				String requestId=UUID.randomUUID().toString();
				requestId=URLEncoder.encode(requestId,"utf-8");
				inparams.put("requestId",requestId);
				JSONObject data=new JSONObject();
				data.put("promotionNumber", j.getPromotionNumber());
				data.put("userId", u.getUserId());
				inparams.put("data", data);
				
				String uri="/cbclient/promotion/sendCoupon";
				String url=IpConstant.getBaseIp()+uri;
				JSONObject json=JSONObject.fromObject(inparams.toString());
				Map<String,Object> n=ParamsGernateMap.ParamNew(json);
				n=SignSortMap.sort(n);
				String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
				sign=URLEncoder.encode(sign,"utf-8");
				inparams.put("sign", sign);			
				try {
					JSONObject result = HttpClientUtil.doPost(url, inparams);
						
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println("送券失败");
				}
			}else{
				jsonparams.put("code", "Y1");
			}			
		}
		Map m=(Map)JSON.parse(jsonparams.toString());
		
		return m;
	}
		//邀请获取情况//钻石礼包
		@RequestMapping("/Share/lq/{userId}")
		@ResponseBody
		public Map ShareLq(@PathVariable("userId") Long userId) throws Exception{
			List<Juan> list=juanService.findShareLB(userId);
			JSONObject jsonparams=new JSONObject();
			JSONObject d=new JSONObject();
			if(list.size()==0){
				jsonparams.put("code","N");
			}else{
				jsonparams.put("code", "Y");
				Juan j=list.get(0);
				d.put("facevalue", j.getFaceValue());
				if(j.getTreasureMap()!=null){
					d.put("treasureMap", j.getTreasureMap());
					
				}
				jsonparams.put("data", d);
				j.setState("Y");
				juanService.updateBybx(j);
				
				User u=userService.findUserById(userId);
				Device de=userService.findDevice(u.getId());
				JSONObject inparams=new JSONObject();
				inparams.put("exchange", "CB_GUOXIANG");
				inparams.put("memberChannel", u.getMemberChannel());
				inparams.put("timestamp", new Date().getTime());
				inparams.put("appKey",SignSortMap.appKey);
				inparams.put("platform", "server");
				inparams.put("version", "2.1.0");
				inparams.put("deviceId", de.getDeviceId());
				String requestId=UUID.randomUUID().toString();
				requestId=URLEncoder.encode(requestId,"utf-8");
				inparams.put("requestId",requestId);
				JSONObject data=new JSONObject();
				data.put("promotionNumber", j.getPromotionNumber());
				data.put("userId", u.getUserId());
				inparams.put("data", data);
				
				String uri="/cbclient/promotion/sendCoupon";
				String url=IpConstant.getBaseIp()+uri;
				JSONObject json=JSONObject.fromObject(inparams.toString());
				Map<String,Object> n=ParamsGernateMap.ParamNew(json);
				n=SignSortMap.sort(n);
				String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
				sign=URLEncoder.encode(sign,"utf-8");
				inparams.put("sign", sign);			
				//System.out.println("送卷req"+inparams);
				try {
					JSONObject result = HttpClientUtil.doPost(url, inparams);
					//System.out.println("送卷res"+result);
						
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println("送券失败");
				}				
			}
			Map m=(Map)JSON.parse(jsonparams.toString());
			return m;		
		}
		//领取最终大礼包
		@RequestMapping("/zzdl/lq/{userId}")
		@ResponseBody
		public Map ZZDL(@PathVariable("userId") Long userId) throws Exception{
			
			JSONObject dataparams=new JSONObject();
			
			List<Juan> list1=juanService.findZZLB(userId);
			List<Juan> list2=null;			
			if(list1.size()!=0){
				list2=juanService.findZZLBX(userId);
			}
			if(list2!=null){
				Juan j1=list1.get(0);
				j1.setTreasureMap("Y1");
				juanService.updateBybx(j1);
				
				Juan j2=list2.get(0);				
				j2.setTreasureMap("Y2");
				juanService.updateBybx(j2);
				int i=(int)(Math.random()*100)+1;//15%,40%,35%,1%,2%,7%
				Juan j=new Juan();
				if(i<=85){
					j.setFaceValue("8.8-58白银通用");
					j.setPromotionNumber(HDCODE.hrlx1);
				}else if(i>=86&&i<=90){
					j.setFaceValue("8.8-138全部通用");
					j.setPromotionNumber(HDCODE.hrlx3);
				}else if(i>=91&&i<=95){
					j.setFaceValue("9.8-138黄金通用");
					j.setPromotionNumber(HDCODE.hrlx2);
				}else if(i>=96&&i<=98){
					j.setFaceValue("100元话费");
					
					
				}else if(i>=99&&i<=100){
					j.setFaceValue("京东卡500元");
				}
				User u=userService.findUserById(userId);
				
				j.setSource("鸿运大礼");
				j.setCreatedTime(new Date());
				j.setUpdatedTime(j.getCreatedTime());
				j.setUserId(userId);
				j.setState("Y");
				j.setTel(u.getMobile());
				dataparams.put("facevalue", j.getFaceValue());//返回信息
				juanService.save(j);
				
				if(j.getPromotionNumber()!=null){
					Device de=userService.findDevice(u.getId());
					JSONObject inparams=new JSONObject();
					inparams.put("exchange", "CB_GUOXIANG");
					inparams.put("memberChannel", u.getMemberChannel());
					inparams.put("timestamp", new Date().getTime());
					inparams.put("appKey",SignSortMap.appKey);
					inparams.put("platform", "server");
					inparams.put("version", "2.1.0");
					inparams.put("deviceId", de.getDeviceId());
					String requestId=UUID.randomUUID().toString();
					requestId=URLEncoder.encode(requestId,"utf-8");
					inparams.put("requestId",requestId);
					JSONObject data=new JSONObject();
					data.put("promotionNumber", j.getPromotionNumber());
					data.put("userId", u.getUserId());
					inparams.put("data", data);
					
					String uri="/cbclient/promotion/sendCoupon";
					String url=IpConstant.getBaseIp()+uri;
					JSONObject json=JSONObject.fromObject(inparams.toString());
					Map<String,Object> n=ParamsGernateMap.ParamNew(json);
					n=SignSortMap.sort(n);
					String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
					sign=URLEncoder.encode(sign,"utf-8");
					inparams.put("sign", sign);			
					//System.out.println("送卷req"+inparams);
					try {
						JSONObject result = HttpClientUtil.doPost(url, inparams);
						//System.out.println("送卷res"+result);
							
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
									
			}else{
				dataparams.put("facevalue", null);
			}
			
			Map m=(Map)JSON.parse(dataparams.toString());
			
			return m;
		}
		@RequestMapping("/Jiangpin/Query/{userId}")
		@ResponseBody
		public Map JiangpinQuery(@PathVariable("userId") Long userId){
			List<Juan> list=juanService.findAllAward(userId);
			JSONObject inparams=new JSONObject();
			JSONObject data=new JSONObject();
			
			int count1=0;
			int count2=0;
			int count3=0;
			int count4=0;
			int count5=0;
			int count6=0;
			int count7=0;
			int count8=0;
			int count9=0;
			if(list.size()==0){
				inparams.put("state", 0);
			}else{
				inparams.put("state", 1);
				for (Juan juan : list) {
					if("8.8".equals(juan.getFaceValue())){
						count1++;						
					}else if("9.8".equals(juan.getFaceValue())){
						count2++;
					}else if("32".equals(juan.getFaceValue())){
						count3++;
					}else if("58".equals(juan.getFaceValue())){
						count4++;
					}else if("68".equals(juan.getFaceValue())){
						count5++;
					}else if("138".equals(juan.getFaceValue())){
						count6++;
					}else if("8.8-58白银通用".equals(juan.getFaceValue())){
						count7=1;
					}else if("9.8-138黄金通用".equals(juan.getFaceValue())){
						count7=2;
					}else if("8.8-138全部通用".equals(juan.getFaceValue())){
						count7=3;
					}else if("100元话费".equals(juan.getFaceValue())){
						count7=4;
					}else if("京东卡500元".equals(juan.getFaceValue())){
						count7=5;
					}else if("京东卡1000元".equals(juan.getFaceValue())){
						count7=6;
					}
					if("N1".equals(juan.getTreasureMap())){
						count8++;
					}
					if("N2".equals(juan.getTreasureMap())){
						count9=1;
					}					
				}
				
				data.put("quan1", count1);//8.8
				data.put("quan2", count2);//9.8
				data.put("quan3", count3);//32
				data.put("quan4", count4);//58
				data.put("quan5", count5);//68
				data.put("quan6", count6);//138
				data.put("ty", count7);//8.8-58...
				data.put("s", count8);//
				data.put("x", count9);//
				inparams.put("data", data);		
			}
			Map m=(Map)JSON.parse(inparams.toString());
			return m;
		}
	
	
		
		
		
		
		
		
	
	
		//送卷接口
		@RequestMapping("/cbclient/promotion/sendCoupon")
		@ResponseBody
		public Map sendCoupon(HttpServletRequest request) throws IOException{
			InputStream in=request.getInputStream();
			String params=IOUtils.toString(in,"utf-8");
			JSONObject inparams=JSONObject.fromObject(params);
			inparams.remove("sign");
			String uri="/cbclient/promotion/sendCoupon";
			String url=IpConstant.getBaseIp()+uri;
			JSONObject json=JSONObject.fromObject(inparams.toString());
			Map<String,Object> n=ParamsGernateMap.ParamNew(json);
			n=SignSortMap.sort(n);
			String sign=SignSortMap.SignString(uri, n,SignSortMap.appSecret);
			sign=URLEncoder.encode(sign,"utf-8");
		
			inparams.put("sign", sign);			
			//System.out.println("送卷req"+inparams);
		try {
			JSONObject result = HttpClientUtil.doPost(url, inparams);
			
			Map map = (Map)JSON.parse(result.toString());	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("code", "FAIL");
			m1.put("message", "送卷失败");
			return m1;
		}
		
		
	}

}

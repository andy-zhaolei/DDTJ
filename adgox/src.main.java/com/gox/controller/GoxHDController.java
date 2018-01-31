package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.User;
import com.gox.manage.service.UserService;
import com.gox.manage.util.IpConstant;

@Controller
@SuppressWarnings("all")
public class GoxHDController {
	@Autowired
	private UserService userService;

	@RequestMapping("/HD")
	@ResponseBody
	public Map demo(HttpServletRequest request) throws IOException{
		//引导图
			/*System.out.println("HD");
			JSONObject data=new JSONObject();
			int num=(int)(Math.random()*2);
			if(num==0){				
				data.put("image", IpConstant.getGoxIp()+"/adgox/img/mn1.png");
			}else{				
				data.put("image",  IpConstant.getGoxIp()+"/adgox/img/mn2.jpg");
			}			
			data.put("time", 8.0);
			data.put("url","Nojump");//
*/			JSONObject jsonparams=new JSONObject();
			
			jsonparams.put("code", "FAIL");
			jsonparams.put("message", "活动页面获取成功");
			//jsonparams.put("data", data);
			Map m=new HashMap();
			m=(Map)JSON.parse(jsonparams.toString());
			return m;	 
			/*
			  {
			  "code":"SUCCESS",
			 data: {
			  	"image":"http://ddtj.shzstr.cn/adgox/img/mn1.png",//图片url
			  	"time":"8.0",//图片停留时间
			  	"url":"https://www.baidu.com" //若点击跳到url
			  }
			  
			  }
			 */
	}
	@RequestMapping("/HD/index/{accessToken}")
	public String HDH5(@PathVariable String accessToken){
		User u=userService.findUser(accessToken);
		long userId=0;
		if(u==null){
			userId=0;
		}else{
			userId=u.getId();
		}
		//传参格式
		String url=IpConstant.getGoxIp()+"/adgox/HD/index.html"+"?userId="+userId;
		//System.out.println("百宝箱跳转url"+url);
		return "redirect:"+url;
	}

}

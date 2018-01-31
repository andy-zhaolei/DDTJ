package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Juan;
import com.gox.manage.model.Order;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.OrderService;
import com.mongodb.util.JSON;


@Controller
@SuppressWarnings("all")
public class GoxOrderTypeCount {
	@Autowired
	private OrderService orderService;
	@Autowired
	private JuanService juanService;
	
	@RequestMapping("/ordertype/rate")
	@ResponseBody
	public Map OrderRate(HttpServletRequest request) throws IOException{
		
		Map<String,Long> n=orderService.findOrderRate();
		long sum=n.get("SUM");
		long bysum=n.get("BYSUM");
		long byxj=n.get("BYXJ");
		long hjjsj=n.get("HJJSJ");
		
		long rate1=50;
		if(bysum!=0){
			rate1=byxj*100/bysum;
		}
		long rate2=100-rate1;
		
		long rate3=50;
		if((sum-bysum)!=0){
			rate3=hjjsj*100/(sum-bysum);
		}
		long rate4=100-rate3;
		
		JSONObject inparams=new JSONObject();
		JSONObject data=new JSONObject();
		inparams.put("code", "SUCCESS");
		data.put("BYXJ",rate1);
		data.put("BYJSJ",rate2);
		data.put("HJXJ", rate4);
		data.put("HJJSJ",rate3);
		inparams.put("data", data);
		Map m=(Map)JSON.parse(inparams.toString());
		return m;
	}
	//用户今日订单查询
	@RequestMapping("/order/query/{userId}")
	@ResponseBody
	public String OrderQuerys(@PathVariable("userId") Long userId){
		
		System.out.println(userId);
		List<Order> list=orderService.findOrder(userId);
		Juan juan=juanService.findJuanByNew(userId);
		System.out.println("juan"+juan);
		System.out.println("list"+list);
		if(list!=null){
			Order r=list.get(0);
			Long dtime=new Date().getTime();
			Long ltime=r.getCreatedTime().getTime()+24L*60*60*1000;			
			if(ltime>dtime&&juan!=null){//完成今日订购
				return "Y";
			}			
		}
		return "N";
	}
	
}

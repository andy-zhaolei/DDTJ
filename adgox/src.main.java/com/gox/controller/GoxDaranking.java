package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.OrderRecord;
import com.gox.manage.model.User;
import com.gox.manage.service.OrderRecordService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.IpConstant;

@Controller
@SuppressWarnings("all")
public class GoxDaranking {//排行版
	@Autowired
	private UserService userService;
	@Autowired
	private OrderRecordService orderRecordService;
	
	@RequestMapping("/talent/ranking")
	@ResponseBody
	public  Map TaRanking(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		
		JSONObject DataResult=new JSONObject();
		JSONArray data=new JSONArray();
		List<OrderRecord> list=orderRecordService.findAll();
		String GoxIp=IpConstant.getGoxIp();
		for (int i = 0; i < list.size(); i++) {
				OrderRecord or=list.get(i);
				JSONObject d=new JSONObject();
				d.put("profit", or.getPlamount());
				d.put("phone", or.getPhone());
				int j=(int)(Math.random()*3)+1;
				d.put("image",GoxIp+"/adgox/img/touxiang"+j+".jpg");
				data.add(i, d);
			}
		DataResult.put("data", data);
		Map m=(Map)JSON.parse(DataResult.toString());
		return m;		
	}

}

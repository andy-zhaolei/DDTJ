package com.gox.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

import com.gox.manage.model.Invitation;
import com.gox.manage.model.User;
import com.gox.manage.service.InvitationService;
import com.gox.manage.service.UserService;
import com.mongodb.util.JSON;

@Controller
@SuppressWarnings("all")
public class GoxInvitationSum {
	//暂时无用//统计邀请数量
	
	/*@RequestMapping("/invitation/sum")//
	@ResponseBody
	public Map InvitationSum(HttpServletRequest request) throws IOException{
		InputStream in=request.getInputStream();
		String params=IOUtils.toString(in,"utf-8");
		JSONObject inparams=JSONObject.fromObject(params);
		String accessToken=inparams.getString("accessToken");
		User u=userService.findUser(accessToken);
		Long userId=u.getId();		
		Invitation inv=invitationService.find(userId);
		
		JSONObject p=new JSONObject();
		JSONObject data=new JSONObject();	
		p.put("code","SUCCESS");
		if(inv==null||inv.getInvitationSum()==null){
			data.put("sum",0);
		}else{			
			data.put("sum",inv.getInvitationSum());
		}
		p.put("data", data);
		Map m=(Map)JSON.parse(p.toString());		
		return m;
	}*/
	
	

}

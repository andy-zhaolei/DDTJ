package com.gox.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gox.manage.model.Invitation;
import com.gox.manage.model.Juan;
import com.gox.manage.model.Order;
import com.gox.manage.model.User;
import com.gox.manage.service.InvitationService;
import com.gox.manage.service.JuanService;
import com.gox.manage.service.OrderService;
import com.gox.manage.service.UserService;
import com.gox.manage.util.HDCODE;

@Controller
@SuppressWarnings("all")
public class GoxImgState {
	@Autowired
	private UserService userService;
	@Autowired
	private JuanService juanService;
	@Autowired
	private InvitationService invitationService;
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/H5/state/{userId}")
	@ResponseBody
	public Map Imgsate(@PathVariable("userId") Long userId){
		//白银宝箱状态
		//黄金宝箱状态
		//钻石礼包状态
		//狗年运礼包状态		
		JSONObject inparams=new JSONObject();
		JSONObject data=new JSONObject();
		int bycount=0;
		int hjcount=0;
		int zscount=0;
		int gncount=0;
		int zzdl=0;
		int orderSum=0;
		
		Juan zsj=juanService.findZslb(userId);//钻石礼包情况
		//邀请人数
		Invitation ins=invitationService.findInvitateSum(userId);
		if(ins!=null&&ins.getInvitationSum()==5){
			//钻石礼包
			List<User> userList=userService.findUserByInvitationCode(userId);//invitationCode
			if(userList.size()!=0){
				for (User user : userList) {//批量查询订购
					List<Order> orderList=orderService.findOrder(user.getId());
					if(orderList.size()!=0){////////////
							orderSum++;						
					}
				}
				
			}			
			User user=userService.findUserById(userId);
			Juan j=new Juan();
			int i=(int)(Math.random()*100)+1;//
			if(i<=90){
				j.setFaceValue("32");
				j.setPromotionNumber(HDCODE.zsbx1);
			}else if(i>=91&&i<=95){
				j.setFaceValue("58");
				j.setPromotionNumber(HDCODE.zsbx2);
				
			}else if(i>=96&&i<=99){
				j.setFaceValue("68");
				j.setPromotionNumber(HDCODE.zsbx3);
				
			}else if(i==100){
				j.setFaceValue("138");
				j.setPromotionNumber(HDCODE.zsbx4);	
			}
			if(i>=1&&i<=50){
				j.setTreasureMap("N2");
			}
			j.setSource("钻石礼包");
			j.setState("N");
			j.setCreatedTime(new Date());
			j.setUpdatedTime(j.getCreatedTime());
			j.setTel(user.getMobile());
			j.setUserId(userId);
			juanService.save(j);					
		}
		List<Juan> list=juanService.findAllByuserId(userId);
		
		List<Juan> list1=juanService.findZZLB(userId);//查询上卷()
		List<Juan> list2=null;
		if(list1.size()!=0){//若有则查询下卷
			list2=juanService.findZZLBX(userId);
			if(list2.size()!=0){
				zzdl=1;
				data.put("gnlb",zzdl);
			}
		}
		
		if(list.size()==0){//邀请人数必须查
			inparams.put("code", "N");
			
			if(ins==null){
				data.put("inv", 0);
			}else{
				data.put("inv", ins.getInvitationSum());
			}
			inparams.put("data",data);
			
		}else{
			for (Juan juan : list) {				
				if("白银宝箱".equals(juan.getSource())){
					bycount++;
				}
				if("黄金宝箱".equals(juan.getSource())){
					hjcount++;
				}				
				if("钻石礼包".equals(juan.getSource()) ){
					zscount=1;
				}					
			}			
			if(zsj!=null){
				zscount=-1;
			}
			if(ins==null){
				data.put("inv", 0);
			}else{
				data.put("inv", ins.getInvitationSum());
			}
			data.put("bybx", bycount);
			data.put("hjbx", hjcount);
			data.put("zslb", zscount);
			inparams.put("code", "Y");
			inparams.put("data", data);
		}
		Map m=(Map)JSON.parse(inparams.toString());	
		return m;
	}

}

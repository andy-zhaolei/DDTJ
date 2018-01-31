package com.gox.manage.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gox.manage.dao.InvitationDao;
import com.gox.manage.model.Invitation;
import com.gox.manage.model.User;
import com.gox.manage.service.InvitationService;
@Repository
@SuppressWarnings("all")
public class InvitationDaoImpl extends BaseDao implements InvitationDao{

	@Override
	public Invitation find(Long id) {
		User u=this.hibernateTemplate.get(User.class, id);//邀请码是邀请人的id
		List<Invitation> list=this.hibernateTemplate.find("from Invitation i where i.userId=?",id);
		Invitation inv=null;
		if(list.size()==0){
			inv=new Invitation();
			inv.setUserId(id);
			inv.setInvitationCode(id+"");
			inv.setCreatedTime(new Date());
			inv.setUpdatedTime(inv.getCreatedTime());
			inv.setInvitationSum(1L);
			inv.setUserName(u.getMobile());
			this.hibernateTemplate.save(inv);
		}else{
			inv=list.get(0);
			long sum=inv.getInvitationSum();
			inv.setInvitationSum(sum+1L);
			inv.setUpdatedTime(new Date());
			this.hibernateTemplate.update(inv);
		}
		return inv ;
	}

	@Override
	public Invitation findInvitateSum(Long userId) {
		List<Invitation> list=this.hibernateTemplate.find("from Invitation i where i.userId=?",userId);
		if(list.size()==0){
			return null;
		}else{
			
			return list.get(0);
		}
	}


	

}

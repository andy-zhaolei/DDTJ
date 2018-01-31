package com.gox.manage.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gox.manage.dao.UserDao;
import com.gox.manage.model.Address;
import com.gox.manage.model.Device;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.Recevinginfo;
import com.gox.manage.model.User;



@Repository("userDao")
@SuppressWarnings("all")
public class UserDaoImpl extends BaseDao  implements UserDao{

	

	public void saveUser(User user) {
		
		this.hibernateTemplate.save(user);
	
		
	}

	@Override
	public void update(String mobile, String password) {
		User u=(User)this.hibernateTemplate.find("from User where mobile=?",mobile);
		u.setPassword(password);
		this.hibernateTemplate.update(u);
	}

	@Override
	public void saveDevice(Device d) {
		this.hibernateTemplate.save(d);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public User find(String mobile, String password) {
		List<User> u=new ArrayList<User>();
		u=this.hibernateTemplate.find("from User where mobile=? and password=?",mobile,password);
		if(u.size()==0){
			return null;
		}else{
			return u.get(0);
		}	
		
	}

	@Override
	public void saveRecevinginfo(Recevinginfo r) {
		this.hibernateTemplate.save(r);
			
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(Long userId, User u,Device device) {
		User user=null;
		List<User> list= hibernateTemplate.find("from User where userId=? ", userId);
		
		System.out.println(list.size());
		
		
		if(list!=null&&list.size()!=0){
			user=list.get(0);		
			List<Device> list1=this.hibernateTemplate.find("from Device where userId=?",user.getId());
			if(list1.size()==0){
				device.setUserId(user.getId());
				this.hibernateTemplate.save(device);
			}
			user.setUserName(u.getUserName());
			user.setSex(u.getSex());
			user.setUpdatedTime(new Date());
			user.setMemberUnitsId(u.getMemberUnitsId());
			user.setHasFundPassword(u.getHasFundPassword());
			user.setWid(u.getWid());
			user.setFlag(u.getFlag());
			user.setAccountMemberunitId(u.getAccountMemberunitId());
			user.setAccountUserId(u.getAccountUserId());
			user.setUnitNo(u.getUnitNo());
			user.setIsRealName(u.getIsRealName());
			user.setAccessToken(u.getAccessToken());
			user.setRefreshToken(u.getRefreshToken());
			user.setAccessTokenExpireTime(u.getAccessTokenExpireTime());
			user.setRefreshTokenExpireTime(u.getRefreshTokenExpireTime());
			user.setUserType(u.getUserType());
			user.setMemberChannel(u.getMemberChannel());		
			hibernateTemplate.update(user);
			
			
			
		}else{
			u.setCreatedTime(new Date());
			u.setUpdatedTime(u.getCreatedTime());
			u.setUserId(userId);			
			this.hibernateTemplate.save(u);
			device.setUserId(u.getId());
			device.setCreatedTime(u.getCreatedTime());
			device.setUpdatedTime(u.getUpdatedTime());			
			this.hibernateTemplate.save(device);	
		}			
	}

	@Override
	public void saveRegisterJF(Jifen jf) {
		this.hibernateTemplate.save(jf);	
	}

	@Override
	public User finUserByAccessToken(String accessToken) {
		List<User> list=hibernateTemplate.find("from User where accessToken=?",accessToken);
		User u=null;
		if(list.size()!=0){			
			u=list.get(0);
		}
		return u;
	}

	@Override
	public void saveAddress(Address address) {
		this.hibernateTemplate.save(address);
		
	}

	@Override
	public void delete(Address address) {
		List<Address> list=this.hibernateTemplate.find("from Address a where a.addressId=?",address.getAddressId());
		address=list.get(0);
		this.hibernateTemplate.delete(address);		
	}

	@Override
	public void updateAccessToken(String oldAccessToken, String accessToken,String accessTokenExpireTime) {
		List<User> list=hibernateTemplate.find("from User where accessToken=?",oldAccessToken);
		User u=list.get(0);
		u.setAccessToken(accessToken);
		Long tokenTime=Long.parseLong(accessTokenExpireTime);
		u.setAccessTokenExpireTime(new Date(tokenTime));
		u.setUpdatedTime(new Date());
		this.hibernateTemplate.update(u);
		
	}

	@Override
	public User finUserBy(String accessToken) {
		List<User> list=hibernateTemplate.find("from User where accessToken=?",accessToken);
		User u=list.get(0);
		return u;
	}

	@Override
	public User findUserById(Long uId) {
		List<User> u=this.hibernateTemplate.find("from User where id=?",uId);
		
		return u.get(0);
	}

	@Override
	public Device findDevice(Long id) {
		List<Device> d=this.hibernateTemplate.find("from Device where userId=?",id);
		
		return d.get(0);
	}

	
	@Override
	public User findUserByPhone(String mobile) {
		List<User> list=this.hibernateTemplate.find("from User where mobile=?",mobile);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
		
	}

	@Override
	public List<User> findUserByInvitationCode(Long userId) {//邀请码
		List<User> list=this.hibernateTemplate.find("from User where invitationCode=?",userId);
		return list;
	}

	
}














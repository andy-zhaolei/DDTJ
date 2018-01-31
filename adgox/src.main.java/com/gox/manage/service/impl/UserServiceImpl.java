package com.gox.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.UserDao;
import com.gox.manage.model.Address;
import com.gox.manage.model.Device;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.Recevinginfo;
import com.gox.manage.model.Record;
import com.gox.manage.model.User;
import com.gox.manage.service.UserService;
@Service
@Scope("prototype")
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	public void saveUser(User user) {
		userDao.saveUser(user);
		
	}

	@Override
	public void update(String mobile, String password) {
		userDao.update(mobile,password);
	}

	@Override
	public void saveDevice(Device d) {
		userDao.saveDevice(d);
		
	}

	@Override
	public void updateUser(Long userId, User u,Device device) {
		userDao.updateUser(userId,u,device);
		
	}

	@Override
	public void saveRegisterJF(Jifen jf) {
		userDao.saveRegisterJF(jf);
	}

	@Override
	public User findUser(String accessToken) {
		return 	userDao.finUserByAccessToken(accessToken);
	}

	@Override
	public void saveAddress(Address address) {
		userDao.saveAddress(address);
		
	}

	@Override
	public void delete(Address address) {
		userDao.delete(address);
		
	}

	@Override
	public void updateAccessToken(String oldAccessToken, String accessToken,String accessTokenExpireTime) {
		userDao.updateAccessToken(oldAccessToken,accessToken,accessTokenExpireTime);
		
	}

	@Override
	public User findUserByAccessToken(String accessToken) {
		
		return userDao.finUserBy(accessToken);
	}

	@Override
	public User findUserById(Long uId) {
		
		return userDao.findUserById(uId);
	}

	@Override
	public Device findDevice(Long id) {
		
		return userDao.findDevice(id);
	}

	

	@Override
	public User findUserByPhone(String mobile) {
		return userDao.findUserByPhone(mobile);
		

	}

	@Override
	public List<User> findUserByInvitationCode(Long userId) {
		
		return userDao.findUserByInvitationCode(userId);
	}

	


}

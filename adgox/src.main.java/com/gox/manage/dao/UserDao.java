package com.gox.manage.dao;

import java.util.List;

import com.gox.manage.model.Address;
import com.gox.manage.model.Device;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.Recevinginfo;
import com.gox.manage.model.User;




public interface UserDao { 
	
	public void saveUser(User user);

	public void update(String mobile, String password);

	public void saveDevice(Device d);

	public User find(String mobile, String password);

	public void saveRecevinginfo(Recevinginfo r);

	public void updateUser(Long userId, User u,Device device);

	public void saveRegisterJF(Jifen jf);

	public User finUserByAccessToken(String accessToken);

	public void saveAddress(Address address);

	public void delete(Address address);

	public void updateAccessToken(String oldAccessToken, String accessToken, String accessTokenExpireTime);

	public User finUserBy(String accessToken);

	public User findUserById(Long uId);

	public Device findDevice(Long id);

	public User findUserByPhone(String mobile);

	public List<User> findUserByInvitationCode(Long userId);

	
}


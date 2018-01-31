package com.gox.manage.service;

import java.util.List;

import com.gox.manage.model.Address;
import com.gox.manage.model.Device;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.Recevinginfo;
import com.gox.manage.model.Record;
import com.gox.manage.model.User;


public interface UserService {
	public void saveUser(User user);

	public void update(String mobile, String password);

	public void saveDevice(Device d);


	public void updateUser(Long userId, User u,Device device);

	public void saveRegisterJF(Jifen jf);

	public User findUser(String accessToken);

	public void saveAddress(Address address);

	public void delete(Address address);

	public void updateAccessToken(String oldAccessToken, String accessToken, String accessTokenExpireTime);

	public User findUserByAccessToken(String string);

	public User findUserById(Long uId);

	public Device findDevice(Long id);


	public User findUserByPhone(String string);

	public List<User> findUserByInvitationCode(Long userId);

	
}

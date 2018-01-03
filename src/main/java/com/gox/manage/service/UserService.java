package com.gox.manage.service;

import java.util.List;

import com.gox.manage.model.User;

public interface UserService {

	List<User> findAll();

	void deleteUser(Long id);

}

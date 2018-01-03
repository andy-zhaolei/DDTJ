package com.gox.manage.dao;

import java.util.List;

import com.gox.manage.model.User;

public interface UserDao {

	List<User> findAll();

	void deleteUser(Long id);

}

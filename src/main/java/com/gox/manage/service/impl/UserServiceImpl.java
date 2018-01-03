package com.gox.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.UserDao;
import com.gox.manage.model.User;
import com.gox.manage.service.UserService;
@Service
@Scope("prototype")
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class UserServiceImpl implements UserService{
	@Autowired
	private  UserDao userDao;

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
		
	}

}

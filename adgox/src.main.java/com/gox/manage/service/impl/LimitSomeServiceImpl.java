package com.gox.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.LimitSomeDao;
import com.gox.manage.model.LimitSome;
import com.gox.manage.service.LimitSomeService;
@Scope("prototype")
@Service
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class LimitSomeServiceImpl implements LimitSomeService {
	@Autowired
	private LimitSomeDao limitSomeDao;

	@Override
	public LimitSome findwithdraw() {
		
		return limitSomeDao.findwithdraw();
	}

}

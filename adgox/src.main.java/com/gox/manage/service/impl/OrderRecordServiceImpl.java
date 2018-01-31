package com.gox.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.OrderRecordDao;
import com.gox.manage.model.OrderRecord;
import com.gox.manage.service.OrderRecordService;
@Service
@Scope("prototype")
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class OrderRecordServiceImpl implements OrderRecordService{
	@Autowired
	private OrderRecordDao orderRecordDao;
	
	@Override
	public void save(OrderRecord or) {
		orderRecordDao.save(or);
		
	}

	@Override
	public OrderRecord find(Long userId) {
		
		return orderRecordDao.find(userId);
	}

	@Override
	public List<OrderRecord> findAll() {
		
		return orderRecordDao.findAll();
	}


	

}

package com.gox.manage.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.OrderDao;
import com.gox.manage.model.Order;
import com.gox.manage.model.User;
import com.gox.manage.service.OrderService;

@Service
@Scope("prototype")
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;

	@Override
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
		
	}

	@Override
	public List<Order> findOrder(Long userId) {
		return orderDao.findOrder(userId);
		
	}

	@Override
	public Map<String,Long> findOrderRate() {
		return orderDao.findOrderRate();
		
	}

	@Override
	public List<Order> findCashOrder(Long userId) {
		
		return orderDao.findCashOrder(userId);
	}

	

	/*@Override
	public void findNewOrder(Long userId) {
		// TODO Auto-generated method stub
		
	}*/
	
	

}

package com.gox.manage.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.gox.manage.model.Order;
import com.gox.manage.model.User;

public interface OrderService {

	void saveOrder(Order order);

	List<Order> findOrder(Long userId);

	Map<String,Long> findOrderRate();

	List<Order> findCashOrder(Long id);


}

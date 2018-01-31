package com.gox.manage.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gox.manage.model.Order;
import com.gox.manage.model.User;

public interface OrderDao {

	void saveOrder(Order order);

	List<Order> findOrder(Long userId);
	Map<String,Long> findOrderRate();

	List<Order> findCashOrder(Long userId);

	

}

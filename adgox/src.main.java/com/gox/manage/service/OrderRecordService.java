package com.gox.manage.service;

import java.util.List;

import com.gox.manage.model.OrderRecord;

public interface OrderRecordService {

	void save(OrderRecord or);

	OrderRecord find(Long userId);

	List<OrderRecord> findAll();


}

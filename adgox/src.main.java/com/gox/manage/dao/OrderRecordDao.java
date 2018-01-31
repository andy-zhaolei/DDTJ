package com.gox.manage.dao;

import java.util.List;

import com.gox.manage.model.OrderRecord;

public interface OrderRecordDao {

	void save(OrderRecord or);

	OrderRecord find(Long userId);

	List<OrderRecord> findAll();

	OrderRecord findOrderRecord(Long id);

}

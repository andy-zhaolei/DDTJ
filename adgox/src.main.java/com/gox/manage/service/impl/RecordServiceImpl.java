package com.gox.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.RecordDao;
import com.gox.manage.model.Record;
import com.gox.manage.service.RecordService;
@Service
@Scope("prototype")
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordDao recordDao;

	@Override
	public void saveRecord(Record r) {
		recordDao.saveRecord(r);
		
	}
	@Override
	public Record findRecord() {
		return recordDao.findRecord();		
	}

}

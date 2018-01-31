package com.gox.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gox.manage.dao.RecordDao;
import com.gox.manage.model.Record;
@Repository
@SuppressWarnings("all")
public class RecordDaoImpl extends BaseDao implements RecordDao{

	@Override
	public void saveRecord(Record r) {
		this.hibernateTemplate.save(r);	
	}

	@Override
	public Record findRecord() {
		List<Record> list=this.hibernateTemplate.find("from Record r order by r.createdTime desc");
		System.out.println("第一条记录："+list.get(0));
		return list.get(0);
	}

}

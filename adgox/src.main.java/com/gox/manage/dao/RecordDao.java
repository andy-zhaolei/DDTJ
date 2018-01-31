package com.gox.manage.dao;

import com.gox.manage.model.Record;

public interface RecordDao {

	void saveRecord(Record r);

	Record findRecord();

}

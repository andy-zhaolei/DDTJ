package com.gox.manage.service;

import com.gox.manage.model.Record;

public interface RecordService {

	void saveRecord(Record r);

	Record findRecord();

}

package com.gox.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gox.manage.dao.LimitSomeDao;
import com.gox.manage.model.LimitSome;
@Repository
@SuppressWarnings("all")
public class LimitSomeDaoImpl extends BaseDao implements LimitSomeDao{

	@Override
	public LimitSome findwithdraw() {
		List<LimitSome> list=this.hibernateTemplate.find("from LimitSome ");
		return list.get(0);
	
	}

}

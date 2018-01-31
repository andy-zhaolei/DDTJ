package com.gox.manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gox.manage.dao.OrderRecordDao;
import com.gox.manage.model.OrderRecord;

@Repository
@SuppressWarnings("all")
public class OrderRecordDaoImpl extends BaseDao implements OrderRecordDao{

	@Override
	public void save(OrderRecord or) {
		this.hibernateTemplate.save(or);
		
	}

	@Override
	public OrderRecord find(Long userId) {
		List<OrderRecord> list=this.hibernateTemplate.find("from OrderRecord r where r.userId=? order by r.selltime desc",userId);
		OrderRecord or=new OrderRecord();
		if(list.size()>0){
			return or=list.get(0);
		}
		return null;
	}

	@Override
	public List<OrderRecord> findAll() {
		
		List<OrderRecord> list = this.hibernateTemplate.executeFind(new HibernateCallback() {//排行版
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
			return session.createSQLQuery("select * from order_record r,(select max(plamount) plamount,userId from order_record group by userId) s where r.plamount=s.plamount and r.userId=s.userId ").addEntity(OrderRecord.class).list();
			}		
		});		
		return list;
	}

	@Override
	public OrderRecord findOrderRecord(Long id) {
		/*this.*/
		return null;
	}

}

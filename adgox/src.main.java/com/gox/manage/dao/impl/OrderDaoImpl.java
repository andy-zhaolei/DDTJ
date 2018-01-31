package com.gox.manage.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gox.manage.dao.OrderDao;
import com.gox.manage.model.Order;
import com.gox.manage.model.User;
@Repository
@SuppressWarnings("all")
public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		this.hibernateTemplate.save(order);
		
	}

	@Override
	public List<Order> findOrder(Long userId) {
		
		List<Order> list=this.hibernateTemplate.find("from Order o where o.userId=? order by createdTime desc limit 0,10 ",userId);
		
		return list;
	}

	@Override
	public Map<String,Long> findOrderRate() {
		Date d=new Date();
		long t=d.getTime()-24L*60*60*1000;
		Date d1=new Date(t);
		String HQL1="select count(*) from Order where createdTime > ?";
		String HQL2="select count(*) from Order o where o.area='XAG' and createdTime > ?";
		String HQL3="select count(*) from Order o where o.area='XAG' and o.buydirection='XJ' and createdTime > ?";
		String HQL4="select count(*) from Order o where o.area='XAU' and o.buydirection='JSJ' and createdTime > ?";
		long count1=((Long)this.hibernateTemplate.find(HQL1,d1).listIterator().next()).longValue();//总行数
		long count2=((Long)this.hibernateTemplate.find(HQL2,d1).listIterator().next()).longValue();//白银总行数
		long count3=((Long)this.hibernateTemplate.find(HQL3,d1).listIterator().next()).longValue();//白银现价行数
		long count4=((Long)this.hibernateTemplate.find(HQL4,d1).listIterator().next()).longValue();//黄金结算价行数
		
		Map<String,Long> m=new HashMap<String,Long>();
		m.put("SUM", count1);
		m.put("BYSUM", count2);
		m.put("BYXJ", count3);
		m.put("HJJSJ",count4);
		return m;
	}

	@Override
	public List<Order> findCashOrder(Long userId) {
		List<Order> list=this.hibernateTemplate.find("from Order o where o.isJuan='0' and userId=?",userId);
		return list;
	}

	
	

}

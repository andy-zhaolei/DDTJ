package com.gox.manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.fx.base.common.PageInfo;
@SuppressWarnings("all")
public class BaseDao {
	@Autowired
	public HibernateTemplate hibernateTemplate;
	
	
	
	public PageInfo findPageInfoByHql(final int pageIndex, final int pageSize, final String hql, final Object[] params) {
		//求未分页的总记录数		
		Integer rowCount = 0;
		String condition = hql.substring(hql.toLowerCase().indexOf("from") + 4);
		String tmp = condition.toLowerCase();
		int pos = tmp.indexOf(" order ");
		if(pos > 0) {
			condition = condition.substring(0, pos);
		}
		
		condition= condition.replaceAll("fetch", ""); //去掉fetch
		List list2 = this.hibernateTemplate.find("select count(*) from " + condition, params);
		if(list2 != null && list2.size() > 0) {
			Object num = list2.get(0);
			rowCount = Integer.valueOf(String.valueOf(num));
		}
		
		//int pageCount = (int)((rowCount + pageSize - 1) / pageSize);
		//final int currentPageIndex = pageIndex <= pageCount ? pageIndex : pageCount;
		final int currentPageIndex =pageIndex;
		
		//求分页后LIST	
		List list = this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				int firstResult = (((currentPageIndex) >= 1 ? currentPageIndex : 1) - 1) * pageSize;
				Query query = session.createQuery(hql)
				.setFirstResult(firstResult)
				.setMaxResults(pageSize);
				
				if(params != null) {
					for(int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}		
		});
		
		return new PageInfo(list, rowCount, pageIndex, pageSize);
	}
	
	
	@SuppressWarnings("unchecked")
	public PageInfo findPageInfoBySQL(final int pageIndex, final int pageSize, final Integer rowCount,final String sql, final Class T) {
		//求未分页的总记录数		
	//	Integer rowCount = 0;
	/*	String condition = sql.substring(sql.toLowerCase().indexOf("from") + 4);
		String tmp = condition.toLowerCase();
		int pos = tmp.indexOf(" order ");
		if(pos > 0) {
			condition = condition.substring(0, pos);
		}
		*/
		//condition= condition.replaceAll("fetch", "").trim(); //去掉fetch
		//char[] cs=condition.toCharArray();
       // cs[0]-=32;
       // condition = String.valueOf(cs);
        
		//List list2 = this.hibernateTemplate.find("select count(*) from " + condition, params);
//		List list2 = this.hibernateTemplate.executeFind(new HibernateCallback() {
//			public Object doInHibernate(Session session) throws HibernateException, SQLException {
//				return session.createSQLQuery(sqlc).addEntity(Integer.class).list();
//				
//			}		
//		});
		
//		if(list2 != null && list2.size() > 0) {
//			Object num = list2.get(0);
//			rowCount = Integer.valueOf(String.valueOf(num));
//		}
		
		//int pageCount = (int)((rowCount + pageSize - 1) / pageSize);
		//final int currentPageIndex = pageIndex <= pageCount ? pageIndex : pageCount;
		final int currentPageIndex =pageIndex;
		
		//求分页后LIST	
		List list = this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				int firstResult = (((currentPageIndex) >= 1 ? currentPageIndex : 1) - 1) * pageSize;
				return session.createSQLQuery(sql).addEntity(T)
				.setFirstResult(firstResult)
				.setMaxResults(pageSize).list();
				
			}		
		});
		
		return new PageInfo(list, rowCount, pageIndex, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo findPageInfoBySQL_1(final int pageIndex, final int pageSize, final Integer rowCount,final String sql, final Class T) {
		final int currentPageIndex =pageIndex;
		
		//求分页后LIST	
		List list = this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				int firstResult = (((currentPageIndex) >= 1 ? currentPageIndex : 1) - 1) * pageSize;
				return session.createSQLQuery(sql)
				.setFirstResult(firstResult)
				.setMaxResults(pageSize).list();
				
			}		
		});
		
		return new PageInfo(list, rowCount, pageIndex, pageSize);
	}
}













package com.gox.manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.gox.manage.dao.UserDao;
import com.gox.manage.model.Jifen;
import com.gox.manage.model.User;
@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
//		List<User> list=hibernateTemplate.find("from User where username =zhansan");
//		list.get(0).getJifen().getAtie();
		
		
		/*List<Object> list=hibernateTemplate.find("from User u,Jifen j where u.userId=j.id");
		for (Object object : list) {
			System.out.println(object);
			
		}*/
		
		List<User> list1=hibernateTemplate.find("from User");
		
		
//		List<Jifen> list2=hibernateTemplate.find("from Jifen");
//		for (User u : list1) {
//			for(Jifen j:list2){
//				if(u.getUserId()==j.getId()){
//					u.setJifen(j);
//				}
//			}
//		}
		return list1;
	}

	@Override
	public void deleteUser(Long id) {
		System.out.println(id);
		Jifen j=new Jifen();
		User u=new User();
		j.setId(id);
		u.setId(id);
		hibernateTemplate.delete(j);
		hibernateTemplate.delete(u);		
	}
	

}

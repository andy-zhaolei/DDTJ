package com.gox.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gox.manage.dao.impl.BaseDao;
import com.gox.manage.model.Juan;
import com.gox.manage.service.JuanDao;

@Repository
@SuppressWarnings("all")
public class JuanDaoImpl extends BaseDao implements JuanDao {
	@Override
	public Juan findJuan(Long id) {
		List<Juan> j=this.hibernateTemplate.find("from Juan j where j.source='新手注册' and j.userId=?",id);
		Juan juan=new Juan();
		if(j.size()==0){
			return null;
		}else{
			juan=j.get(0);
			return juan;
		}				
	}

	@Override
	public void save(Juan j) {
		this.hibernateTemplate.save(j);
		
	}

	@Override
	public Juan update(Long id) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.source='新手注册' and j.state='N' and  j.userId =? ",id);
		if(list.size()==0){
			return null;
		}else{
			Juan j=list.get(0);
			j.setState("Y");
			j.setUpdatedTime(new Date());
			this.hibernateTemplate.update(j);
			return j;
		}
		
	}

	@Override
	public List<Juan> findTodayTrank() {
		return this.hibernateTemplate.find("from Juan j where j.state='Y' order by createdTime desc limit 0,20");
		
		
	}

	@Override
	public List<Juan>  findByUserId(Long uId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='N' and source='白银宝箱' and userId=? ",uId);
		if(list.size()==0){
			return null;
		}else{
			return list;
		}
		
	}

	@Override
	public Juan findJuanByNew(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='N' and source='黄金宝箱' and userId=? ",userId);//treasureMap藏宝图
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	
	}

	@Override
	public List<Juan> findHjById(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='N' and source='黄金宝箱' and userId=?",userId);
		return list;
			
		
	}

	@Override
	public List<Juan> findAllByuserId(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='N' and userId=?",userId);
		
		return list;
	}

	@Override
	public void updateBybx(Juan j) {
		this.hibernateTemplate.update(j);
		
	}

	@Override
	public Juan findLqTime(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='Y' and j.source='白银宝箱' and userId=? order by updatedTime desc ",userId);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
		
	}

	@Override
	public Juan findHJnew(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='Y' and j.source='黄金宝箱' and userId=? order by updatedTime desc",userId);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);			
		}
	}

	@Override
	public List<Juan> findShareLB(Long userId) {
		List<Juan> list= this.hibernateTemplate.find("from Juan j where j.source='钻石礼包' and userId=? ",userId);
		return list;
	}

	@Override
	public Juan findZslb(Long userId) {
	
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.source='钻石礼包' and j.state='Y' and j.userId=?",userId);
		if(list.size()==0){
			return null;
		}else{			
			return list.get(0);
		}
	
	}

	@Override
	public List<Juan> findZZLB(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.treasureMap='N1' and j.userId=?",userId);
		return list;
	}

	@Override
	public List<Juan> findZZLBX(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.treasureMap='N2' and j.userId=?",userId);
		return list;
	}

	@Override
	public List<Juan> findAllAward(Long userId) {
		List<Juan> list=this.hibernateTemplate.find("from Juan j where j.state='Y' and userId=?",userId);
		return list;
	}

}

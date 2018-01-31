package com.gox.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.model.Juan;
import com.gox.manage.service.JuanDao;
import com.gox.manage.service.JuanService;
@Scope("prototype")
@Service
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class JuanServiceImpl implements JuanService{
	@Autowired
	private JuanDao juanDao;

	@Override
	public Juan findJuan(Long id) {
		return juanDao.findJuan(id);
		
	}

	@Override
	public void save(Juan j) {
		juanDao.save(j);
		
	}

	@Override
	public Juan update(Long id) {
		return juanDao.update(id);
		
	}

	@Override
	public List<Juan> findTodayTrank() {
		return juanDao.findTodayTrank();
		
	}

	@Override
	public List<Juan> findByUserId(Long uId) {
		return juanDao.findByUserId(uId);
		
	}

	@Override
	public Juan findJuanByNew(Long userId) {
		
		return juanDao.findJuanByNew(userId);
	}

	@Override
	public List<Juan> findHjById(Long userId) {
		return juanDao.findHjById(userId);
	}

	@Override
	public List<Juan> findAllByuserId(Long userId) {
		
		return juanDao.findAllByuserId(userId);
	}

	@Override
	public void updateBybx(Juan j) {
		juanDao.updateBybx(j);
		
	}

	@Override
	public Juan findLqTime(Long userId) {
		return  juanDao.findLqTime(userId);
		
	}

	@Override
	public Juan findHJnew(Long userId) {
		
		return juanDao.findHJnew(userId);
	}

	@Override
	public List<Juan> findShareLB(Long userId) {
		
		return juanDao.findShareLB(userId);
	}

	@Override
	public Juan findZslb(Long userId) {
		
		return juanDao.findZslb(userId);
	}

	@Override
	public List<Juan> findZZLB(Long userId) {
		
		return juanDao.findZZLB(userId);
	}

	@Override
	public List<Juan> findZZLBX(Long userId) {
		
		return juanDao.findZZLBX(userId);
	}

	@Override
	public List<Juan> findAllAward(Long userId) {
		
		return juanDao.findAllAward(userId);
	}
	

}

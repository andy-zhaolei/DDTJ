package com.gox.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gox.manage.dao.InvitationDao;
import com.gox.manage.model.Invitation;
import com.gox.manage.service.InvitationService;
@Service
@Scope("prototype")
@Transactional(value="transactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = { "java.lang.Throwable" })
public class InvitationServiceImpl implements InvitationService{
	@Autowired
	private InvitationDao invitationDao;

	@Override
	public Invitation find(Long id) {
		
		return invitationDao.find(id);
	}

	@Override
	public Invitation findInvitateSum(Long userId) {
		
		return invitationDao.findInvitateSum(userId);
	}

	
	

}

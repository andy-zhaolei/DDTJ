package com.gox.manage.dao;

import com.gox.manage.model.Invitation;

public interface InvitationDao {

	Invitation find(Long id);

	Invitation findInvitateSum(Long userId);

}

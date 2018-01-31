package com.gox.manage.service;

import com.gox.manage.model.Invitation;

public interface InvitationService {

	Invitation find(Long id);

	Invitation findInvitateSum(Long userId);




}

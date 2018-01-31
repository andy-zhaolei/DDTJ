package com.gox.manage.model;

import java.util.Date;

public class Invitation {
	private Long id;
	private Long userId;
	private String invitationCode;
	private Long invitationSum;
	private Date createdTime;
	private Date updatedTime;
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public Long getInvitationSum() {
		return invitationSum;
	}
	public void setInvitationSum(Long invitationSum) {
		this.invitationSum = invitationSum;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Override
	public String toString() {
		return "Invitation [id=" + id + ", userId=" + userId
				+ ", invitationCode=" + invitationCode + ", invitationSum="
				+ invitationSum + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", userName=" + userName
				+ "]";
	}
	
	
	
	

}

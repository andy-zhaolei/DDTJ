package com.gox.manage.model;

import java.util.Date;

public class Device {
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Long userId;
	private String deviceId;
	private String platform;
	private Date createdTime;
	private Date updatedTime;
	private String idfa;
	private String idfv;
	private String IMEI;
	
	
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", userId=" + userId + ", deviceId="
				+ deviceId + ", platform=" + platform + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + ", idfa="
				+ idfa + ", idfv=" + idfv + ", IMEI=" + IMEI + "]";
	}
}

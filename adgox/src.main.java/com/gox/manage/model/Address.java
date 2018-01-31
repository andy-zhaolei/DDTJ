package com.gox.manage.model;

import java.io.Serializable;
@SuppressWarnings("all")
public class Address implements Serializable{
	
	private Long id;
	private Long addressId;
	private Long userId;
	private String region;
	private Long flag;
	private String receivingAddress;
	private String wid;
	private Long isDefault;
	private String postCode;
	private String receivingName;
	private String receivingMobile;
	private String createDate;
	private String updateDate;
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public String getReceivingAddress() {
		return receivingAddress;
	}
	public void setReceivingAddress(String receivingAddress) {
		this.receivingAddress = receivingAddress;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public Long getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Long isDefault) {
		this.isDefault = isDefault;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getReceivingName() {
		return receivingName;
	}
	public void setReceivingName(String receivingName) {
		this.receivingName = receivingName;
	}
	public String getReceivingMobile() {
		return receivingMobile;
	}
	public void setReceivingMobile(String receivingMobile) {
		this.receivingMobile = receivingMobile;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", addressId=" + addressId + ", userId="
				+ userId + ", region=" + region + ", flag=" + flag
				+ ", receivingAddress=" + receivingAddress + ", wid=" + wid
				+ ", isDefault=" + isDefault + ", postCode=" + postCode
				+ ", receivingName=" + receivingName + ", receivingMobile="
				+ receivingMobile + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
	
	
	
}

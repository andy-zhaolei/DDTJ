package com.gox.manage.bean;

public class EnterPrise {
	private String userId;
	private String mobile;
	private String password;
	private String verifyCode;
	private String legalPersonName;
	private String legalPersonCertNo;
	private String bizLicencePicture;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getLegalPersonName() {
		return legalPersonName;
	}
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	public String getLegalPersonCertNo() {
		return legalPersonCertNo;
	}
	public void setLegalPersonCertNo(String legalPersonCertNo) {
		this.legalPersonCertNo = legalPersonCertNo;
	}
	public String getBizLicencePicture() {
		return bizLicencePicture;
	}
	public void setBizLicencePicture(String bizLicencePicture) {
		this.bizLicencePicture = bizLicencePicture;
	}
	@Override
	public String toString() {
		return "EnterPrise [userId=" + userId + ", mobile=" + mobile
				+ ", password=" + password + ", verifyCode=" + verifyCode
				+ ", legalPersonName=" + legalPersonName
				+ ", legalPersonCertNo=" + legalPersonCertNo
				+ ", bizLicencePicture=" + bizLicencePicture + "]";
	}
	
}

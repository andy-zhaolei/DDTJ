package com.gox.manage.model;


import java.util.Date;


public class User implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	
	private String userName;
	
	private String password;
	
	private Integer sex;
	
	private String mobile;
	private Jifen jifen;
	
	
	
	
	public Jifen getJifen() {
		return jifen;
	}
	public void setJifen(Jifen jifen) {
		this.jifen = jifen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Date createdTime;
	private Date updatedTime;
	
	private String wid;
	private Integer memberUnitsId;
	private Integer hasFundPassword;
	private String invitationCode ;
	private Integer userType;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	private Integer flag;
	private String accountMemberunitId;
	private String accountUserId;
	private String unitNo;
	private Integer isRealName;
	private String memberChannel;
	private String userPic;
	private String AccessToken;
	private String RefreshToken;
	private Date AccessTokenExpireTime;
	private Date RefreshTokenExpireTime;
	
	
	
	
	
	
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public Integer getMemberUnitsId() {
		return memberUnitsId;
	}
	public void setMemberUnitsId(Integer memberUnitsId) {
		this.memberUnitsId = memberUnitsId;
	}
	public Integer getHasFundPassword() {
		return hasFundPassword;
	}
	public void setHasFundPassword(Integer hasFundPassword) {
		this.hasFundPassword = hasFundPassword;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getAccountMemberunitId() {
		return accountMemberunitId;
	}
	public void setAccountMemberunitId(String accountMemberunitId) {
		this.accountMemberunitId = accountMemberunitId;
	}
	public String getAccountUserId() {
		return accountUserId;
	}
	public void setAccountUserId(String accountUserId) {
		this.accountUserId = accountUserId;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public Integer getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(Integer isRealName) {
		this.isRealName = isRealName;
	}
	public String getMemberChannel() {
		return memberChannel;
	}
	public void setMemberChannel(String memberChannel) {
		this.memberChannel = memberChannel;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public String getRefreshToken() {
		return RefreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}
	public Date getAccessTokenExpireTime() {
		return AccessTokenExpireTime;
	}
	public void setAccessTokenExpireTime(Date accessTokenExpireTime) {
		AccessTokenExpireTime = accessTokenExpireTime;
	}
	public Date getRefreshTokenExpireTime() {
		return RefreshTokenExpireTime;
	}
	public void setRefreshTokenExpireTime(Date refreshTokenExpireTime) {
		RefreshTokenExpireTime = refreshTokenExpireTime;
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
	public Long getId() {//http://localhost:8080/adgox/cbclient/user/regist?userName=1&password=1&sex=1&mobile=1234324
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userName=" + userName + ", password=" + password + ", sex="
				+ sex + ", mobile=" + mobile + ", jifen=" + jifen + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + ", wid=" + wid + ", memberUnitsId=" + memberUnitsId + ", hasFundPassword="
				+ hasFundPassword + ", invitationCode=" + invitationCode + ", userType=" + userType + ", flag=" + flag
				+ ", accountMemberunitId=" + accountMemberunitId + ", accountUserId=" + accountUserId + ", unitNo="
				+ unitNo + ", isRealName=" + isRealName + ", memberChannel=" + memberChannel + ", userPic=" + userPic
				+ ", AccessToken=" + AccessToken + ", RefreshToken=" + RefreshToken + ", AccessTokenExpireTime="
				+ AccessTokenExpireTime + ", RefreshTokenExpireTime=" + RefreshTokenExpireTime + "]";
	}


}

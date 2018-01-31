package com.gox.manage.model;

import java.util.Date;

public class Jifen {
	
	private Long id;
	private Long userId;
	
	private Long sum;//总积分
	private Integer login;//登陆积分
	private Integer register;//
	private Integer luntang;//论坛首次发言
	private Integer juan;//首次使用卷
	private Integer cash;//首次使用现金获取积分
	private Integer gendang;//首次跟单
	private Integer certName;//实名认证过
	private Integer shangban;//上榜
	private Integer questionaire;//调查问卷
	private Integer Atie;//A贴获取积分
	private Integer invitation;//邀请获取积分
	private Integer loginParam;//连续登陆积分系数
	private Integer loginState;//登陆状态
	private Integer registerState;//注册获取积分状态
	private Integer luntangState;//论堂获取积分状态
	private Integer juanState;//卷获取积分状态
	private Integer cashState;//现金获取积分状态
	private Integer gendangState;//跟单获取积分状态
	private Integer certNameState;//认证获取积分状态，1代表认证，2代表获取积分过，0代表为认证
	private Integer AtieState;//A帖子状态
	private Integer questState;//问卷状态
	private Integer invitationState;//邀请获取积分状态
	private Integer shangbanState;//上榜获取积分状态
	private Integer HD;//活动获取积分
	private Integer HDState;//活动获取积分状态
	private Date createdTime;
	private Date updatedTime;
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	public Long getSum() {
		return sum;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}
	public Integer getLogin() {
		return login;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setLogin(Integer login) {
		this.login = login;
	}
	public Integer getRegister() {
		return register;
	}
	public void setRegister(Integer register) {
		this.register = register;
	}
	public Integer getLuntang() {
		return luntang;
	}
	public void setLuntang(Integer luntang) {
		this.luntang = luntang;
	}
	public Integer getJuan() {
		return juan;
	}
	public void setJuan(Integer juan) {
		this.juan = juan;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	public Integer getGendang() {
		return gendang;
	}
	public void setGendang(Integer gendang) {
		this.gendang = gendang;
	}
	public Integer getCertName() {
		return certName;
	}
	public void setCertName(Integer certName) {
		this.certName = certName;
	}
	public Integer getShangban() {
		return shangban;
	}
	public void setShangban(Integer shangban) {
		this.shangban = shangban;
	}
	public Integer getQuestionaire() {
		return questionaire;
	}
	public void setQuestionaire(Integer questionaire) {
		this.questionaire = questionaire;
	}
	public Integer getAtie() {
		return Atie;
	}
	public void setAtie(Integer atie) {
		Atie = atie;
	}
	public Integer getInvitation() {
		return invitation;
	}
	public void setInvitation(Integer invitation) {
		this.invitation = invitation;
	}
	public Integer getloginParam() {
		return loginParam;
	}
	public void setloginParam(Integer loginParam) {
		this.loginParam = loginParam;
	}
	public Integer getLoginState() {
		return loginState;
	}
	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}
	public Integer getRegisterState() {
		return registerState;
	}
	public void setRegisterState(Integer registerState) {
		this.registerState = registerState;
	}
	public Integer getLuntangState() {
		return luntangState;
	}
	public void setLuntangState(Integer luntangState) {
		this.luntangState = luntangState;
	}
	public Integer getJuanState() {
		return juanState;
	}
	public void setJuanState(Integer juanState) {
		this.juanState = juanState;
	}
	public Integer getCashState() {
		return cashState;
	}
	public void setCashState(Integer cashState) {
		this.cashState = cashState;
	}
	public Integer getGendangState() {
		return gendangState;
	}
	public void setGendangState(Integer gendangState) {
		this.gendangState = gendangState;
	}
	public Integer getCertNameState() {
		return certNameState;
	}
	public void setCertNameState(Integer certNameState) {
		this.certNameState = certNameState;
	}
	public Integer getAtieState() {
		return AtieState;
	}
	public void setAtieState(Integer atieState) {
		AtieState = atieState;
	}
	public Integer getQuestState() {
		return questState;
	}
	public void setQuestState(Integer questState) {
		this.questState = questState;
	}
	public Integer getInvitationState() {
		return invitationState;
	}
	public void setInvitationState(Integer invitationState) {
		this.invitationState = invitationState;
	}
	public Integer getShangbanState() {
		return shangbanState;
	}
	public void setShangbanState(Integer shangbanState) {
		this.shangbanState = shangbanState;
	}
	public Integer getHD() {
		return HD;
	}
	public void setHD(Integer hD) {
		HD = hD;
	}
	public Integer getHDState() {
		return HDState;
	}
	public void setHDState(Integer hDState) {
		HDState = hDState;
	}
	@Override
	public String toString() {
		return "Jifen [id=" + id + ", userId=" + userId + ", sum=" + sum
				+ ", login=" + login + ", register=" + register + ", luntang="
				+ luntang + ", juan=" + juan + ", cash=" + cash + ", gendang="
				+ gendang + ", certName=" + certName + ", shangban=" + shangban
				+ ", questionaire=" + questionaire + ", Atie=" + Atie
				+ ", invitation=" + invitation + ", loginParam=" + loginParam
				+ ", loginState=" + loginState + ", registerState="
				+ registerState + ", luntangState=" + luntangState
				+ ", juanState=" + juanState + ", cashState=" + cashState
				+ ", gendangState=" + gendangState + ", certNameState="
				+ certNameState + ", AtieState=" + AtieState + ", questState="
				+ questState + ", invitationState=" + invitationState
				+ ", shangbanState=" + shangbanState + ", HD=" + HD
				+ ", HDState=" + HDState + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", userName=" + userName
				+ "]";
	}
}

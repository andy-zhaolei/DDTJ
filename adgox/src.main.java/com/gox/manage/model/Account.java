package com.gox.manage.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
	/*
	 * id
userId
name
idCard
realName
balance
cash
password
cardCity
cardPro
bankCode
branch
storage
createdTime
updatedTime

	 */
	private Long id;
	private Long userId;
	private String username;
	private Long idCard;
	private String realName;
	private BigDecimal balance;
	private BigDecimal cash;
	private String password;
	private String cardPro;
	private String cardCity;
	private String bankCode;
	private String branch;
	private BigDecimal storage;
	private Date createdTime;
	private Date updatedTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	
	public String getCardPro() {
		return cardPro;
	}
	public void setCardPro(String cardPro) {
		this.cardPro = cardPro;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getIdCard() {
		return idCard;
	}
	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCardCity() {
		return cardCity;
	}
	public void setCardCity(String cardCity) {
		this.cardCity = cardCity;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public BigDecimal getStorage() {
		return storage;
	}
	public void setStorage(BigDecimal storage) {
		this.storage = storage;
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
		return "Account [id=" + id + ", userId=" + userId + ", username="
				+ username + ", idCard=" + idCard + ", realName=" + realName
				+ ", balance=" + balance + ", cash=" + cash + ", password="
				+ password + ", cardPro=" + cardPro + ", cardCity=" + cardCity
				+ ", bankCode=" + bankCode + ", branch=" + branch
				+ ", storage=" + storage + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
	
}

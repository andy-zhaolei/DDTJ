package com.gox.manage.model;

import java.math.BigDecimal;
import java.util.Date;

public class Record {

  
	private Long id;
	private Long userId;
	private String orderTypeStr;//订货类型
	private BigDecimal balance;//余额
	private String orderNumber;//订单号
	private BigDecimal money;//支出或充值金额
	private String proDesc;//商品名称
	private Date addTime;//订单时间
	private Date updatedTime;
	private Date createdTime;
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
	public String getOrderTypeStr() {
		return orderTypeStr;
	}
	public void setOrderTypeStr(String orderTypeStr) {
		this.orderTypeStr = orderTypeStr;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", userId=" + userId + ", orderTypeStr="
				+ orderTypeStr + ", balance=" + balance + ", orderNumber="
				+ orderNumber + ", money=" + money + ", proDesc=" + proDesc
				+ ", addTime=" + addTime + ", updatedTime=" + updatedTime
				+ ", createdTime=" + createdTime + "]";
	}
	
	
	
	
	
}
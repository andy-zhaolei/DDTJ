package com.gox.manage.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	
	private Long id;
	private Long userId;
	
	private String productId;//商品id

	private String area;//产品类
	private String number;//手数
	private String isJuan;//是否用卷
	private String toplimit;///止盈
	private String bottomlimit;//止损
	private String buydirection;
	private String coponType;//卷类型
	private String buymoney;
	private String fee;
	private Date createdTime;
	private Date updatedTime;
	
	
	public String getBuymoney() {
		return buymoney;
	}
	public void setBuymoney(String buymoney) {
		this.buymoney = buymoney;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIsJuan() {
		return isJuan;
	}
	public void setIsJuan(String isJuan) {
		this.isJuan = isJuan;
	}
	public String getToplimit() {
		return toplimit;
	}
	public void setToplimit(String toplimit) {
		this.toplimit = toplimit;
	}
	public String getBottomlimit() {
		return bottomlimit;
	}
	public void setBottomlimit(String bottomlimit) {
		this.bottomlimit = bottomlimit;
	}
	public String getBuydirection() {
		return buydirection;
	}
	public void setBuydirection(String buydirection) {
		this.buydirection = buydirection;
	}
	public String getCoponType() {
		return coponType;
	}
	public void setCoponType(String coponType) {
		this.coponType = coponType;
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
		return "Order [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", area=" + area + ", number=" + number
				+ ", isJuan=" + isJuan + ", toplimit=" + toplimit
				+ ", bottomlimit=" + bottomlimit + ", buydirection="
				+ buydirection + ", coponType=" + coponType + ", buymoney="
				+ buymoney + ", fee=" + fee + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
	
	
}
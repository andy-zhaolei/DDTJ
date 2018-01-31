package com.gox.manage.model;

import java.io.Serializable;
import java.util.Date;

public class Juan implements Serializable{
	
	private Long id;
	private Long userId;
	private String productId;
	private String couponId;
	private String faceValue;
	private String state;
	private String source;
	private Date createdTime;
	private Date updatedTime;
	private String status;
	private String tel;
	private String promotionNumber;
	private String treasureMap;
	private String image1;
	private String image2;
	
	
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getTreasureMap() {
		return treasureMap;
	}
	public void setTreasureMap(String treasureMap) {
		this.treasureMap = treasureMap;
	}
	public String getPromotionNumber() {
		return promotionNumber;
	}
	public void setPromotionNumber(String promotionNumber) {
		this.promotionNumber = promotionNumber;
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
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Juan [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", couponId=" + couponId + ", faceValue="
				+ faceValue + ", state=" + state + ", source=" + source
				+ ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + ", status=" + status + ", tel=" + tel
				+ ", promotionNumber=" + promotionNumber + ", treasureMap="
				+ treasureMap + ", image1=" + image1 + ", image2=" + image2
				+ "]";
	}
	
}

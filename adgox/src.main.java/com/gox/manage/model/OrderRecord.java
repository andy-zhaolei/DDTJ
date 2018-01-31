package com.gox.manage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderRecord implements Serializable{
	
	
	private Long id;
	private Long userId;
	
	private String productId;//商品id
	private Long orderid;//订单id
	private String area;//产区
	private String number;//手数
	private String isJuan;//是否用卷
	private String toplimit;///止盈
	private String bottomlimit;//止损
	private String couponId;//卷id
	private String coponType;//卷类型
	private BigDecimal buymoney;//定金
	private String ordernum;//订单号
	private Integer ordertype;//
	private String productpic;//商品图片
	private Integer buydirection;//订货类型2：现价，1：结算价
	private BigDecimal fee;//手续费
	private Date addtime;//下单时间
	private Date selltime;
	private String name;//商品名
	private BigDecimal buyprice;//买入价
	private BigDecimal sellprice;//#退jiage
	private Integer count;//
	private BigDecimal plamount;//#盈亏金额（订单差价）
	private BigDecimal actualAmount;//#实际差价
	private Date createdTime;
	private Date updatedTime;
	private String phone;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getSelltime() {
		return selltime;
	}
	public void setSelltime(Date selltime) {
		this.selltime = selltime;
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
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
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
		this.toplimit =toplimit;
	}
	public String getBottomlimit() {
		return bottomlimit;
	}
	public void setBottomlimit(String bottomlimit) {
		this.bottomlimit = bottomlimit;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCoponType() {
		return coponType;
	}
	public void setCoponType(String coponType) {
		this.coponType = coponType;
	}
	public BigDecimal getBuymoney() {
		return buymoney;
	}
	public void setBuymoney(BigDecimal buymoney) {
		this.buymoney = buymoney;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	public String getProductpic() {
		return productpic;
	}
	public void setProductpic(String productpic) {
		this.productpic = productpic;
	}
	public Integer getBuydirection() {
		return buydirection;
	}
	public void setBuydirection(Integer buydirection) {
		this.buydirection = buydirection;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBuyprice() {
		return buyprice;
	}
	public void setBuyprice(BigDecimal buyprice) {
		this.buyprice = buyprice;
	}
	public BigDecimal getSellprice() {
		return sellprice;
	}
	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getPlamount() {
		return plamount;
	}
	public void setPlamount(BigDecimal plamount) {
		this.plamount = plamount;
	}
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	@Override
	public String toString() {
		return "OrderRecord [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", orderid=" + orderid + ", area=" + area
				+ ", number=" + number + ", isJuan=" + isJuan + ", toplimit="
				+ toplimit + ", bottomlimit=" + bottomlimit + ", couponId="
				+ couponId + ", coponType=" + coponType + ", buymoney="
				+ buymoney + ", ordernum=" + ordernum + ", ordertype="
				+ ordertype + ", productpic=" + productpic + ", buydirection="
				+ buydirection + ", fee=" + fee + ", addtime=" + addtime
				+ ", selltime=" + selltime + ", name=" + name + ", buyprice="
				+ buyprice + ", sellprice=" + sellprice + ", count=" + count
				+ ", plamount=" + plamount + ", actualAmount=" + actualAmount
				+ ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + ", phone=" + phone + "]";
	}
}

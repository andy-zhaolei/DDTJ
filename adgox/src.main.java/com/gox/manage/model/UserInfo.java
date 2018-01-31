package com.gox.manage.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserInfo {
	/*
	 * id
userId
image
memberChannel
type
fans
focus
reward
counts
score
createdTime
updatedTime

	 */
	private Long id;
	private Long userId;
	private String memberChannel;
	private String type;
	private Long fans;
	private Long focus;
	private Long counts;
	private BigDecimal reward;
	private Long score;
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
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMemberChannel() {
		return memberChannel;
	}
	public void setMemberChannel(String memberChannel) {
		this.memberChannel = memberChannel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getFans() {
		return fans;
	}
	public void setFans(Long fans) {
		this.fans = fans;
	}
	public Long getFocus() {
		return focus;
	}
	public void setFocus(Long focus) {
		this.focus = focus;
	}
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	public BigDecimal getReward() {
		return reward;
	}
	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
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
		return "UserInfo [id=" + id + ", userId=" + userId + ", memberChannel="
				+ memberChannel + ", type=" + type + ", fans=" + fans
				+ ", focus=" + focus + ", counts=" + counts + ", reward="
				+ reward + ", score=" + score + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
	

}

package com.gox.manage.model;

import java.math.BigDecimal;

public class LimitSome {
	private Long id;
	private BigDecimal withdraw;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}
	@Override
	public String toString() {
		return "LimitSome [id=" + id + ", withdraw=" + withdraw + "]";
	}
	
}

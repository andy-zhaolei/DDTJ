package com.gox.manage.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Appbaseinfo entity. @author MyEclipse Persistence Tools
 */

public class Appbaseinfo_ad_tencent implements java.io.Serializable {

	private Long id;
	private String putInPlan;
	private String putInDate;
	private String putInTime;
	private String appName;
	private String fileSize;
	private String verCode;
	private String underlying;
	private BigDecimal advPrice;
	private BigDecimal adPutInMoney;
	private BigDecimal adCoefficient;
	private BigDecimal realPrice ;
	private String chinaForeign;
	private String isIosOrAndroid;
	private String flg;
	private String onOff;
	private String channelSelect;
	private String showType;
	
	private String advertiser_id;
	private String creative_id;
	private Integer product_type;
	private Long app_id;
	private String pkgname;
	private String click_through_url;
	private String click_monitor_url1;
	private String click_monitor_url2;
	private String click_monitor_url3;
	private String monitor_url1;
	private String monitor_url2;
	private String monitor_url3;
	private Date begin_date_included;
	private Date end_date_included;
	private Integer creative_spec;
	private String title;
	private String description;
	private String multimedia1_file_url;
	private String multimedia2_file_url;
	private String multimedia3_file_url;
	private String multimedia4_file_url;
	private String call_to_action;
	private String deep_link_uri;
	private String is_deep_link;
	private String img_size;
	private Date create_time;
	
	
	
	public String getImg_size() {
		return img_size;
	}
	public void setImg_size(String img_size) {
		this.img_size = img_size;
	}
	public String getIs_deep_link() {
		return is_deep_link;
	}
	public void setIs_deep_link(String is_deep_link) {
		this.is_deep_link = is_deep_link;
	}
	public String getChinaForeign() {
		return chinaForeign;
	}
	public void setChinaForeign(String chinaForeign) {
		this.chinaForeign = chinaForeign;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public BigDecimal getAdvPrice() {
		return advPrice;
	}
	public void setAdvPrice(BigDecimal advPrice) {
		this.advPrice = advPrice;
	}
	public BigDecimal getAdPutInMoney() {
		return adPutInMoney;
	}
	public void setAdPutInMoney(BigDecimal adPutInMoney) {
		this.adPutInMoney = adPutInMoney;
	}
	public BigDecimal getAdCoefficient() {
		return adCoefficient;
	}
	public void setAdCoefficient(BigDecimal adCoefficient) {
		this.adCoefficient = adCoefficient;
	}
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	public String getAdvertiser_id() {
		return advertiser_id;
	}
	public void setAdvertiser_id(String advertiser_id) {
		this.advertiser_id = advertiser_id;
	}
	public String getCreative_id() {
		return creative_id;
	}
	public void setCreative_id(String creative_id) {
		this.creative_id = creative_id;
	}
	public Integer getProduct_type() {
		return product_type;
	}
	public void setProduct_type(Integer product_type) {
		this.product_type = product_type;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getClick_through_url() {
		return click_through_url;
	}
	public void setClick_through_url(String click_through_url) {
		this.click_through_url = click_through_url;
	}
	public String getClick_monitor_url1() {
		return click_monitor_url1;
	}
	public void setClick_monitor_url1(String click_monitor_url1) {
		this.click_monitor_url1 = click_monitor_url1;
	}
	public String getClick_monitor_url2() {
		return click_monitor_url2;
	}
	public void setClick_monitor_url2(String click_monitor_url2) {
		this.click_monitor_url2 = click_monitor_url2;
	}
	public String getClick_monitor_url3() {
		return click_monitor_url3;
	}
	public void setClick_monitor_url3(String click_monitor_url3) {
		this.click_monitor_url3 = click_monitor_url3;
	}
	public String getMonitor_url1() {
		return monitor_url1;
	}
	public void setMonitor_url1(String monitor_url1) {
		this.monitor_url1 = monitor_url1;
	}
	public String getMonitor_url2() {
		return monitor_url2;
	}
	public void setMonitor_url2(String monitor_url2) {
		this.monitor_url2 = monitor_url2;
	}
	public String getMonitor_url3() {
		return monitor_url3;
	}
	public void setMonitor_url3(String monitor_url3) {
		this.monitor_url3 = monitor_url3;
	}
	public Date getBegin_date_included() {
		return begin_date_included;
	}
	public void setBegin_date_included(Date begin_date_included) {
		this.begin_date_included = begin_date_included;
	}
	public Date getEnd_date_included() {
		return end_date_included;
	}
	public void setEnd_date_included(Date end_date_included) {
		this.end_date_included = end_date_included;
	}
	public Integer getCreative_spec() {
		return creative_spec;
	}
	public void setCreative_spec(Integer creative_spec) {
		this.creative_spec = creative_spec;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMultimedia1_file_url() {
		return multimedia1_file_url;
	}
	public void setMultimedia1_file_url(String multimedia1_file_url) {
		this.multimedia1_file_url = multimedia1_file_url;
	}
	public String getMultimedia2_file_url() {
		return multimedia2_file_url;
	}
	public void setMultimedia2_file_url(String multimedia2_file_url) {
		this.multimedia2_file_url = multimedia2_file_url;
	}
	public String getMultimedia3_file_url() {
		return multimedia3_file_url;
	}
	public void setMultimedia3_file_url(String multimedia3_file_url) {
		this.multimedia3_file_url = multimedia3_file_url;
	}
	public String getMultimedia4_file_url() {
		return multimedia4_file_url;
	}
	public void setMultimedia4_file_url(String multimedia4_file_url) {
		this.multimedia4_file_url = multimedia4_file_url;
	}
	public String getCall_to_action() {
		return call_to_action;
	}
	public void setCall_to_action(String call_to_action) {
		this.call_to_action = call_to_action;
	}
	public String getDeep_link_uri() {
		return deep_link_uri;
	}
	public void setDeep_link_uri(String deep_link_uri) {
		this.deep_link_uri = deep_link_uri;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getChannelSelect() {
		return channelSelect;
	}
	public void setChannelSelect(String channelSelect) {
		this.channelSelect = channelSelect;
	}

	public String getOnOff() {
		return onOff;
	}

	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}


	public String getIsIosOrAndroid() {
		return isIosOrAndroid;
	}

	public void setIsIosOrAndroid(String isIosOrAndroid) {
		this.isIosOrAndroid = isIosOrAndroid;
	}

	/*标的物*/
	public String getUnderlying() {
		return underlying;
	}
	
	/*标的物*/
	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*推广计划*/
	public String getPutInPlan() {
		return this.putInPlan;
	}
	/*推广计划*/
	public void setPutInPlan(String putInPlan) {
		this.putInPlan = putInPlan;
	}

	/*投放日期*/
	public String getPutInDate() {
		return this.putInDate;
	}
	/*投放日期*/
	public void setPutInDate(String putInDate) {
		this.putInDate = putInDate;
	}
	
	/*投放时间*/
	public String getPutInTime() {
		return this.putInTime;
	}
	/*投放时间*/
	public void setPutInTime(String putInTime) {
		this.putInTime = putInTime;
	}

	/*审批状态*/
	public String getFlg() {
		return this.flg;
	}
	/*审批状态*/
	public void setFlg(String flg) {
		this.flg = flg;
	}

	/*应用名称*/
	public String getAppName() {
		return this.appName;
	}
	/*应用名称*/
	public void setAppName(String appName) {
		this.appName = appName;
	}


	/*应用版本号*/
	public String getVerCode() {
		return this.verCode;
	}
	/*应用版本号*/
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}



}
package com.gox.manage.bean;

public class AppBaseInfoBean  implements java.io.Serializable {

	private Long id;
	private String appmenuType;
	private Long uid;
	private Long channelId;
	private String putInPlan;
	private String putInDate;
	private String putInTime;
	private String flg;
	private String appName;
	private Double fileSize;
	private String pname;
	private String verCode;
	private String packageNumb;
	private String iconUrl;
	private String appUrl;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String img5;
	private String desc;
	private String payType;
	private String advPrice;
	private String channelAdvPrice;
	private String createTime;
	private String updateTime;
	private String underlying;
	private String goxId ;
	
	private String adPutInMoney;
	private String isIosOrAndroid;
	private Long downloadNumb;
	private String adCoefficient;
	private String bannerImg;
	private String screenImg;
	private String onOff;
	private String adType;
	private String putInPlatform;
	
	private Double realPrice ;
	
	private int countNumb;//查询的数据总数
	
	private String chinaForeign;
	
	private String channelSelect;//分配的渠道商数组
	
	public String getChannelSelect() {
		return channelSelect;
	}
	public void setChannelSelect(String channelSelect) {
		this.channelSelect = channelSelect;
	}
	public String getChinaForeign() {
		return chinaForeign;
	}
	public void setChinaForeign(String chinaForeign) {
		this.chinaForeign = chinaForeign;
	}
	
	
	public int getCountNumb() {
		return countNumb;
	}
	public void setCountNumb(int countNumb) {
		this.countNumb = countNumb;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public String getPutInPlatform() {
		return putInPlatform;
	}
	public void setPutInPlatform(String putInPlatform) {
		this.putInPlatform = putInPlatform;
	}
	public String getOnOff() {
		return onOff;
	}
	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAdPutInMoney() {
		return adPutInMoney;
	}
	public void setAdPutInMoney(String adPutInMoney) {
		this.adPutInMoney = adPutInMoney;
	}
	public String getIsIosOrAndroid() {
		return isIosOrAndroid;
	}
	public void setIsIosOrAndroid(String isIosOrAndroid) {
		this.isIosOrAndroid = isIosOrAndroid;
	}
	public Long getDownloadNumb() {
		return downloadNumb;
	}
	public void setDownloadNumb(Long downloadNumb) {
		this.downloadNumb = downloadNumb;
	}
	public String getAdCoefficient() {
		return adCoefficient;
	}
	public void setAdCoefficient(String adCoefficient) {
		this.adCoefficient = adCoefficient;
	}
	public String getBannerImg() {
		return bannerImg;
	}
	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}
	public String getScreenImg() {
		return screenImg;
	}
	public void setScreenImg(String screenImg) {
		this.screenImg = screenImg;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppmenuType() {
		return appmenuType;
	}
	public void setAppmenuType(String appmenuType) {
		this.appmenuType = appmenuType;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getPutInPlan() {
		return putInPlan;
	}
	public void setPutInPlan(String putInPlan) {
		this.putInPlan = putInPlan;
	}
	public String getPutInDate() {
		return putInDate;
	}
	public void setPutInDate(String putInDate) {
		this.putInDate = putInDate;
	}
	public String getPutInTime() {
		return putInTime;
	}
	public void setPutInTime(String putInTime) {
		this.putInTime = putInTime;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getVerCode() {
		return verCode;
	}
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
	public String getPackageNumb() {
		return packageNumb;
	}
	public void setPackageNumb(String packageNumb) {
		this.packageNumb = packageNumb;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getImg4() {
		return img4;
	}
	public void setImg4(String img4) {
		this.img4 = img4;
	}
	public String getImg5() {
		return img5;
	}
	public void setImg5(String img5) {
		this.img5 = img5;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getAdvPrice() {
		return advPrice;
	}
	public void setAdvPrice(String advPrice) {
		this.advPrice = advPrice;
	}
	public String getChannelAdvPrice() {
		return channelAdvPrice;
	}
	public void setChannelAdvPrice(String channelAdvPrice) {
		this.channelAdvPrice = channelAdvPrice;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUnderlying() {
		return underlying;
	}
	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}
	public String getGoxId() {
		return goxId;
	}
	public void setGoxId(String goxId) {
		this.goxId = goxId;
	}
	
}

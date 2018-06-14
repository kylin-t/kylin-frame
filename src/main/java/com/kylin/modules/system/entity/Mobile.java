package com.kylin.modules.system.entity;

import java.io.Serializable;


/**
 * 用户
 * 
 * @author zhaohongbo
 * @date 2017-12-18 15:22:06
 */
public class Mobile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//移动端注册码
	private String mobileNum;
	//收费站名称
	private String stationName;
	//所属收费站编码
	private String stationNum;
	//移动端MAC地址
	private String mobileMac;
	//注册时间
	private String createTime;
	//入口站同步状态
	private String stationSync;
	//入口站同步状态
	private String usersSync;
	//入口站同步状态
	private String goodsSync;
	//入口站同步状态
	private String catalogSync;
	//当前系统版本号
	private Double versionNo;

	private String remark;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public String getMobileMac() {
		return mobileMac;
	}

	public void setMobileMac(String mobileMac) {
		this.mobileMac = mobileMac;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStationSync() {
		return stationSync;
	}

	public void setStationSync(String stationSync) {
		this.stationSync = stationSync;
	}

	public String getUsersSync() {
		return usersSync;
	}

	public void setUsersSync(String usersSync) {
		this.usersSync = usersSync;
	}

	public String getGoodsSync() {
		return goodsSync;
	}

	public void setGoodsSync(String goodsSync) {
		this.goodsSync = goodsSync;
	}

	public String getCatalogSync() {
		return catalogSync;
	}

	public void setCatalogSync(String catalogSync) {
		this.catalogSync = catalogSync;
	}

	public Double getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Double versionNo) {
		this.versionNo = versionNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

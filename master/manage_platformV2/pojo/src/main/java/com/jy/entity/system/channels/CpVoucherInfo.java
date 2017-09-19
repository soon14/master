package com.jy.entity.system.channels;

import java.util.Date;

public class CpVoucherInfo {

	private String batchNo; // 批次

	private Integer schemeID;

	private Date usedDate;//使用时间

	private String usedMachine;// 终端编号

	private Integer userID;

	private Date vEnd;//结束时间

	private Date vStart;//开始时间

	private String vMoney;// 券金额

	private String vStatus;// 使用状态

	private String vRecordUser;// 记录录入人

	private Date vRecordDate;// 记录录入时间

	private String vNo;// 代金券编号

	private Date sycDate;

	private String buyWay;//处理方式：1—自己取票;2—委托出票;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getSchemeID() {
		return schemeID;
	}

	public void setSchemeID(Integer schemeID) {
		this.schemeID = schemeID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public String getUsedMachine() {
		return usedMachine;
	}

	public void setUsedMachine(String usedMachine) {
		this.usedMachine = usedMachine;
	}

	public Date getvEnd() {
		return vEnd;
	}

	public void setvEnd(Date vEnd) {
		this.vEnd = vEnd;
	}

	public Date getvStart() {
		return vStart;
	}

	public void setvStart(Date vStart) {
		this.vStart = vStart;
	}

	public String getvMoney() {
		return vMoney;
	}

	public void setvMoney(String vMoney) {
		this.vMoney = vMoney;
	}

	public String getvStatus() {
		return vStatus;
	}

	public void setvStatus(String vStatus) {
		this.vStatus = vStatus;
	}

	public String getvRecordUser() {
		return vRecordUser;
	}

	public void setvRecordUser(String vRecordUser) {
		this.vRecordUser = vRecordUser;
	}

	public Date getvRecordDate() {
		return vRecordDate;
	}

	public void setvRecordDate(Date vRecordDate) {
		this.vRecordDate = vRecordDate;
	}

	public String getvNo() {
		return vNo;
	}

	public void setvNo(String vNo) {
		this.vNo = vNo;
	}

	public Date getSycDate() {
		return sycDate;
	}

	public void setSycDate(Date sycDate) {
		this.sycDate = sycDate;
	}

	public String getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(String buyWay) {
		this.buyWay = buyWay;
	}
}

package com.jy.entity.system.channels;

import java.util.Date;

/**
 * @文件名:VoucherInfo.java @功能描述：
 * @创建日期:2017年3月21日上午9:16:46
 * @创建人：Dingj @Copyright（c） 2017,all rights reserved by days
 */
public class VoucherInfo {
	
	private String schemeNo; // 方案编号
	
	private String batchNo; // 批次
	
	private String vNo; // 代金券编号
	
	private String vMoney; // 券金额
	
	private String vStatus; // 使用状态
	
	private Date usedDate; // 使用时间
	
	private String usedMachine; // 终端编号
	
	private Date vStart; // 开始日期
	
	private Date vEnd; // 结束日期
	
	private Date vRecordDate; // 记录录入时间
	
	private String vRecordUser;// 记录录入人

	private String buyWay;//处理方式：1—自己取票;2—委托出票;

	public String getSchemeNo() {
		return schemeNo;
	}
	
	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}
	
	public String getBatchNo() {
		return batchNo;
	}
	
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public String getvNo() {
		return vNo;
	}
	
	public void setvNo(String vNo) {
		this.vNo = vNo;
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

	public Date getvStart() {
		return vStart;
	}

	public void setvStart(Date vStart) {
		this.vStart = vStart;
	}

	public Date getvEnd() {
		return vEnd;
	}

	public void setvEnd(Date vEnd) {
		this.vEnd = vEnd;
	}

	public Date getvRecordDate() {
		return vRecordDate;
	}
	
	public void setvRecordDate(Date vRecordDate) {
		this.vRecordDate = vRecordDate;
	}

	public String getvRecordUser() {
		return vRecordUser;
	}
	
	public void setvRecordUser(String vRecordUser) {
		this.vRecordUser = vRecordUser;
	}

	public String getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(String buyWay) {
		this.buyWay = buyWay;
	}
}

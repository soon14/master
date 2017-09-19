package com.jy.entity.system.finance.reconciliation.lottery;

import java.math.BigDecimal;
import java.util.Date;


/** 
 * @文件名:TicketInfoDetail.java 
 * @功能描述：
 * @创建日期:2017年3月10日下午6:29:35 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
public class TicketInfoDetail {
	
	 private String batchNo; // 交易批次号
	    private String issueNo; //彩票期次
	    private String ticketNo ;//用户方案编号
	    private double money ;//票面资金
	    private String lotteryName ;//彩种
	    private String machionNo ;//投注机编号
	    private String playWay ;//玩法
	    private String flowCode; //系统流水号
	    private Date ticketTime ; //出票时间
	    private Date importDateTime ; //导入时间
	    private String importDate;      //导入日期
	    private String ImportUser;  // 导入用户
	    private String isOnline;    //1 线上 2 线下
		private String 		totalNumber;   		//总笔数
	    private BigDecimal	totalAumone;	//总金额
	    private Date	createTime;
	    private Integer status;
	    private Integer isValid;
		
        public String getBatchNo() {
        	return batchNo;
        }
		
        public void setBatchNo(String batchNo) {
        	this.batchNo = batchNo;
        }
		
        public String getIssueNo() {
        	return issueNo;
        }
		
        public void setIssueNo(String issueNo) {
        	this.issueNo = issueNo;
        }
		
        public String getTicketNo() {
        	return ticketNo;
        }
		
        public void setTicketNo(String ticketNo) {
        	this.ticketNo = ticketNo;
        }
		
        public String getLotteryName() {
        	return lotteryName;
        }
		
        public void setLotteryName(String lotteryName) {
        	this.lotteryName = lotteryName;
        }
		
        public String getMachionNo() {
        	return machionNo;
        }
		
        public void setMachionNo(String machionNo) {
        	this.machionNo = machionNo;
        }
		
        public String getPlayWay() {
        	return playWay;
        }
		
        public void setPlayWay(String playWay) {
        	this.playWay = playWay;
        }
		
        public String getFlowCode() {
        	return flowCode;
        }
		
        public void setFlowCode(String flowCode) {
        	this.flowCode = flowCode;
        }
		
        public Date getImportDateTime() {
        	return importDateTime;
        }
		
        public void setImportDateTime(Date importDateTime) {
        	this.importDateTime = importDateTime;
        }
		
        public String getImportDate() {
        	return importDate;
        }
		
        public void setImportDate(String importDate) {
        	this.importDate = importDate;
        }
		
        public String getImportUser() {
        	return ImportUser;
        }
		
        public void setImportUser(String importUser) {
        	ImportUser = importUser;
        }
		
        public String getIsOnline() {
        	return isOnline;
        }
		
        public void setIsOnline(String isOnline) {
        	this.isOnline = isOnline;
        }
		
        public BigDecimal getTotalAumone() {
        	return totalAumone;
        }
		
        public void setTotalAumone(BigDecimal totalAumone) {
        	this.totalAumone = totalAumone;
        }

		
        public String getTotalNumber() {
        	return totalNumber;
        }

		
        public void setTotalNumber(String totalNumber) {
        	this.totalNumber = totalNumber;
        }

		
        public Date getCreateTime() {
        	return createTime;
        }

		
        public void setCreateTime(Date createTime) {
        	this.createTime = createTime;
        }

		
        public Integer getStatus() {
        	return status;
        }

		
        public void setStatus(Integer status) {
        	this.status = status;
        }

		
        public Integer getIsValid() {
        	return isValid;
        }

		
        public void setIsValid(Integer isValid) {
        	this.isValid = isValid;
        }

		
        public double getMoney() {
        	return money;
        }

		
        public void setMoney(double money) {
        	this.money = money;
        }

		
        public Date getTicketTime() {
        	return ticketTime;
        }

		
        public void setTicketTime(Date ticketTime) {
        	this.ticketTime = ticketTime;
        }

		




}

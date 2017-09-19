package com.jy.entity.system.finance.reconciliation.lottery;

import java.math.BigDecimal;
import java.util.Date;


/** 
 * @文件名:统计销售数量
 * @功能描述：
 * @创建日期:2017年3月14日下午7:43:55 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
public class CountSale {
	private Integer id;
	private BigDecimal bettingAmount;		//投注金额
	private BigDecimal ticketAmount;		//出票金额
	private Integer bettingNumber;			//投注条数
	private Integer ticketNumber;			//出票条数
	private String	statisticsDate; 		//统计时间（某一天数据的时间）
	private BigDecimal totalAumone;			//总条数
	private BigDecimal amountDifference;	//差异金额
	private Integer numberDifference;		//差异条数
	private BigDecimal offlineVolume;		//线下销量
	private Date createDate;				//创建时间
	private String createUser;				//创建人
	
	
    public BigDecimal getBettingAmount() {
    	return bettingAmount;
    }
	
    public void setBettingAmount(BigDecimal bettingAmount) {
    	this.bettingAmount = bettingAmount;
    }
	
    public BigDecimal getTicketAmount() {
    	return ticketAmount;
    }
	
    public void setTicketAmount(BigDecimal ticketAmount) {
    	this.ticketAmount = ticketAmount;
    }
	
    public Integer getBettingNumber() {
    	return bettingNumber;
    }
	
    public void setBettingNumber(Integer bettingNumber) {
    	this.bettingNumber = bettingNumber;
    }
	
    public Integer getTicketNumber() {
    	return ticketNumber;
    }
	
    public void setTicketNumber(Integer ticketNumber) {
    	this.ticketNumber = ticketNumber;
    }
	
    public BigDecimal getTotalAumone() {
    	return totalAumone;
    }
    public void setTotalAumone(BigDecimal totalAumone) {
    	this.totalAumone = totalAumone;
    }
	
    public BigDecimal getAmountDifference() {
    	return amountDifference;
    }
	
    public void setAmountDifference(BigDecimal amountDifference) {
    	this.amountDifference = amountDifference;
    }
	
    public Integer getNumberDifference() {
    	return numberDifference;
    }
	
    public void setNumberDifference(Integer numberDifference) {
    	this.numberDifference = numberDifference;
    }
	
    public BigDecimal getOfflineVolume() {
    	return offlineVolume;
    }
	
    public void setOfflineVolume(BigDecimal offlineVolume) {
    	this.offlineVolume = offlineVolume;
    }
	
    public Date getCreateDate() {
    	return createDate;
    }
	
    public void setCreateDate(Date createDate) {
    	this.createDate = createDate;
    }
	
    public String getCreateUser() {
    	return createUser;
    }
	
    public void setCreateUser(String createUser) {
    	this.createUser = createUser;
    }

	
    public Integer getId() {
    	return id;
    }

	
    public void setId(Integer id) {
    	this.id = id;
    }

	
    public String getStatisticsDate() {
    	return statisticsDate;
    }

	
    public void setStatisticsDate(String statisticsDate) {
    	this.statisticsDate = statisticsDate;
    }

}

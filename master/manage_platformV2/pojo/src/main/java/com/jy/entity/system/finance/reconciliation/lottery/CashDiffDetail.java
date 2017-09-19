package com.jy.entity.system.finance.reconciliation.lottery;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @文件名:CashDiffDetail.java
 * @功能描述：
 * @创建日期:2017年3月21日下午3:29:53
 * @创建人：xin
 * @Copyright（c） 2017,all rights reserved by days
 */
@Alias("CashDiffDetail")
public class CashDiffDetail extends BaseEntity {

	private int dfId;

	private Date normalDate;

	private Date prizeTime;

	private int dfReportId;

	private String ticketNo;

	private BigDecimal sendPrize;

	private BigDecimal ticketPrize;

	private BigDecimal diffMoney;

	private Date dfInitDate;

	private String dfProcessStauts;

	private String dfProcessInfo;

	private Integer type;				//区分差异类型

	public int getDfId() {
		return dfId;
	}

	public void setDfId(int dfId) {
		this.dfId = dfId;
	}

	public Date getNormalDate() {
		return normalDate;
	}

	public void setNormalDate(Date normalDate) {
		this.normalDate = normalDate;
	}

	public Date getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(Date prizeTime) {
		this.prizeTime = prizeTime;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public BigDecimal getSendPrize() {
		return sendPrize;
	}

	public void setSendPrize(BigDecimal sendPrize) {
		this.sendPrize = sendPrize;
	}

	public BigDecimal getTicketPrize() {
		return ticketPrize;
	}

	public void setTicketPrize(BigDecimal ticketPrize) {
		this.ticketPrize = ticketPrize;
	}

	public BigDecimal getDiffMoney() {
		return diffMoney;
	}

	public void setDiffMoney(BigDecimal diffMoney) {
		this.diffMoney = diffMoney;
	}

	public Date getDfInitDate() {
		return dfInitDate;
	}

	public void setDfInitDate(Date dfInitDate) {
		this.dfInitDate = dfInitDate;
	}

	public String getDfProcessStauts() {
		return dfProcessStauts;
	}

	public void setDfProcessStauts(String dfProcessStauts) {
		this.dfProcessStauts = dfProcessStauts;
	}

	public String getDfProcessInfo() {
		return dfProcessInfo;
	}

	public void setDfProcessInfo(String dfProcessInfo) {
		this.dfProcessInfo = dfProcessInfo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getDfReportId() {
		return dfReportId;
	}

	public void setDfReportId(int dfReportId) {
		this.dfReportId = dfReportId;
	}


}

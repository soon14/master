package com.jy.entity.system.finance.reconciliation.lottery;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import sun.security.util.BigInt;

/**
 * @文件名:SendPrizeInfo.java
 * @功能描述：
 * @创建日期:2017年3月20日下午3:35:30
 * @创建人：xin
 */
@Alias("SendPrizeInfo")
public class SendPrizeInfo {

	private Date sycDate;// 同步时间

	private BigInt schemeId;// 方案ID

	private BigInt schemeExtendId;// 方案ID

	private BigDecimal money;// 订单号金额

	private BigInt userId;// 彩票系统的订单ID

	private String lotteryName;// 派奖的彩种名称

	private String issue;// 中奖期次号

	private BigDecimal prize;

	private Date prizeTime;// 派奖时间

	private Date createTime;// 创建时间

	private int isValid;// 是否有效

	// private String batchId;// 派奖批次号(彩票号)

	private BigDecimal sendPrizeTotalAccount;// 派奖总金额

	public Date getSycDate() {
		return sycDate;
	}

	public void setSycDate(Date sycDate) {
		this.sycDate = sycDate;
	}

	public BigInt getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(BigInt schemeId) {
		this.schemeId = schemeId;
	}

	public BigInt getSchemeExtendId() {
		return schemeExtendId;
	}

	public void setSchemeExtendId(BigInt schemeExtendId) {
		this.schemeExtendId = schemeExtendId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigInt getUserId() {
		return userId;
	}

	public void setUserId(BigInt userId) {
		this.userId = userId;
	}

	public String getLotteryName() {
		return lotteryName;
	}

	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public Date getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(Date prizeTime) {
		this.prizeTime = prizeTime;
	}

	public BigDecimal getSendPrizeTotalAccount() {
		return sendPrizeTotalAccount;
	}

	public void setSendPrizeTotalAccount(BigDecimal sendPrizeTotalAccount) {
		this.sendPrizeTotalAccount = sendPrizeTotalAccount;
	}

}

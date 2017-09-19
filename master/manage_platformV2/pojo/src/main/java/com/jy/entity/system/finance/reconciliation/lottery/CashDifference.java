package com.jy.entity.system.finance.reconciliation.lottery;

import java.math.BigDecimal;
import java.util.Date;


import org.apache.ibatis.type.Alias;

import com.jy.entity.base.BaseEntity;

/**
 * @文件名:CashDifference.java
 * @功能描述：
 * @创建日期:2017年3月16日下午1:38:26
 * @创建人：xin
 * @Copyright（c） 2017,all rights reserved by days
 */
@Alias("CashDifference")
public class CashDifference extends BaseEntity {

	private Integer id;// 主键id

	private String date;// 对账日期

	private BigDecimal startGap;// 期初差异额

	private BigDecimal onlineRedeemAmount;// 线上兑奖总额

	private BigDecimal bigAmount;// 大额兑奖

	private BigDecimal onlineSentAmount;// 线上派奖总额

	private BigDecimal redeemSentGap;// 线上兑奖和向上派奖差异、

	private BigDecimal endGap;// 期末差异额

	private BigDecimal offlineRedeemAmount;// 线下兑奖总额

	private BigDecimal totalRedeemAmount;// 总的兑奖金额

	private int isValid;// 状态

	private Date createDate;// 创建时间

	private String createUser;// 创建人

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getStartGap() {
		return startGap;
	}

	public void setStartGap(BigDecimal startGap) {
		this.startGap = startGap;
	}

	public BigDecimal getOnlineRedeemAmount() {
		return onlineRedeemAmount;
	}

	public void setOnlineRedeemAmount(BigDecimal onlineRedeemAmount) {
		this.onlineRedeemAmount = onlineRedeemAmount;
	}

	public BigDecimal getBigAmount() {
		return bigAmount;
	}

	public void setBigAmount(BigDecimal bigAmount) {
		this.bigAmount = bigAmount;
	}

	public BigDecimal getOnlineSentAmount() {
		return onlineSentAmount;
	}

	public void setOnlineSentAmount(BigDecimal onlineSentAmount) {
		this.onlineSentAmount = onlineSentAmount;
	}

	public BigDecimal getRedeemSentGap() {
		return redeemSentGap;
	}

	public void setRedeemSentGap(BigDecimal redeemSentGap) {
		this.redeemSentGap = redeemSentGap;
	}

	public BigDecimal getEndGap() {
		return endGap;
	}

	public void setEndGap(BigDecimal endGap) {
		this.endGap = endGap;
	}

	public BigDecimal getOfflineRedeemAmount() {
		return offlineRedeemAmount;
	}

	public void setOfflineRedeemAmount(BigDecimal offlineRedeemAmount) {
		this.offlineRedeemAmount = offlineRedeemAmount;
	}

	public BigDecimal getTotalRedeemAmount() {
		return totalRedeemAmount;
	}

	public void setTotalRedeemAmount(BigDecimal totalRedeemAmount) {
		this.totalRedeemAmount = totalRedeemAmount;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
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

}

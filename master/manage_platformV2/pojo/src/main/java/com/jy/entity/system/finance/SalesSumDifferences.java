package com.jy.entity.system.finance;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * 投注系统销售差异总报表
 */
@Alias("SalesSumDifferences")
public class SalesSumDifferences extends BaseEntity{
	private Integer betId;
	private String 	betDate;				//时间
	private String	betBetCount;			//投注系统笔数
	private Double	betBetMoney;			//投注系统金额
	private String	betIssueCount;			//出票系统笔数
	private Double	betIssueMoney;			//出票系统金额
	private Double	betOffline;				//线下销量
	private Double	betSumMoney;			//销售总额
	private String	betDifferenceCount;		//差异笔数
	private Double	betDifferenceMoney;		//差异金额

	public Integer getBetId() {
		return betId;
	}

	public void setBetId(Integer betId) {
		this.betId = betId;
	}

	public String getBetDate() {
		return betDate;
	}

	public void setBetDate(String betDate) {
		this.betDate = betDate;
	}

	public String getBetBetCount() {
		return betBetCount;
	}

	public void setBetBetCount(String betBetCount) {
		this.betBetCount = betBetCount;
	}

	public Double getBetBetMoney() {
		return betBetMoney;
	}

	public void setBetBetMoney(Double betBetMoney) {
		this.betBetMoney = betBetMoney;
	}

	public String getBetIssueCount() {
		return betIssueCount;
	}

	public void setBetIssueCount(String betIssueCount) {
		this.betIssueCount = betIssueCount;
	}

	public Double getBetIssueMoney() {
		return betIssueMoney;
	}

	public void setBetIssueMoney(Double betIssueMoney) {
		this.betIssueMoney = betIssueMoney;
	}

	public Double getBetOffline() {
		return betOffline;
	}

	public void setBetOffline(Double betOffline) {
		this.betOffline = betOffline;
	}

	public Double getBetSumMoney() {
		return betSumMoney;
	}

	public void setBetSumMoney(Double betSumMoney) {
		this.betSumMoney = betSumMoney;
	}

	public String getBetDifferenceCount() {
		return betDifferenceCount;
	}

	public void setBetDifferenceCount(String betDifferenceCount) {
		this.betDifferenceCount = betDifferenceCount;
	}

	public Double getBetDifferenceMoney() {
		return betDifferenceMoney;
	}

	public void setBetDifferenceMoney(Double betDifferenceMoney) {
		this.betDifferenceMoney = betDifferenceMoney;
	}
}
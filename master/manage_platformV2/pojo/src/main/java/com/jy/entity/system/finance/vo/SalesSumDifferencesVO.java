package com.jy.entity.system.finance.vo;

import com.jy.entity.system.finance.SalesSumDifferences;
import org.apache.ibatis.type.Alias;

/**
 * 投注系统销售差异总报表
 */
@Alias("SalesSumDifferencesVO")
public class SalesSumDifferencesVO extends SalesSumDifferences{
	private String	dCondition;					//处理状态
	private String	beginTime;					//开始时间
	private String	endTime;					//截止时间
	private String	dealResultStatus;			//处理状态
	private String	datetime;					//日期时间

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDealResultStatus() {
		return dealResultStatus;
	}

	public void setDealResultStatus(String dealResultStatus) {
		this.dealResultStatus = dealResultStatus;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getdCondition() {
		return dCondition;
	}

	public void setdCondition(String dCondition) {
		this.dCondition = dCondition;
	}
}
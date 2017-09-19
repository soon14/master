package com.jy.entity.system.finance.reconciliation.lottery;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.Date;


/**
 * @文件名:PlatformFundsRunning.java
 * @功能描述：平台资金流水对账汇总表
 * @创建日期:2017-1-6上午10:51:12
 * @创建人：Dingj
 * @Copyright（c） 2017, all rights reserved by days
 */
@Alias("PlatformFundsRunning")
public class PlatformFundsRunning extends BaseEntity {

    private String id;//表主键Id
    private Date dzDate;//对账日
    private Double totalUnionAmt;//银联交易总金额
    private Double totalDaysAmt;//得仕通交易总金额
    private Double totalWechatAmt;//微信交易总金额
    private Double totalAmt;//彩票平台交易总金额
    private Double totalRunUnionAmt;//银联流水交易总金额
    private Double totalRunDaysAmt;//得仕通流水交易总金额
    private Double totalRunWechatAmt;//微信流水交易总金额
    private Double totalRunAmt;//流水交易总金额
    private Double totalDive;//总差异额
    private Double dealAmt;//处理总金额
    private Double diveAfterDeal;//处理后总差异额
    private Double totalSumDive;//累计总差异额
    private int dealStatus;//处理状态(0:未处理 1：处理中 2：已处理 3:不用处理)
    private String createTime;//创建时间
    private int isValid;//是否有效(0:无效 1:有效)
    private Double insideRecharge;//内部存入

    @Override
    public String toString() {
        return "PlatformFundsRunning{" +
                "id='" + id + '\'' +
                ", dzDate=" + dzDate +
                ", totalUnionAmt=" + totalUnionAmt +
                ", totalDaysAmt=" + totalDaysAmt +
                ", totalWechatAmt=" + totalWechatAmt +
                ", totalAmt=" + totalAmt +
                ", totalRunUnionAmt=" + totalRunUnionAmt +
                ", totalRunDaysAmt=" + totalRunDaysAmt +
                ", totalRunWechatAmt=" + totalRunWechatAmt +
                ", totalRunAmt=" + totalRunAmt +
                ", totalDive=" + totalDive +
                ", dealAmt=" + dealAmt +
                ", totalSumDive=" + totalSumDive +
                ", dealStatus=" + dealStatus +
                ", createTime='" + createTime + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDzDate() {
        return dzDate;
    }

    public void setDzDate(Date dzDate) {
        this.dzDate = dzDate;
    }

    public Double getTotalUnionAmt() {
        return totalUnionAmt;
    }

    public void setTotalUnionAmt(Double totalUnionAmt) {
        this.totalUnionAmt = totalUnionAmt;
    }

    public Double getTotalDaysAmt() {
        return totalDaysAmt;
    }

    public void setTotalDaysAmt(Double totalDaysAmt) {
        this.totalDaysAmt = totalDaysAmt;
    }

    public Double getTotalWechatAmt() {
        return totalWechatAmt;
    }

    public void setTotalWechatAmt(Double totalWechatAmt) {
        this.totalWechatAmt = totalWechatAmt;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Double getTotalRunUnionAmt() {
        return totalRunUnionAmt;
    }

    public void setTotalRunUnionAmt(Double totalRunUnionAmt) {
        this.totalRunUnionAmt = totalRunUnionAmt;
    }

    public Double getTotalRunDaysAmt() {
        return totalRunDaysAmt;
    }

    public void setTotalRunDaysAmt(Double totalRunDaysAmt) {
        this.totalRunDaysAmt = totalRunDaysAmt;
    }

    public Double getTotalRunWechatAmt() {
        return totalRunWechatAmt;
    }

    public void setTotalRunWechatAmt(Double totalRunWechatAmt) {
        this.totalRunWechatAmt = totalRunWechatAmt;
    }

    public Double getTotalRunAmt() {
        return totalRunAmt;
    }

    public void setTotalRunAmt(Double totalRunAmt) {
        this.totalRunAmt = totalRunAmt;
    }

    public Double getTotalDive() {
        return totalDive;
    }

    public void setTotalDive(Double totalDive) {
        this.totalDive = totalDive;
    }

    public int getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(int dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public Double getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(Double dealAmt) {
        this.dealAmt = dealAmt;
    }

    public Double getTotalSumDive() {
        return totalSumDive;
    }

    public void setTotalSumDive(Double totalSumDive) {
        this.totalSumDive = totalSumDive;
    }

    public Double getDiveAfterDeal() {
        return diveAfterDeal;
    }

    public void setDiveAfterDeal(Double diveAfterDeal) {
        this.diveAfterDeal = diveAfterDeal;
    }

    public Double getInsideRecharge() {
        return insideRecharge;
    }

    public void setInsideRecharge(Double insideRecharge) {
        this.insideRecharge = insideRecharge;
    }
}

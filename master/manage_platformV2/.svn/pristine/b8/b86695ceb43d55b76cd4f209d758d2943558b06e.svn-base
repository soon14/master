package com.jy.entity.system.finance.reconciliation.lottery;

import org.apache.ibatis.type.Alias;

import com.jy.entity.base.BaseEntity;

import java.util.Date;


/**
 * @文件名:PlatformFunds.java
 * @功能描述：平台资金汇总表
 * @创建日期:2017-1-6上午10:51:12
 * @创建人：Dingj
 * @Copyright（c） 2017, all rights reserved by days
 */
@Alias("PlatformFunds")
public class PlatformFunds extends BaseEntity {

    private String id;//表主键Id
    private Date dzDate;//对账日
    private Double qcye;//统计日期期初余额
    private Double totalRecharge;//充值总金额
    private Double recharge;//正常充值
    private Double internalDeposit ;//内部存入
    private Double totalBuyPick;//购彩_自取方式
    private Double totalBuyEntrust;//购彩——委托方式
    private Double totalBuyTrance;//购彩——委托方式--追期
    private Double totalBuyCommon;//购彩——委托方式--非追期
    private Double totalBuy;//购彩汇总
    private Double totalPrizeAmt;//派奖金额
    private Double totalWithdraw;//提现
    private Double withdraw;//正常提现
    private Double  internalExtraction;//内部取出
    private Double totalRefund;//退款总金额
    private Double totalRefundWithDraw;//提现退款
    private Double totalRefundTicketFail;//出票退款
    private Double  commission;//返佣
    private  Double coupon;//优惠券
    private Double qmye;//统计日期期末余额
    private Double totalDive;//总差异额
    private Double dealAmt;//处理总金额
    private Double diveAfterDeal;//处理后总差异额
    private Double totalSumDive;//累计总差异额
    private int dealStatus;//处理状态(0:未处理 1：处理中 2：已处理 3:不用处理)
    private String createTime;//创建时间
    private int isValid;//是否有效(0:无效 1:有效)
    private int type;//类型(1:正常 2:移动活动,3：得仕捕鱼，4：得仕棋牌5：欧建商户)

    @Override
    public String toString() {
        return "PlatformFunds{" +
                "id='" + id + '\'' +
                ", dzDate=" + dzDate +
                ", qcye=" + qcye +
                ", totalRecharge=" + totalRecharge +
                ", totalBuyPick=" + totalBuyPick +
                ", totalBuyEntrust=" + totalBuyEntrust +
                ", totalBuy=" + totalBuy +
                ", totalPrizeAmt=" + totalPrizeAmt +
                ", totalWithdraw=" + totalWithdraw +
                ", totalRefund=" + totalRefund +
                ", qmye=" + qmye +
                ", totalDive=" + totalDive +
                ", dealAmt=" + dealAmt +
                ", diveAfterDeal=" + diveAfterDeal +
                ", totalSumDive=" + totalSumDive +
                ", dealStatus=" + dealStatus +
                ", createTime='" + createTime + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public Double getRecharge() {
        return recharge;
    }

    public void setRecharge(Double recharge) {
        this.recharge = recharge;
    }

    public Double getInternalDeposit() {
        return internalDeposit;
    }

    public void setInternalDeposit(Double internalDeposit) {
        this.internalDeposit = internalDeposit;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getInternalExtraction() {
        return internalExtraction;
    }

    public void setInternalExtraction(Double internalExtraction) {
        this.internalExtraction = internalExtraction;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getCoupon() {
        return coupon;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Double getQcye() {
        return qcye;
    }

    public void setQcye(Double qcye) {
        this.qcye = qcye;
    }

    public Double getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(Double totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public Double getTotalBuyPick() {
        return totalBuyPick;
    }

    public void setTotalBuyPick(Double totalBuyPick) {
        this.totalBuyPick = totalBuyPick;
    }

    public Double getTotalBuyEntrust() {
        return totalBuyEntrust;
    }

    public void setTotalBuyEntrust(Double totalBuyEntrust) {
        this.totalBuyEntrust = totalBuyEntrust;
    }

    public Double getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(Double totalBuy) {
        this.totalBuy = totalBuy;
    }

    public Double getTotalPrizeAmt() {
        return totalPrizeAmt;
    }

    public void setTotalPrizeAmt(Double totalPrizeAmt) {
        this.totalPrizeAmt = totalPrizeAmt;
    }

    public Double getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(Double totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Double getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Double totalRefund) {
        this.totalRefund = totalRefund;
    }

    public Double getQmye() {
        return qmye;
    }

    public void setQmye(Double qmye) {
        this.qmye = qmye;
    }

    public Double getTotalDive() {
        return totalDive;
    }

    public void setTotalDive(Double totalDive) {
        this.totalDive = totalDive;
    }

    public Double getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(Double dealAmt) {
        this.dealAmt = dealAmt;
    }

    public Double getDiveAfterDeal() {
        return diveAfterDeal;
    }

    public void setDiveAfterDeal(Double diveAfterDeal) {
        this.diveAfterDeal = diveAfterDeal;
    }

    public Double getTotalSumDive() {
        return totalSumDive;
    }

    public void setTotalSumDive(Double totalSumDive) {
        this.totalSumDive = totalSumDive;
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

    public Double getTotalBuyTrance() {
        return totalBuyTrance;
    }

    public void setTotalBuyTrance(Double totalBuyTrance) {
        this.totalBuyTrance = totalBuyTrance;
    }

    public Double getTotalBuyCommon() {
        return totalBuyCommon;
    }

    public Double getTotalRefundTicketFail() {
        return totalRefundTicketFail;
    }

    public void setTotalBuyCommon(Double totalBuyCommon) {
        this.totalBuyCommon = totalBuyCommon;
    }

    public Double getTotalRefundWithDraw() {
        return totalRefundWithDraw;
    }

    public void setTotalRefundWithDraw(Double totalRefundWithDraw) {
        this.totalRefundWithDraw = totalRefundWithDraw;
    }

    public void setTotalRefundTicketFail(Double totalRefundTicketFail) {
        this.totalRefundTicketFail = totalRefundTicketFail;
    }
}

package com.jy.entity.system.finance.reconciliation.lottery;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lijunke on 2017/5/18.
 * 兑奖差异汇总
 */
@Alias("CashPrizeDifference")
public class CashPrizeDifference {

    private int id;
    private String  tallyDate;                      //记录日期
    private BigDecimal firstDiffPrize;              //期初差异额
    private BigDecimal onlineCashPrize;             //线上兑奖总额
    private BigDecimal bigAmount;                   //大额兑奖
    private BigDecimal onlineSentPrize;             //线上派奖金额
    private BigDecimal cashSendDiff;                //线上兑奖和派奖的差异
    private BigDecimal lastDiffPrize;               //期末差异额
    private BigDecimal offlineCashPrize;            //线下兑奖总额
    private BigDecimal totalPrize;                  //总的兑奖金额
    private BigDecimal totalDiffPrize;              //总差异
    private int isValid;                            //状态
    private Date createDate;
    private Date updateDate;

    //查询字段
    private Date startDate;
    private Date endDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTallyDate() {
        return tallyDate;
    }

    public void setTallyDate(String tallyDate) {
        this.tallyDate = tallyDate;
    }

    public BigDecimal getFirstDiffPrize() {
        return firstDiffPrize;
    }

    public void setFirstDiffPrize(BigDecimal firstDiffPrize) {
        this.firstDiffPrize = firstDiffPrize;
    }

    public BigDecimal getOnlineCashPrize() {
        return onlineCashPrize;
    }

    public void setOnlineCashPrize(BigDecimal onlineCashPrize) {
        this.onlineCashPrize = onlineCashPrize;
    }

    public BigDecimal getBigAmount() {
        return bigAmount;
    }

    public void setBigAmount(BigDecimal bigAmount) {
        this.bigAmount = bigAmount;
    }

    public BigDecimal getOnlineSentPrize() {
        return onlineSentPrize;
    }

    public void setOnlineSentPrize(BigDecimal onlineSentPrize) {
        this.onlineSentPrize = onlineSentPrize;
    }

    public BigDecimal getCashSendDiff() {
        return cashSendDiff;
    }

    public void setCashSendDiff(BigDecimal cashSendDiff) {
        this.cashSendDiff = cashSendDiff;
    }

    public BigDecimal getLastDiffPrize() {
        return lastDiffPrize;
    }

    public void setLastDiffPrize(BigDecimal lastDiffPrize) {
        this.lastDiffPrize = lastDiffPrize;
    }

    public BigDecimal getOfflineCashPrize() {
        return offlineCashPrize;
    }

    public void setOfflineCashPrize(BigDecimal offlineCashPrize) {
        this.offlineCashPrize = offlineCashPrize;
    }

    public BigDecimal getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(BigDecimal totalPrize) {
        this.totalPrize = totalPrize;
    }

    public BigDecimal getTotalDiffPrize() {
        return totalDiffPrize;
    }

    public void setTotalDiffPrize(BigDecimal totalDiffPrize) {
        this.totalDiffPrize = totalDiffPrize;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

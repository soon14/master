package com.jy.vo.system.statistics.lottery;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/20.
 */
public class QrCodeSoldVo {
    private Integer batchId;//批次
    private Date batchStartTime;//批次开始时间
    private Date batchEndTime;//批次结束时间
    private String date;//日期
    private Double startAllMoney;//券未兑换期初余额
    private Double selfSaleMoney;//自取售出金额
    private Double selfUsedMoney;//自取兑换金额
    private Double weiTuoSaleMoney;//委托售出金额
    private Double weiTuoUsedMoney;//委托兑换金额
    private Double saleAllMoney;//售出总金额
    private Double usedAllMoney;//兑换总金额
    private Double diff;//售出券未兑换期未余额差异
    private Double startExpireMoney;//期初过期兑换券额
    private Double nowNewExpireMoney;//本期新增过期兑换券额s
    private Double nowExpireMoney;//本期兑换过期券额
    private Double endExpireMoney;//期末过期兑换券额

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Date getBatchStartTime() {
        return batchStartTime;
    }

    public void setBatchStartTime(Date batchStartTime) {
        this.batchStartTime = batchStartTime;
    }

    public Date getBatchEndTime() {
        return batchEndTime;
    }

    public void setBatchEndTime(Date batchEndTime) {
        this.batchEndTime = batchEndTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getStartAllMoney() {
        return startAllMoney;
    }

    public void setStartAllMoney(Double startAllMoney) {
        this.startAllMoney = startAllMoney;
    }

    public Double getSelfSaleMoney() {
        return selfSaleMoney;
    }

    public void setSelfSaleMoney(Double selfSaleMoney) {
        this.selfSaleMoney = selfSaleMoney;
    }

    public Double getSelfUsedMoney() {
        return selfUsedMoney;
    }

    public void setSelfUsedMoney(Double selfUsedMoney) {
        this.selfUsedMoney = selfUsedMoney;
    }

    public Double getWeiTuoSaleMoney() {
        return weiTuoSaleMoney;
    }

    public void setWeiTuoSaleMoney(Double weiTuoSaleMoney) {
        this.weiTuoSaleMoney = weiTuoSaleMoney;
    }

    public Double getWeiTuoUsedMoney() {
        return weiTuoUsedMoney;
    }

    public void setWeiTuoUsedMoney(Double weiTuoUsedMoney) {
        this.weiTuoUsedMoney = weiTuoUsedMoney;
    }

    public Double getSaleAllMoney() {
        return saleAllMoney;
    }

    public void setSaleAllMoney(Double saleAllMoney) {
        this.saleAllMoney = saleAllMoney;
    }

    public Double getUsedAllMoney() {
        return usedAllMoney;
    }

    public void setUsedAllMoney(Double usedAllMoney) {
        this.usedAllMoney = usedAllMoney;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public Double getStartExpireMoney() {
        return startExpireMoney;
    }

    public void setStartExpireMoney(Double startExpireMoney) {
        this.startExpireMoney = startExpireMoney;
    }

    public Double getNowNewExpireMoney() {
        return nowNewExpireMoney;
    }

    public void setNowNewExpireMoney(Double nowNewExpireMoney) {
        this.nowNewExpireMoney = nowNewExpireMoney;
    }

    public Double getNowExpireMoney() {
        return nowExpireMoney;
    }

    public void setNowExpireMoney(Double nowExpireMoney) {
        this.nowExpireMoney = nowExpireMoney;
    }

    public Double getEndExpireMoney() {
        return endExpireMoney;
    }

    public void setEndExpireMoney(Double endExpireMoney) {
        this.endExpireMoney = endExpireMoney;
    }
}

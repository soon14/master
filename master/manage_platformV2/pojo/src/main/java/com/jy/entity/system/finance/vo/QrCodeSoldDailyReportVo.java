package com.jy.entity.system.finance.vo;


/**
 * Created by Administrator on 2017/4/10.
 */
public class QrCodeSoldDailyReportVo {
    private String batchId;//批次
    private String batchStartTime;//批次开始时间
    private String batchEndTime;//批次结束时间
    private String date;//日期
    private String startAllMoney;//券未兑换期初余额
    private String selfSaleMoney;//自取售出金额
    private String selfUsedMoney;//自取兑换金额
    private String weiTuoSaleMoney;//委托售出金额
    private String weiTuoUsedMoney;//委托兑换金额
    private String saleAllMoney;//售出总金额
    private String usedAllMoney;//兑换总金额
    private String diff;//售出券未兑换期未余额差异
    private String startExpireMoney;//期初过期兑换券额
    private String nowNewExpireMoney;//本期新增过期兑换券额s
    private String nowExpireMoney;//本期兑换过期券额
    private String endExpireMoney;//期末过期兑换券额

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchStartTime() {
        return batchStartTime;
    }

    public void setBatchStartTime(String batchStartTime) {
        this.batchStartTime = batchStartTime;
    }

    public String getBatchEndTime() {
        return batchEndTime;
    }

    public void setBatchEndTime(String batchEndTime) {
        this.batchEndTime = batchEndTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartAllMoney() {
        return startAllMoney;
    }

    public void setStartAllMoney(String startAllMoney) {
        this.startAllMoney = startAllMoney;
    }

    public String getSelfSaleMoney() {
        return selfSaleMoney;
    }

    public void setSelfSaleMoney(String selfSaleMoney) {
        this.selfSaleMoney = selfSaleMoney;
    }

    public String getSelfUsedMoney() {
        return selfUsedMoney;
    }

    public void setSelfUsedMoney(String selfUsedMoney) {
        this.selfUsedMoney = selfUsedMoney;
    }

    public String getWeiTuoSaleMoney() {
        return weiTuoSaleMoney;
    }

    public void setWeiTuoSaleMoney(String weiTuoSaleMoney) {
        this.weiTuoSaleMoney = weiTuoSaleMoney;
    }

    public String getWeiTuoUsedMoney() {
        return weiTuoUsedMoney;
    }

    public void setWeiTuoUsedMoney(String weiTuoUsedMoney) {
        this.weiTuoUsedMoney = weiTuoUsedMoney;
    }

    public String getSaleAllMoney() {
        return saleAllMoney;
    }

    public void setSaleAllMoney(String saleAllMoney) {
        this.saleAllMoney = saleAllMoney;
    }

    public String getUsedAllMoney() {
        return usedAllMoney;
    }

    public void setUsedAllMoney(String usedAllMoney) {
        this.usedAllMoney = usedAllMoney;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getStartExpireMoney() {
        return startExpireMoney;
    }

    public void setStartExpireMoney(String startExpireMoney) {
        this.startExpireMoney = startExpireMoney;
    }

    public String getNowNewExpireMoney() {
        return nowNewExpireMoney;
    }

    public void setNowNewExpireMoney(String nowNewExpireMoney) {
        this.nowNewExpireMoney = nowNewExpireMoney;
    }

    public String getNowExpireMoney() {
        return nowExpireMoney;
    }

    public void setNowExpireMoney(String nowExpireMoney) {
        this.nowExpireMoney = nowExpireMoney;
    }

    public String getEndExpireMoney() {
        return endExpireMoney;
    }

    public void setEndExpireMoney(String endExpireMoney) {
        this.endExpireMoney = endExpireMoney;
    }
}

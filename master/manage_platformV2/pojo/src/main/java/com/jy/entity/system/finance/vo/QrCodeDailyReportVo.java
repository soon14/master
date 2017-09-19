package com.jy.entity.system.finance.vo;

/**
 * Created by Administrator on 2017/4/10.
 */
public class QrCodeDailyReportVo {
    private String batchId;//批次
    private String batchStartTime;//批次开始时间
    private String batchEndTime;//批次结束时间
    private String date;//日期
    private String startNum;//兑换券未使用期初数
    private String endNum;//兑换券未使用期末数
    private String diffNum;//差异数
    private String saleMoney;//售出金额

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

    public String getStartNum() {
        return startNum;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getEndNum() {
        return endNum;
    }

    public void setEndNum(String endNum) {
        this.endNum = endNum;
    }

    public String getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(String diffNum) {
        this.diffNum = diffNum;
    }

    public String getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(String saleMoney) {
        this.saleMoney = saleMoney;
    }
}

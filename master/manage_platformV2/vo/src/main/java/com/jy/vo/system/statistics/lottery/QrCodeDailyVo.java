package com.jy.vo.system.statistics.lottery;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/20.
 */
public class QrCodeDailyVo {

    private Integer batchId;//批次
    private Date batchStartTime;//批次开始时间
    private Date batchEndTime;//批次结束时间
    private String date;//日期
    private Double startNum;//兑换券未使用期初数
    private Double endNum;//兑换券未使用期末数
    private Double diffNum;//差异数
    private Double saleMoney;//售出金额

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

    public Double getStartNum() {
        return startNum;
    }

    public void setStartNum(Double startNum) {
        this.startNum = startNum;
    }

    public Double getEndNum() {
        return endNum;
    }

    public void setEndNum(Double endNum) {
        this.endNum = endNum;
    }

    public Double getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(Double diffNum) {
        this.diffNum = diffNum;
    }

    public Double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }
}

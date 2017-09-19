package com.jy.entity.system.finance.statistics;

import java.util.Date;

/**
 * 兑换券销售日报
 * Created by Administrator on 2017/2/13.
 */
public class QrCodeDailyReport {
    private Integer id;//
    private Integer batchId;//批次
    private Date batchStartTime;//批次开始时间
    private Date batchEndTime;//批次结束时间
    private String date;//日期
    private Double startNum;//兑换券未使用期初数
    private Double endNum;//兑换券未使用期末数
    private Double diffNum;//差异数
    private Double saleMoney;//售出金额

    private String beginTime;
    private String endTime;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBatchStartTime() {
        return batchStartTime;
    }

    public Date getBatchEndTime() {
        return batchEndTime;
    }

    public String getDate() {
        return date;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public Double getDiffNum() {
        return diffNum;
    }

    public Double getEndNum() {
        return endNum;
    }

    public Double getSaleMoney() {
        return saleMoney;
    }

    public Double getStartNum() {
        return startNum;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setBatchEndTime(Date batchEndTime) {
        this.batchEndTime = batchEndTime;
    }

    public void setBatchStartTime(Date batchStartTime) {
        this.batchStartTime = batchStartTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDiffNum(Double diffNum) {
        this.diffNum = diffNum;
    }

    public void setEndNum(Double endNum) {
        this.endNum = endNum;
    }

    public void setStartNum(Double startNum) {
        this.startNum = startNum;
    }

    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }
}

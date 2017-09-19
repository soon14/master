package com.jy.entity.system.finance.reconciliation.lottery;

/**
 * Created by Administrator on 2017/6/20.
 */
public class LotteryBuyAndTicketDiffReport {
    private Integer id;
    private  String date;
    private  String schemeExtendId;
    private  Double diffMoney;
    private String diffType;
    private String diffReason;
    private String status;//处理状态，0：待处理，1：处理中，2：已处理，3：不用处理
    private String handler;//处理人
    private String handleTime;//处理时间
    private String beginTime;
    private String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSchemeExtendId() {
        return schemeExtendId;
    }

    public void setSchemeExtendId(String schemeExtendId) {
        this.schemeExtendId = schemeExtendId;
    }

    public Double getDiffMoney() {
        return diffMoney;
    }

    public void setDiffMoney(Double diffMoney) {
        this.diffMoney = diffMoney;
    }

    public String getDiffType() {
        return diffType;
    }

    public void setDiffType(String diffType) {
        this.diffType = diffType;
    }

    public String getDiffReason() {
        return diffReason;
    }

    public void setDiffReason(String diffReason) {
        this.diffReason = diffReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

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
}

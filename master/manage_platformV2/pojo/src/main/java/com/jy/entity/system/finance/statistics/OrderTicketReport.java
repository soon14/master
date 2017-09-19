package com.jy.entity.system.finance.statistics;

/**方案与票报表，检验拆票过程
 * Created by Administrator on 2017/6/5.
 */
public class OrderTicketReport {
    private  Integer id;
    private String date;
    private Double commonOrderMoney;//普通订单表出票金额
    private Double tranceOrderMoney;//追期订单表出票金额
    private Double activityOrderMoney;//商户活动订单表出票金额
    private Double orderMoney;//订单表出票总金额
    private Double ticketMoney;//票表出票金额
    private Double diff;//差异


    private String beginTime;
    private String endTime;

    public Double getCommonOrderMoney() {
        return commonOrderMoney;
    }

    public void setCommonOrderMoney(Double commonOrderMoney) {
        this.commonOrderMoney = commonOrderMoney;
    }

    public Double getTranceOrderMoney() {
        return tranceOrderMoney;
    }

    public void setTranceOrderMoney(Double tranceOrderMoney) {
        this.tranceOrderMoney = tranceOrderMoney;
    }

    public Double getActivityOrderMoney() {
        return activityOrderMoney;
    }

    public void setActivityOrderMoney(Double activityOrderMoney) {
        this.activityOrderMoney = activityOrderMoney;
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

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Double getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(Double ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }
}

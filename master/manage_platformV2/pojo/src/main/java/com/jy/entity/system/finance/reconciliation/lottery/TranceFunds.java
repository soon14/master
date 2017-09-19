package com.jy.entity.system.finance.reconciliation.lottery;

import org.apache.ibatis.type.Alias;

import javax.xml.ws.Action;

/**
 * Created by Administrator on 2017/4/21.
 */
@Alias("TranceFunds")
public class TranceFunds {
    private Integer id;
    private String dzDate;//日期
    private Double allQcTranceMoney;//期初追期总额
    private Double ticketMoney;//当日出票金额
    private Double refund;//退款金额
    private Double addTranceMoney;//新增追期金额
    private Double allQmTranceMoney;//期末追期余额
    private Double diff;//差异


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

    public String getDzDate() {
        return dzDate;
    }

    public void setDzDate(String dzDate) {
        this.dzDate = dzDate;
    }

    public Double getAllQcTranceMoney() {
        return allQcTranceMoney;
    }

    public void setAllQcTranceMoney(Double allQcTranceMoney) {
        this.allQcTranceMoney = allQcTranceMoney;
    }

    public Double getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(Double ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public Double getRefund() {
        return refund;
    }

    public void setRefund(Double refund) {
        this.refund = refund;
    }

    public Double getAddTranceMoney() {
        return addTranceMoney;
    }

    public void setAddTranceMoney(Double addTranceMoney) {
        this.addTranceMoney = addTranceMoney;
    }

    public Double getAllQmTranceMoney() {
        return allQmTranceMoney;
    }

    public void setAllQmTranceMoney(Double allQmTranceMoney) {
        this.allQmTranceMoney = allQmTranceMoney;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }
}

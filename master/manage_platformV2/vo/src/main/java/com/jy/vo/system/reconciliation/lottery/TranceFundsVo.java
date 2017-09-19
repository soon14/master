package com.jy.vo.system.reconciliation.lottery;

/**
 * Created by Administrator on 2017/4/21.
 */
public class TranceFundsVo {
    private String dzDate;//日期
    private Double allQcTranceMoney;//期初追期总额
    private Double ticketMoney;//当日出票金额
    private Double refund;//退款金额
    private Double addTranceMoney;//新增追期金额
    private Double allQmTranceMoney;//期末追期余额
    private Double diff;//差异

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

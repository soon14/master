package com.jy.from.system.finance.reconciliation.lottery;

import java.io.Serializable;

/**
 * @decription:平台资金查询条件 Created by Dingj on 2017-01-11.
 */
public class PlatformFundsForm implements Serializable {
    private String beginTime;//开始日期
    private String endTime;//结束日期
    private Integer dealResultStatus;//处理状态(0:未处理 1：处理中 2：已处理 3:不用处理)
    private Integer fundsType;//

    public Integer getFundsType() {
        return fundsType;
    }

    public void setFundsType(Integer fundsType) {
        this.fundsType = fundsType;
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

    public Integer getDealResultStatus() {
        return dealResultStatus;
    }

    public void setDealResultStatus(Integer dealResultStatus) {
        this.dealResultStatus = dealResultStatus;
    }

    @Override
    public String toString() {
        return "PlatformFundsForm{" +
                "beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", dealResultStatus='" + dealResultStatus + '\'' +
                '}';
    }
  
}

package com.jy.from.system.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by shixi on 2017/4/27.
 */
public class UserBalanceForm implements Serializable
{
    @JSONField(name = "userId")
    private String userId;
    @JSONField(name = "balance")
    private Double funds;
    @JSONField(name = "transTime")
    private String transTime;
    @JSONField(name = "qcDate")
    private String qcDate;
    @JSONField(name = "transStatus")
    private String transStatus;
    @JSONField(name = "batchNo")
    private String batchNo;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Double getFunds()
    {
        return funds;
    }

    public void setFunds(Double funds)
    {
        this.funds = funds;
    }

    public String getTransTime()
    {
        return transTime;
    }

    public void setTransTime(String transTime)
    {
        this.transTime = transTime;
    }

    public String getQcDate()
    {
        return qcDate;
    }

    public void setQcDate(String qcDate)
    {
        this.qcDate = qcDate;
    }

    public String getTransStatus()
    {
        return transStatus;
    }

    public void setTransStatus(String transStatus)
    {
        this.transStatus = transStatus;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }
}

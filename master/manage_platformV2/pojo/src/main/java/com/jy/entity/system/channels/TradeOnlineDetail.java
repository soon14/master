package com.jy.entity.system.channels;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matthew on 2017/5/23.
 */
public class TradeOnlineDetail implements Serializable {


    private Integer cpUserId;
    private String tranTime;
    private String schemeType;
    private String tradeMoney;
    private String balance;
    private String lotteryId;
    private String userName;
    private Date createTime;

    public Integer getCpUserId() {
        return cpUserId;
    }

    public void setCpUserId(Integer cpUserId) {
        this.cpUserId = cpUserId;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
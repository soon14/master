package com.jy.entity.system.finance;

import java.util.Date;

/**
 * Created by ZQY on 2017/3/20.
 */
public class CpOrderInfo {
    private Integer id;//
    private String sycDate;//
    private Integer userId;
    private String lotteryId;//
    private String lotteryName;//
    private int playType;//playWay
    private String issue;//issueNo
    private Integer schemeId;//orderNo
    private Integer schemeExtendId;//subOrderNo
    private Double money;//orderMoney
    private String buyTime;//orderDate
    private int buyWay;//
    private int online;//isOnline
    private int  status;//orderStatus
    private String orderNo;//payId
    private String transNo;
    private String createTime;//
    private Date createTimeD;
    private String userName;
    private Integer isValid;//
    private String ticketTime;//出票时间
    private String schemeType;//
    private String type;
    private String schemeExtendType;
    private String orderType;
    private String refundTime;
    private String qcDate;

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSchemeExtendType() {
        return schemeExtendType;
    }

    public void setSchemeExtendType(String schemeExtendType) {
        this.schemeExtendType = schemeExtendType;
    }

    public Date getCreateTimeD() {
        return createTimeD;
    }

    public void setCreateTimeD(Date createTimeD) {
        this.createTimeD = createTimeD;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSycDate() {
        return sycDate;
    }

    public void setSycDate(String sycDate) {
        this.sycDate = sycDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBatchNo() {
        return sycDate;
    }

    public void setBatchNo(String batchNo) {
        sycDate = batchNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public Integer getSchemeExtendId() {
        return schemeExtendId;
    }

    public void setSchemeExtendId(Integer schemeExtendId) {
        this.schemeExtendId = schemeExtendId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public int getBuyWay() {
        return buyWay;
    }

    public void setBuyWay(int buyWay) {
        this.buyWay = buyWay;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getQcDate() {
        return qcDate;
    }

    public void setQcDate(String qcDate) {
        this.qcDate = qcDate;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }
}

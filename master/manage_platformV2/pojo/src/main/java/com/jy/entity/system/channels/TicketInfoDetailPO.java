package com.jy.entity.system.channels;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/13.
 * 投注系统出票明细
 */
public class TicketInfoDetailPO {
    private Integer id;                 //
    private Date    sycDate;            //同步日期
    private Integer userId;             //彩票系统用户ID
    private Integer schemeId;           //用户方案ID
    private Integer schemeExtendId;     //用户扩展ID
    private String  batchId;            //
    private String  lotteryName;        //彩种名称
    private Double  money;              //购彩金额
    private Date    sendTime;           //传票时间
    private Date    ticketTime;         //出票时间
    private Integer status;             //出票状态 0：初始状态 1：出票中   2：成功   3：失败
    private Date    createTime;         //创建时间
    private Integer isValid;            //是否有效(1:有效  0:无效)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSycDate() {
        return sycDate;
    }

    public void setSycDate(Date sycDate) {
        this.sycDate = sycDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}

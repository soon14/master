package com.jy.entity.task.ticket;

/**
 *
 * Created by huangshifei on 2017/4/26.
 */
public class TaskTicketInfo {
    private Integer id;//表ID
    private String BatchNo;//同步批次号yyyyMMdd_01
    private Integer userId;//彩票系统用户ID
    private Integer schemeId;//用户方案ID
    private Integer schemeExtendId	;//用户扩展ID
    private String batchId;//票号
    private String lotteryName;//彩种名称
    private Double money;//购彩金额
    private String sendTime;//传票时间
    private String ticketTime;		//出票时间
    private int status;//出票状态 0：初始状态 1：出票中   2：成功   3：失败
    private String createTime;//创建时间
    private int isValid;//是否有效(1:有效  0:无效)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }
}

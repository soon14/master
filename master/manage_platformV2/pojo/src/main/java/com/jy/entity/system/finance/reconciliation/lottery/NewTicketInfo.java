package com.jy.entity.system.finance.reconciliation.lottery;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lijunke on 2017/6/2.
 */
public class NewTicketInfo {

    private String ticketNo;//票号
    private BigDecimal money;//金额
    private Date ticketTime;//出票时间
    private Long schemeExtendId;//方案扩展ID
    private Long lotteryId;//彩票ID
    private String issue;//期次
    private Integer mulity;//倍数
    private Date splitTime;//拆票后的入库时间
    private Date postTime;//提票时间
    private Date awardTime;//奖金核对时间
    private String interfaceId;//接口ID
    private String voteTicketId;//出票商票ID
    private Integer castStatus;//出票商出票状态(-1-出票失败 0-待提票 1-提票成功未出票 2-出票成功 3-中奖核对成功)
    private String castMsg;//描述
    private int voteId;//出票商ID
    private BigDecimal prize;//出票商返回奖金
    private BigDecimal taxPrize;//出票商税后奖金
    private Integer isBigPrize;//是否大奖标记(0-非大奖 1-大奖)
    private String prizeInfo;//奖金明细(0,0,0,0,0 中奖的奖级注数 逗号分开)
    private String codes;//投注串
    private String memo;//出票sp（竞彩使用）
    private Integer isChaseNum; //是否追号(默认0否;1是)
    private Date sycDate;//同步日期

    public Date getSycDate() {
        return sycDate;
    }

    public void setSycDate(Date sycDate) {
        this.sycDate = sycDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Date createTime;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }

    public Long getSchemeExtendId() {
        return schemeExtendId;
    }

    public void setSchemeExtendId(Long schemeExtendId) {
        this.schemeExtendId = schemeExtendId;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Integer getMulity() {
        return mulity;
    }

    public void setMulity(Integer mulity) {
        this.mulity = mulity;
    }

    public Date getSplitTime() {
        return splitTime;
    }

    public void setSplitTime(Date splitTime) {
        this.splitTime = splitTime;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getVoteTicketId() {
        return voteTicketId;
    }

    public void setVoteTicketId(String voteTicketId) {
        this.voteTicketId = voteTicketId;
    }

    public Integer getCastStatus() {
        return castStatus;
    }

    public void setCastStatus(Integer castStatus) {
        this.castStatus = castStatus;
    }

    public String getCastMsg() {
        return castMsg;
    }

    public void setCastMsg(String castMsg) {
        this.castMsg = castMsg;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public BigDecimal getTaxPrize() {
        return taxPrize;
    }

    public void setTaxPrize(BigDecimal taxPrize) {
        this.taxPrize = taxPrize;
    }

    public Integer getIsBigPrize() {
        return isBigPrize;
    }

    public void setIsBigPrize(Integer isBigPrize) {
        this.isBigPrize = isBigPrize;
    }

    public String getPrizeInfo() {
        return prizeInfo;
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = prizeInfo;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsChaseNum() {
        return isChaseNum;
    }

    public void setIsChaseNum(Integer isChaseNum) {
        this.isChaseNum = isChaseNum;
    }
}

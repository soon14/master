package com.jy.from.system.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lijunke on 2017/5/27.
 * 出票新接口同步
 */
public class NewTicketInfoForm implements Serializable {
    @JSONField(name = "ticket")
    private String ticketNo;//票号
    @JSONField(name = "money")
    private BigDecimal money;//金额
    @JSONField(name = "dealTime",format="yyyy-MM-dd HH:mm:ss")
    private Date ticketTime;//出票时间
    @JSONField(name = "schemeExtendId")
    private Long schemeExtendId;//方案扩展ID
    @JSONField(name = "lotteryId")
    private Long lotteryId;//彩票ID
    @JSONField(name = "issue")
    private String issue;//期次
    @JSONField(name = "mulity")
    private Integer mulity;//倍数
    @JSONField(name = "sendTime",format="yyyy-MM-dd HH:mm:ss")
    private Date splitTime;//拆票后的入库时间
    @JSONField(name = "postTime",format="yyyy-MM-dd HH:mm:ss")
    private Date postTime;//提票时间
    @JSONField(name="awardTime",format="yyyy-MM-dd HH:mm:ss")
    private Date awardTime;//奖金核对时间
    @JSONField(name="ct")
    private String interfaceId;//接口ID
    @JSONField(name="voteTicketId")
    private String voteTicketId;//出票商票ID
    @JSONField(name="castStatus")
    private Integer castStatus;//出票商出票状态(-1-出票失败 0-待提票 1-提票成功未出票 2-出票成功 3-中奖核对成功)
    @JSONField(name="castMsg")
    private String castMsg;//描述
    @JSONField(name="voteId")
    private int voteId;//出票商ID
    @JSONField(name="prize")
    private BigDecimal prize;//出票商返回奖金
    @JSONField(name="taxPrize")
    private BigDecimal taxPrize;//出票商税后奖金
    @JSONField(name="big")
    private Integer isBigPrize;//是否大奖标记(0-非大奖 1-大奖)
    @JSONField(name="prizeInfo")
    private String prizeInfo;//奖金明细(0,0,0,0,0 中奖的奖级注数 逗号分开)
    @JSONField(name="codes")
    private String codes;//投注串
    @JSONField(name="memo")
    private String memo;//出票sp（竞彩使用）
    @JSONField(name="isChaseNum")
    private Integer isChaseNum; //是否追号(默认0否;1是)

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
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

package com.jy.entity.system.channels;
import java.util.Date;
import java.util.List;

/**
 * Created by Matthew on 2017/2/18 0018.
 *渠道预收款信息
 */
public class PrepaymentExtend {
    private Integer	id;
    private Integer mId;
    private String merchantName;//预收款名称
    private Double payMoney;// 交易金额
    private Integer payType;// 预存款类型
    private Double balance;// 余额
    private Double realBalance;// 可支配余额
    private Double warningMoney;// 预警金额
    private Date createTime; //创建时间
    private String createUser; //创建人
    private Date changeTime;
    private Integer dataType; //数据类型 1：线上 2：线下
    private String dataTypeName;

    private String preEIds; //分配目标商户
    private String preEId;  //分配来源商户

    public String getPreEIds() {
        return preEIds;
    }

    public void setPreEIds(String preEIds) {
        this.preEIds = preEIds;
    }

    public String getPreEId() {
        return preEId;
    }

    public void setPreEId(String preEId) {
        this.preEId = preEId;
    }

    //查询条件
    private Date beginTimes;
    private Date endTimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(Double realBalance) {
        this.realBalance = realBalance;
    }

    public Double getWarningMoney() {
        return warningMoney;
    }

    public void setWarningMoney(Double warningMoney) {
        this.warningMoney = warningMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public Date getBeginTimes() {
        return beginTimes;
    }

    public void setBeginTimes(Date beginTimes) {
        this.beginTimes = beginTimes;
    }

    public Date getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Date endTimes) {
        this.endTimes = endTimes;
    }
}

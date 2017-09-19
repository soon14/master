package com.jy.entity.system.channels;
import java.util.Date;
import java.util.List;

/**
 * Created by Matthew on 2017/2/18 0018.
 *渠道预收款信息
 */
public class Prepayment {
    private  Integer id;
    private Integer merchantId;//渠道商户主键
    private List<Integer> mid;
    private String merchantName;//预收款名称
    private Double payMoney;//预收款金额
    private String payType;//预收款类型，1：出票预售，2：其他
    private String payTypeName;
    private Double balance;//余额
    private double realBalance;// 可支配余额
    private Double warningMoney;//预警金额
    private Integer userId;//操作人
    private String userName;//操作人姓名
    private Date createTime; //创建时间
    private Date changeTime;//操作时间
    private Integer ppStatus;//状态  1：未审核 2：已审核 3：已作废
    private String ppStatusName;
    private Integer dataType;//数据来源类型
    private String dataTypeName;
//    private float a= (float)12.34;

    //查询条件
    private Date beginTimes;
    private Date endTimes;

    public double getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(double realBalance) {
        this.realBalance = realBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public List<Integer> getMid() {
        return mid;
    }

    public void setMid(List<Integer> mid) {
        this.mid = mid;
    }

    public Integer getPpStatus() {
        return ppStatus;
    }

    public void setPpStatus(Integer ppStatus) {
        this.ppStatus = ppStatus;
    }

    public String getPpStatusName() {
        return ppStatusName;
    }

    public void setPpStatusName(String ppStatusName) {
        this.ppStatusName = ppStatusName;
    }

    public Date getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Date endTimes) {
        this.endTimes = endTimes;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public Double getWarningMoney() {
        return warningMoney;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getPayType() {
        return payType;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWarningMoney(Double warningMoney) {
        this.warningMoney = warningMoney;
    }

}

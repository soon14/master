package com.jy.entity.system.channels;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 渠道商户应付账款账单
 * Created by Administrator on 2017/1/6.
 */
public class Payable {
    private Integer id;//
    private Integer merchantId;//渠道商户主键
    private String merchantName;//商户名称
    private Integer commissionId;//渠道佣金返佣主键
    private Integer status;//状态，1，待付款，2，付款中，3，付款成功
    private String statusName;
    private BigDecimal money;//付款金额
    private String payWay;//支付方式
    private String payWayStr;
    private Date payDate;//付款日期
    private Integer userId;//操作人
    private String userName;//操作人姓名
    private String ids;
    //结算周期
    private Date beginDate;
    private Date endDate;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPayWayStr() {
        return payWayStr;
    }

    public void setPayWayStr(String payWayStr) {
        this.payWayStr = payWayStr;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}




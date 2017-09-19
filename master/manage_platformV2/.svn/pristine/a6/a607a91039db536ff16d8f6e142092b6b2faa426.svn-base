package com.jy.entity.system.channels;

//import javax.jnlp.IntegrationService;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 渠道返佣明细
 * Created by Administrator on 2017/1/6.
 */

public class Commission {
    private Integer id;//渠道佣金明细
    private Integer merchantId;//渠道商户主键
    private String merchantName;//渠道商户名称
    private String commissionType;//返佣类型
    private String commissionTypeName;//返佣类型名称
    private Integer baseCommssionId;//渠道佣金配置主键
    private Date beginDate;//佣金计算开始日期
    private Date endDate;//佣金计算结束日期
    private BigDecimal commission;//线上返佣金额
    private BigDecimal sellMoney;//线上销量
    private String isOver;//是否已返，1：已返，2：未返
    private String isOverStr;
    private Date sendCommissionDate;//返佣操作时间
    private Integer userId;
    private String userName;//操作人
    private Integer dataType;//数据来源 1：线上 2：线下
    private String dataTypeName;

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(BigDecimal sellMoney) {
        this.sellMoney = sellMoney;
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

    public String getIsOverStr() {
        return isOverStr;
    }

    public void setIsOverStr(String isOverStr) {
        this.isOverStr = isOverStr;
    }


    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public String getCommissionTypeName() {
        return commissionTypeName;
    }

    public void setCommissionTypeName(String commissionTypeName) {
        this.commissionTypeName = commissionTypeName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getSendCommissionDate() {
        return sendCommissionDate;
    }

    public void setSendCommissionDate(Date sendCommissionDate) {
        this.sendCommissionDate = sendCommissionDate;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getBaseCommssionId() {
        return baseCommssionId;
    }

    public static void main(String[] args) {
        String[] str={"a","b","c"};
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length; i++){
            sb. append(str[i]);
            sb.append(",");
        }
        String s = sb.toString();
        System.out.println(s);
    }


    public Integer getMerchantId() {
        return merchantId;
    }

    public void setBaseCommssionId(Integer baseCommssionId) {
        this.baseCommssionId = baseCommssionId;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getIsOver() {
        return isOver;
    }

    public void setIsOver(String isOver) {
        this.isOver = isOver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package com.jy.entity.system.channels;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户销量展示信息
 * Created by Administrator on 2017/1/6.
 */
public class SalesVolume {

    private Integer id;
    private Integer userId; //彩票用户Id
    private String accountId; //彩票用户Id
    private String phone;//手机号码
    private String name;// 用户名称
    private String userName;//用户昵称
    private Integer isSeller;//是否销售人员(0:不是   1:是)
    private Integer marketer;//营销人
    private Date marketTime;//营销时间
    private Integer userType;//类型（0：个人  1：企业）
    private String userTypeName; //类型名
    private Integer isUse;//是否启用，1：启用，2：禁用
    private Date createTime;//创建时间
    private Date orderTime; //最后下单时间
    private Date updateTime;  // 更新时间
    private BigDecimal salesVolume ; //销量
    private Integer records ;         //订单数量
    //查询条件
    private Date beginTimes;
    private Date endTimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Integer isSeller) {
        this.isSeller = isSeller;
    }

    public Integer getMarketer() {
        return marketer;
    }

    public void setMarketer(Integer marketer) {
        this.marketer = marketer;
    }

    public Date getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(Date marketTime) {
        this.marketTime = marketTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(BigDecimal salesVolume) {
        this.salesVolume = salesVolume;
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

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}

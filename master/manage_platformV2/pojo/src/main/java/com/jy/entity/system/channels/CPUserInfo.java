package com.jy.entity.system.channels;


import java.util.Date;

/**
 * @文件名:CPUserinfo.java 
 * @功能描述：
 * @创建日期:2017年3月8日上午11:10:18 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
public class CPUserInfo {
	
	private Integer userId;			//id
	private String  phone;			//手机号
	private String  name;			//用户名字
	private String  userName;		//用户昵称
	private Integer marketer;		//营销人
	private String  marketTime; 	//营销时间
    private int     isSeller;       //是否是销售人员（0.不是 1是）
    private int     userType;       //类型（0：个人  1：企业）  默认个人
    private int     isUse;          //是否使用(1:在使用  0:禁用)
    private Date    createTime;     //创建时间
    private Date    updateTime;     //更新时间

    public int getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
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

    public Integer getUserId() {
    	return userId;
    }
	
    public void setUserId(Integer userId) {
    	this.userId = userId;
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
	
    public Integer getMarketer() {
    	return marketer;
    }
	
    public void setMarketer(Integer marketer) {
    	this.marketer = marketer;
    }
	
    public String getMarketTime() {
    	return marketTime;
    }
	
    public void setMarketTime(String marketTime) {
    	this.marketTime = marketTime;
    }
	
}

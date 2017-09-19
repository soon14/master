package com.jy.entity.system.finance.statistics;

import java.util.Date;
import java.util.List;

/**
 * 销售佣金报表
 * Created by Administrator on 2017/1/9.
 */
public class SalesCommissionReport {
    private Integer id;
    private String date;//报表截止日期
    private Integer merchantId;//渠道商户
    private String merchantName;//渠道商户名
    private Integer childMerchantCount;//发展商户数
    private Double sales;//发展商户的总销量
    private Integer level;//渠道等级
    private Double percent;//返点百分比
    private Double commission;//返佣金额
    private Integer parentMerchantId;//上级渠道id

    //查询参数
    private String beginTime;//
    private String endTime;//
	private String userId; // 平台用户ID绑定ID 勿删
	private String orgSel; //选择的组织id
	
    private String contactUser;
    private String name;
    private String mobile;

    
	public String getContactUser() {
		return contactUser;
	}

	
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getMobile() {
		return mobile;
	}

	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getParentMerchantId() {
        return parentMerchantId;
    }

    public void setParentMerchantId(Integer parentMerchantId) {
        this.parentMerchantId = parentMerchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getDate() {
        return date;
    }

    public Double getCommission() {
        return commission;
    }

    public Double getPercent() {
        return percent;
    }

    public Integer getChildMerchantCount() {
        return childMerchantCount;
    }

    public Double getSales() {
        return sales;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

//    public String getMerchantName() {
//        return merchantName;
//    }

    public void setChildMerchantCount(Integer childMerchantCount) {
        this.childMerchantCount = childMerchantCount;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

//    public void setMerchantName(String merchantName) {
//        this.merchantName = merchantName;
//    }
    

    public void setSales(Double sales) {
        this.sales = sales;
    }

	public void setPercent(Double percent) {
        this.percent = percent;
    }

	public String getOrgSel() {
    	return orgSel;
    }


	
    public void setOrgSel(String orgSel) {
    	this.orgSel = orgSel;
    }


	
    public String getUserId() {
    	return userId;
    }


	
    public void setUserId(String userId) {
    	this.userId = userId;
    }
    
    
    
}

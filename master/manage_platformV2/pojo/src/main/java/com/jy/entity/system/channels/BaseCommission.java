package com.jy.entity.system.channels;

import java.util.Date;
import java.util.Map;

/**
 * 渠道商户返佣配置 Created by Administrator on 2017/1/6.
 */
public class BaseCommission {

    private String loginName;

	private Integer id;
	
	private String bcId;

    private String bcIdLine;
	
	private String name;// 商户名称
	
	private Integer merchantId;// 渠道商户主键
	
	private String merchantName;// 商户名称
	
	private Integer type;// 佣金规则类型，1：标签用户，2：投注机
    private String typeName;

	private Integer oneRankMin;// 一级销量起始值
	
	private Integer oneRankMax;// 一级销量最大值
	
	private Double onePercent;// 一级返点百分比

	private Integer twoRankMin;// 二级销量起始值
	
	private Integer twoRankMax;// 二级销量最大值
	
	private Double twoPercent;// 二级返点百分比

	private Integer threeRankMin;// 三级销量起始值
	
	private Integer threeRankMax;// 三级销量最大值
	
	private Double threePercent;// 三级返点百分比

	private Integer isEnable;// 是否启用，1：启用，2：禁用
	
	private String createUser;// 创建人
	
	private Date changeDate;// 变更时间
	
	private Integer bcFlag; // 分配状态 1：已分配 0：未分配
	// 查询条件
    private Date beginTimes;
    private Date endTimes;
	private String userName; // 平台用户ID绑定ID 勿删
	
	private Map mId;// 商户ID

    public String getBcIdLine() {
        return bcIdLine;
    }

    public void setBcIdLine(String bcIdLine) {
        this.bcIdLine = bcIdLine;
    }

    public Map getmId() {
        return mId;
    }

    public String getBcId() {
		return bcId;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	public void setmId(Map mId) {
        this.mId = mId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
    	return userName;
    }

	
    public void setUserName(String userName) {
    	this.userName = userName;
    }

	public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }



    public Integer getId() {
        return id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public Integer getOneRankMin() {
        return oneRankMin;
    }

    public void setOneRankMin(Integer oneRankMin) {
        this.oneRankMin = oneRankMin;
    }

    public Integer getOneRankMax() {
        return oneRankMax;
    }

    public void setOneRankMax(Integer oneRankMax) {
        this.oneRankMax = oneRankMax;
    }

    public Double getOnePercent() {
        return onePercent;
    }

    public void setOnePercent(Double onePercent) {
        this.onePercent = onePercent;
    }

    public Integer getTwoRankMin() {
        return twoRankMin;
    }

    public void setTwoRankMin(Integer twoRankMin) {
        this.twoRankMin = twoRankMin;
    }

    public Integer getTwoRankMax() {
        return twoRankMax;
    }

    public void setTwoRankMax(Integer twoRankMax) {
        this.twoRankMax = twoRankMax;
    }

    public Double getTwoPercent() {
        return twoPercent;
    }

    public void setTwoPercent(Double twoPercent) {
        this.twoPercent = twoPercent;
    }

    public Integer getThreeRankMin() {
        return threeRankMin;
    }

    public void setThreeRankMin(Integer threeRankMin) {
        this.threeRankMin = threeRankMin;
    }

    public Integer getThreeRankMax() {
        return threeRankMax;
    }

    public void setThreeRankMax(Integer threeRankMax) {
        this.threeRankMax = threeRankMax;
    }

    public Double getThreePercent() {
        return threePercent;
    }

    public void setThreePercent(Double threePercent) {
        this.threePercent = threePercent;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getType() {
        return type;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getBcFlag() {
        return bcFlag;
    }

    public void setBcFlag(Integer bcFlag) {
        this.bcFlag = bcFlag;
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

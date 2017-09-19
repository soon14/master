package com.jy.entity.system.finance.reconciliation.game;



/** 
 * @文件名:用户充值明细 
 * @功能描述：
 * @创建日期:2017年3月29日下午1:53:16 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
public class GameUserPaymentInfo {
	private Integer id;
	private String payId;		//交易记录号
	private String userId;		//用户编号
	private String payFlowNo;	//第三方流水
	private Integer transMoney;	//交易金额以分为单位
	private Integer payWay;		//第三方支付渠道1=微信，2=银联，3=得仕通
	private String transTime;	//交易日期yyyyMMddHHmmss
	private Integer openId;		//第三方渠道唯一id
	private String sycTime;		//用户入库的创建时间 
	private String createTime;	//创建时间
	private String batchId;
	
    public String getPayId() {
    	return payId;
    }
	
    public void setPayId(String payId) {
    	this.payId = payId;
    }
	
    public String getUserId() {
    	return userId;
    }
	
    public void setUserId(String userId) {
    	this.userId = userId;
    }
	
    public String getPayFlowNo() {
    	return payFlowNo;
    }
	
    public void setPayFlowNo(String payFlowNo) {
    	this.payFlowNo = payFlowNo;
    }
	
    public Integer getTransMoney() {
    	return transMoney;
    }
	
    public void setTransMoney(Integer transMoney) {
    	this.transMoney = transMoney;
    }
	
    public Integer getPayWay() {
    	return payWay;
    }
	
    public void setPayWay(Integer payWay) {
    	this.payWay = payWay;
    }
	
    public String getTransTime() {
    	return transTime;
    }
	
    public void setTransTime(String transTime) {
    	this.transTime = transTime;
    }
	
    public Integer getOpenId() {
    	return openId;
    }
	
    public void setOpenId(Integer openId) {
    	this.openId = openId;
    }
	
    public String getCreateTime() {
    	return createTime;
    }
	
    public void setCreateTime(String createTime) {
    	this.createTime = createTime;
    }

	
    public Integer getId() {
    	return id;
    }

	
    public void setId(Integer id) {
    	this.id = id;
    }

	
    public String getBatchId() {
    	return batchId;
    }

	
    public void setBatchId(String batchId) {
    	this.batchId = batchId;
    }

	
    public String getSycTime() {
    	return sycTime;
    }

	
    public void setSycTime(String sycTime) {
    	this.sycTime = sycTime;
    }
	
}

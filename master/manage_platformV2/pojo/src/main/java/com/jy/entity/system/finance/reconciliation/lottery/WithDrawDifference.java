package com.jy.entity.system.finance.reconciliation.lottery;

import com.google.gson.annotations.SerializedName;

public class WithDrawDifference
{
	@SerializedName("TDate")
	private String tdate;   //交易时间

	private String TFlowNo; //银行流水

	private String TStatus; //对账状态

	@SerializedName("tMoney")
	private String TMoney;  //对账金额

	private String bankMoney; //银行金额

	private String status;

	private String money;

	private String batchNo;   //批次号

	private String TUserName; //用户名

	private String TBankName; //银行名称

	private String TBankAccount; //账号

	private String TTradeNo;   //提现流水

	private String TRecordDate; //入库时间

	private Integer times;//记录处理中的数据出现的次数

	public String getTdate()
	{
		return tdate;
	}

	public void setTdate(String tdate)
	{
		this.tdate = tdate;
	}

	public String getTFlowNo()
	{
		return TFlowNo;
	}

	public void setTFlowNo(String TFlowNo)
	{
		this.TFlowNo = TFlowNo;
	}

	public String getTStatus()
	{
		return TStatus;
	}

	public void setTStatus(String TStatus)
	{
		this.TStatus = TStatus;
	}

	public String getTMoney()
	{
		return TMoney;
	}

	public void setTMoney(String TMoney)
	{
		this.TMoney = TMoney;
	}

	public String getBankMoney()
	{
		return bankMoney;
	}

	public void setBankMoney(String bankMoney)
	{
		this.bankMoney = bankMoney;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getMoney()
	{
		return money;
	}

	public void setMoney(String money)
	{
		this.money = money;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setBatchNo(String batchNo)
	{
		this.batchNo = batchNo;
	}

	public String getTUserName()
	{
		return TUserName;
	}

	public void setTUserName(String TUserName)
	{
		this.TUserName = TUserName;
	}

	public String getTBankName()
	{
		return TBankName;
	}

	public void setTBankName(String TBankName)
	{
		this.TBankName = TBankName;
	}

	public String getTBankAccount()
	{
		return TBankAccount;
	}

	public void setTBankAccount(String TBankAccount)
	{
		this.TBankAccount = TBankAccount;
	}

	public String getTTradeNo()
	{
		return TTradeNo;
	}

	public void setTTradeNo(String TTradeNo)
	{
		this.TTradeNo = TTradeNo;
	}

	public String getTRecordDate()
	{
		return TRecordDate;
	}

	public void setTRecordDate(String TRecordDate)
	{
		this.TRecordDate = TRecordDate;
	}

	public Integer getTimes()
	{
		return times;
	}

	public void setTimes(Integer times)
	{
		this.times = times;
	}
}

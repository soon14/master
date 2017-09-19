package com.jy.entity.system.finance.reconciliation.game.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @文件名:GameParamReq.java
 * @功能描述：
 * @创建日期:2017年3月29日下午3:47:43
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */
public class GameParamReq implements Serializable {
	
	/** 
     */
	private static final long serialVersionUID = 5690299200106124165L;
	
	/*
	 * { "method" : "fish.getUserInfo" , "param" : {"startDate" : "20170320", "endDate" : "20170321", "pageSize" : 100, "currentPage" : 1 } }
	 */
	
	private String method;
	
	private Map<Object, Object> param = new HashMap<Object, Object>();
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Map<Object, Object> getParam() {
		return param;
	}
	
	public void setParam(Map<Object, Object> param) {
		this.param = param;
	}
	
	public GameParamReq(String method, String startDate, String endDate, int pageSize, int currentPage) {
		super();
		this.method = method;
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("pageSize", pageSize);
		param.put("currentPage", currentPage);
	}
	
}

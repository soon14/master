package com.jy.service.system.finance.reconciliation.game;

import com.jy.entity.system.finance.reconciliation.game.GameUserTransInfo;
import com.jy.service.base.BaseService;


/** 
 * @文件名:GameUserTransInfoService.java 
 * @功能描述：
 * @创建日期:2017年3月29日下午5:51:06 
 * @创建人：Dingj
 * @Copyright（c） 2017,all rights reserved by days 
 */
public interface GameUserTransInfoService extends BaseService<GameUserTransInfo>
{
	
	
	/**
	 * 
	 * @方法功能描述： 获取用户交易明细数据
	 * @param url		   请求地址
	 * @param method	   接口名称
	 * @param startDate  开始时间
	 * @param endDate	  结束时间
	 * @param pageSize   每页条数
	 * @param currentPage 当前页
	 * @return 
	 * GameUserTransInfo
	 * @author lijunke
	 * @创建时间： 2017年3月29日下午5:54:09
	 * @throws Exception 
	 */
	public void synchronousData(String startDate, String endDate, int pageSize, int currentPage) throws Exception;
	
	
	public void switchData();

	
}

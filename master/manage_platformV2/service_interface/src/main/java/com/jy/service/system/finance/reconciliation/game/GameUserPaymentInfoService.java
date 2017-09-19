package com.jy.service.system.finance.reconciliation.game;

import com.jy.entity.system.finance.reconciliation.game.GameUserPaymentInfo;
import com.jy.service.base.BaseService;


/** 
 * @文件名:GameUserPaymentInfoService.java 
 * @功能描述：
 * @创建日期:2017年3月30日下午2:25:53 
 * @创建人：Dingj
 * @Copyright（c） 2017,all rights reserved by days 
 */
public interface GameUserPaymentInfoService extends BaseService<GameUserPaymentInfo> {
	
	public void synchronousData(String startDate, String endDate, int pageSize, int currentPage) throws Exception;
	
	public void switchData();
}

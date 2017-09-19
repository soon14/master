package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CountSale;
import com.jy.service.base.BaseService;


/**
 * @文件名:CountSaleService.java
 * @功能描述：
 * @创建日期:2017年3月27日下午6:02:32
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */
public interface CountSaleService extends BaseService<CountSale> {
	
	public void updateCountAumone(CountSale cs);
}

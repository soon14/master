package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.CountSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.entity.system.finance.reconciliation.lottery.CountSale;
import com.jy.repository.system.finance.reconciliation.lottery.CountSaleDao;


/**
 * @文件名:CountSaleServiceImpl.java
 * @功能描述：
 * @创建日期:2017年3月28日上午9:16:04
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */
@Service("countSaleService")
public class CountSaleServiceImpl extends BaseServiceImp<CountSale> implements CountSaleService {
	
	@Autowired
	public CountSaleDao countSaleDao;
	
	@Override
	public void updateCountAumone(CountSale cs) {
		countSaleDao.updateCountAumone(cs);
	}
	
}

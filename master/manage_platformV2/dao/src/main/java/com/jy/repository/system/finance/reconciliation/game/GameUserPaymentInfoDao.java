package com.jy.repository.system.finance.reconciliation.game;

import java.util.List;

import com.jy.entity.system.finance.reconciliation.game.GameUserPaymentInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;


/** 
 * @文件名:GameUserPaymentInfoDao.java 
 * @功能描述：
 * @创建日期:2017年3月30日下午2:34:32 
 * @创建人：Dingj
 * @Copyright（c） 2017,all rights reserved by days 
 */
@JYBatis
public interface GameUserPaymentInfoDao extends BaseDao<GameUserPaymentInfo>{

	void save(List<GameUserPaymentInfo> userPaymentInfoList);

	GameUserPaymentInfo findByBatchId();
	
}

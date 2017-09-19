package com.jy.repository.system.finance.reconciliation.game;

import java.util.List;

import com.jy.entity.system.finance.reconciliation.game.GameUserTransInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;


/** 
 * @文件名:GameUserTransInfoDao.java 
 * @功能描述：
 * @创建日期:2017年3月29日下午7:49:27 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
@JYBatis
public interface GameUserTransInfoDao extends BaseDao<GameUserTransInfo>{

	void save(List<GameUserTransInfo> userTransInfoList);

	GameUserTransInfo findByBatchId();
	
}

package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CountSale;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/** 
 * @文件名:总销量统计dao接口
 * @功能描述：
 * @创建日期:2017年3月14日下午7:51:14 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
@JYBatis
public interface CountSaleDao  extends BaseDao<CountSale> {
	
	List<CountSale> findAll(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

	void insert(@Param("startTime") Date startTime, @Param("endTime")Date endTime);

	void delete(@Param("startTime")Date startTime);

	void updateCountAumone(CountSale cs);
}

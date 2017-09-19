package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.SalesSumDifferences;
import com.jy.entity.system.finance.vo.SalesSumDifferencesVO;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@JYBatis
public interface SalesSumDifferencesDao extends BaseDao<SalesSumDifferences>{
	/**
	 * 查询销售差异总报表数据
	 * @param date
	 * @param time
	 * @param dealResultStatus
     * @return
     */
	public List<SalesSumDifferencesVO> findDifference(@Param("date")String date, @Param("time")String time, @Param("dealResultStatus")String dealResultStatus);
}

package com.jy.repository.system.finance.reconciliation.lottery;


import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.mybatis.Page;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@JYBatis
public interface SalesDifferencesDetailDao extends BaseDao<SalesDifferencesDetail>{
	/**
	 * 查询销售差异明细报表数据
	 * @param date
     * @return
     */
	public SalesDifferencesDetail findDifferenceDetail(@Param("date") String date);

	/**
	 * 获取type类型的某日差异处理结果
	 * ，1：平台总资金差异 2：第三方流水差异
	 * @param detail
	 * @param page
	 * @return
	 */
	List<SalesDifferencesDetail> findDetailByDateAndType(@Param("param") SalesDifferencesDetail detail, Page<SalesDifferencesDetail> page);

	void insertDifference(List<SalesDifferencesDetail> list);

	void deleteDifference(SalesDifferencesDetail detail);

	List<SalesDifferencesDetail> findMonth(SalesDifferencesDetail m);
}

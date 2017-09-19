package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.entity.system.channels.TicketInfoDetailPO;
import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.entity.system.finance.SalesSum;
import com.jy.entity.system.finance.SalesSumDifferences;
import com.jy.entity.system.finance.vo.SalesSumDifferencesVO;
import com.jy.service.base.BaseService;

import java.util.List;

public interface SalesSumService extends BaseService<SalesSumDifferences>{
	/**
	 * 查询销售差异总报表数据
	 * @param date		开始时间
	 * @param time		截止时间
	 * @param dealResultStatus		处理状态
	 * @return
	 */
	public List<SalesSumDifferencesVO> findDifference(String date, String time, String dealResultStatus);

	/**
	 * 查询投注系统销售总报表,总金额明细
	 * @param date	日期
	 * @return
	 */
	public SalesSum findMarket(String date);

	/**
	 * 查询销售差异明细报表
	 * @param date	日期
	 * @return
	 */
	public SalesDifferencesDetail findDifferencesDetail(String date);



	/**
	 * 整合投注系统销售总报表,总金额明细
	 * @return
	 */
	public List<TicketInfoDetailPO> findSalesSum();

	/**
	 * 整合投注系统销售差异总报表数据
	 */
	public void findSalesSumDifferences();

	/**
	 * 整合销售差异明细报表数据
	 */
	public void findSalesDifferencesDetail();


	/**
	 * 获取type类型的某日差异处理结果
	 * ，1：平台总资金差异 2：第三方流水差异
	 * @param form
	 * @return
	 */
	public List<SalesDifferencesDetail> findDetailByDateAndType(SalesDifferencesDetail form);

}

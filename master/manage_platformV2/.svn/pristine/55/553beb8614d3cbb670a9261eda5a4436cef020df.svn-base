package com.jy.service.system.finance.reconciliation.lottery;

import java.util.List;

import com.jy.mybatis.Page;
import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.entity.system.finance.reconciliation.lottery.PlatformFunds;
import com.jy.entity.system.finance.reconciliation.lottery.PlatformFundsRunning;
import com.jy.service.base.BaseService;
import com.jy.vo.system.reconciliation.lottery.FundsActivityReportVo;
import com.jy.vo.system.reconciliation.lottery.PlatFormFundsRunningVo;
import com.jy.vo.system.reconciliation.lottery.PlatFormFundsVo;

public interface PlatformFundsService extends BaseService<PlatformFunds> {

	/**
	 * 根据对账统计的日期段查询平台用户资金汇总对账数据
	 *
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param page
	 * @return
	 */
	public Page<PlatFormFundsVo> findFundsListByPage(String startDate, String endDate, Integer status,Integer type, Page<PlatFormFundsVo> page);

	/**
	 * 根据对账统计的日期段查询平台用户资金汇总对账数据
	 *
	 * @param form
	 * @param page
	 * @return
	 */
//	public Page<PlatFormFundsVo> findFundsListByPage(PlatformFundsForm form, Page<PlatFormFundsVo> page);

	/**
	 * 根据对账统计的日期段查询平台用户交易流水汇总对账数据
	 *
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param page
	 * @return
	 */
	public Page<PlatFormFundsRunningVo> findFundsRunningListByPage(String startDate, String endDate, Integer status, Page<PlatFormFundsRunningVo> page);

	/**
	 * 根据对账统计的日期段查询平台用户交易流水汇总对账数据
	 *
	 * @param form
	 * @param page
	 * @return
	 */
//	public Page<PlatFormFundsRunningVo> findFundsRunningListByPage(PlatformFundsForm form, Page<PlatFormFundsRunningVo> page);

	/**
	 * 存储平台资金每日总报表
	 *
	 * @param date
	 */
	public void execPlatFormFundsTask( String date);

	/**
	 * 存储平台资金流水与第三方流水对账数据
	 *
	 * @param date
	 */
	public void execThirdpayTask(String date);

	/**
	 * 保存平台资金差异
	 *
	 * @param form
	 */
	public void saveDiveDifference(SalesDifferencesDetail form);

	/**
	 * 查询某日差异记录
	 *
	 * @param form
	 * @param page
	 * @return
	 */
	public Page<SalesDifferencesDetail> findSalesDifferencesDetailByPage(SalesDifferencesDetail form, Page<SalesDifferencesDetail> page);

	/**
	 * 获得某天第三方对账记录
	 *
	 * @param o
	 * @return
	 */
	public List<PlatformFundsRunning> findRunning(PlatformFundsRunning o);

	/**
	 * 修改某天第三方对账表记录
	 *
	 * @param funds
	 */
	public void updateRunning(PlatformFundsRunning funds);

	/**
	 * @方法功能描述： 方法作用 获取兑奖差异列表
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return Page<CashDifference>
	 * @author Xin
	 * @创建时间： 2017年3月16日下午3:30:52
	 */
	public Page<CashDifference> findCashDifferenceListByPage(String beginTime, String endTime, Page<CashDifference> page);

	/**
	 * 生成平台资金对账报表
	 * @param date
	 */
	void GenerateFundsReport( String date);

	/**
	 * 生成第三方对账报表
	 * @param auto
	 * @param date
	 */
	void generateRunningReport(String auto, String date);

	Page<FundsActivityReportVo> findActivityFundsListByPage(String beginTime, String endTime, Integer dealResultStatus, Integer fundsType, Page<FundsActivityReportVo> page);
}

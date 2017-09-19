package com.jy.service.system.finance.reconciliation.lottery;

import java.util.Date;
import java.util.List;

import com.jy.mybatis.Page;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.service.base.BaseService;

/**
 * @文件名:cashDifferenceService.java
 * @功能描述：
 * @创建日期:2017年3月17日上午9:36:14
 * @创建人：xin
 * @Copyright（c） 2017,all rights reserved by days
 */
	public interface CashDifferenceService extends BaseService<CashDifference> {

	/**
	 * @方法功能描述： 获取兑奖对账的汇总信息列表
	 * @param date
	 * @param page
	 * @return Page<CashDifference>
	 * @author xin
	 * @创建时间： 2017年3月17日下午4:04:07
	 */
	public Page<CashDifference> cashDifferenceDate(String date, Page<CashDifference> page);

	/**
	 * @方法功能描述:保存兑奖差异数据到汇总表中
	 * @return void
	 * @author Xin
	 * @创建时间： 2017年3月20日下午1:56:41
	 */
	public List saveCashDifferenceInfo(String date);

	/**
	 * @方法功能描述:生成兑奖派奖总报表 void
	 * @author Xin
	 * @创建时间： 2017年3月21日上午8:59:31
	 */
	public void GenerateCashCountReport();

	/**
	 * @方法功能描述:保存兑奖差异明细数据
	 * @param date void
	 * @author Dingj
	 * @创建时间： 2017年3月21日下午1:41:37
	 */
	public List<CashDiffDetail> saveCashDifferenceDetail(String date);

	/**
	 * @方法功能描述:查询兑奖详细明细
	 * @param date
	 * @param page
	 * @return List<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年3月21日下午6:53:16
	 */
	public Page<CashDiffDetail> findCashDiffDetails(String date, Page page);

	/**
	 * @方法功能描述： 生成兑奖差异明细报表 void
	 * @author xin
	 * @创建时间： 2017年3月21日下午8:00:08
	 */
	public void GenerateDetailedReport();

	/**
	 * @方法功能描述:重置该时间段内的数据
	 * @param startTime
	 * @param endTime
	 * @param page void
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午1:21:07
	 */
	public Page<CashDifference> restTask(Date startTime, Date endTime, Page<CashDifference> page);

	/**
	 * @方法功能描述:重置改段时间内兑奖差异总报表数据,并生成报表
	 * @param startTime
	 * @param endTime void
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午3:24:18
	 */
	public void dateRestTask(Date startTime, Date endTime);

	/**
	 * @方法功能描述:具体执行重置该段时间内兑奖差异总报表数据
	 * @param startDate
	 * @param endDate
	 * @return List<CashDifference>
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午3:25:13
	 */
	public List<CashDifference> executeRest(Date startDate, Date endDate);

	/**
	 * @方法功能描述:保存线下录入的兑奖数据
	 * @param cs void
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午5:23:34
	 */
	public void updateCountAumone(CashDifference cs);

	public void dateRestDetailTask(Date startDate, Date endDate);

	/**
	 * @方法功能描述：查询该用户方案编号的所有讯息
	 * @param schemeExtendId
	 * @param page
	 * @return Page<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年4月17日下午3:26:44
	 */
	public Page<CashDiffDetail> findschemeExtendIdDetails(String schemeExtendId,Page<CashDiffDetail> page);

	/**
	 * 下载报表
	 * @param s
	 * @return
     */
	List<Object> download(String s);

	/**
	 * 下载明细报表
	 * @param date
	 * @return
     */
	List<Object> downloadDetail(String date);

	/**
	 * 查询该日期对应的兑奖差异数据
	 * @param id
	 * @return
     */
	CashDifference findOfflineRedeemAmount(String id);
}

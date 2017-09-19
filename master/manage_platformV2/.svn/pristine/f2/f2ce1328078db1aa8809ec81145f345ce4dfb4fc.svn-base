package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.entity.system.finance.reconciliation.lottery.SendPrizeInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @文件名:CashDifferenceDao.java
 * @功能描述：
 * @创建日期:2017年3月17日下午4:21:24
 * @创建人：xin
 */
@JYBatis
public interface CashDifferenceDao extends BaseDao<CashDifference> {

	/**
	 * @方法功能描述： 获取兑派奖差异列表
	 * @param cashDifference
	 * @return List<CashDifference>
	 * @author Xin
	 * @创建时间： 2017年3月20日下午2:11:13
	 */
	public List<CashDifference> findByDate(@Param("param") CashDifference cashDifference);

	/**
	 * @方法功能描述：依据日期查询兑奖总金额
	 * @param date void
	 * @author xin
	 * @创建时间： 2017年3月20日下午2:12:58
	 */
	public BigDecimal findCashSumbyDates(@Param("date") String date);

	/**
	 * @方法功能描述： 依据日期段查询派奖总金额讯息
	 * @param date
	 * @return List<SendPrizeInfo>
	 * @author xin
	 * @创建时间： 2017年3月20日下午3:43:48
	 */
	public BigDecimal findSendPrizeSumByDates(@Param("date") String date);

	/**
	 * @方法功能描述:获取该日期下的期末差异额
	 * @param date
	 * @return CashDifference
	 * @author xin
	 * @创建时间： 2017年3月20日下午4:27:09
	 */
	public BigDecimal findEndGap(@Param("date") String date);

	/**
	 * @方法功能描述:保存该时间段内的兑奖派奖差异汇总数据列表
	 * @param list
	 * @author xin
	 * @创建时间： 2017年3月20日下午5:13:10
	 */
	public void saveCashDifferenceList(List<CashDifference> list);

	/**
	 * @方法功能描述:查询该日兑奖明细
	 * @param date
	 * @return List<CashInfo>
	 * @author xin
	 * @创建时间： 2017年3月21日下午2:53:24
	 */
	public List<CashInfo> findCashDetail(@Param("date") String date);

	/**
	 * @方法功能描述：查询该日的派奖明细
	 * @param date
	 * @return List<SendPrizeInfo>
	 * @author Dingj
	 * @创建时间： 2017年3月21日下午2:56:21
	 */
	public List<SendPrizeInfo> findSendPrizeDetail(@Param("date") String date);

	// /**
	// * @方法功能描述:联合表查询兑奖差异部分明细
	// * @param beginTime
	// * @param endTime void
	// * @author Dingj
	// * @创建时间： 2017年3月21日下午5:44:17
	// */
	// public List<CashDiffDetail> findCashDiffDetail(@Param("date") String date);

	/**
	 * @方法功能描述:保存兑奖差异明细数据到数据库
	 * @param cashDiffDetails void
	 * @author Dingj
	 * @创建时间： 2017年3月21日下午6:32:08
	 */
	public void saveCashDiffDetail(List<CashDiffDetail> cashDiffDetails);

	/**
	 * @方法功能描述：查询兑奖详细差异明细
	 * @param date
	 * @return List<CashDiffDetail>
	 * @author xin
	 * @创建时间： 2017年3月21日下午6:51:19
	 */
	public List<CashDiffDetail> findCashDiffDetails(@Param("date") String date);

	/**
	 * @方法功能描述：依据时间联合查询每个订单的兑奖金额
	 * @param date void
	 * @author xin
	 * @创建时间： 2017年3月27日下午6:25:20
	 */
	public List<CashDiffDetail> findCashDiffBySchemeId(@Param("date") String date);

	/**
	 * @方法功能描述： 依据时间查询每个订单的派奖金额
	 * @param date
	 * @return List<CashDiffDetail>
	 * @author xin
	 * @创建时间： 2017年3月27日下午6:33:22
	 */
	public List<CashDiffDetail> findSendDiffBySchemeId(@Param("date") String date);

	/**
	 * @方法功能描述：查询该时间段内每日的兑奖总金额
	 * @param startDate
	 * @param endDate
	 * @return List<CashInfo>
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午1:37:27
	 */
	public List<CashInfo> findCashByRedates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * @方法功能描述:查询该时间段内每日的派奖总金额
	 * @param startDate
	 * @param endDate
	 * @return List<SendPrizeInfo>
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午1:38:12
	 */
	public List<SendPrizeInfo> findsendByRedates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * @方法功能描述：删除该日期的兑奖差异数据
	 * @param cashDifference
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午3:09:16
	 */
	public void deleteCashDiffByDate(@Param("param") CashDifference cashDifference);

	/**
	 * @方法功能描述:保存线下兑奖录入的数据
	 * @param param
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午5:30:49
	 */
	public void updateCountAumone(@Param("param") CashDifference param);

	/**
	 * @方法功能描述:查询要重置的派奖明细
	 * @param startDate
	 * @param endDate
	 * @return List<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午7:57:30
	 */
	public List<CashDiffDetail> refindSendDiffBySchemeId(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * @方法功能描述：查询要重置的兑奖明细
	 * @param startDate
	 * @param endDate
	 * @return List<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午7:57:35
	 */
	public List<CashDiffDetail> refindCashDiffBySchemeId(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * @方法功能描述:更新该时间段的兑奖明细数据
	 * @param tList void
	 * @author Dingj
	 * @创建时间： 2017年3月31日上午10:13:41
	 */
	public void updateCashDiffDetail(List<CashDiffDetail> tList);

	/**
	 * @方法功能描述:删除该段时间内的兑奖明细
	 * @param arguMap
	 * @author Dingj
	 * @创建时间： 2017年3月31日下午2:17:59
	 * @param arguMap
	 */
	public void deleteByTicketNo(Map<String, Object> arguMap);

	/**
	 * @方法功能描述:重新保存(重置)该段时间内的兑奖明细数据
	 * @param tList void
	 * @author Dingj
	 * @创建时间： 2017年3月31日下午2:24:56
	 */
	public void reSavaCashDetail(List<CashDiffDetail> tList);

	/**
	 * @方法功能描述： 查询该日期所在的月的所有的数据
	 * @param date
	 * @return List<CashDifference>
	 * @author xin
	 * @创建时间： 2017年4月5日下午4:38:24
	 */
	public List<CashDifference> findAllCashDiffByMonth(@Param("date") Date date);

	/**
	 * @方法功能描述：查询该日期所在的月的所有数据
	 * @param date
	 * @return List<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年4月5日下午5:35:43
	 */
	public List<CashDiffDetail> findAllCashDetialDiff(@Param("date") Date date);

	/**
	 * @方法功能描述： 查询该用户方案编号的所有明细
	 * @param schemeExtendId
	 * @return List<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年4月17日下午3:30:48
	 */
	public List<CashDiffDetail> findschemeExtendIdDetails(@Param("schemeExtendId") String schemeExtendId);

	/**
	 * 查询该日期对应的兑奖数据
	 * @param id
	 * @return
     */
	public CashDifference findOfflineRedeemAmount(@Param("date") String id);
}

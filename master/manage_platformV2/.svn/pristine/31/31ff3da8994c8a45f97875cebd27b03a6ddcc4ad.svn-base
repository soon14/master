package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashPrizeDifference;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lijunke on 2017/5/18.
 */
@JYBatis
public interface CashPrizeDifferenceDao extends BaseDao<CashPrizeDifference> {

    /**
     * 根据日期区间获取第三方派奖总额
     * @return
     */
    CashPrizeDifference byTSTotalAumone(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 根据日期区间获取投注派奖总额
     * @return
     */
    CashPrizeDifference byDCTotalAumone(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 批量保存汇总差异数据
     * @param listDiff
     */
    void saveCashDiffAll(List<CashPrizeDifference> listDiff);

    /**
     * 查询昨天期末差异额
     * @param yesterDay
     * @return
     */
    BigDecimal queryYesterdayLastAumone(@Param("yesterDay") String yesterDay);


    /**
     * 根据日期区间查询数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<CashPrizeDifference> findAll(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

    /**
     * 查询差异明细数据
     * @param startDate
     * @param endDate
     */
    List<CashDiffDetail> queryDiffDetail(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
}

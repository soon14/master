package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.common.exception.DaysBaseException;
import com.jy.entity.system.finance.reconciliation.lottery.CashPrizeDifference;
import com.jy.service.base.BaseService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lijunke on 2017/5/18.
 * 兑奖服务接口
 */
public interface CashPrizeDifferenceService  extends BaseService<CashPrizeDifference> {

    /**
     * 根据日期区间获取第三方派奖总额
     * @return
     */
    BigDecimal byTSTotalAumone(Date startDate, Date endDate);

    /**
     * 根据日期区间获取投注派奖总额
     * @return
     */
    BigDecimal byDCTotalAumone(Date startDate, Date endDate);


    /**
     * //汇集数据，对比差异
     * @param startDate
     * @param endDate
     */
     void collectionData(Date startDate, Date endDate) throws DaysBaseException;

    /**
     * 手动添加大额兑奖数据，更新该数据
     * @param id
     * @param bigPrize
     */
    void updateBigPrize(int id, BigDecimal bigPrize);

}

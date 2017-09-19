package com.jy.repository.system.finance.reconciliation.game;

import com.jy.entity.system.finance.reconciliation.game.DiamondBalance;
import com.jy.entity.system.finance.reconciliation.game.GoldBalance;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台用户资金汇总对账数据层
 */
@JYBatis
public interface GoldBalanceDao extends BaseDao<GoldBalance> {
    /**
     *查询某日数据
     * @param a
     * @return
     */
    List<GoldBalance> findIsExitByDate(String a);

    /**
     * 购买金币消耗钻石数量
     * @param date
     * @param dateMax
     * @return
     */
    Double findConsumeDiamondForBuyGold(String date, String dateMax);

    Double findQcGoldBetweenDate(String date, String dateMax);

    /**
     * 获取某月数据
     * @param month
     * @return
     */
    List<GoldBalance> findMonth(@Param("month") String month);

    /**
     * XX-XXX时间消费金币
     * @param date
     * @param dateMax
     * @return
     */
    Double findConsumeGoldBetweenDate(String date, String dateMax);

    /**
     * XX-XXX时间增加金币
     * @param date
     * @param dateMax
     * @return
     */
    Double findAddGoldBetweenDate(String date, String dateMax);

    void deleteList(List<GoldBalance> list);
}

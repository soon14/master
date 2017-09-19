package com.jy.repository.system.finance.reconciliation.game;


import com.jy.entity.system.finance.reconciliation.game.DiamondBalance;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台用户资金汇总对账数据层
 */
@JYBatis
public interface DiamondBalanceDao extends BaseDao<DiamondBalance> {

    /**
     * 某段时间期初钻石额
     * @param date
     * @param dateMax
     * @return
     */
    Integer findQcDiamondBetweenDate(String date, String dateMax);

    /**
     * 查询某日数据
     * @param date
     * @return
     */
    List<DiamondBalance> findIsExitByDate(String date);

    /**
     * 查询某月数据
     * @param month
     * @return
     */
    List<DiamondBalance> findMonth(@Param("month") String month);

    /**
     * 消费钻石
     * @param date
     * @param dateMax
     * @return
     */
    Double findConsumeDiamondBetweenDate(String date, String dateMax);

    /**
     * 新增钻石
     * @param date
     * @param dateMax
     * @return
     */
    Double findAddDiamondBetweenDate(String date, String dateMax);

    void deleteList(List<DiamondBalance> list);
}

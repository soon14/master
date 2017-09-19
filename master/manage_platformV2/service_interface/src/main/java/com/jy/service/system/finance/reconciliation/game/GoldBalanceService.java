package com.jy.service.system.finance.reconciliation.game;


import com.jy.entity.system.finance.reconciliation.game.GoldBalance;
import com.jy.service.base.BaseService;

public interface GoldBalanceService extends BaseService<GoldBalance> {

    /**
     * 金币数据入库
     * @param auto
     * @param date
     */
    void execGoldBalanceTask(String auto, String date);

    /**
     * 生成金币报表
     * @param auto
     * @param month
     */
    void generateGoldBalanceReport(String auto, String month);
}

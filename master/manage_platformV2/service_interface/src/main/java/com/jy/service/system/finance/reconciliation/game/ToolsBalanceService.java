package com.jy.service.system.finance.reconciliation.game;


import com.jy.entity.system.finance.reconciliation.game.ToolsBalance;
import com.jy.service.base.BaseService;

public interface ToolsBalanceService extends BaseService<ToolsBalance>
{

    /**
     * 道具数据入库
     * @param auto
     * @param date
     */
    void execToolsBalanceTask(String auto, String date);

    /**
     * 生成道具报表
     * @param auto
     * @param date
     */
    void generateToolsBalanceReport(String auto, String date);
}

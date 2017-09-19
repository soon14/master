package com.jy.repository.system.finance.reconciliation.game;

import com.jy.entity.system.finance.reconciliation.game.ToolsBalance;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 平台用户资金汇总对账数据层
 */
@JYBatis
public interface ToolsBalanceDao extends BaseDao<ToolsBalance> {
    /**
     *查询某日数据
     * @param date
     * @return
     */
    List<ToolsBalance>  findIsExitByDate(String date);

    /**
     * 充值。消费数
     * @param date
     * @param dateMax
     * @param i
     * @return
     */
    Double findAddOrConsumeToolsBetweenDate(String date, String dateMax, int type, String i);


    /**
     * 期初道具数
     * @param date
     * @param dateMax
     * @param id
     * @return
     */
    Double findQcToolsBetweenDate(String date, String dateMax, String id);

    /**
     * 获取某月数据
     * @param month
     * @return
     */
    List<ToolsBalance> findMonth(@Param("month") String month);

    /**
     * 获取某日道具id列表
     * @param date
     * @param dateMax
     * @return
     */
    List<String> findToolsIdListBetweenDate(String date, String dateMax);

    /**
     * 批量插入数据
     * @param list
     */
    void insertList(List<ToolsBalance> list);

    /**
     * 删除某日数据
     * @param date
     */
    void deleteByDate(String date);

    /**
     * 一月中已统计日期
     * @param month
     * @return
     */
    List<ToolsBalance> findDateInMonth(@Param("month") String month);

    /**
     * 每个道具期初数，期初价值
     * @param date
     * @param dateMax
     * @return
     */
    List<ToolsBalance> findToolsBetweenDate(String date, String dateMax);

    /**
     * 道具期初总数，总价值
     * @param date
     * @param dateMax
     * @return
     */
    ToolsBalance findQllQcTools(String date, String dateMax);

    /**
     * 道具XX消耗数
     * @param date
     * @param dateMax
     * @param id
     * @return
     */
    ToolsBalance findConsumeToolsBetweenDate(String date, String dateMax, Integer id);

    /**
     * 道具XX新增数
     * @param date
     * @param dateMax
     * @param id
     * @return
     */
    ToolsBalance findAddToolsBetweenDate(String date, String dateMax, Integer id);

    /**
     * 1,道具消费总数，总价值，2：新增总数，总价值
     * @param date
     * @param dateMax
     * @param i
     * @return
     */
    ToolsBalance findAllAddOrConsumeBetweenDate(String date, String dateMax, @Param("type") int type);
}

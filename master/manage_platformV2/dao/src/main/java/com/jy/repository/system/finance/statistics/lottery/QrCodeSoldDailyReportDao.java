package com.jy.repository.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.QrCodeSoldDailyReport;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;

import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
@JYBatis
public interface QrCodeSoldDailyReportDao extends BaseDao<QrCodeSoldDailyReport> {
    /**
     * 获取某天自取（或者委托）销售券额
     * @param way
     * @param date
     * @param dateMax
     * @return
     */
    Double findSaleMoneyByWay(int way, String date, String dateMax);

    /**
     * 查询某天数据
     * @param date
     * @return
     */
    List<QrCodeSoldDailyReport>  findIsExitByDate(String date);

    List<QrCodeSoldDailyReport> findMonth(QrCodeSoldDailyReport m);

    void deleteList(List<QrCodeSoldDailyReport>  list);
}

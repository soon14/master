package com.jy.repository.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.QrCodeDetailDailyReport;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
@JYBatis
public interface QrCodeDetailDailyReportDao extends BaseDao<QrCodeDetailDailyReport> {

    List<QrCodeDetailDailyReport> findIsExitByDate(String date);

    Double findSoldMoneyByType(String dateMin, String date, int type);

    Double findStartMoneyByType(String date, Date batchStartTime, Date batchEndTime, int type);

    /**
     * 获得兑换券总额
     * @param startTime
     * @param endTime
     * @return
     */
    QrCodeDetailDailyReport findAllReport(Date startTime, Date endTime);

    /**
     * 获得兑换券售出额
     * @param date
     * @param dateMax
     * @return
     */
    QrCodeDetailDailyReport findSoldReport(String date, String dateMax);

    List<QrCodeDetailDailyReport> findMonth(QrCodeDetailDailyReport m);

    void deleteList(List<QrCodeDetailDailyReport> list);

    QrCodeDetailDailyReport findQcReport(String date, Date batchStartTime, Date batchEndTime);
}

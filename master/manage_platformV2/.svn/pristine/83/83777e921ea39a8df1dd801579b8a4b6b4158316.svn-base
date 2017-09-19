package com.jy.repository.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.QrCodeDailyReport;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
@JYBatis
public interface QrCodeDailyReportDao extends BaseDao<QrCodeDailyReport> {
    /**
     * 获取兑换券某日的期初额
     * @param date
     * @return
     */
   public  Double findStartNum(String date);

    /**
     * Date2~date时间的兑换券售出额
     * @param date
     * @param dateMax
     * @return
     */
    Double findSaleMoney(String date, String dateMax);

    /**
     * 查询某日数据
     * @param date
     * @return
     */
    List<QrCodeDailyReport> findReportIsExitByDate(String date);

    /**
     * 获取兑换券批次，以及起始结束时间
     * @param date
     * @return
     */
    List<QrCodeDailyReport> findQrCodeInfo(String date);

    List<QrCodeDailyReport> findMonth(QrCodeDailyReport m);

    Double findAllNumByBatch(Date batchStartTime, Date batchEndTime);

    void deleteList(List<QrCodeDailyReport> list);

 Double findQcMoney(String date, Date batchStartTime, Date batchEndTime);
}

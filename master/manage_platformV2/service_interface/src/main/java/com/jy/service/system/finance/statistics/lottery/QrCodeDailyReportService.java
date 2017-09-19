package com.jy.service.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.QrCodeDailyReport;
import com.jy.service.base.BaseService;


/**
 * Created by Administrator on 2017/2/13.
 */
public interface QrCodeDailyReportService extends BaseService<QrCodeDailyReport>
{
    /**
     * 兑换券销售日报统计任务
     * @param auto
     * @param date
     */
    public void insertReport(String auto, String date);

    void generateReport(String manual, String month);
}

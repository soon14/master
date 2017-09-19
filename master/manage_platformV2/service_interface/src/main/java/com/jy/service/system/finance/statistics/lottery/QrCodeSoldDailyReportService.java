package com.jy.service.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.QrCodeSoldDailyReport;
import com.jy.service.base.BaseService;

/**
 * Created by Administrator on 2017/2/13.
 */
public interface QrCodeSoldDailyReportService extends BaseService<QrCodeSoldDailyReport> {

    void insertSoldReport(String auto, String date);

    QrCodeSoldDailyReport findIsExitByDate(String date);

    void generateSoldReport(String manual, String month);
}


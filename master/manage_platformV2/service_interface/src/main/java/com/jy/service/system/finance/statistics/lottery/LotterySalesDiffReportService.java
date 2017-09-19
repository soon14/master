package com.jy.service.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.LotterySalesDiffReport;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface LotterySalesDiffReportService extends BaseService<LotterySalesDiffReport> {
    void generateReport(String month);

    void execTask(String thisDate);

    Page<LotterySalesDiffReport> findDiffByPage(LotterySalesDiffReport m, Page<LotterySalesDiffReport> page);
}

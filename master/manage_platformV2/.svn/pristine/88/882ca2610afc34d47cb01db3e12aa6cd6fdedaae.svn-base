package com.jy.process.impl.system.request.lottery;


import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.process.impl.system.request.base.ApiRequestTxtProcessImpl;
import com.jy.process.system.request.lottery.TraceRequestProcess;
import com.jy.service.system.channels.CpOrderInfoService;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Matthew on 2017/4/26.
 */
@Service("traceRequestProcess")
public class TraceRequestProcessImpl extends ApiRequestTxtProcessImpl implements TraceRequestProcess {

    @Value("${order.Trace}")
    private String ftpFileName;//下载文件的名称
    @Autowired
    private CpOrderInfoService cpOrderInfoService;
    @Autowired
    private JobTaskStatisticsService jobTaskStatisticsService;

    private static final Logger LOG = LoggerFactory.getLogger(TraceRequestProcessImpl.class);

    @Override
    public String byJobName() {
        return "追期接口";
    }

    @Override
    public void saveJobLog(JobTaskStatistics jobTaskStatistics) {
        try {
            this.jobTaskStatisticsService.insertJobTaskStatistics(jobTaskStatistics);
        } catch (Exception e) {
            LOG.info("追期接口保存LOG数据失败！");
            e.printStackTrace();
        }
    }

    @Override
    public int saveFTPData(String filePath, String date) {
        int count = 0;
        try {
            count = this.cpOrderInfoService.traceSave(filePath, date);
            LOG.info("入库行数：{}", count);
        } catch (Exception e) {
            LOG.info("用户提现接口保存数据失败！");
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void synchronizeTraceData(String date) throws Exception {
        super.ftpCommonsFunction(ftpFileName, date);
    }
}

package com.jy.process.impl.system.request.lottery;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.process.impl.system.request.base.ApiRequestTxtProcessImpl;
import com.jy.process.system.request.base.TxtInterface;
import com.jy.process.system.request.lottery.UserBalanceRequestProcess;
import com.jy.service.system.finance.common.UserScoreService;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by shixi on 2017/4/27.
 */
@Service("scoreBalanceRequestProcess")
public class UserScoreBalanceProcessImpl extends ApiRequestTxtProcessImpl implements TxtInterface
{

    @Autowired
    protected UserScoreService service;

    @Autowired
    protected JobTaskStatisticsService jobTaskServiice;
    @Value("${score.balance}")
    private String ftpFileName;//下载文件的名称
    private static final Logger LOG = LoggerFactory.getLogger(UserScoreBalanceProcessImpl.class);

    @Override
    public String byJobName() {
        return "用户积分余额接口";
    }

    @Override
    public void saveJobLog(JobTaskStatistics jobTaskStatistics) {
        try {
            this.jobTaskServiice.insertJobTaskStatistics(jobTaskStatistics);
        } catch (Exception e) {
            LOG.info("用户积分余额接口保存LOG数据失败！");
            e.printStackTrace();
        }
    }

    @Override
    public int saveFTPData(String filePath, String date) {
        int count = 0;
        try {
            count = this.service.saveBalanceTxT(filePath, date);
            LOG.info("入库行数：{}", count);
        } catch (Exception e) {
            LOG.info("用户积分余额接口保存数据失败！");
            e.printStackTrace();
        }
        return count;
    }


    @Override
    public void synchronizationData(String date) throws Exception {
        super.ftpCommonsFunction(ftpFileName, date);
    }
}

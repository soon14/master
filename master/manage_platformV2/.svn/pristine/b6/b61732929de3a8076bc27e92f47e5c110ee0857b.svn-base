package com.jy.process.impl.system.request.lottery;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.process.impl.system.request.base.ApiRequestTxtProcessImpl;
import com.jy.process.system.request.lottery.UserTransDetailRequestProcess;
import com.jy.service.system.finance.common.UserTransDetailService;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lijunke on 2017/4/24.
 */
@Service("userTransDetailRequestProcess")
public class UserTransDetailRequestProcessImpl extends ApiRequestTxtProcessImpl implements UserTransDetailRequestProcess
{
    protected static final Logger log = LoggerFactory.getLogger(UserInfoRequestProcessImpl.class);


    @Autowired
    protected UserTransDetailService service;

    @Autowired
    protected JobTaskStatisticsService jobTaskServiice;

    @Value("${usertrans.info}")
    private String ftpFileName;

    @Override
    public void synchronizationUserTransData(String date) throws Exception {
        super.ftpCommonsFunction(ftpFileName, date);
    }

    @Override
    public String byJobName() {
        return "用户交易信息";
    }

    @Override
    public void saveJobLog(JobTaskStatistics jobTaskStatistics) {
        try {
            this.jobTaskServiice.insertJobTaskStatistics(jobTaskStatistics);
        } catch (Exception e) {
            log.error("用户交易同步数据接口保存LOG数据异常！");
            e.printStackTrace();
        }
    }

    @Override
    public int saveFTPData(String filePath, String date) {
        try {
            return service.save(filePath, date);
        } catch (Exception e) {
            log.error("用户交易同步数据接口保存数据异常！");
            e.printStackTrace();
        }
        return 0;
    }

}

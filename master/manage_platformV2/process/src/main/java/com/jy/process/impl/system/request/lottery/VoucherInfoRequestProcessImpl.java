package com.jy.process.impl.system.request.lottery;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.process.impl.system.request.base.ApiRequestTxtProcessImpl;
import com.jy.process.system.request.lottery.VoucherInfoRequestProcess;
import com.jy.service.system.channels.CpVoucherInfoService;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Matthew on 2017/4/26.
 */
@Service("voucherInfoRequestProcess")
public class VoucherInfoRequestProcessImpl extends ApiRequestTxtProcessImpl implements VoucherInfoRequestProcess {
    protected static final Logger log = LoggerFactory.getLogger(UserInfoRequestProcessImpl.class);

    @Autowired
    protected CpVoucherInfoService cpVoucherInfoService;
    @Autowired
    private JobTaskStatisticsService jobTaskService;

    @Value("${voucher.name}")
    private String ftpFileName;

    @Override
    public void synchronizeVoucherData(String date) throws Exception {
        super.ftpCommonsFunction(ftpFileName, date);
    }

    @Override
    public String byJobName() {
        return "兑换券信息";
    }

    @Override
    public void saveJobLog(JobTaskStatistics jobTaskStatistics) {
        try {
            this.jobTaskService.insertJobTaskStatistics(jobTaskStatistics);
        } catch (Exception e) {
            log.error("兑换券同步数据接口保存LOG数据异常！");
            e.printStackTrace();
        }
    }

    @Override
    public int saveFTPData(String filePath, String date) {
        try {
            return cpVoucherInfoService.save(filePath, date);
        } catch (Exception e) {
            log.error("兑换券同步数据接口保存数据异常！");
            e.printStackTrace();
        }
        return 0;
    }
}

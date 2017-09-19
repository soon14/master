package com.jy.task.job.request.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.impl.system.request.lottery.TaskWithdrawProcessImpl;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZQY on 2017/4/27.
 */
@Service
public class WithdrawInfoJob extends BaseTask {

    @Override
    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户提现开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        TaskWithdrawProcessImpl process = (TaskWithdrawProcessImpl) ac.getBean("taskWithdrawProcess");

        try {
            String queryTime = super.getParam()[2];

            if (!"default".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.saveTaskWithdrawInfoList(string);
                    }
                } else {
                    process.saveTaskWithdrawInfoList(queryTime);
                }
            } else {
                queryTime = DateUtils.getDate("yyyyMMdd");
                process.saveTaskWithdrawInfoList(queryTime);
            }
        } catch (Exception e) {
            log.error("同步用户提现JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户提现结束");
    }
}

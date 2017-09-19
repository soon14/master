package com.jy.task.job.request.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.impl.system.request.lottery.UserScoreBalanceProcessImpl;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by shixi on 2017/4/28.
 */
public class ScoreBalanceTask extends BaseTask {
    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户积分余额开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        UserScoreBalanceProcessImpl process = (UserScoreBalanceProcessImpl) ac.getBean("scoreBalanceRequestProcess");

        try {
            String queryTime = super.getParam()[2];

            if (!"default".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.synchronizationData(string);
                    }
                } else {
                    process.synchronizationData(queryTime);
                }
            } else {
                queryTime = DateUtils.getDate("yyyyMMdd");
                process.synchronizationData(queryTime);
            }
        } catch (Exception e) {
            log.error("同步用户积分余额JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户积分余额结束");
    }
}

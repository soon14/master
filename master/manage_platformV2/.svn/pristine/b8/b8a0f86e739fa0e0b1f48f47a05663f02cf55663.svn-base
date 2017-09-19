package com.jy.task.job.request.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.impl.system.request.lottery.UserTransDetailRequestProcessImpl;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by Matthew on 2017/6/26.
 */
public class UserTransTask extends BaseTask
{

    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户交易数据开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        UserTransDetailRequestProcessImpl process = (UserTransDetailRequestProcessImpl) ac.getBean("userTransDetailRequestProcess");
        try {
            String queryTime = super.getParam()[2];

            if (!"default".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.synchronizationUserTransData(string);
                    }
                } else {
                    process.synchronizationUserTransData(queryTime);
                }
            } else {
                queryTime = DateUtils.getDate("yyyyMMdd");
                process.synchronizationUserTransData(queryTime);
            }
        } catch (Exception e) {
            log.error("同步用户交易信息JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户交易信息结束");
    }
}

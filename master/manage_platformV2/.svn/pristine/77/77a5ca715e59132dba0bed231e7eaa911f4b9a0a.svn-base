package com.jy.task.job.request.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.system.request.lottery.NewTicketInfoProcess;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by Matthew on 2017/6/20.
 */
public class NewTicketTask  extends BaseTask {


    @Override
    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步出票信息开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        NewTicketInfoProcess process = (NewTicketInfoProcess) ac.getBean("newTicketInfoProcess");

        try {
            String queryTime = super.getParam()[2];

            if (!"default".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.synchronizeNewTicketData(string);
                    }
                } else {
                    process.synchronizeNewTicketData(queryTime);
                }
            } else {
                queryTime = DateUtils.getDate("yyyyMMdd");
                process.synchronizeNewTicketData(queryTime);
            }
        } catch (Exception e) {
            log.error("同步出票信息JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步出票信息结束");
    }
}

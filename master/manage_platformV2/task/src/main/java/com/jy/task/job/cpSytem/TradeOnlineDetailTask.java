package com.jy.task.job.cpSytem;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.system.request.lottery.TradeOnlineDetailProcess;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 线上交易明细入库
 */
public class TradeOnlineDetailTask extends BaseTask {

    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "线上交易明细任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        TradeOnlineDetailProcess process = (TradeOnlineDetailProcess) ac.getBean("tradeOnlineDetailProcess");

        try {
            String queryTime = super.getParam()[2];
            if (!"default".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.synchronizeData(string);
                    }
                } else {
                    process.synchronizeData(queryTime);
                }
            } else {
                queryTime= DateUtils.getDate("yyyyMMdd");
                process.synchronizeData(queryTime);
            }
        } catch (Exception e) {
            log.error("线上交易明细任务JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 线上交易明细任务结束");
    }
   
}

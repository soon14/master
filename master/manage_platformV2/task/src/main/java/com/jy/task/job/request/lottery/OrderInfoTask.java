package com.jy.task.job.request.lottery;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.impl.system.request.lottery.OrderInfoRequestProcessImpl;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 用户购彩数据入库
 */
public class OrderInfoTask extends BaseTask {

    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户购彩开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        OrderInfoRequestProcessImpl process = (OrderInfoRequestProcessImpl) ac.getBean("orderInfoRequestProcess");

        try {
            String queryTime = super.getParam()[2];

            if (!"default".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.synchronizeOrderData(string);
                    }
                } else {
                    process.synchronizeOrderData(queryTime);
                }
            } else {
                queryTime = DateUtils.getDate("yyyyMMdd");
                process.synchronizeOrderData(queryTime);
            }
        } catch (Exception e) {
            log.error("同步用户购彩JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户购彩结束");
    }
}

package com.jy.task.job.request.lottery;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.service.system.channels.CommissionService;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

/**
 * 线下佣金日报汇总同步入库
 */
public class CommissionOfflineTask extends BaseTask {

    public void saveData() {
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——线下佣金日报同步任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        CommissionService service = (CommissionService) ac.getBean("commissionService");

        try {
            service.synchroCommisonOffline();
        } catch (Exception e) {
            e.printStackTrace();
        }
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——线下佣金日报同步任务结束");
    }
}

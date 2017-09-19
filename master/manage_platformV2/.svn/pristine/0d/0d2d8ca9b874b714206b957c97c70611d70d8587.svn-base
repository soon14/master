package com.jy.task.job.cpSytem;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.impl.task.util.TaskLogUtil;
import com.jy.service.system.channels.CommissionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 按佣金结算周期统计佣金明细
 * 并根据佣金返佣配置返佣周期生成佣金返佣应付账款单 Created by Matthew on 2017-03-23.
 */
public class synchroCommissionPayable implements Job {

    /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(synchroPrepayment.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]开始运行");
        try {
            // 用户增量信息读取
            synchroInsert();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]成功运行结束");
    }

    private void synchroInsert() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——返佣应付账款信息统计任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        CommissionService service = (CommissionService) ac.getBean("CommissionService");
        try {
//            service.synchroCommison();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——返佣应付账款信息统计任务结束");
    }
}

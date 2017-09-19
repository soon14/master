package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.system.finance.statistics.lottery.QrCodeDailyReportService;
import com.jy.service.system.finance.statistics.lottery.QrCodeDetailDailyReportService;
import com.jy.service.system.finance.statistics.lottery.QrCodeSoldDailyReportService;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Administrator on 2017/2/13.
 */
public class QrCodeTask  implements Job {
    private static final Logger log = LoggerFactory.getLogger(InsertSmReport.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {
            QrCodeInsertTask();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]成功运行结束");
    }

    private void QrCodeInsertTask() {
        //兑换券销售日报数据存入
        QrCodeDailyReportTask();
        //已售兑换券销售日报数据存入
        QrCodeSoldDailyReportTask();
        //兑换券销售明细日报数据存入
        QrCodeDetailDailyReportTask();
    }

    /**
     * 兑换券销售日报
     */
    private void QrCodeDailyReportTask() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——兑换券销售日报统计任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        QrCodeDailyReportService service  = (QrCodeDailyReportService) ac.getBean("QrCodeDailyReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        service.insertReport("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——兑换券销售日报统计任务结束");
    }

    /**
     * 已售兑换券销售日报数据存入
     */
    private void QrCodeSoldDailyReportTask() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——已售兑换券销售日报数据存入统计任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        QrCodeSoldDailyReportService service  = (QrCodeSoldDailyReportService) ac.getBean("QrCodeSoldDailyReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        service.insertSoldReport("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——已售兑换券销售日报数据存入统计任务结束");
    }

    /**
     * 兑换券销售明细日报数据存入
     */
    private void QrCodeDetailDailyReportTask() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——兑换券销售明细日报数据存入统计任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        QrCodeDetailDailyReportService service  = (QrCodeDetailDailyReportService) ac.getBean("QrCodeDetailDailyReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        service.insertDetailReport("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——兑换券销售明细日报数据存入统计任务结束");
    }

}

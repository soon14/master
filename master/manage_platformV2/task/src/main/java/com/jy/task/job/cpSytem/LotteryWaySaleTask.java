package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.system.finance.statistics.lottery.LotteryWaySaleReportService;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


/**
 * @文件名:LotteryWaySaleRepory.java
 * @功能描述：
 * @创建日期:2017年3月9日下午6:41:22
 * @创建人：Dingj
 * @Copyright（c） 2017,all rights reserved by days
 */
public class LotteryWaySaleTask implements Job {

	 /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(LotteryWaySaleTask.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {
            execLotteryWayTask();
            execOrderTicketTask();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]成功运行结束");
    }

    private void execOrderTicketTask() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        LotteryWaySaleReportService service  = (LotteryWaySaleReportService) ac.getBean("lotteryWaySaleReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveOrderTicketTableValue(date,service);
        _generateOrderTicketReport(month,service);
    }

    private void _generateOrderTicketReport(String month, LotteryWaySaleReportService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——方案与票对账报表生成excel开始运行");
        service.generateOrderTicketReport(month);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——方案与票对账报表生成excel任务结束");
    }

    private void _saveOrderTicketTableValue(String date, LotteryWaySaleReportService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——方案与票对账报表统计任务开始运行");
        try {
            service.execOrderTicketTask(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——方案与票对账报表统计任务任务结束");
    }

    private void execLotteryWayTask() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        LotteryWaySaleReportService service  = (LotteryWaySaleReportService) ac.getBean("lotteryWaySaleReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveTableValue(date,service);
        _generateReport(month,service);
    }

    private void _saveTableValue(String date, LotteryWaySaleReportService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——彩种销售额统计任务开始运行");
        try {
            service.execLotteryTask(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——彩种销售额统计任务任务结束");
    }

    private void _generateReport(String month, LotteryWaySaleReportService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——彩种销售额生成excel开始运行");
        service.generateReport(month);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——彩种销售额生成excel任务结束");
    }

}

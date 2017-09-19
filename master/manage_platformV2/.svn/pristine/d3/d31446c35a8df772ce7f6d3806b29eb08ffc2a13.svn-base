package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.impl.task.util.TaskLogUtil;
import com.jy.service.system.finance.reconciliation.lottery.LotteryBuyAndTicketService;
import com.jy.service.system.finance.statistics.lottery.LotteryWaySaleReportService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;



public class LotteryBuyAndTicketTask implements Job {

	 /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(LotteryBuyAndTicketTask.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {
            execLotteryBuyTicketTask();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]成功运行结束");
    }

    private void execLotteryBuyTicketTask() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        LotteryBuyAndTicketService service  = (LotteryBuyAndTicketService) ac.getBean("LotteryBuyAndTicketService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveLotteryBuyTicketTableValue(date,service);
        _generateLotteryBuyTicketReport(month,service);
    }

    private void _saveLotteryBuyTicketTableValue(String month, LotteryBuyAndTicketService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——购彩与出票对账报表生成excel开始运行");
        service.execTask(month);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——购彩与出票对账报表生成excel任务结束");
    }

    private void _generateLotteryBuyTicketReport(String date, LotteryBuyAndTicketService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——购彩与出票对账报表统计任务开始运行");
        try {
            service.generateReport(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——购彩与出票对账报表统计任务任务结束");
    }




}

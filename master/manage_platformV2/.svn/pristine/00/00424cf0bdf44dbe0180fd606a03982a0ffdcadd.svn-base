package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.impl.task.util.TaskLogUtil;
import com.jy.service.system.finance.reconciliation.lottery.LotteryBuyAndTicketService;
import com.jy.service.system.finance.statistics.lottery.LotterySalesDiffReportService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


public class LotterySalesDiffReportTask implements Job {

	 /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(LotterySalesDiffReportTask.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {
            execLotterySalesDiffReportTask();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]成功运行结束");
    }

    private void execLotterySalesDiffReportTask() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        LotterySalesDiffReportService service  = (LotterySalesDiffReportService) ac.getBean("LotterySalesDiffReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveLotterySalesDiffReportTableValue(date,service);
        _generateLotterySalesDiffReport(month,service);
    }

    private void _saveLotterySalesDiffReportTableValue(String month, LotterySalesDiffReportService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——差异报表生成excel开始运行");
        service.execTask(month);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——差异报表生成excel任务结束");
    }

    private void _generateLotterySalesDiffReport(String date, LotterySalesDiffReportService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——差异报表统计任务开始运行");
        try {
            service.generateReport(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——差异报表统计任务任务结束");
    }




}

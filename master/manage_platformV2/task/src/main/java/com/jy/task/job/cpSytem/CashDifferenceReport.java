package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.impl.system.finance.reconciliation.lottery.CashDifferenceServiceImpl;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @文件名:BettingDifferenceReport.java
 * @功能描述：
 * @创建日期:2017年3月16日下午4:57:24
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */
public class CashDifferenceReport implements Job {

	/* 日志对象 */
	private static final Logger log = LoggerFactory.getLogger(CashDifferenceReport.class);

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
		String jobName = scheduleJob.getJobName();
		String jobGroup = scheduleJob.getJobGroup();
		String jobClass = scheduleJob.getJobClass();
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]开始运行");
		try {
			// 生成excel
			this.GenerateReport();
			// 保存日志
			TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
		} catch (Exception e) {
			log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]异常", e);
			// 保存异常日志
			TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
		}
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]成功运行结束");
	}

	public void GenerateReport() {
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——兑奖派奖差异总报表任务开始运行");
		ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
		CashDifferenceServiceImpl cashDifferenceService = (CashDifferenceServiceImpl) ac.getBean("cashDifferenceService");
		cashDifferenceService.GenerateCashCountReport();
		cashDifferenceService.GenerateDetailedReport();
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——兑奖派奖差异总报表统计任务结束");
	}

}

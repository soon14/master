package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.reconciliation.lottery.TicketDifferenceService;
import com.jy.service.system.finance.reconciliation.lottery.WithDrawDifferenceService;
import com.jy.service.system.finance.statistics.lottery.SalesCommissionReportService;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 销售佣金报表统计并插入数据 Created by Dingj on 2017-01-15.
 */
/* 案例 同步和不同步的区别 非同步需要加@DisallowConcurrentExecution */
public class InsertSmReport implements Job {

	/* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(InsertSmReport.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]开始运行");
        try {
			// 用户增量信息读取
            InsertReport();
			// 保存日志
			TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
			log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]异常", e);
			// 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]成功运行结束");
    }

    private void InsertReport() {
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——销售佣金报表统计任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        SalesCommissionReportService service  = (SalesCommissionReportService) ac.getBean("SalesCommissionReportService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        try {
            service.insertReport("auto",date);
        } catch (Exception e) {
            e.printStackTrace();
        }
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——销售佣金报表统计任务结束");

		SysDictService sdService = (SysDictService) ac.getBean("SysDictService");
		SysDict o = new SysDict();
		o.setParamKey("filePath");
		List<SysDict> dictList = sdService.find(o);
		SysDict obj = dictList.get(0);
		String path = obj.getParamValue();

		service.findExportReport();// 生成渠道佣金统计报表
		WithDrawDifferenceService wdService = (WithDrawDifferenceService) ac.getBean("WithDrawDifferenceService");
		wdService.findExportReport();// 生成提现汇总日报

		TicketDifferenceService dtService = (TicketDifferenceService) ac.getBean("TicketDifferenceService");
		dtService.findExportReport(path);// 生成券报表
    }
}

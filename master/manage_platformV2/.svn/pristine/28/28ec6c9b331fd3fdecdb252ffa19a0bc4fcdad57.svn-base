package com.jy.task.job.cpSytem;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.system.channels.DevelopCustomerService;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @文件名：: DevelopCoustomerReport
 * @功能描述: TODO
 * @创建时间: 2017年3月3日 上午9:17:12 <br/>
 * @author lijunke
 * @version
 */
public class DevelopCoustomerReport implements Job {
    /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(DevelopCoustomerReport.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {
            //生成excel
        	GenerateReport();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]成功运行结束");
    }

    private void GenerateReport() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——发展客户报表任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        DevelopCustomerService service  = (DevelopCustomerService) ac.getBean("developCustomerService");
        service.GenerateReport();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——发展客户报表统计任务结束");
    }


}

package com.jy.task.job.request.base;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.impl.system.dict.SysDictServiceImp;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by shixi on 2017/5/3.
 */
public abstract class BaseTask implements Job {

    /* 日志对象 */
    protected static final Logger log = LoggerFactory.getLogger(BaseTask.class);

    protected static final int PAGE_SIGE = 3000;//默认每页条数

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]开始运行");
        try {
            this.saveData();
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "  任务[" + jobName + "]成功运行结束");
    }

    public abstract void saveData();

    /**
     * [0]  = queryAll
     * [1]  = pageSize
     * [2]  = queryTime
     *
     * @return
     */
    public String[] getParam() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        SysDictServiceImp dictServiceImp = (SysDictServiceImp) ac.getBean("SysDictService");
        String[] dataParam = new String[3];
        dataParam[0] = dictServiceImp.byValue("queryAll");
        dataParam[1] = dictServiceImp.byValue("pageSize");
        dataParam[2] = dictServiceImp.byValue("queryTime");
        return dataParam;
    }
}

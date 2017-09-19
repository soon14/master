package com.jy.task.job.cpSytem;

import com.jy.common.Const.TaskExcuteConst;

import com.jy.common.utils.DateUtils;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.task.job.helper.ReadCpUserInfoHelper;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.service.impl.task.util.TaskLogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 彩票投注系统对账数据导入
 * Created by Dingj on 2017-01-15.
 */
/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
public class ReadDzFile implements Job {
    /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(ReadDzFile.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {

            //用户增量信息读取
            readCpUserInfo(scheduleJob.getScheduleJobId());
            //用户资金交易明细读取
            readCpUserQcFunds(scheduleJob.getScheduleJobId());
            //用户资金交易明细读取
            readCpUserFundsDetail(scheduleJob.getScheduleJobId());
            //用户购彩交易明细读取
            readCpTransDetail(scheduleJob.getScheduleJobId());
            //用户兑奖明细读取
            readCpPrizeDetail(scheduleJob.getScheduleJobId());
            //用户购彩出票销售明细
            readCpTicketDetail(scheduleJob.getScheduleJobId());
            // 保存日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.NORMAL, "测试任务正常运行");
        } catch (Exception e) {
            log.error(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]异常", e);
            // 保存异常日志
            TaskLogUtil.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogUtil.EXCEPTION, e.toString());
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]成功运行结束");
    }

    /**
     * 用户增量信息读取
     */
    private void readCpUserInfo(String jobId) {
        //检查任务执行状态
        if(ReadCpUserInfoHelper.checkJobExcute(jobId, TaskExcuteConst.CPREADFILE_TYPE_USERINFO,DateUtils.getDate("yyyyMMdd"))){
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户增量信息读取任务开始运行");
            ReadCpUserInfoHelper.excute();
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户增量信息读取任务结束");
        }
    }

    /**
     * 用户资金期初明细读取
     */
    private void readCpUserQcFunds(String jobId) {
        //检查任务执行状态
        if(ReadCpUserInfoHelper.checkJobExcute(jobId, TaskExcuteConst.CPREADFILE_TYPE_QCFUNDS,DateUtils.getDate("yyyyMMdd"))){
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户资金期初明细读取任务开始运行");
            ReadCpUserInfoHelper.excute();
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户资金期初明细读取任务结束");
        }
    }

    /**
     * 用户资金交易明细读取
     */
    private void readCpUserFundsDetail(String jobId) {
        //检查任务执行状态
        if(ReadCpUserInfoHelper.checkJobExcute(jobId, TaskExcuteConst.CPREADFILE_TYPE_FUNDSDETAIL,DateUtils.getDate("yyyyMMdd"))){
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户资金交易明细读取任务开始运行");
            ReadCpUserInfoHelper.excute();
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户资金交易明细读取任务结束");
        }

    }

    /**
     * 用户购彩交易明细读取
     */
    private void readCpTransDetail(String jobId) {
        //检查任务执行状态
        if(ReadCpUserInfoHelper.checkJobExcute(jobId, TaskExcuteConst.CPREADFILE_TYPE_TRANDETAIL,DateUtils.getDate("yyyyMMdd"))){
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户购彩交易明细读取任务开始运行");
            ReadCpUserInfoHelper.excute();
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户购彩交易明细读取任务结束");
        }

    }

    /**
     * 用户兑奖明细读取
     */
    private void readCpPrizeDetail(String jobId) {
        //检查任务执行状态
        if(ReadCpUserInfoHelper.checkJobExcute(jobId, TaskExcuteConst.CPREADFILE_TYPE_PRIZEDETAIL,DateUtils.getDate("yyyyMMdd"))){
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户兑奖明细读取任务开始运行");
            ReadCpUserInfoHelper.excute();
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户兑奖明细读取任务结束");
        }

    }

    /**
     * 用户购彩出票销售明细
     */
    private void readCpTicketDetail(String jobId) {
        //检查任务执行状态
        if(ReadCpUserInfoHelper.checkJobExcute(jobId, TaskExcuteConst.CPREADFILE_TYPE_TICKETDETAIL,DateUtils.getDate("yyyyMMdd"))){
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户购彩出票销售明细任务开始运行");
            ReadCpUserInfoHelper.excute();
            log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——用户购彩出票销售明细任务结束");
        }

    }

}

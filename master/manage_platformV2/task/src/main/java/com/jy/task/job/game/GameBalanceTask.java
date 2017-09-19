package com.jy.task.job.game;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.common.utils.task.ScheduleUtils;
import com.jy.entity.task.base.ScheduleJob;
import com.jy.service.impl.task.util.TaskLogUtil;
import com.jy.service.system.finance.reconciliation.game.DiamondBalanceService;
import com.jy.service.system.finance.reconciliation.game.GoldBalanceService;
import com.jy.service.system.finance.reconciliation.game.ToolsBalanceService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Administrator on 2017/3/2.
 */
public class GameBalanceTask implements Job{

    /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(GameBalanceTask.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String jobClass = scheduleJob.getJobClass();
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+"  任务[" + jobName + "]开始运行");
        try {
            //游戏资金报表对账统计
            gameFundsTask();
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
     * 游戏资金报表对账统计
     */
    private void gameFundsTask() {
        //金币统计
        goldBalanceInsert();
        //钻石统计
        diamondBalanceInsert();
        //道具统计
        toolsBalanceInsert();
    }



    private void goldBalanceInsert() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        GoldBalanceService service  = (GoldBalanceService) ac.getBean("GoldBalanceService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveGoldTableValue(date,service);
        _generateGoldReport(month,service);
    }

    private void _saveGoldTableValue(String date, GoldBalanceService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——金币统计任务start");
        service.execGoldBalanceTask("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——金币统计任务end");
    }

    private void _generateGoldReport(String month, GoldBalanceService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——生成金币报表开始");
        service.generateGoldBalanceReport("auto",month);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——生成金币报表结束");
    }

    private void diamondBalanceInsert() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        DiamondBalanceService service  = (DiamondBalanceService) ac.getBean("diamondBalanceService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveDiamondTableValue(date,service);
        _generateDiamondReport(month,service);

    }

    private void _generateDiamondReport(String month, DiamondBalanceService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——生成钻石报表start");
        service.generateDiamondBalanceReport("auto",month);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——生成钻石报表end");
    }

    private void _saveDiamondTableValue(String date, DiamondBalanceService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——钻石统计任务start");
        service.execDiamondBalanceTask("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——钻石统计任务end");
    }

    private void toolsBalanceInsert() {
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        ToolsBalanceService service  = (ToolsBalanceService) ac.getBean("ToolsBalanceService");
        String date=DateUtils.getAfterDayDate("-1").substring(0,10);
//        String month=DateUtils.getAfterDayDate("-1").substring(0,7);
        _saveToolsTableValue(date,service);
        _generateToolsReport(date,service);
    }

    private void _saveToolsTableValue(String date, ToolsBalanceService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——道具统计任务start");
        service.execToolsBalanceTask("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——道具统计任务start");
    }

    private void _generateToolsReport(String date, ToolsBalanceService service) {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——生成道具报表开始");
        service.generateToolsBalanceReport("auto",date);
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+" ——生成道具报表结束");
    }


}

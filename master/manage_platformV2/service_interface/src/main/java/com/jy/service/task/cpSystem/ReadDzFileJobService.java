package com.jy.service.task.cpSystem;

import com.jy.entity.task.cpSystem.JobExcuteProcess;
import com.jy.service.base.BaseService;

/**
 * 定时任务service
 */
public interface ReadDzFileJobService extends BaseService<JobExcuteProcess>
{

    /**
     * 根据具体的类和执行类型 查询对应日期的执行记录
     *
     * @return
     */
    public JobExcuteProcess findJobExcuteProcess();

}

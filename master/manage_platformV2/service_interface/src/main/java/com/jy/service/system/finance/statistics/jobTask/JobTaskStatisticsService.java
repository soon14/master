package com.jy.service.system.finance.statistics.jobTask;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;


public interface JobTaskStatisticsService extends BaseService<JobTaskStatistics>
{

    public Page<JobTaskStatistics> fundJobTaskStatistics(Page<JobTaskStatistics> page,JobTaskStatistics form);

    public Integer insertJobTaskStatistics(JobTaskStatistics form);

}

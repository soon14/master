package com.jy.process.impl.system.statistics.jobTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.mybatis.Page;
import com.jy.process.system.statistics.jobTask.JobTaskStatisticsProcess;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;

@Service("jobTaskStatisticsProcess")
public class JobTaskStatisticsProcessImpl implements JobTaskStatisticsProcess {
	
	
    @Autowired
    public JobTaskStatisticsService jobTaskStatisticsService;
	

	@Override
	public Page<JobTaskStatistics> fundJobTaskStatistics(Page<JobTaskStatistics> page, JobTaskStatistics form) {

		return jobTaskStatisticsService.fundJobTaskStatistics(page, form);
	}

}

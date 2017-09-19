package com.jy.repository.system.finance.statistics.jobTask;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;


@JYBatis
public interface JobTaskStatisticsDao extends BaseDao<JobTaskStatistics> {

	public List<JobTaskStatistics> fundJobTaskStatistics(@Param("param")JobTaskStatistics form);

    public Integer insertJobTaskStatistics(JobTaskStatistics form);

}

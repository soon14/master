package com.jy.service.impl.system.finance.statistics.jobTask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.common.mybatis.PageCalculation;
import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.mybatis.Page;
import com.jy.repository.system.finance.statistics.jobTask.JobTaskStatisticsDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;


@Service("JobTaskStatisticsService")
public class JobTaskStatisticsServiceImpl extends BaseServiceImp<JobTaskStatistics> implements JobTaskStatisticsService {

	@Autowired
	private JobTaskStatisticsDao jobTaskStatisticsDao;

	@Override
	public Page<JobTaskStatistics> fundJobTaskStatistics(Page<JobTaskStatistics> page, JobTaskStatistics form) {

		List<JobTaskStatistics> list = jobTaskStatisticsDao.fundJobTaskStatistics(form);
		page.setResults(list);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<JobTaskStatistics>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(list.size());
		}
		return page;
	}

	@Override
	public Integer insertJobTaskStatistics(JobTaskStatistics form) {

		return jobTaskStatisticsDao.insertJobTaskStatistics(form);
	}
	
	
}

package com.jy.service.system.finance.statistics.lottery;

import com.jy.common.exception.DaysBaseException;
import com.jy.entity.system.finance.LotteryWaySaleReport;
import com.jy.entity.system.finance.statistics.OrderTicketDiffReport;
import com.jy.entity.system.finance.statistics.OrderTicketReport;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;


/**
 * @文件名:LotteryWaySaleReportService.java
 * @功能描述：
 * @创建日期:2017年3月9日上午11:41:32
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
public interface LotteryWaySaleReportService extends BaseService<LotteryWaySaleReport> {

	 void generateReport(String month);

	void execLotteryTask(String date)throws DaysBaseException;

	void execOrderTicketTask(String date) throws DaysBaseException ;

	void generateOrderTicketReport(String month);

	Page<OrderTicketReport> findOrderTicketByPage(OrderTicketReport m, Page<OrderTicketReport> page);

	Page<OrderTicketDiffReport> findOrderTicketDiffByPage(OrderTicketDiffReport m, Page<OrderTicketDiffReport> page);
}

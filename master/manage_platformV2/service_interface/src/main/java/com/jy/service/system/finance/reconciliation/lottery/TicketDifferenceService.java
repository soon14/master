package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.TicketDifference;
import com.jy.service.base.BaseService;

import java.util.List;


public interface TicketDifferenceService extends BaseService<TicketDifference>
{

	public List<TicketDifference> findDifference(String vstart, String vend);
	
	public List<TicketDifference> childDiffByBatchNo(String usedDate, String VStart, String VEnd, String VNo);
	
	public List<TicketDifference> findTicketDifference();
	
	public void insertTicketDifference(List<TicketDifference> list);

	public void findExportReport(String filePath);

}

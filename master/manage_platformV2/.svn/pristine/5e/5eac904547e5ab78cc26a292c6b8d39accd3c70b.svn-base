package com.jy.repository.system.finance.reconciliation.lottery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jy.entity.system.finance.reconciliation.lottery.TicketDifference;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;


@JYBatis
public interface TicketDifferenceDao extends BaseDao<TicketDifference> {
	
	public List<TicketDifference> findDifference(@Param("vstart") String vstart, @Param("vend") String vend);
	
	public List<TicketDifference> childDiffByBatchNo(@Param("usedDate") String usedDate, @Param("VStart") String VStart, @Param("VEnd") String VEnd, @Param("VNo") String VNo);
	
	public List<TicketDifference> findTicketDifference();
	
	public void insertTicketDifference(List<TicketDifference> list);
	
	public void deleteTicketDifference();
}

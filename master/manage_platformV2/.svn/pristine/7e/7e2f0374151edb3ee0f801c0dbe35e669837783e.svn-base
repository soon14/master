package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.WithDrawDifference;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@JYBatis
public interface WithDrawDifferenceDao extends BaseDao<WithDrawDifference> {
	
	public List<WithDrawDifference> findByTDate(@Param("tdate") String tdate, @Param("TFlowNo") String TFlowNo);

	public void insertItems(List<WithDrawDifference> w);

	public WithDrawDifference findByTradeNo(@Param("tradeNo") String tradeNo);

	public void save(WithDrawDifference withDrawDifference);

	public void updateByTradeNo(WithDrawDifference withDrawDifference);
}

package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.BettingDifference;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * @文件名:BettingDifferenceDao.java
 * @功能描述：对账差异dao
 * @创建日期:2017年3月10日下午4:15:59
 * @创建人：lijunke
 * @Copyright（c） 2017, all rights reserved by days
 */
@JYBatis
public interface BettingDifferenceDao extends BaseDao<BettingDifference> {

    List<BettingDifference> findByDate(@Param("date") String date, @Param("ids") String ids, @Param("number")int number);

    void delete(@Param("startTime") Date startTime);

    void insert(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<BettingDifference> findAll(@Param("startTime") Date startDate);
}

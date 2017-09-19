package com.jy.repository.system.finance.statistics.lottery;

import com.jy.entity.system.finance.statistics.LotterySalesDiffReport;
import com.jy.mybatis.Page;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
@JYBatis
public interface LotterySalesDiffReportDao extends BaseDao<LotterySalesDiffReport>{



//    List<LotteryBuyAndTicket> findReportByDate(String thisDate);

    List<LotterySalesDiffReport> findMonth(@Param("param") LotterySalesDiffReport m);




    List<LotterySalesDiffReport> findDiffByPage(@Param("param") LotterySalesDiffReport m, Page<LotterySalesDiffReport> page);


    List<LotterySalesDiffReport> findDiffReportByDate(String thisDate);

    void insertDiffReport(List<LotterySalesDiffReport> diffList);

    void deleteDiffList(List<LotterySalesDiffReport> isExit);

    List<LotterySalesDiffReport> findDiffMonth(@Param("param") LotterySalesDiffReport m);

    List<LotterySalesDiffReport> findOrderTicketDiff(String thisDate);

    List<LotterySalesDiffReport> findBuyAndTicketDiff(String thisDate);

    List<LotterySalesDiffReport> findSystemAndMachineDiff(String thisDate);

    List<LotterySalesDiffReport> findTicketAndTicketDiff(String thisDate);
}

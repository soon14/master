package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.TranceFunds;
import com.jy.entity.system.finance.statistics.QrCodeDailyReport;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
@JYBatis
public interface TranceFundsDao extends BaseDao<TranceFunds>{


    Double findQcTranceMoney(String date);

    Double findTicketMoneyBetweenDate(String date);

    Double findRefundMoney(String date);

    Double findAddTranceMoney(String date);

    List<TranceFunds> findReportByDate(String thisDate);

    List<TranceFunds> findMonth(@Param("param") TranceFunds m);

    void deleteList( List<TranceFunds> list);
}

package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicket;
import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicketDiffReport;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface LotteryBuyAndTicketService extends BaseService<LotteryBuyAndTicket> {
    void generateReport(String month);

    void execTask( String thisDate);

    Page<LotteryBuyAndTicketDiffReport> findLotteryBuyAndTicketDiffByPage(LotteryBuyAndTicketDiffReport m, Page<LotteryBuyAndTicketDiffReport> page);
}

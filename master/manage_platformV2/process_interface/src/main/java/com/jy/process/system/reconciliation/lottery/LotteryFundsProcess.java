package com.jy.process.system.reconciliation.lottery;

import com.jy.from.system.finance.reconciliation.lottery.PlatformFundsForm;
import com.jy.from.system.finance.reconciliation.lottery.SalesDifferencesDetailForm;
import com.jy.mybatis.Page;
import com.jy.vo.system.reconciliation.lottery.FundsActivityReportVo;
import com.jy.vo.system.reconciliation.lottery.PlatFormFundsRunningVo;
import com.jy.vo.system.reconciliation.lottery.PlatFormFundsVo;
import com.jy.vo.system.reconciliation.lottery.SalesDifferencesDetailVo;

/**
 * Created by yutao on 2017/4/12.
 */
public interface LotteryFundsProcess {
    boolean findFundsListCheck(PlatformFundsForm form);

    Page<PlatFormFundsVo> findFundsList(PlatformFundsForm form, Page<PlatFormFundsVo> page);

    Page<PlatFormFundsRunningVo> findRunningList(PlatformFundsForm form, Page<PlatFormFundsRunningVo> page);

    void saveFundsDive(SalesDifferencesDetailForm form);

    void saveRunningDive(SalesDifferencesDetailForm form);

    Page<SalesDifferencesDetailVo> findDiffList(SalesDifferencesDetailForm form, Page<SalesDifferencesDetailVo> page);

    Object dowload(String date, String type);

    Page<FundsActivityReportVo> findActivityFundsList(PlatformFundsForm form, Page<FundsActivityReportVo> page);
}

package com.jy.test;

import com.jy.common.utils.DateUtils;
import com.jy.process.system.reconciliation.lottery.CashPrizeProcess;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lijunke on 2017/5/22.
 */
public class CashPrizeDifferenceProcessTest extends BaseJunit4Test {

    @Resource
    private CashPrizeProcess cashPrizeProcess;

    @Test
    public void test () {
        Date date = DateUtils.parseDate("2017-04-01");
        Date date1 = DateUtils.parseDate("2017-05-02");
        this.cashPrizeProcess.generateDataAndMergerData(date,date1);
    }

}

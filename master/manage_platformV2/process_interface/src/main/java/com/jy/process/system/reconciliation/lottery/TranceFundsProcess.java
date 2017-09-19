package com.jy.process.system.reconciliation.lottery;

import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.vo.system.reconciliation.lottery.TranceFundsVo;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface TranceFundsProcess {
    Page<TranceFundsVo> findList(BaseForm form, Page<TranceFundsVo> page);

    Object dowload(String date);
}

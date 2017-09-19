package com.jy.process.system.common;

import com.jy.from.system.finance.reconciliation.lottery.BaseForm;

/**
 * Created by yutao on 2017/4/12.
 */
public interface ManualProcess {
    void manualByType(BaseForm form, int i)throws Exception;
}

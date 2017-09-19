package com.jy.process.impl.system.common;

import com.jy.common.utils.DateUtils;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.process.system.common.FormProcess;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service("FormProcess")
public class FormProcessImpl implements FormProcess {
    @Override
    public void check(BaseForm form) {
        if (form.getBeginTime() == null || form.getBeginTime().equals("")) {
            form.setBeginTime(DateUtils.getPreDate(-7));
        }
        if (form.getEndTime() == null || form.getEndTime().equals("")) {
            form.setEndTime(DateUtils.getDate("yyyy-MM-dd"));
        }
    }
}

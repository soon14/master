package com.jy.process.system.reconciliation.lottery.qrCode;

import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.vo.system.statistics.lottery.QrCodeDailyVo;


/**
 * Created by yutao on 2017/4/12.
 */
public interface DailyProcess {
    Page<QrCodeDailyVo> findByPage(Page<QrCodeDailyVo> page, BaseForm form);

    Object download(String date);
}

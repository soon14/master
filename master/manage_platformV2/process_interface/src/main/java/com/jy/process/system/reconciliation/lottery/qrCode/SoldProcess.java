package com.jy.process.system.reconciliation.lottery.qrCode;

import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.from.system.finance.statistics.QrCodeSoldDailyReportForm;
import com.jy.mybatis.Page;
import com.jy.vo.system.statistics.lottery.QrCodeSoldVo;

/**
 * Created by yutao on 2017/4/12.
 */
public interface SoldProcess {
    Page<QrCodeSoldVo> findByPage(Page<QrCodeSoldVo> page, BaseForm form);

    void add(QrCodeSoldDailyReportForm form);

    Object download(String date);
}

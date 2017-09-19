package com.jy.process.system.reconciliation.games;

import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.vo.system.reconciliation.games.GoldVo;

/**
 * Created by yutao on 2017/4/12.
 */
public interface GoldProcess {
    Page<GoldVo> findByPage(BaseForm form, Page<GoldVo> page);

    Object download(String date);
}

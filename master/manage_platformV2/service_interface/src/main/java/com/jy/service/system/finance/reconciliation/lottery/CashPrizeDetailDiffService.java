package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;

import java.util.Date;

/**
 * Created by lijunke on 2017/5/25.
 */
public interface CashPrizeDetailDiffService extends BaseService<CashDiffDetail> {

    Page<CashDiffDetail>  findAllDetail(Date date,Page<CashDiffDetail> page);
}

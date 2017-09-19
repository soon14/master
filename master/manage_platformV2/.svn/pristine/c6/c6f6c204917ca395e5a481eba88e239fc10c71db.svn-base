package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.mybatis.Page;
import com.jy.repository.system.finance.reconciliation.lottery.CashPrizeDetailDifferenceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.CashPrizeDetailDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lijunke on 2017/5/25.
 */
@Service("cashPrizeDetailDiffService")
public class CashPrizeDetailDiffServiceImpl extends BaseServiceImp<CashDiffDetail> implements CashPrizeDetailDiffService {

    @Autowired
    private CashPrizeDetailDifferenceDao dao;

    @Override
    public Page<CashDiffDetail> findAllDetail(Date date, Page<CashDiffDetail> page) {
        page.setResults(dao.findAllDetail(date, page));
        return page;
    }
}

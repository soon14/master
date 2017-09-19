package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.entity.system.finance.reconciliation.lottery.NewTicketInfo;
import com.jy.repository.system.finance.reconciliation.lottery.NewTicketDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.NewTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lijunke on 2017/6/2.
 */
@Service("newTicketService")
public class NewTicketServiceImpl extends BaseServiceImp<NewTicketInfo> implements NewTicketService {

    @Autowired
    private NewTicketDao newTicketDao;

    @Override
    public int save(String filePath, String currentDate) {
        newTicketDao.deleteByDate(DateUtils.stringToDate(currentDate, "yyyyMMdd"));
        return newTicketDao.save(filePath, DateUtils.stringToDate(currentDate, "yyyyMMdd"));
    }

}
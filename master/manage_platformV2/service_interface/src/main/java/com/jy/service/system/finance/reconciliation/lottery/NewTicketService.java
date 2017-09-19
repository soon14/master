package com.jy.service.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.NewTicketInfo;
import com.jy.service.base.BaseService;

/**
 * Created by lijunke on 2017/6/2.
 */
public interface NewTicketService extends BaseService<NewTicketInfo> {

    int save(String filePath, String currentDate);
}

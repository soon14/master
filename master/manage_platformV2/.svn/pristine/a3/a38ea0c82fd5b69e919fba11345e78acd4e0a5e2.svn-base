package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.NewTicketInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lijunke on 2017/6/2.
 */
@JYBatis
public interface NewTicketDao extends BaseDao<NewTicketInfo> {

    int saveNewTicket(@Param("list") List<NewTicketInfo> newTicketInfoList);

    int save(@Param("fileName") String filePath, @Param("currentDate")Date currentDate);

    void deleteByDate(@Param("currentDate")Date currentDate);

}

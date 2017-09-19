package com.jy.repository.system.channels;

import com.jy.entity.system.channels.TicketImportRecordsVO;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * 出票导入记录
 */
@JYBatis
public interface TicketImportRecordsDao extends BaseDao<TicketImportRecordsVO>{


    /**
     * 查找出当天导入次数
     * @param
     * @return
     */
    public Integer findImportCount(@Param("createDate") String createDate, @Param("transType") String transType);

}

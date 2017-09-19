package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.TaskPrizeInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZQY on 2017/4/26.
 */
@JYBatis
public interface TaskPrizeDao extends BaseDao<TaskPrizeInfo> {
    int  save ( @Param("fileName") String fileName, @Param("sycDate")String date);
    /**
     * 删除全部数据
     */
    void deleteByAll(@Param("sycDate") String sycDate);
}

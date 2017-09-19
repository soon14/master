package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.task.withdraw.TaskWithdrawInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZQY on 2017/4/26.
 */
@JYBatis
public interface TaskWithdrawDao extends BaseDao<TaskWithdrawInfo> {
    public int saveTaskWithdrawInfoList(@Param("fileName") String fileName, @Param("sycTime") String sycTime);
    public void deleteWithDrawInfo(@Param("deleteTime") String deleteTime);
}

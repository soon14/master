package com.jy.repository.system.finance.commom;

import com.jy.entity.system.finance.common.UserBalancePO;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shixi on 2017/4/27.
 */
@JYBatis
public interface UserBalanceDao  extends BaseDao<UserBalancePO>
{
    /**
     * 批量保存
     * @param list
     */
    int  save ( @Param("fileName") String fileName, @Param("sycDate")String date);
    /**
     * 删除全部数据
     */
    void deleteByAll(@Param("sycDate") String sycDate);
}

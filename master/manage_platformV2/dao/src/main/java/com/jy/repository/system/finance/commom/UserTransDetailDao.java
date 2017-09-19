package com.jy.repository.system.finance.commom;

import com.jy.entity.system.finance.common.UserTransDetail;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
@JYBatis
public interface UserTransDetailDao extends BaseDao<UserTransDetail>{


    /**
     *查询某条流水记录
     * @param payFlowNo,交易流水号
     * @param i ，类型
     * @return
     */
    UserTransDetail findObjByPayFlowNo(String payFlowNo, int i);

    /**
     * 某段时间内的交易流水记录
     * @param params
     * @return
     */
    List<UserTransDetail> findListByParam(Map<String, Object> params);

    /**
     * 批量保存
     * @param list
     */
    int  save (@Param("list") List<UserTransDetail> list);
    /**
     * 删除全部数据
     */
    void deleteByAll(@Param("tranDate") String tranDate);

    void deleteByDate(@Param("date") String tranDate);

    int save(@Param("fileName") String filePath, @Param("currentDate")Date currentDate,@Param("charset") String charset);
}

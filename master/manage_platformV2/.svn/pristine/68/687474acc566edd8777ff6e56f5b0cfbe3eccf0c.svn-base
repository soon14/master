package com.jy.repository.system.channels;

import com.jy.mybatis.Page;
import com.jy.entity.system.channels.Prepayment;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 渠道销量
 */
@JYBatis
public interface PrepaymentDao extends BaseDao<Prepayment> {


    public List<Prepayment> findByPage(Map map);

    public Integer counts(Map map);

    public List<Prepayment> findByPage(@Param("mid") List<Integer> mid,
                                       @Param("beginTimes") Date beginTimes,
                                       @Param("endTimes") Date endTimes,
                                       @Param("merchantName") String merchantName, Page page);

    /**
     * 查询一级商户信息
     *
     */
    public List<Prepayment> findMerchants();

    /**
     * 查询预存款商户
     *
     */
    public List<Prepayment> findMerchantPre();

    /**
     *  同步预收款信息
     *
     */
    public Integer synchroPrepayment(@Param("list")List<Prepayment> list);

    /**
     *  同步预收款信息.线上
     *
     */
    public Integer synchroOnline(@Param("list")List<Prepayment> list);

    /**
     * 修改商户的预收款的状态
     *
     * @param prepayment
     */
    public void updateBC(Prepayment prepayment);

    /**
     * 更新预付款信息
     *
     */
    public void updatePre(List<Prepayment> list);

}

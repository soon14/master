package com.jy.repository.system.channels;

import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.mybatis.Page;
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
public interface PrepaymentExtendDao extends BaseDao<PrepaymentExtend> {


    public List<PrepaymentExtend> findByPages(Map map);

    public List<PrepaymentExtend> findByPage(@Param("beginTimes") Date beginTimes,
                                       @Param("endTimes") Date endTimes,
                                       @Param("merchantName") String merchantName, Page page);
    public Integer counts(Map map);

    /**
     * 查询线下二级渠道预存款信息
     *
     */
    public List<PrepaymentExtend> findByMId(@Param("merchantId")String mId);

    /**
     * 预存款调配.通过危机渠道Id查询线下二级渠道
     *
     */
    public List<PrepaymentExtend> findPreE(PrepaymentExtend pre);

    /**
     * 预存款调配.通过一级渠道id查询线下二级渠道
     *
     */
    public List<PrepaymentExtend> findPreById(PrepaymentExtend pre);

    /**
     * 预存款调配.操作
     *
     */
    public void deploy(PrepaymentExtend pre);
    public void deploys(PrepaymentExtend pre);
    public void deployPre(PrepaymentExtend pre);
    public void deployPres(PrepaymentExtend pre);


}

package com.jy.service.system.channels;

import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 二级渠道线下预存款
 */
public interface PrepaymentExtendService extends BaseService<PrepaymentExtend> {


    /**
     * 查询
     *
     */
    public Page findByPage(Map map, Page page);

//    public List<PrepaymentExtend> findByPage(Map map);

    public Integer counts(Map map);

//    public List<PrepaymentExtend> findByPage(@Param("beginTimes") Date beginTimes,
//                                             @Param("endTimes") Date endTimes,
//                                             @Param("merchantName") String merchantName, Page page);

    /**
     * 查询线下二级渠道预存款信息
     *
     */
    Page<PrepaymentExtend> findByMId(String mId,Page page);

    /**
     * 预存款调配.查询线下二级渠道
     */
    List<PrepaymentExtend> findPreE(PrepaymentExtend o);

    /**
     * 预存款调配.操作
     *
     */
    public void deploy(PrepaymentExtend pre);
    public void deploys(PrepaymentExtend pre);
    public void deployPre(PrepaymentExtend pre);
    public void deployPres(PrepaymentExtend pre);

}

package com.jy.service.impl.system.channels;

import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.mybatis.Page;
import com.jy.repository.system.channels.BaseCommissionDao;
import com.jy.repository.system.channels.MerchantDao;
import com.jy.repository.system.channels.PrepaymentDao;
import com.jy.repository.system.channels.PrepaymentExtendDao;
import com.jy.repository.system.finance.statistics.lottery.SalesCommissionReportDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.PrepaymentExtendService;
import com.jy.service.system.channels.PrepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 渠道商户预收款
 */
@Service("PrepaymentExtendService")
public class PrepaymentExtendServiceImpl extends BaseServiceImp<PrepaymentExtend> implements PrepaymentExtendService {

    @Autowired
    private PrepaymentExtendDao prepaymentExtendDao;
    @Autowired
    private MerchantDao merchantDao;


    /**
     * 查询
     */
    public Page findByPage(Map map,Page page){
        List<PrepaymentExtend> list = prepaymentExtendDao.findByPages(map);
        page.setResults(list);
        return page;
    }

    /**
     * 统计
     *
     */
    public Integer counts(Map map){
        return prepaymentExtendDao.counts(map);
    };

    /**
     * 查询线下二级渠道预存款商户
     *
     */
    public Page<PrepaymentExtend> findByMId(String mId, Page page){
        List<PrepaymentExtend> byPage =prepaymentExtendDao.findByMId(mId);
        page.setResults(byPage);
        return page;
    }

    /**
     * 预存款调配.查询线下二级渠道
     */
    public List<PrepaymentExtend> findPreE(PrepaymentExtend o){
        return prepaymentExtendDao.findPreById(o);
    }

    /**
     * 预存款调配.操作
     *
     */
    public void deploy(PrepaymentExtend pre){
        prepaymentExtendDao.deploy(pre);
    }
    public void deploys(PrepaymentExtend pre){
        prepaymentExtendDao.deploys(pre);
    }
    public void deployPre(PrepaymentExtend pre){
        prepaymentExtendDao.deployPre(pre);
    }
    public void deployPres(PrepaymentExtend pre){
        prepaymentExtendDao.deployPres(pre);
    }
}

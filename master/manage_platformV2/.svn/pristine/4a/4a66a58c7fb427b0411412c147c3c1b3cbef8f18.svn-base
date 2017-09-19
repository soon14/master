package com.jy.service.impl.system.channels;

import com.jy.entity.system.channels.Payable;
import com.jy.mybatis.Page;
import com.jy.repository.system.channels.PayableDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.PayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 渠道商户应付款账单
 */
@Service("PayableService")
public class PayableServiceImpl extends BaseServiceImp<Payable> implements PayableService {

    @Autowired
    private PayableDao paybleDao;

    public Page<Payable> findByPage(Map map, Page page) throws Exception{
        page.setResults(paybleDao.findByPage(map));
        return page;
    }

    /**
     * 统计
     *
     */
    public Integer counts(Map map){
        return paybleDao.counts(map);
    }

    /**
     * 查找
     *
     */
    public Payable findById(String id){
        return paybleDao.findById(id);

    };
}

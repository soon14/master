package com.jy.service.impl.system.channels;

import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.repository.system.channels.CpOrderInfoDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.CpOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Matthew on 2017/4/26.
 */
@Service("cpOrderInfoService")
public class CpOrderInfoServiceImpl extends BaseServiceImp<CpOrderInfo> implements CpOrderInfoService {

    @Autowired
    private CpOrderInfoDao cpOrderInfoDao;

    @Override
    public int orderSave(String fileName,String date) {
        this.cpOrderInfoDao.orderDelete(date);
        return  this.cpOrderInfoDao.orderSave(fileName,date);
    }

    @Override
    public int traceSave(String fileName,String date) {
        this.cpOrderInfoDao.traceDelete(date);
        return  this.cpOrderInfoDao.traceSave(fileName,date);
    }

    @Override
    public void orderDelete(String date){
        cpOrderInfoDao.orderDelete(date);
    }

    @Override
    public void traceDelete(String date){
        cpOrderInfoDao.traceDelete(date);
    }

    @Override
    public int findByTypeAndTime(String date,String type){
        return cpOrderInfoDao.findByTypeAndTime(date,type);
    }
}

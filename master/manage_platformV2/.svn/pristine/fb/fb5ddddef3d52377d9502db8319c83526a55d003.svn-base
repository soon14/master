package com.jy.service.impl.system.channels;


import com.jy.entity.system.channels.SalesVolume;
import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.from.system.request.UserInfoForm;
import com.jy.mybatis.Page;
import com.jy.repository.system.channels.SalesVolumeDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.SalesVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 商户销量信息
 */
@Service("salesVolumeService")
public class SalesVolumeServiceImpl extends BaseServiceImp<SalesVolume> implements SalesVolumeService {

    @Autowired
    private SalesVolumeDao salesVolumeDao;


    @Override
    public Page<SalesVolume> findDetailByPage(SalesVolume o, Page<SalesVolume> page) {
        page.setResults(salesVolumeDao.findDetailByPage(o, page));
        return page;
    }

    @Override
    public String findLastOrderTime(String userId){
        return salesVolumeDao.findLastOrderTime(userId);
    }

    @Override
    public List<UserInfoForm> customerSales(Map map) {
        return salesVolumeDao.customerSales(map);
    }

    @Override
    public List<UserInfoForm> sumCustomerSales(Map map) {
        return salesVolumeDao.sumCustomerSales(map);
    }

    @Override
    public List<UserInfoForm> countCustomerSales(Map map) {
        return salesVolumeDao.countCustomerSales(map);
    }

    @Override
    public List<CpOrderInfo> findSaleList(Map map) {
        return salesVolumeDao.findSaleList(map);
    }

    @Override
    public List<CpOrderInfo> countSaleList(Map map) {
        return salesVolumeDao.countSaleList(map);
    }

    @Override
    public List<CpOrderInfo> sumSaleList(Map map) {
        return salesVolumeDao.sumSaleList(map);
    }

    @Override
    public List<UserInfoForm> countCustomer(Map map) {
        return salesVolumeDao.countCustomer(map);
    }


}

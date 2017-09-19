package com.jy.service.system.channels;

import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.from.system.request.UserInfoForm;
import com.jy.mybatis.Page;
import com.jy.entity.system.channels.SalesVolume;
import com.jy.service.base.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 商户销量信息
 */
public interface SalesVolumeService extends BaseService<SalesVolume>
{

    /**
     * 获得对象列表
     * @param o 对象
     * @param page 分页对象
     * @return List
     */
    public Page<SalesVolume> findDetailByPage(SalesVolume o, Page<SalesVolume> page);


    public String findLastOrderTime(String userId);
    public List<UserInfoForm> customerSales(Map map);
    public List<UserInfoForm> sumCustomerSales(Map map);
    public List<UserInfoForm> countCustomerSales(Map map);
    public List<CpOrderInfo> findSaleList(Map map);
    public List<CpOrderInfo> countSaleList(Map map);
    public List<CpOrderInfo> sumSaleList(Map map);
    public List<UserInfoForm> countCustomer(Map map);

}



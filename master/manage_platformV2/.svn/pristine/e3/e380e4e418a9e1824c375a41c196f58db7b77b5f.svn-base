package com.jy.service.system.channels;

import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.service.base.BaseService;

/**
 * Created by Matthew on 2017/4/26.
 */
public interface CpOrderInfoService extends BaseService<CpOrderInfo> {
    int orderSave(String fileName,String date);
    int traceSave(String fileName,String date);
    void orderDelete(String date);
    void traceDelete(String date);
    int findByTypeAndTime(String date,String type);
}

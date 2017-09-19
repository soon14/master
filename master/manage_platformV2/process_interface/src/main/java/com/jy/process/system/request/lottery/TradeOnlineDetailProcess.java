package com.jy.process.system.request.lottery;

import com.jy.from.system.request.RequestParamInfoForm;
import com.jy.from.system.request.TradeOnlineDetailForm;

import java.util.List;

/**
 * Created by Matthew on 2017/5/22.
 */
public interface TradeOnlineDetailProcess {

    void synchronizeData(String  date) throws Exception;

//    public Integer transforPojoSave(List<TradeOnlineDetailForm> listAll, String date);


}

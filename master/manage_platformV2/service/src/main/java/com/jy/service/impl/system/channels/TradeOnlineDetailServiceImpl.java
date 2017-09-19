package com.jy.service.impl.system.channels;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.entity.system.channels.TradeOnlineDetail;
import com.jy.from.system.request.TradeOnlineDetailForm;
import com.jy.repository.system.channels.TradeOnlineDetailDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.TradeOnlineDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Matthew on 2017/5/23.
 */
@Service("tradeOnlineDetailService")
public class TradeOnlineDetailServiceImpl extends BaseServiceImp<TradeOnlineDetail> implements TradeOnlineDetailService {

    @Autowired
    private TradeOnlineDetailDao tradeOnlineDetailDao;

//    @Override
//    public Integer transforPojoSave(List<TradeOnlineDetailForm> listAll, String date) {
//        List<TradeOnlineDetail> tradeOnlineDetails = new ArrayList<>();
//        for (TradeOnlineDetailForm tradeOnlineDetailForm : listAll) {
//            TradeOnlineDetail tradeOnlineDetail = new TradeOnlineDetail();
//            BeanUtils.copyProperties(tradeOnlineDetailForm, tradeOnlineDetail);
//            tradeOnlineDetail.setCreateTime(new Date());
//            tradeOnlineDetail.setUserName("system");
//            tradeOnlineDetails.add(tradeOnlineDetail);
//        }
//
//        int count =this.tradeOnlineDetailDao.save(tradeOnlineDetails);
//        return count ;
//    }

    @Override
    public void clean(String date){
        this.tradeOnlineDetailDao.clean(date);
    }

    @Override
    public int save(String filePath, String currentDate){
        String charset = byValue("charset");
        tradeOnlineDetailDao.clean(currentDate);
        return tradeOnlineDetailDao.save(filePath, DateUtils.stringToDate(currentDate, "yyyyMMdd"), charset != null ? charset : "utf8mb4");
    };
}

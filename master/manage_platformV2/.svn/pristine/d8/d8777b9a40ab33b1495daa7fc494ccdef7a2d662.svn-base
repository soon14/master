package com.jy.common.CpSystem;

import com.jy.common.utils.DateUtils;
import com.jy.entity.system.finance.statistics.QrCodeDailyReport;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public class QrCodeConst {

    /**
     *获得某日date所属于的兑换券批次
     * @param dataList
     * @param date
     * @return
     */
    public static QrCodeDailyReport getNowBatch(List<QrCodeDailyReport> dataList, String date) {

        if(dataList!=null){
            for(int i=0;i<dataList.size();i++){
                QrCodeDailyReport r=dataList.get(i);
                r.setBatchId(i+1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String endTime=sdf.format(r.getBatchEndTime());
                String startTime=sdf.format(r.getBatchStartTime());
                long numStart= DateUtils.getDaySub(date,startTime);
                long numEnd=DateUtils.getDaySub(date,endTime);
                if(numStart<=0&&numEnd>=0){
                    return r;
                }
            }

        }
        return null;
    }
}
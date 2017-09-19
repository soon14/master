package com.jy.process.impl.system.request.lottery;

import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.process.impl.system.request.base.ApiRequestTxtProcessImpl;
import com.jy.process.system.request.lottery.TradeOnlineDetailProcess;
import com.jy.service.system.channels.TradeOnlineDetailService;
import com.jy.service.system.finance.statistics.jobTask.JobTaskStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Matthew on 2017/5/22.
 */
@Service("tradeOnlineDetailProcess")
public class TradeOnlineDetailProcessImpl extends ApiRequestTxtProcessImpl implements TradeOnlineDetailProcess {


    @Autowired
    protected TradeOnlineDetailService tradeOnlineDetailService;
    @Autowired
    private JobTaskStatisticsService jobTaskService;
    @Value("${tradeDetail.path}")
    private  String path;

    private static final Logger LOG = LoggerFactory.getLogger(OrderInfoRequestProcessImpl.class);

    @Override
    public String byJobName() {
        return "线上交易明细数据接口";
    }

    @Override
    public void saveJobLog(JobTaskStatistics jobTaskStatistics) {
        try {
            this.jobTaskService.insertJobTaskStatistics(jobTaskStatistics);
        } catch (Exception e) {
            LOG.info("线上交易明细数据接口保存LOG数据失败！");
            e.printStackTrace();
        }
    }

    @Override
    public int saveFTPData(String filePath, String date) {
        int count = 0;
        try {
            count = this.tradeOnlineDetailService.save(filePath, date);
            LOG.info("入库行数：{}", count);
        } catch (Exception e) {
            LOG.info("线上交易明细数据接口保存数据失败！");
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void synchronizeData(String date) throws Exception {
        super.ftpCommonsFunction(path, date);
    }



//    public Integer transforPojoSave(List<TradeOnlineDetailForm> listAll, String date) {
//        /**统计入库条数*/
//        int count = 0;
//        final int pageSize = 500;
//        if (listAll.size() > pageSize) {
//            int limit = pageSize;
//            int size = listAll.size() / limit;
//            for (int i = 0; i < size; i++) {
//                List<TradeOnlineDetailForm> listPage = listAll.subList(0, limit - 1);
//                int countPage = this.tradeOnlineDetailService.transforPojoSave(listPage, date);
//                count += countPage;
//                listAll.subList(0, limit - 1).clear();
//                if (listAll.size() < pageSize) {
//                    int countX = this.tradeOnlineDetailService.transforPojoSave(listAll, date);
//                    count += countX;
//                }
//            }
//        } else {
//            count = this.tradeOnlineDetailService.transforPojoSave(listAll, date);
//        }
//        return count;
//    }

}
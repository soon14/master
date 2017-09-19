package com.jy.service.impl.system.finance.reconciliation.lottery;
import com.jy.repository.system.finance.reconciliation.lottery.TaskPrizeDao;
import com.jy.service.system.finance.reconciliation.lottery.TaskPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZQY on 2017/4/26.
 */
@Service
public class TaskPrizeServiceImpl implements TaskPrizeService {
    @Autowired
    private TaskPrizeDao taskPrizeDao;

    @Override
    public int saveTaskPrizeInfoList(String fileName, String date) {
        taskPrizeDao.deleteByAll(date);
        return taskPrizeDao.save(fileName,date);
    }

    @Override
    public void deletePrizeInfo(String date) {
        taskPrizeDao.deleteByAll(date);
    }
}

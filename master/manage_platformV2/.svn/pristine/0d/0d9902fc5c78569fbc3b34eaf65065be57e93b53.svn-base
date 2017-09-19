package com.jy.service.impl.system.finance.reconciliation.lottery;
import com.jy.repository.system.finance.reconciliation.lottery.TaskWithdrawDao;
import com.jy.service.system.finance.reconciliation.lottery.TaskWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZQY on 2017/4/26.
 */
@Service
public class TaskWithdrawServiceImpl implements TaskWithdrawService {
    @Autowired
    private TaskWithdrawDao taskWithdrawDao;
    @Override
    public int saveTaskWithdrawInfoList(String fileName,String date) throws Exception {
        taskWithdrawDao.deleteWithDrawInfo(date);
       return taskWithdrawDao.saveTaskWithdrawInfoList(fileName,date);
    }

    @Override
    public void deleteWithDrawInfo(String date) {
        taskWithdrawDao.deleteWithDrawInfo(date);
    }
}

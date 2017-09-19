package com.jy.service.impl.system.finance.common;

import com.jy.repository.system.finance.commom.UserScoreDao;
import com.jy.service.system.finance.common.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shixi on 2017/8/02.
 */
@Service("UserScoreService")
public class UserScoreServiceImpl  implements UserScoreService {

    @Autowired
    private UserScoreDao UserScoreDao;

    @Override
    public int saveBalanceTxT(String fileName, String date) {
        this.UserScoreDao.deleteBalanceBySycDate(date);
        return this.UserScoreDao.saveBalanceTxT(fileName);
    }

    @Override
    public void deleteBalanceBySycDate(String date) {
        this.UserScoreDao.deleteBalanceBySycDate(date);
    }

    @Override
    public int saveTransTxT(String fileName, String date) {
        this.UserScoreDao.deleteTransBySycDate(date);
        return this.UserScoreDao.saveTransTxT(fileName, date);
    }

    @Override
    public void deleteTransBySycDate(String date) {
        this.UserScoreDao.deleteTransBySycDate(date);
    }
}

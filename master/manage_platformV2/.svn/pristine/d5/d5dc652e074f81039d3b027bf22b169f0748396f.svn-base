package com.jy.service.impl.system.finance.common;

import com.jy.entity.system.finance.common.UserBalancePO;
import com.jy.repository.system.finance.commom.UserBalanceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.common.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shixi on 2017/4/27.
 */
@Service("UserBalanceService")
public class UserBalanceServiceImpl extends BaseServiceImp<UserBalancePO> implements UserBalanceService {

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Override
    public int userBalanceforPojoSave(String fileName, String date) throws Exception {
        this.userBalanceDao.deleteByAll(date);
        return this.userBalanceDao.save(fileName, date);
    }

    @Override
    public void deleteByAll(String date) throws Exception {
        this.userBalanceDao.deleteByAll(date);
    }
}

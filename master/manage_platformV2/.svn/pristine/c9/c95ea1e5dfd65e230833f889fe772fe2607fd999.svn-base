package com.jy.service.impl.system.channels;

import java.util.List;
import java.util.Map;

import com.jy.entity.system.account.Account;
import com.jy.service.impl.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.mybatis.Page;
import com.jy.entity.system.channels.BaseCommission;
import com.jy.entity.system.channels.Merchant;
import com.jy.repository.system.channels.BaseCommissionDao;
import com.jy.service.system.channels.BaseCommissionService;


/**
 * 娓犻亾浣ｉ噾閰嶇疆
 */
@Service("BaseCommissionService")
public class BaseCommissionServiceImpl extends BaseServiceImp<BaseCommission> implements BaseCommissionService {

    @Autowired
    private BaseCommissionDao commissionDao;


    /**
     * 淇敼鍟嗘埛鐨勪剑閲戝垎閰嶇殑鐘舵��
     *
     * @param commission
     */
    public void deleteBaseCommission(BaseCommission commission) {
        commissionDao.deleteBaseCommission(commission);
    };

    public void updateBaseCommission(BaseCommission commission) {
        commissionDao.updateBaseCommission(commission);
    };

    public void insertBaseCommission(BaseCommission commission) {
        commissionDao.insertBaseCommission(commission);
    };

    @Override
    public List<BaseCommission> findCommissionByMerchant(Merchant merchant){
        return commissionDao.findListByMerchant(merchant);
    }
    public Page findByPages(BaseCommission o) {
        List list = commissionDao.findByPage(o, null);
        Page page = new Page();
        page.setResults(list);
        return page;
    }

    @Override
    public Page<BaseCommission> findByUserName(Page<BaseCommission> page,Map m) {
        List<BaseCommission> list = commissionDao.findByUserName(m);
        page.setResults(list);
        page.setTotalRecord(list.size());
        return page;
    }

    @Override
    public List<Account> findUserName(Map o) {
        return commissionDao.findUserName(o);
    }

    @Override
    public Page<BaseCommission> findAllCommission(Page<BaseCommission> page, Map m) {
        List<BaseCommission> list = commissionDao.findAllCommission(m);
        page.setResults(list);
        page.setTotalRecord(list.size());
        return page;
    }

    @Override
    public List<BaseCommission> findById(Map m) {
        return commissionDao.findById(m);
    }

}

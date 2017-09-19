package com.jy.service.impl.system.channels;


import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.OperationRecords;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.repository.system.channels.AuditDao;
import com.jy.repository.system.channels.PrepaymentDao;
import com.jy.repository.system.channels.PrepaymentExtendDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 渠道商户
 */
@Service("AuditService")
public class AuditServiceImpl extends BaseServiceImp<Object> implements AuditService {
    @Autowired
    private AuditDao auditDao;

    public Integer updateMerchant(Merchant merchant){
        OperationRecords records=new OperationRecords();
        Integer res=0;
        res=auditDao.updateMerchant(merchant);
        if(res==1){
            //记录操作
            String userId= AccountShiroUtil.getCurrentUser().getAccountId();
            records.setaReviewer(userId);
            records.setaUpdateDate(new Date());
            if(merchant.getmStatus()==1){
                records.setaOperation("审核商户(一审) 通过");
            }else if(merchant.getmStatus()==2){
                records.setaOperation("审核商户(二审) 通过");
            }else if(merchant.getmStatus()==3){
                records.setaOperation("审核商户 拒绝");
            }
            records.setAbeOperation(merchant.getmId().toString());
        }
        return res;
    }


    public Merchant findMerchantId(Merchant merchant){
        return auditDao.findMerchantId(merchant);
    }

    public Integer updateUserAccount(Merchant merchant){
        return auditDao.updateUserAccount(merchant);
    }

    @Override
    public List<Merchant> findOneExamineMerchant(Map o) {
        return auditDao.findOneExamineMerchant(o);
    }
}



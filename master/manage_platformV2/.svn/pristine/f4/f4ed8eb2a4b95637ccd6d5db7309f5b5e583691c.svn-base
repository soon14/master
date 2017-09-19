package com.jy.repository.system.channels;

import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.from.system.request.OutLineDataForm;
import com.jy.repository.base.JYBatis;

import java.util.List;
import java.util.Map;

/**
 * Created by ZQY on 2017/5/17.
 */
@JYBatis
public interface OutLineDataDao {
    public void addOutLineData(OutLineDataForm outLineDataForm) throws Exception;
    public void addDeposit(Merchant merchant) throws Exception;
    public void delDeposit(Map map) throws Exception;
    public void updateDeposit(Merchant merchant) throws Exception;
    public List findOutLineData(Map map) throws Exception;
    public List findDeposit(Map map) throws Exception;
    public List findOutLineDataTotal(Map map) throws Exception;
    public List findDepositTotal(Map map) throws Exception;
    public void updatePreDepositBalance(Map map) throws Exception;
    public void updatePreDepositBalanceEj(Map map) throws Exception;
    public Prepayment findPrePaymentInfo(Map map) throws Exception;
    public PrepaymentExtend findPrePaymentExtend(Map map) throws Exception;
    public List<Merchant> findMerchant() throws  Exception;

}

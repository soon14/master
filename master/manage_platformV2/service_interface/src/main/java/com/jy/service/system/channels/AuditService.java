package com.jy.service.system.channels;


import com.jy.entity.system.channels.Merchant;
import com.jy.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 渠道商户
 */
public interface AuditService extends BaseService<Object>
{
    /**
     * 审核商户
     * @param merchant  商户信息
     * @return
     */
    public Integer updateMerchant(Merchant merchant);

    /**
     * 通过渠道ID查询渠道商户手机号
     * @param merchant
     * @return
     */
    public Merchant findMerchantId(Merchant merchant);

    /**
     * 修改
     * @param merchant
     * @return
     */
    public Integer updateUserAccount(Merchant merchant);


    public List<Merchant> findOneExamineMerchant(Map o);

}
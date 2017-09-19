package com.jy.repository.system.channels;


import com.jy.entity.system.channels.Merchant;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;

import java.util.List;
import java.util.Map;

/**
 * 渠道
 */
@JYBatis
public interface AuditDao extends BaseDao<Object> {
    /**
     * 审核商户(一审)
     * @param merchant  商户信息
     * @return
     */
    public Integer updateMerchant(Merchant merchant);

    /**
     * 修改
     * @param merchant
     * @return
     */
    public Integer updateUserAccount(Merchant merchant);


    /**
     * 通过渠道ID查询渠道商户手机号
     * @param merchant
     * @return
     */
    public Merchant findMerchantId(Merchant merchant);

    public List<Merchant> findOneExamineMerchant(Map o);


}

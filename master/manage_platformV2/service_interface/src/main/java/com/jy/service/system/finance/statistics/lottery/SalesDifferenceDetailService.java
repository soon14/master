package com.jy.service.system.finance.statistics.lottery;

import com.jy.mybatis.Page;
import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.service.base.BaseService;
import com.jy.vo.system.reconciliation.lottery.SalesDifferencesDetailVo;

/**
 * Created by Administrator on 2017/2/13.
 */
public interface SalesDifferenceDetailService extends BaseService<SalesDifferencesDetail>
{


    Page<SalesDifferencesDetailVo> findSalesDifferencesDetailByPage(SalesDifferencesDetail form, Page<SalesDifferencesDetailVo> page);
}

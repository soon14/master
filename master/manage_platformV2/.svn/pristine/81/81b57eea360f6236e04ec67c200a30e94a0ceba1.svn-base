package com.jy.repository.system.finance.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.mybatis.Page;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lijunke on 2017/5/24.
 */
@JYBatis
public interface CashPrizeDetailDifferenceDao extends BaseDao<CashDiffDetail> {

    /**
     * 保存差异结果
     * @param list
    */
    void saveCashDiffDetail(List<CashDiffDetail> list);

    /**
     * 分页查询数据
     * @param date
     * @param page
     * @return
     */
    List<CashDiffDetail> findAllDetail(@Param("beginDate") Date date, Page<CashDiffDetail> page);
}

package com.jy.process.system.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashPrizeDifference;
import com.jy.mybatis.Page;

import java.util.Date;

/**
 * Created by lijunke on 2017/5/18.
 * 兑奖服务
 */
public interface CashPrizeProcess {

    /**
     * 根据日期区间查询数据
     *
     * @param startDate
     * @param endDate
     * @param page
     * @return
     */
    Page<CashPrizeDifference> findAll(Date startDate, Date endDate, Page<CashPrizeDifference> page);


    /**
     * 根据日期查询差异明细页面
     * @param startDate
     * @param page
     * @return
     */
    Page<CashDiffDetail> findAllDetail(Date startDate, Page<CashDiffDetail> page);


    /**
     * 生成兑奖数据,统计差异额 保存到兑奖差异表
     * <p>
     * 重置接口
     * 跑批接口
     */
    void generateDataAndMergerData(Date startDate, Date endDate);



}

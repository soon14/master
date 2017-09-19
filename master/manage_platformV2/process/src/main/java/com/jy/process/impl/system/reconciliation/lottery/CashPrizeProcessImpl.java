package com.jy.process.impl.system.reconciliation.lottery;

import com.jy.common.exception.DaysBaseException;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashPrizeDifference;
import com.jy.mybatis.Page;
import com.jy.process.system.reconciliation.lottery.CashPrizeProcess;
import com.jy.service.system.finance.reconciliation.lottery.CashPrizeDetailDiffService;
import com.jy.service.system.finance.reconciliation.lottery.CashPrizeDifferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lijunke on 2017/5/18.
 * 兑奖服务
 */
@Service("cashPrizeProcess")
public class CashPrizeProcessImpl implements CashPrizeProcess {

    @Autowired
    private CashPrizeDifferenceService service;

    @Autowired
    private CashPrizeDetailDiffService cashDetailService;


    /**
     * 查询兑奖汇总数据，仅仅只是查询展示页面
     *
     * @return
     */
    @Override
    public Page<CashPrizeDifference> findAll(Date startDate, Date endDate, Page<CashPrizeDifference> page) {
        CashPrizeDifference instance = new CashPrizeDifference();
        instance.setStartDate(startDate);
        instance.setEndDate(endDate);
        Page<CashPrizeDifference> list = this.service.findByPage(instance, page);
        return list;
    }


    /**
     * 查询兑奖明细差异数据，仅仅只是查询展示页面
     * @param startDate
     * @param page
     * @return
     */
    @Override
    public Page<CashDiffDetail> findAllDetail(Date startDate, Page<CashDiffDetail> page) {
        Page<CashDiffDetail> list = this.cashDetailService.findAllDetail(startDate, page);
        return list;
    }


    /**
     * 汇集数据，对比差异
     *
     * @param startDate
     * @param endDate
     */
    @Override
    public void generateDataAndMergerData(Date startDate, Date endDate) {
        try {
            this.service.collectionData(startDate, endDate);
        } catch (DaysBaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收大额兑奖数据,并保存
     */
    public void byBigPrize(int id, BigDecimal bigPrize) {
        this.service.updateBigPrize(id, bigPrize);
    }


}

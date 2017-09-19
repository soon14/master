package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashPrizeDifference;
import com.jy.repository.system.finance.reconciliation.lottery.CashPrizeDetailDifferenceDao;
import com.jy.repository.system.finance.reconciliation.lottery.CashPrizeDifferenceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.CashPrizeDifferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lijunke on 2017/5/18.
 * 兑奖服务接口
 */
@Service("cashPrizeDifferenceService")
public class CashPrizeDifferenceServiceImpl extends BaseServiceImp<CashPrizeDifference> implements CashPrizeDifferenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CashPrizeDifferenceServiceImpl.class);

    @Autowired
    private CashPrizeDifferenceDao dao;

    @Autowired
    private CashPrizeDetailDifferenceDao cashDetailDiffDao;

    /**
     * 汇集数据、保入库
     *
     * @param startDate
     * @param endDate
     */
    @Override
    public void collectionData(Date startDate, Date endDate) {
        List<Date> dataList = DateUtils.findDates(startDate, endDate);
        for (Date dataTo : dataList) {
            CashPrizeDifference cashPrize = difference(dataTo, dataTo);
            try {
                this.cashObjectExistsDelete(cashPrize);
                this.dao.insert(cashPrize);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("save cash prize difference confluence fail！", e.getCause());
            }
            this.generateDiffDetailDate(cashPrize, dataTo, cashPrize.getId());
        }
    }


    /**
     * 线上兑奖总额
     * 根据日期区间获取第三方派奖总额
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public BigDecimal byTSTotalAumone(Date startDate, Date endDate) {
        CashPrizeDifference cashPrizeDifference = this.dao.byTSTotalAumone(DateUtils.getDateStart(startDate), DateUtils.getDateEnd(endDate));
        if (null != cashPrizeDifference) {
            return cashPrizeDifference.getOnlineCashPrize();
        }
        return new BigDecimal(0);
    }

    /**
     * 线上派奖总额
     * 根据日期区间获取投注派奖总额
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public BigDecimal byDCTotalAumone(Date startDate, Date endDate) {
        CashPrizeDifference cashPrizeDifference = this.dao.byDCTotalAumone(DateUtils.getDateStart(startDate), DateUtils.getDateEnd(endDate));
        if (null != cashPrizeDifference) {
            return cashPrizeDifference.getOnlineSentPrize();
        }
        return new BigDecimal(0);
    }

    /**
     * 返回 线上兑奖总额 、线上派奖总额、差异额、总兑奖金额
     * （差异额在这里并没有 + 大额兑奖）
     * （总兑奖金额在这里并没有 + 大额兑奖）
     * (兑奖差异在这里并没有 - 线下兑奖总额)
     */
    public CashPrizeDifference difference(Date startDate, Date endDate) {
        CashPrizeDifference cashPrizeDifference = new CashPrizeDifference();
        BigDecimal cashTotal = this.byTSTotalAumone(DateUtils.getDateStart(startDate), DateUtils.getDateEnd(endDate));
        BigDecimal sendTotal = this.byDCTotalAumone(DateUtils.getDateStart(startDate), DateUtils.getDateEnd(endDate));
        BigDecimal diff = cashTotal.subtract(sendTotal);
        cashPrizeDifference.setOnlineCashPrize(cashTotal);
        cashPrizeDifference.setOnlineSentPrize(sendTotal);
        //日期
        cashPrizeDifference.setTallyDate(DateUtils.formatDate(startDate, "yyyy-MM-dd"));
        //线上兑奖和派奖的差异
        cashPrizeDifference.setCashSendDiff(diff);
        //总兑奖金额
        BigDecimal totalMoney = cashTotal.add(sendTotal);
        cashPrizeDifference.setTotalPrize(totalMoney);
        //兑奖差异
        cashPrizeDifference.setTotalDiffPrize(totalMoney.subtract(cashTotal));
        //期初、期末差异额
        if (DateUtils.format(startDate, "yyyy-MM-dd").equals("2016-11-01")) {
            cashPrizeDifference.setFirstDiffPrize(new BigDecimal(0));
            cashPrizeDifference.setLastDiffPrize(diff);
        } else {
            //根据当前日期，取得昨天的期末
            BigDecimal yesterDayMoney = this.byYesterdayAumone(startDate);
            //今天的期初=昨天的期末
            cashPrizeDifference.setFirstDiffPrize(yesterDayMoney);
            //今天的期末=昨天的期末+今天的差异；
            cashPrizeDifference.setLastDiffPrize(yesterDayMoney.add(diff));
        }
        cashPrizeDifference.setIsValid(1);//有效
        cashPrizeDifference.setCreateDate(new Date());
        cashPrizeDifference.setUpdateDate(new Date());
        return cashPrizeDifference;
    }

    /**
     * 根据日期查询昨天的期末差异额
     *
     * @param date
     * @return
     */
    public BigDecimal byYesterdayAumone(Date date) {
        String yesterDay = DateUtils.getPreDateByDay(date, "-1");
        BigDecimal lastMoney = this.dao.queryYesterdayLastAumone(yesterDay);
        if (null != lastMoney) {
            return lastMoney;
        } else {
            return new BigDecimal(0);
        }
    }


    /**
     * 生成差异明细数据并保存
     *
     * @param cashPrize
     * @param dataTo
     * @param cashid
     */
    public void generateDiffDetailDate(CashPrizeDifference cashPrize, Date dataTo, int cashid) {
        if (cashPrize.getCashSendDiff() != new BigDecimal(0)) {
            //查询单边账或批次相同金额差异的数据
            LOGGER.info("start query cash prize difference detailed data..");
            List<CashDiffDetail> list = this.dao.queryDiffDetail(DateUtils.getDateStart(dataTo), DateUtils.getDateEnd(dataTo));
            if (list.size() > 0) {
                for (CashDiffDetail cashDiffDetail : list) {
                    cashDiffDetail.setDfReportId(cashid);
                    cashDiffDetail.setNormalDate(dataTo);
                    cashDiffDetail.setDfInitDate(new Date());
                    this.detailObjectExistsDelete(cashDiffDetail);
                }
                try {
                    this.cashDetailDiffDao.saveCashDiffDetail(list);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("save detailed difference fail！", e.getCause());
                }
            }
        }
    }


    /**
     * 兑奖总汇对象存在则删除
     *
     * @param cashPrizeDifference
     */
    private void cashObjectExistsDelete(CashPrizeDifference cashPrizeDifference) {
        int exists = this.dao.count(cashPrizeDifference);
        if (exists > 0) {
            try {
                this.dao.delete(cashPrizeDifference);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("delete cash prize difference count table fail！", e.getCause());
            }
        }
    }

    /**
     * 差异明细对象存在的则删除
     *
     * @param cashDiffDetail
     */
    private void detailObjectExistsDelete(CashDiffDetail cashDiffDetail) {
        // List<Integer> isExists = new ArrayList<>();
        int exists = this.cashDetailDiffDao.count(cashDiffDetail);
        // isExists.add(exists);
        if (exists > 0) {
            try {
                this.cashDetailDiffDao.delete(cashDiffDetail);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("delete detailed difference fail！", e.getCause());
            }
        }
    }


    /**
     * @param id
     * @param bigPrize
     */
    @Override
    public void updateBigPrize(int id, BigDecimal bigPrize) {

    }

}

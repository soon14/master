package com.jy.process.system.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.mybatis.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Xinpeng on 2017/4/19.
 */
public interface CashDifferenceProcess {

    /**
     * 得到兑奖差异数据
     * @param date
     * @param page
     * @return
     */
   public Page<CashDifference> cashDifferenceDate(String date, Page<CashDifference> page);

    /**
     * 得到兑奖差异明细数据
     * @param date
     * @param page
     * @return
     */
    public Page<CashDiffDetail> findCashDiffDetails(String date, Page<CashDiffDetail> page);

    /**
     * 得到该用户方案扩展ID的兑奖明细
     * @param schemeExtendId
     * @param page
     * @return
     */
    public Page<CashDiffDetail> findschemeExtendIdDetails(String schemeExtendId,Page<CashDiffDetail> page);

    /**
     * 重置数据
     * @param beginTime
     * @param endTime
     * @param page
     */
    public void restTask(Date beginTime, Date endTime, Page<CashDifference> page);

    /**
     * 得到路径
     * @return
     */
    public String findFilePath();

    /**
     * 录入线下兑奖数据
     * @param cs
     */
    void updateCountAumone(CashDifference cs);

    /**
     * 下载总报表
     * @param s
     * @return
     */
 List<Object> download(String s);

    /**
     * 下载明细报表
     * @param date
     * @return
     */
 List<Object> downloadDetail(String date);

 /**
  * 查询该日期的兑奖差异数据
  * @param id
  * @return
     */
 CashDifference findOfflineRedeemAmount(String id);

}


package com.jy.process.impl.system.reconciliation.lottery;

import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.mybatis.Page;
import com.jy.process.system.reconciliation.lottery.CashDifferenceProcess;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.reconciliation.lottery.CashDifferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Xinpeng on 2017/4/19.
 */
@Service("cashDifferenceProcess")
public class CashDifferenceProcessImpl implements CashDifferenceProcess {
    @Autowired
    public CashDifferenceService cashDifferenceService;

    @Autowired
    public SysDictService sysDictService;
//    @Autowired
//    public PropertiesServiceImpl propertiesServiceImpl;
//
//    public String cashTotalFileName;
//    public String suffix;
//    public String cashDetailFileName;
//
//    public String getCashTotalFileName() {
//        return propertiesServiceImpl.cashTotalFileName;
//    }
//
//
//    public String getSuffix() {
//        return propertiesServiceImpl.suffix;
//    }
//
//
//    public String getCashDetailFileName() {
//        return propertiesServiceImpl.cashDetailFileName;
//    }


    @Override
    public Page<CashDifference> cashDifferenceDate(String date, Page<CashDifference> page) {

        return cashDifferenceService.cashDifferenceDate(date,page);
    }

    @Override
    public Page<CashDiffDetail> findCashDiffDetails(String date, Page<CashDiffDetail> page) {
        return cashDifferenceService.findCashDiffDetails(date,page);
    }



    @Override
    public Page<CashDiffDetail> findschemeExtendIdDetails(String schemeExtendId,Page<CashDiffDetail> page) {
        return cashDifferenceService.findschemeExtendIdDetails(schemeExtendId,page);
    }

    @Override
    public void restTask(Date beginTime, Date endTime, Page<CashDifference> page) {
        cashDifferenceService.restTask(beginTime,endTime,page);
    }

    @Override
    public String findFilePath() {
        SysDict _sysDict = sysDictService.findFilePath("filePath");
        String filePath = _sysDict.getParamValue();
        return filePath;
    }

    @Override
    public void updateCountAumone(CashDifference cs) {
        cashDifferenceService.updateCountAumone(cs);
    }

    @Override
    public List<Object> download(String s) {

        return cashDifferenceService.download(s);
    }

    @Override
    public List<Object> downloadDetail(String date) {
        return cashDifferenceService.downloadDetail(date);
    }

    @Override
    public CashDifference findOfflineRedeemAmount(String id) {
        return cashDifferenceService.findOfflineRedeemAmount(id);
    }
}

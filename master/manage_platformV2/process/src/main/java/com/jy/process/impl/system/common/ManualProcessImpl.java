package com.jy.process.impl.system.common;

import com.jy.common.exception.DaysBaseException;
import com.jy.common.utils.DateUtils;
import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicket;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.process.system.common.ManualProcess;
import com.jy.service.system.finance.reconciliation.game.DiamondBalanceService;
import com.jy.service.system.finance.reconciliation.game.GoldBalanceService;
import com.jy.service.system.finance.reconciliation.game.ToolsBalanceService;
import com.jy.service.system.finance.reconciliation.lottery.LotteryBuyAndTicketService;
import com.jy.service.system.finance.reconciliation.lottery.PlatformFundsService;
import com.jy.service.system.finance.reconciliation.lottery.TranceFundsService;
import com.jy.service.system.finance.statistics.lottery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service("ManualProcess")
public class ManualProcessImpl implements ManualProcess {
    @Autowired
    private PlatformFundsService fundsService;
    @Autowired
    private QrCodeSoldDailyReportService soldservice;
    @Autowired
    private QrCodeDailyReportService dailyService;
    @Autowired
    private QrCodeDetailDailyReportService detailService;
    @Autowired
    private GoldBalanceService goldService;
    @Autowired
    private DiamondBalanceService diamondService;
    @Autowired
    private ToolsBalanceService toolsService;
    @Autowired
    private TranceFundsService  tranceService;
    @Autowired
    private LotteryWaySaleReportService  lotteryWaySaleReportService;
    @Autowired
    private LotteryBuyAndTicketService lotteryBuyAndTicketService;
    @Autowired
    private LotterySalesDiffReportService lotterySalesDiffReportService;


    @Override
    public void manualByType(BaseForm form, int i) throws Exception{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = form.getBeginTime();
            String endDate = form.getEndTime();
            Date dBegin = sdf.parse(startDate);
            Date dEnd = sdf.parse(endDate);
            List<Date> sectionDate = DateUtils.findDates(dBegin, dEnd);
            for (Date date : sectionDate) {
                String thisDate= sdf.format(date);
                _doByType(i,thisDate);

            }
            String month= sdf.format(dBegin).substring(0,7);
            String end=sdf.format(dEnd);
            _generateReportByType(i,end,month);


    }

    private void _generateReportByType(int type, String end, String month) {
//FIXME:
//        diamondService.generateDiamondBalanceReport("manual",month);

        if(type==1){
            fundsService.generateRunningReport("manual",month);
        }else if(type==2){
            fundsService.GenerateFundsReport(month);
        }else if(type==3){
            soldservice.generateSoldReport("manual",month);
        } else if (type==4){
            dailyService.generateReport("manual",month);
        }else if (type==5){
            detailService.generateDetailReport("manual",month);
        }else if (type==6){
            goldService.generateGoldBalanceReport("manual",month);
        }else if (type==7){
            diamondService.generateDiamondBalanceReport("manual",month);
        }else if (type==8){
            toolsService.generateToolsBalanceReport("manual",end);
        }else if (type==9){
            tranceService.generateReport(month);
        }else if (type==10){
            lotteryWaySaleReportService.generateReport(month);
        }else if (type==11){
            lotteryWaySaleReportService.generateOrderTicketReport(month);
        }else if (type==12){
            lotteryBuyAndTicketService.generateReport(month);
        }else if (type==13){
            lotterySalesDiffReportService.generateReport(month);
        }


    }

    private void _doByType(int type, String thisDate)  throws DaysBaseException {
        if(type==1){
            fundsService.execThirdpayTask(thisDate);
        }else if(type==2){
            fundsService.execPlatFormFundsTask( thisDate);
        }
        else if (type==3){
            soldservice.insertSoldReport("manual", thisDate);
        }
        else if (type==4){
            dailyService.insertReport("manual", thisDate);
        }
        else if (type==5){
            detailService.insertDetailReport("manual", thisDate);
        }else if (type==6){
            goldService.execGoldBalanceTask("manual", thisDate);
        }else if (type==7){
            diamondService.execDiamondBalanceTask("manual", thisDate);
        }else if (type==8){
           toolsService.execToolsBalanceTask("manual", thisDate);
        }else if (type==9){
            tranceService.execTask(thisDate);
        }else if (type==10){
            lotteryWaySaleReportService.execLotteryTask(thisDate);
        }else if (type==11){
            lotteryWaySaleReportService.execOrderTicketTask(thisDate);
        }else if (type==12){
            lotteryBuyAndTicketService.execTask(thisDate);
        }else if (type==13){
            lotterySalesDiffReportService.execTask(thisDate);
        }
    }
}

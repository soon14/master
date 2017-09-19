package com.jy.service.impl.system.finance.statistics.lottery;

import com.jy.common.CpSystem.QrCodeConst;
import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.ChannelsUtils;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.excel.ExcelReport;
import com.jy.entity.system.finance.statistics.QrCodeDailyReport;
import com.jy.entity.system.finance.statistics.QrCodeDetailDailyReport;

import com.jy.repository.system.finance.statistics.lottery.QrCodeDailyReportDao;
import com.jy.repository.system.finance.statistics.lottery.QrCodeDetailDailyReportDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.impl.help.ExcelUtil;
import com.jy.service.system.finance.statistics.lottery.QrCodeDetailDailyReportService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */
@Service("QrCodeDetailDailyReportService")
public class QrCodeDetailDailyReportServiceImpl extends BaseServiceImp<QrCodeDetailDailyReport> implements QrCodeDetailDailyReportService {

    @Autowired
    private QrCodeDailyReportDao dao;
    @Autowired
    private QrCodeDetailDailyReportDao detailDao;
    @Value("${download.path}")
    private String filePath;

    @Value("${download.qrcodeDetailDailyReport.filename}")
    private String qrcodeDetailDailyReportFileName;
    @Autowired
    private ExcelUtil excelutil;

    @Override
    public void  insertDetailReport(String auto, String date){
        QrCodeDetailDailyReport report=new QrCodeDetailDailyReport();
        report.setDate(date);
        _setQrCodeInfo(report, date);
        if (!ChannelsUtils.IsNull(report.getBatchId())) {
            _installQcQrCodeInfo(date,report);
            _installSoldQrcodeInfo(report, date);
            _setEndQrcodeInfo(report);
            _checkAndSave(report, date, auto);
        }
    }

    private QrCodeDetailDailyReport _findQcQrCodeInfo(String date,QrCodeDetailDailyReport report) {
        QrCodeDetailDailyReport all=detailDao.findQcReport(date,report.getBatchStartTime(),report.getBatchEndTime());
        if(all==null){
            all=new QrCodeDetailDailyReport();
        }
        return all;
    }

    private void _installQcQrCodeInfo( String date,QrCodeDetailDailyReport report) {

//        Double allTwo=_getStartMoney(date,report.getBatchStartTime(),report.getBatchEndTime(),2);
//        Double allThree=_getStartMoney(date,report.getBatchStartTime(),report.getBatchEndTime(),3);
//        Double allTen=_getStartMoney(date,report.getBatchStartTime(),report.getBatchEndTime(),10);
//        Double allTwenty=_getStartMoney(date,report.getBatchStartTime(),report.getBatchEndTime(),20);
//        Double allFifty=_getStartMoney(date,report.getBatchStartTime(),report.getBatchEndTime(),50);
//        Double allHundred=_getStartMoney(date,report.getBatchStartTime(),report.getBatchEndTime(),100);
//        Double start2=Double.valueOf(CalculationUtils.add(allTwo,allThree));
//        Double start3=Double.valueOf(CalculationUtils.sub(start2,allTen));
//        Double start10=Double.valueOf(CalculationUtils.sub(start3,allTwenty));
//        Double start20=Double.valueOf(CalculationUtils.sub(start10,allFifty));
//        Double allMoney=Double.valueOf(CalculationUtils.sub(start20,allHundred));

        QrCodeDetailDailyReport qcReport= _findQcQrCodeInfo(date,report);
        Double allTwo=CalculationUtils.fomatMoney(qcReport.getStartTwoMoney());
        Double allThree=CalculationUtils.fomatMoney(qcReport.getStartThreeMoney());
        Double allTen=CalculationUtils.fomatMoney(qcReport.getStartTenMoney());
        Double allTwenty=CalculationUtils.fomatMoney(qcReport.getStartTwentyMoney());
        Double allFifty=CalculationUtils.fomatMoney(qcReport.getStartFiftyMoney());
        Double allHundred=CalculationUtils.fomatMoney(qcReport.getStartHundredMoney());
        Double allMoney=CalculationUtils.fomatMoney(qcReport.getStartAllMoney());
        report.setStartTwoMoney(allTwo);
        report.setStartThreeMoney(allThree);
        report.setStartTenMoney(allTen);
        report.setStartTwentyMoney(allTwenty);
        report.setStartFiftyMoney(allFifty);
        report.setStartHundredMoney(allHundred);
        report.setStartAllMoney(allMoney);

    }

    private QrCodeDetailDailyReport _findAllQrCodeInfo(QrCodeDetailDailyReport report) {
        QrCodeDetailDailyReport all=detailDao.findAllReport(report.getBatchStartTime(),report.getBatchEndTime());
        if(all==null){
            all=new QrCodeDetailDailyReport();
        }
        return all;
    }


    private void _checkAndSave(QrCodeDetailDailyReport report, String date, String auto) {
        List<QrCodeDetailDailyReport> isExit=detailDao.findIsExitByDate(date);
        if(isExit==null||isExit.size()==0||isExit.isEmpty()){
            detailDao.insert(report);
        }else {
//            if(auto.equals("manual")){
                detailDao.deleteList(isExit);
                detailDao.insert(report);
//            }
        }
    }

    private void _setEndQrcodeInfo(QrCodeDetailDailyReport report) {

        //兑换券未使用期末总额
        Double endAllMoney=_CalMoney(report.getStartAllMoney(),report.getSoldAllMoney());
        report.setEndAllMoney(endAllMoney);
        Double endTwoMoney=_CalMoney(report.getStartTwoMoney(),report.getSoldTwoMoney());
        report.setEndTwoMoney(endTwoMoney);
        Double endThreeMoney=_CalMoney(report.getStartThreeMoney(),report.getSoldThreeMoney());
        report.setEndThreeMoney(endThreeMoney);
        Double endTenMoney=_CalMoney(report.getStartTenMoney(),report.getSoldTenMoney());
        report.setEndTenMoney(endTenMoney);
        Double endTwentyMoney=_CalMoney(report.getStartTwentyMoney(),report.getSoldTwentyMoney());
        report.setEndTwentyMoney(endTwentyMoney);
        Double endFiftyMoney=_CalMoney(report.getStartFiftyMoney(),report.getSoldFiftyMoney());
        report.setEndFiftyMoney(endFiftyMoney);
        Double endHundredMoney=_CalMoney(report.getStartHundredMoney(),report.getSoldHundredMoney());
        report.setEndHundredMoney(endHundredMoney);
    }

    private Double _CalMoney(Double startAllMoney, Double soldAllMoney) {
        Double aMoney=CalculationUtils.fomatMoney(startAllMoney);
        Double bMoney=CalculationUtils.fomatMoney(soldAllMoney);
        return Double.valueOf(CalculationUtils.sub(aMoney,bMoney));
    }

    private void _installSoldQrcodeInfo(QrCodeDetailDailyReport report, String date) {
        date=DateUtils.getDateStartString(date);
        String dateMax=DateUtils.getDateEndString(date);
        QrCodeDetailDailyReport soldReport=_findSoldReport(date,dateMax);
        //面额2元兑换券售出额
        Double soldTwoMoney=CalculationUtils.fomatMoney(soldReport.getSoldTwoMoney());
        report.setSoldTwoMoney(soldTwoMoney);

        //面额3元兑换券售出额
        Double soldThreeMoney=CalculationUtils.fomatMoney(soldReport.getSoldThreeMoney());
        report.setSoldThreeMoney(soldThreeMoney);

        //面额10元兑换券售出额
        Double soldTenMoney=CalculationUtils.fomatMoney(soldReport.getSoldTenMoney());
        report.setSoldTenMoney(soldTenMoney);
        //面额20元兑换券售出额
        Double soldTwentyMoney=CalculationUtils.fomatMoney(soldReport.getSoldTwentyMoney());
        report.setSoldTwentyMoney(soldTwentyMoney);
        //面额50元兑换券售出额
        Double soldFiftyMoney=CalculationUtils.fomatMoney(soldReport.getSoldFiftyMoney());
        report.setSoldFiftyMoney(soldFiftyMoney);
        //面额100元兑换券售出额
        Double soldHundredMoney=CalculationUtils.fomatMoney(soldReport.getSoldHundredMoney());
        report.setSoldHundredMoney(soldHundredMoney);
        // 兑换券期售出总额
        Double soldAllMoney=CalculationUtils.fomatMoney(soldReport.getSoldAllMoney());
        report.setSoldAllMoney(soldAllMoney);
    }

    private QrCodeDetailDailyReport _findSoldReport(String date, String dateMax) {
        QrCodeDetailDailyReport r=detailDao.findSoldReport(date, dateMax);
        if(r==null){
            r=new QrCodeDetailDailyReport();
        }
        return r;
    }

    private void _setQrCodeInfo(QrCodeDetailDailyReport report,String date) {
        List<QrCodeDailyReport> dataList=dao.findQrCodeInfo(date);
        QrCodeDailyReport nowBatch= QrCodeConst.getNowBatch(dataList,date);
        if(nowBatch!=null){
            report.setBatchId(nowBatch.getBatchId());
            report.setBatchEndTime(nowBatch.getBatchEndTime());
            report.setBatchStartTime(nowBatch.getBatchStartTime());
        }
    }

    /**
     * 获得不同面额的售出额
     *
     * @param dateMin
     * @param date
     *@param type  @return
     */
    private Double _getSoldMoney(String dateMin, String date, int type) {
        Double money= detailDao.findSoldMoneyByType(dateMin, date, type);
        return CalculationUtils.fomatMoney(money);
    }

    /**
     * 获得不同面额的券期初额
     *
     * @param batchStartTime
     * @param batchEndTime
     *@param type  @return
     */
    private Double _getStartMoney(String date, Date batchStartTime, Date batchEndTime, int type) {
        Double money= detailDao.findStartMoneyByType(date,batchStartTime,batchEndTime, type);
        return CalculationUtils.fomatMoney(money);
    }



    @Override
    public void generateDetailReport(String auto, String month){
        ExcelReport ep=_installDetailExcelObj(month);
        try {
            excelutil.GenerateExcel(ep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExcelReport _installDetailExcelObj(String month) {
        ExcelReport ep = new ExcelReport();
        StringBuffer sbf = new StringBuffer();
        String fileName = qrcodeDetailDailyReportFileName;
        ep.setTitle(fileName);
        ep.setFileName(sbf.append(fileName).append(month).toString());
        String headers[] = new String[] {"批次","批次开始时间","批次结束时间","日期",
                "兑换券未使用期初数,2元/3元/10元/20元/50元/100元/总金额",
                "兑换券售出额,2元/3元/10元/20元/50元/100元/总金额",
                "兑换券期末余额,2元/3元/10元/20元/50元/100元/总金额",};
        String attrs[] = new String[] {
                "batchId", "batchStartTime", "batchEndTime", "date",
                "startTwoMoney", "startThreeMoney", "startTenMoney","startTwentyMoney","startFiftyMoney","startHundredMoney","startAllMoney",
                "soldTwoMoney", "soldThreeMoney", "soldTenMoney","soldTwentyMoney","soldFiftyMoney","soldHundredMoney","soldAllMoney",
                "endTwoMoney", "endThreeMoney", "endTenMoney","endTwentyMoney","endFiftyMoney","endHundredMoney","endAllMoney",
        };
        JSONArray data=_installDetailExcelData(month);
        ep.setHeader(headers);
        ep.setAttrs(attrs);
        ep.setData(data);
        ep.setHeaderType(1);
        return ep;
    }

    private JSONArray _installDetailExcelData(String month) {
        QrCodeDetailDailyReport m = new QrCodeDetailDailyReport();
        m.setDate(month);
        List<QrCodeDetailDailyReport> list = detailDao.findMonth(m);
        if(list==null){
            list=new ArrayList<>();
        }
        JSONArray data= JSONArray.fromObject(list);
        return data;
    }
}

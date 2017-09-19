package com.jy.service.impl.system.finance.statistics.lottery;

import com.jy.common.CpSystem.QrCodeConst;
import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.ChannelsUtils;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.excel.ExcelReport;
import com.jy.entity.system.finance.statistics.QrCodeDailyReport;
import com.jy.entity.system.finance.statistics.QrCodeSoldDailyReport;

import com.jy.repository.system.finance.statistics.lottery.QrCodeDailyReportDao;
import com.jy.repository.system.finance.statistics.lottery.QrCodeSoldDailyReportDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.impl.help.ExcelUtil;
import com.jy.service.system.finance.statistics.lottery.QrCodeSoldDailyReportService;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */
@Service("QrCodeSoldDailyReportService")
public class QrCodeSoldDailyReportServiceImpl extends BaseServiceImp<QrCodeSoldDailyReport> implements QrCodeSoldDailyReportService{
    @Autowired
    private QrCodeSoldDailyReportDao soldDao;
    @Autowired
    private QrCodeDailyReportDao dao;
    @Value("${download.path}")
    private String filePath;

    @Value("${download.qrcodeSoldDailyReport.filename}")
    private String qrcodeSoldDailyReportFileName;
    @Autowired
    private ExcelUtil excelutil;

    @Override
    public  void insertSoldReport(String auto, String date){
        QrCodeSoldDailyReport report=new QrCodeSoldDailyReport();
        report.setDate(date);
        _setQrCodeInfo(report, date);
        if (!ChannelsUtils.IsNull(report.getBatchId())) {
            _setValue(report,date);
            _checkAndSave(report, date, auto);
        }
    }


    private void _checkAndSave(QrCodeSoldDailyReport report, String date, String auto) {
        List<QrCodeSoldDailyReport> isExit=soldDao.findIsExitByDate(date);
        if(isExit==null||isExit.size()==0||isExit.isEmpty()){
            soldDao.insert(report);
        }else {
//            if(auto.equals("manual")){
                soldDao.deleteList(isExit);
                soldDao.insert(report);
//            }
        }
    }

    private void _setValue(QrCodeSoldDailyReport report, String date) {
        date=DateUtils.getDateStartString(date);
        String dateMax=DateUtils.getDateEndString(date);
//        Double allNum=CalculationUtils.fomatMoney(dao.findAllNumByBatch(report.getBatchStartTime(),report.getBatchEndTime()));
        //自取售出券额
        Double selfSaleMoney=_getSaleMoneyByWay(2,date,dateMax);
        //委托售出券额
        Double weiTuoSaleMoney=_getSaleMoneyByWay(1,date,dateMax);
        report.setSelfSaleMoney(selfSaleMoney);
        report.setWeiTuoSaleMoney(weiTuoSaleMoney);
        //售出总额
        Double saleAllMoney= Double.valueOf(CalculationUtils.add(selfSaleMoney,weiTuoSaleMoney));
        report.setSaleAllMoney(saleAllMoney);
        //兑换券期初额
//        Double startNum=Double.valueOf(CalculationUtils.sub(allNum,saleAllMoney));
        Double startNum=CalculationUtils.fomatMoney(dao.findQcMoney(date,report.getBatchStartTime(),report.getBatchEndTime()));
        report.setStartAllMoney(startNum);
        //自取兑换券额-----------待处理
        Double selfUsedMoney=0.0;
        //委托兑换券额------------待处理
        Double weiTuoUsedMoney=0.0;
        //兑换总额
        Double usedAllMoney=Double.valueOf(CalculationUtils.add(selfUsedMoney,weiTuoUsedMoney));
        report.setSelfUsedMoney(selfUsedMoney);
        report.setUsedAllMoney(usedAllMoney);
        report.setWeiTuoUsedMoney(weiTuoUsedMoney);
        //售出券未兑换期末余额差异=期初额+售出总额-兑换总额
        Double diff=Double.valueOf(CalculationUtils.sub(Double.valueOf(CalculationUtils.add(startNum,saleAllMoney)),usedAllMoney));
        report.setDiff(diff);
        //待处理，数据来源未知
        //期初过期兑换券额
        Double startExpireMoney=0.0;
        //本期新增过期兑换券额s
        Double nowNewExpireMoney=0.0;
        //本期兑换过期券额
        Double nowExpireMoney=0.0;
        //期末过期兑换券额
        Double endExpireMoney=Double.valueOf(CalculationUtils.add(startExpireMoney,nowNewExpireMoney));
        report.setEndExpireMoney(endExpireMoney);
        report.setNowExpireMoney(nowExpireMoney);
        report.setStartExpireMoney(startExpireMoney);
        report.setNowNewExpireMoney(nowNewExpireMoney);
    }

    private void _setQrCodeInfo(QrCodeSoldDailyReport report, String date) {
        List<QrCodeDailyReport> dataList=dao.findQrCodeInfo(date);
        QrCodeDailyReport nowBatch= QrCodeConst.getNowBatch(dataList, date);
        if(nowBatch!=null){
            report.setBatchId(nowBatch.getBatchId());
            report.setBatchEndTime(nowBatch.getBatchEndTime());
            report.setBatchStartTime(nowBatch.getBatchStartTime());
        }
    }

    /**
     * 获得某日委托或者自取兑换券售出额
     * @param way,1:自取，2：委托
     * @param date
     * @param dateMax
     * @return
     */
    private Double _getSaleMoneyByWay(int way, String date, String dateMax) {
        Double money=soldDao.findSaleMoneyByWay(way,date,dateMax);
        return CalculationUtils.fomatMoney(money);
    }


    @Override
    public QrCodeSoldDailyReport findIsExitByDate(String date){
        List<QrCodeSoldDailyReport> list=soldDao.findIsExitByDate(date);
        if(list==null||list.size()==0||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    @Override
    public  void generateSoldReport(String auto, String month) {
        String name= qrcodeSoldDailyReportFileName;
        Workbook wb = new HSSFWorkbook();
        //创建工作表
        Sheet sheet = wb.createSheet(name);
        QrCodeSoldDailyReport m = new QrCodeSoldDailyReport();
        m.setDate(month);
        List<QrCodeSoldDailyReport> list = soldDao.findMonth(m);
        Object[][] data = new Object[list.size()][list.size()];
        for(int i=0;i<list.size();i++){
            int batchId = list.get(i).getBatchId();
            String batchStartTime = DateUtils.format(list.get(i).getBatchStartTime(),"yyyy-MM-dd");
            String batchEndTime = DateUtils.format(list.get(i).getBatchEndTime(),"yyyy-MM-dd");
            String showDate = list.get(i).getDate();
            String startAllMoney=list.get(i).getStartAllMoney().toString();
            String selfSaleMoney = list.get(i).getSelfSaleMoney().toString();
            String selfUsedMoney = list.get(i).getSelfUsedMoney().toString();
            String weiTuoSaleMoney = list.get(i).getWeiTuoSaleMoney().toString();
            String weiTuoUsedMoney = list.get(i).getWeiTuoUsedMoney().toString();
            String saleAllMoney = list.get(i).getSaleAllMoney().toString();
            String usedAllMoney = list.get(i).getUsedAllMoney().toString();
            String diff = list.get(i).getDiff().toString();
            String startExpireMoney = list.get(i).getStartExpireMoney().toString();
            String nowNewExpireMoney = list.get(i).getNowNewExpireMoney().toString();
            String nowExpireMoney = list.get(i).getNowExpireMoney().toString();
            String endExpireMoney = list.get(i).getEndExpireMoney().toString();

            data[i]=new Object[]{batchId,batchStartTime,batchEndTime,showDate,startAllMoney,selfSaleMoney,
                    selfUsedMoney,weiTuoSaleMoney,weiTuoUsedMoney,saleAllMoney,usedAllMoney,diff,startExpireMoney,
                    nowNewExpireMoney,nowExpireMoney,endExpireMoney};
        }
        //显示标题
        Row title_row = sheet.createRow(0);
        title_row.setHeight((short)(40*20));
        Cell title_cell = title_row.createCell(0);
        String headers[] = new String[]{"批次","批次开始时间","批次结束时间","日期","券未兑换期初余额","自取售出金额","自取兑换金额"
                ,"委托售出金额","委托兑换金额","售出总金额","兑换总金额","余额差异","期初过期兑换券额","本期新增过期兑换券额","本期兑换过期券额","期末过期兑换券额"};
        Row header_row = sheet.createRow(1);
        header_row.setHeight((short)(20*24));
        //创建单元格的 显示样式
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); //水平方向上的对其方式
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  //垂直方向上的对其方式
        title_cell.setCellStyle(style);
        title_cell.setCellValue(name);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length-1));
        for(int i=0;i<headers.length;i++){
            //设置列宽   基数为256
            sheet.setColumnWidth(i, 20*256);
            Cell cell = header_row.createCell(i);
            //应用样式到  单元格上
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }
        for(int i=0;i<data.length;i++){
            Row row = sheet.createRow(i+2);
            row.setHeight((short)(20*20)); //设置行高  基数为20
            for(int j=0;j<data[i].length;j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(data[i][j]+"");
            }
        }
        FileOutputStream fileOut;
        try {
            File dirFile = new File(filePath);
            if (!dirFile.exists()) {// 文件路径不存在时，自动创建目录
                dirFile.mkdir();
            }
            fileOut = new FileOutputStream(filePath+name+month+".xls");
            wb.write(fileOut);
            fileOut.close();
            logger.info(name+"成功"+ month);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(name+"失败"+e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(name+"失败"+e);
        } ExcelReport ep=_installSoldExcelObj(month);
        try {
            excelutil.GenerateExcel(ep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExcelReport _installSoldExcelObj(String month) {
        ExcelReport ep = new ExcelReport();
        StringBuffer sbf = new StringBuffer();
        String fileName = qrcodeSoldDailyReportFileName;
        ep.setTitle(fileName);
        ep.setFileName(sbf.append(fileName).append(month).toString());
        String headers[] = new String[] {"批次","批次开始时间","批次结束时间","日期","券未兑换期初余额","方式一(自取),售出金额/兑换金额"
                ,"方式二(委托),售出金额/兑换金额","售出总金额","兑换总金额","余额差异","期初过期兑换券额","本期新增过期兑换券额","本期兑换过期券额","期末过期兑换券额"};
        String attrs[] = new String[] {
                "batchId", "batchStartTime", "batchEndTime", "date",
                "startAllMoney", "selfSaleMoney", "selfUsedMoney","weiTuoSaleMoney","weiTuoUsedMoney","saleAllMoney","usedAllMoney",
                "diff", "startExpireMoney", "nowNewExpireMoney","nowExpireMoney","endExpireMoney"};
        JSONArray data=_installSoldExcelData(month);
        ep.setHeader(headers);
        ep.setAttrs(attrs);
        ep.setData(data);
        ep.setHeaderType(1);
        return ep;
    }

    private JSONArray _installSoldExcelData(String month) {
        QrCodeSoldDailyReport m = new QrCodeSoldDailyReport();
        m.setDate(month);
        List<QrCodeSoldDailyReport> list = soldDao.findMonth(m);
        if(list==null){
            list=new ArrayList<>();
        }
        JSONArray data= JSONArray.fromObject(list);
        return data;
    }
}

package com.jy.service.impl.system.finance.statistics.lottery;

import com.jy.common.CpSystem.CpConst;
import com.jy.common.CpSystem.DifferenceConst;
import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.excel.ExcelReport;
import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicket;
import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicketDiffReport;
import com.jy.entity.system.finance.statistics.LotterySalesDiffReport;
import com.jy.mybatis.Page;
import com.jy.repository.system.finance.reconciliation.lottery.LotteryBuyAndTicketDao;
import com.jy.repository.system.finance.statistics.lottery.LotterySalesDiffReportDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.impl.help.ExcelUtil;
import com.jy.service.system.finance.reconciliation.lottery.LotteryBuyAndTicketService;
import com.jy.service.system.finance.statistics.lottery.LotterySalesDiffReportService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
@Service("LotterySalesDiffReportService")
public class LotterySalesDiffReportServiceImpl extends BaseServiceImp<LotterySalesDiffReport> implements LotterySalesDiffReportService {
    @Autowired
    private LotterySalesDiffReportDao dao;
    @Autowired
    private ExcelUtil excelutil;

    @Value("${download.LotterySalesDiffReport.filename}")
    private String LotterySalesDiffReport;


    @Override
    public Page<LotterySalesDiffReport> findDiffByPage(LotterySalesDiffReport m, Page<LotterySalesDiffReport> page) {
        page.setResults(dao.findDiffByPage(m, page));
        return page;
    }


    /**
     * 数据入库
     *
     * @param thisDate
     */
    @Override
    public void execTask(String thisDate) {
        List<LotterySalesDiffReport> list=new ArrayList<>();
        List<LotterySalesDiffReport> report1 = _findOrderTicket(thisDate);
        List<LotterySalesDiffReport> report2 = _findBuyAndTicket(thisDate);
//        List<LotterySalesDiffReport> report3 = _findSystemAndMachine(thisDate);
        List<LotterySalesDiffReport> report4 = _findTicketAndTicket(thisDate);
        _check(list,report1);
        _check(list,report2);
        _check(list,report4);
        if(list!=null&&list.size()!=0&&!list.isEmpty()) {
            _checkDiffAndSave(thisDate, list);
        }
    }

    private void _check(List<LotterySalesDiffReport> list, List<LotterySalesDiffReport> report) {
        if(report.size()!=0&&!report.isEmpty()&&report!=null){
            list.addAll(report);
        }
    }

    //方案和票差异
    private List<LotterySalesDiffReport> _findOrderTicket(String thisDate) {
        List<LotterySalesDiffReport> list=dao.findOrderTicketDiff(thisDate);
        return list;
    }
    //购彩和出票差异
    private List<LotterySalesDiffReport> _findBuyAndTicket(String thisDate) {
        List<LotterySalesDiffReport> list=dao.findBuyAndTicketDiff(thisDate);
        return list;
    }
    //出票系统和彩票机差异
    private List<LotterySalesDiffReport> _findSystemAndMachine(String thisDate) {
        List<LotterySalesDiffReport> list=dao.findSystemAndMachineDiff(thisDate);
        return list;
    }
    //销售汇总差异
    private List<LotterySalesDiffReport> _findTicketAndTicket(String thisDate) {
        List<LotterySalesDiffReport> list=dao.findTicketAndTicketDiff(thisDate);
        if(list.size()!=0&&!list.isEmpty()&&list!=null){
            list=_turnList(list);
        }
        return list;
    }

    private List<LotterySalesDiffReport> _turnList(List<LotterySalesDiffReport> list) {
            for(LotterySalesDiffReport report:list){
                if(report.getDiffType().equals("1")){
                    report.setDiffType("金额差");
                }else if(report.getDiffType().equals("2")){
                    report.setDiffType("投注系统有，出票系统无");
                }else if(report.getDiffType().equals("3")){
                    report.setDiffType("投注系统无，出票系统有");
                }
            }
            return list;
    }


    private void _checkDiffAndSave(String thisDate, List<LotterySalesDiffReport> diffList) {
        List<LotterySalesDiffReport> isExit = dao.findDiffReportByDate(thisDate);
        if (isExit == null || isExit.size() == 0 || isExit.isEmpty()) {
            dao.insertDiffReport(diffList);
        } else {
            dao.deleteDiffList(isExit);
            dao.insertDiffReport(diffList);
        }
    }










    //-------------------生成报表---------------------------------
    @Override
    public void generateReport(String month) {
        ExcelReport ep = _installExcel(month);
        try {
            excelutil.GenerateExcel(ep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ExcelReport _installExcel(String month) {
        ExcelReport ep = new ExcelReport();
        ep.setSheet(true);
        List<ExcelReport> list = new ArrayList<>();
        ExcelReport ep1 = _installExcelObj(month);
//        ExcelReport ep2 = _installDiffExcelObj(month);
        list.add(ep1);
//        list.add(ep2);
        StringBuffer sbf = new StringBuffer();
        String fileName = LotterySalesDiffReport;
        ep.setFileName(sbf.append(fileName).append(month).toString());
        ep.setList(list);
        return ep;
    }

    private ExcelReport _installExcelObj(String month) {
        ExcelReport ep = new ExcelReport();
        StringBuffer sbf = new StringBuffer();
        String fileName = LotterySalesDiffReport;
        ep.setTitle(fileName);
        String headers[] = new String[]{"日期", "编号", "差异金额", "差异类型", "差异原因", "处理状态", "审核状态","来源"};
        String attrs[] = new String[]{
                "date", "number", "diffMoney", "diffType", "diffReason", "status", "isCheck", "source",};
        JSONArray data = _installExcelData(month);
        ep.setHeader(headers);
        ep.setAttrs(attrs);
        ep.setData(data);
        ep.setHeaderType(1);
        return ep;
    }

    private JSONArray _installExcelData(String month) {
        LotterySalesDiffReport m = new LotterySalesDiffReport();
        m.setDate(month);
        List<LotterySalesDiffReport> list = dao.findMonth(m);
        if (list == null) {
            list = new ArrayList<>();
        }
        for(LotterySalesDiffReport l:list){
            if(l.getSource().equals(DifferenceConst.LOTTERYSALESDIFF_TYPE_1+"")){
                l.setSource("方案和票差异");
            }else if(l.getSource().equals(DifferenceConst.LOTTERYSALESDIFF_TYPE_2+"")){
                l.setSource("购彩和出票差异");
            }else  if(l.getSource().equals(DifferenceConst.LOTTERYSALESDIFF_TYPE_3+"")){
                l.setSource("出票系统和彩票机差异");
            }else  if(l.getSource().equals(DifferenceConst.LOTTERYSALESDIFF_TYPE_4+"")){
                l.setSource("销售汇总差异");
            }
        }
        JSONArray data = JSONArray.fromObject(list);
        return data;
    }



}

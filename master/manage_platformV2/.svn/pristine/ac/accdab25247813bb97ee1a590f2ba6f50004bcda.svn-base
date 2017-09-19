package com.jy.process.impl.system.reconciliation.lottery.qrCode;

import com.jy.entity.system.finance.statistics.QrCodeSoldDailyReport;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.from.system.finance.statistics.QrCodeSoldDailyReportForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.reconciliation.lottery.qrCode.SoldProcess;
import com.jy.service.system.finance.statistics.lottery.QrCodeSoldDailyReportService;
import com.jy.vo.system.statistics.lottery.QrCodeSoldVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service("SoldProcess")
public class SoldProcessImpl implements SoldProcess {
    @Autowired
    private QrCodeSoldDailyReportService service;
    @Autowired
    private DownLoadProcess downLoadProcess;

    @Value("${download.qrcodeSoldDailyReport.filename}")
    private String qrcodeSoldDailyReportFilename;

    @Override
    public Page<QrCodeSoldVo> findByPage(Page<QrCodeSoldVo> pageVo, BaseForm form) {
        QrCodeSoldDailyReport o=new QrCodeSoldDailyReport();
        o.setBeginTime(form.getBeginTime());
        o.setEndTime(form.getEndTime());
        Page<QrCodeSoldDailyReport> page =new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        Page<QrCodeSoldDailyReport> reports=service.findByPage(o, page);
        List<QrCodeSoldVo> voList=new ArrayList<>();
        if(reports.getResults()==null){
            return new Page<QrCodeSoldVo>();
        }else{
            pageVo.setPageSize(reports.getPageSize());
            pageVo.setTotalPage(reports.getTotalPage());
            pageVo.setTotalRecord(reports.getTotalRecord());
            List<QrCodeSoldDailyReport> list=reports.getResults();
            for(QrCodeSoldDailyReport report:list){
                QrCodeSoldVo vo=new QrCodeSoldVo();
                BeanUtils.copyProperties(report,vo);
                voList.add(vo);
            }
        }
        pageVo.setResults(voList);
        return pageVo;

    }
    @Override
    public void add(QrCodeSoldDailyReportForm form) {
        QrCodeSoldDailyReport qc=service.findIsExitByDate(form.getDate());
        if(qc!=null){
            qc.setWeiTuoUsedMoney(form.getWeiTuoUsedMoney());
            qc.setSelfUsedMoney(form.getSelfUsedMoney());
            service.update(qc);
        }
    }


    @Override
    public Object download(String date) {
        String url="/system/finance/statistics/qrcode/QrCodeSoldDailyReport";
        Object obj=downLoadProcess.downLoad(date,qrcodeSoldDailyReportFilename,url);
        return obj;
    }
}

package com.jy.process.impl.system.reconciliation.lottery.qrCode;

import com.jy.entity.system.finance.statistics.QrCodeDetailDailyReport;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.reconciliation.lottery.qrCode.DetailProcess;
import com.jy.service.system.finance.statistics.lottery.QrCodeDetailDailyReportService;
import com.jy.vo.system.statistics.lottery.QrCodeDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service("DetailProcess")
public class DetailProcessImpl implements DetailProcess {
    @Autowired
    private QrCodeDetailDailyReportService service;
    @Autowired
    private DownLoadProcess downLoadProcess;

    @Value("${download.qrcodeDetailDailyReport.filename}")
    private String qrcodeDetailDailyReportFilename;

    @Override
    public Object download(String date) {
        String url="/system/finance/statistics/qrcode/QrCodeDetailDailyReport";
        Object obj=downLoadProcess.downLoad(date,qrcodeDetailDailyReportFilename,url);
        return obj;
    }

    @Override
    public Page<QrCodeDetailVo> findByPage(Page<QrCodeDetailVo> pageVo, BaseForm form) {
            QrCodeDetailDailyReport o=new QrCodeDetailDailyReport();
            o.setBeginTime(form.getBeginTime());
            o.setEndTime(form.getEndTime());
        Page<QrCodeDetailDailyReport> page =new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        Page<QrCodeDetailDailyReport> reports=service.findByPage(o, page);
        List<QrCodeDetailVo> voList=new ArrayList<>();
        if(reports.getResults()==null){
            return new Page<QrCodeDetailVo>();
        }else{
            pageVo.setPageSize(reports.getPageSize());
            pageVo.setTotalPage(reports.getTotalPage());
            pageVo.setTotalRecord(reports.getTotalRecord());
            List<QrCodeDetailDailyReport> list=reports.getResults();
            for(QrCodeDetailDailyReport report:list){
                QrCodeDetailVo vo=new QrCodeDetailVo();
                BeanUtils.copyProperties(report,vo);
                voList.add(vo);
            }
        }
        pageVo.setResults(voList);
        return pageVo;
    }
}

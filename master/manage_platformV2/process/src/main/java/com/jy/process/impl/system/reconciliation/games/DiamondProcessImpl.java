package com.jy.process.impl.system.reconciliation.games;

import com.jy.entity.system.finance.reconciliation.game.DiamondBalance;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.reconciliation.games.DiamondProcess;
import com.jy.service.system.finance.reconciliation.game.DiamondBalanceService;
import com.jy.vo.system.reconciliation.games.DiamondVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service("DiamondProcess")
public class DiamondProcessImpl implements DiamondProcess {
    @Autowired
    private DiamondBalanceService service;
    @Autowired
    private DownLoadProcess downLoadProcess;
    @Value("${download.game.diamondReport.filename}")
    private String diamondReportFileName;

    @Override
    public Page<DiamondVo> findByPage(BaseForm form, Page<DiamondVo> pageVo) {
        DiamondBalance balance=new DiamondBalance();
        BeanUtils.copyProperties(form,balance);
        Page<DiamondBalance> page=new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        Page<DiamondBalance> reports = service.findByPage(balance, page);
        List<DiamondVo> voList=new ArrayList<>();
        if(reports.getResults()==null){
            return new Page<DiamondVo>();
        }else{
            pageVo.setPageSize(reports.getPageSize());
            pageVo.setTotalPage(reports.getTotalPage());
            pageVo.setTotalRecord(reports.getTotalRecord());
            List<DiamondBalance> list=reports.getResults();
            for(DiamondBalance report:list){
                DiamondVo vo=new DiamondVo();
                BeanUtils.copyProperties(report,vo);
                voList.add(vo);
            }
        }
        pageVo.setResults(voList);
        return pageVo;
    }

    @Override
    public Object download(String date) {
        String name=diamondReportFileName;
        String url="/backstage/reconciliation/diamondBalance/diamondBalance";
        Object obj=downLoadProcess.downLoad(date,name,url);
        return obj;
    }
}

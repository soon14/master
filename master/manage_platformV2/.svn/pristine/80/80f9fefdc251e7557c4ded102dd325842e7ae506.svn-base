package com.jy.process.impl.system.reconciliation.games;

import com.jy.entity.system.finance.reconciliation.game.GoldBalance;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.reconciliation.games.GoldProcess;
import com.jy.service.system.finance.reconciliation.game.GoldBalanceService;
import com.jy.vo.system.reconciliation.games.GoldVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service("GoldProcess")
public class GoldProcessImpl implements GoldProcess {
    @Autowired
    private GoldBalanceService service;
    @Autowired
    private DownLoadProcess downLoadProcess;

    @Value("${download.game.goldReport.filename}")
    private String goldReportFileName;


    @Override
    public Page<GoldVo> findByPage(BaseForm form, Page<GoldVo> pageVo){
        GoldBalance balance=new GoldBalance();
        BeanUtils.copyProperties(form,balance);
        Page<GoldBalance> page=new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        Page<GoldBalance> reports = service.findByPage(balance, page);
        List<GoldVo> voList=new ArrayList<>();
        if(reports.getResults()==null){
            return new Page<GoldVo>();
        }else{
            pageVo.setPageSize(reports.getPageSize());
            pageVo.setTotalPage(reports.getTotalPage());
            pageVo.setTotalRecord(reports.getTotalRecord());
            List<GoldBalance> list=reports.getResults();
            for(GoldBalance report:list){
                GoldVo vo=new GoldVo();
                BeanUtils.copyProperties(report,vo);
                voList.add(vo);
            }
        }
        pageVo.setResults(voList);
        return pageVo;
    }

    @Override
    public Object download(String date) {
        String name=goldReportFileName;
        String url="/backstage/reconciliation/goldBalance/goldBalance";
        Object obj=downLoadProcess.downLoad(date,name,url);
        return obj;
    }
}

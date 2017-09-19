package com.jy.process.impl.system.reconciliation.games;

import com.jy.entity.system.finance.reconciliation.game.ToolsBalance;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.reconciliation.games.ToolsProcess;
import com.jy.service.system.finance.reconciliation.game.ToolsBalanceService;
import com.jy.vo.system.reconciliation.games.ToolsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service("ToolsProcess")
public class ToolsProcessImpl implements ToolsProcess {
    @Autowired
    private ToolsBalanceService service;
    @Autowired
    private DownLoadProcess downLoadProcess;

    @Value("${download.game.toolsReport.filename}")
    private String toolsReportFileName;


    @Override
    public Object download(String date) {
        String name=toolsReportFileName;
        String url="/backstage/reconciliation/toolsBalance/toolsBalance";
        Object obj=downLoadProcess.downLoad(date,name,url);
        return obj;
    }

    @Override
    public Page<ToolsVo> findByPage(BaseForm form, Page<ToolsVo> pageVo) {
        ToolsBalance balance=new ToolsBalance();
        BeanUtils.copyProperties(form,balance);
        Page<ToolsBalance> page=new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        Page<ToolsBalance> reports = service.findByPage(balance, page);
        List<ToolsVo> voList=new ArrayList<>();
        if(reports.getResults()==null){
            return new Page<ToolsVo>();
        }else{
            pageVo.setPageSize(reports.getPageSize());
            pageVo.setTotalPage(reports.getTotalPage());
            pageVo.setTotalRecord(reports.getTotalRecord());
            List<ToolsBalance> list=reports.getResults();
            for(ToolsBalance report:list){
                ToolsVo vo=new ToolsVo();
                BeanUtils.copyProperties(report,vo);
                voList.add(vo);
            }
        }
        pageVo.setResults(voList);
        return pageVo;
    }
}

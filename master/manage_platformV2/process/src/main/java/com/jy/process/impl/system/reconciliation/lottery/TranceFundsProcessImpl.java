package com.jy.process.impl.system.reconciliation.lottery;

import com.jy.entity.system.finance.reconciliation.lottery.TranceFunds;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.reconciliation.lottery.TranceFundsProcess;
import com.jy.service.system.finance.reconciliation.lottery.TranceFundsService;
import com.jy.vo.system.reconciliation.lottery.TranceFundsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
@Service("TranceFundsProcess")
public class TranceFundsProcessImpl implements TranceFundsProcess{

    @Autowired
    private TranceFundsService service;
    @Autowired
    private DownLoadProcess downLoadProcess;
    @Value("${download.tranceFunds.filename}")
    private String tranceFundsFileName;

    @Override
    public Page<TranceFundsVo> findList(BaseForm form, Page<TranceFundsVo> pageVo) {
        TranceFunds o=new TranceFunds();
        o.setBeginTime(form.getBeginTime());
        o.setEndTime(form.getEndTime());
        Page<TranceFunds> page =new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        Page<TranceFunds> reports=service.findByPage(o, page);
        List<TranceFundsVo> voList=new ArrayList<>();
        if(reports.getResults()==null){
            return new Page<TranceFundsVo>();
        }else{
            pageVo.setPageSize(reports.getPageSize());
            pageVo.setTotalPage(reports.getTotalPage());
            pageVo.setTotalRecord(reports.getTotalRecord());
            List<TranceFunds> list=reports.getResults();
            for(TranceFunds funds:list){
                TranceFundsVo vo=new TranceFundsVo();
                BeanUtils.copyProperties(funds,vo);
                voList.add(vo);
            }
        }
        pageVo.setResults(voList);
        return pageVo;
    }

    @Override
    public Object dowload(String date) {
        String name=tranceFundsFileName;
        String url="/backstage/reconciliation/lottery/tranceFunds/index";
        Object obj=downLoadProcess.downLoad(date,name,url);
        return obj;
    }
}

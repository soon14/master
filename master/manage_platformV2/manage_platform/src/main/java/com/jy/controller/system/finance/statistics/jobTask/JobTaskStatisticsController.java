package com.jy.controller.system.finance.statistics.jobTask;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import com.jy.entity.system.finance.statistics.QrCodeDailyReport;
import com.jy.mybatis.Page;
import com.jy.process.system.statistics.jobTask.JobTaskStatisticsProcess;


@Controller
@RequestMapping("/statistics/jobTaskStatistics/")
public class JobTaskStatisticsController extends BaseController<QrCodeDailyReport> {

    @Autowired
    private JobTaskStatisticsProcess process;
    
    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/statistics/jobTask/jobTaskStatistics";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping(value="fundJobTaskStatistics", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<JobTaskStatistics> page, JobTaskStatistics form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/statistics/jobTaskStatistics/index"))){
            try {
            	String beginTime = getRequest().getParameter("beginTime");
            	String endTime = getRequest().getParameter("endTime");
            	form.setDate(beginTime);
            	form.setEndDate(endTime);
                Page<JobTaskStatistics> reports=process.fundJobTaskStatistics(page,form);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list",reports);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }
}

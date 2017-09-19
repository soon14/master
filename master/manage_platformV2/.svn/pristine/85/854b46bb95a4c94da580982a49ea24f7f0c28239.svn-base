package com.jy.controller.system.finance.statistics.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.exception.DaysBaseException;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.statistics.LotterySalesDiffReport;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.common.FormProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.service.system.finance.statistics.lottery.LotterySalesDiffReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */
@Controller
@RequestMapping("/backstage/statistics/lottery/lotterySalesDiffReport")
public class LotterySalesDiffReportController extends BaseController<LotterySalesDiffReport>{

    @Autowired
    private ManualProcess manualProcess;
    @Autowired
    private FormProcess formProcess;
    @Autowired
    private LotterySalesDiffReportService service;
    @Value("${download.LotterySalesDiffReport.filename}")
    private String LotterySalesDiffReport;

    @Autowired
    private DownLoadProcess downLoadProcess;

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "system/finance/statistics/lottery/lotterySalesDiffReport";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<LotterySalesDiffReport> page, LotterySalesDiffReport form) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/statistics/lottery/lotterySalesDiffReport/index"))) {
            try {
//                formProcess.check(form);
                Page<LotterySalesDiffReport> list =service.findDiffByPage(form, page);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list", list);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }






    @RequestMapping(value="manual", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes manual(BaseForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/statistics/lottery/lotterySalesDiffReport/index"))){
            try {
                manualProcess.manualByType(form,13);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("msg", "数据重置成功!");
                ar.setSucceed(p);
            }catch (DaysBaseException e) {
                logger.error(e.toString(), e);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("msg", e.getRegMsg().getMsg());
                ar.setSucceed(p);

            } catch (Exception e){
                logger.error(e.toString(),e);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("msg", "数据重置失败!");
                ar.setSucceed(p);
            }}
        return ar;
    }


    @RequestMapping(value = "download", method = {
            RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
    public Object download() {
        String date = getRequest().getParameter("date");
        String url="/backstage/statistics/lottery/lotterySalesDiffReport";
        Object obj=downLoadProcess.downLoad(date,LotterySalesDiffReport,url);
        return obj;
    }



}

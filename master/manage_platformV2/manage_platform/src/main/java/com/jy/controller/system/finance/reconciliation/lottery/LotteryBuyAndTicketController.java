package com.jy.controller.system.finance.reconciliation.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.exception.DaysBaseException;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicket;
import com.jy.entity.system.finance.reconciliation.lottery.LotteryBuyAndTicketDiffReport;
import com.jy.entity.system.finance.reconciliation.lottery.TranceFunds;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.common.FormProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.lottery.TranceFundsProcess;
import com.jy.service.system.finance.reconciliation.lottery.LotteryBuyAndTicketService;
import com.jy.vo.system.reconciliation.lottery.TranceFundsVo;
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
@RequestMapping("/backstage/reconciliation/lottery/lotteryBuyAndTicket")
public class LotteryBuyAndTicketController extends BaseController<LotteryBuyAndTicket>{

    @Autowired
    private ManualProcess manualProcess;
    @Autowired
    private FormProcess formProcess;
    @Autowired
    private LotteryBuyAndTicketService service;
    @Value("${download.lotteryBuyAndTicket.filename}")
    private String lotteryBuyAndTicket;
    @Value("${download.lotteryBuyAndTicketDiff.filename}")
    private String lotteryBuyAndTicketDiff;

    @Autowired
    private DownLoadProcess downLoadProcess;

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/reconciliation/lottery/funds/LotteryBuyAndTicket";
        }
        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping("indexDiff")
    public String indexDiff(Model model,String date){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
            model.addAttribute("date", date);
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            return "/system/finance/reconciliation/lottery/funds/lotteryBuyAndTicketDiff";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<LotteryBuyAndTicket> page, LotteryBuyAndTicket form) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/reconciliation/lottery/lotteryBuyAndTicket/index"))) {
            try {
//                formProcess.check(form);
                Page<LotteryBuyAndTicket> list =service.findByPage(form, page);
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

    @RequestMapping(value = "findDiffList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findDiffList(Page<LotteryBuyAndTicketDiffReport> page, LotteryBuyAndTicketDiffReport m) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/statistics/lotteryWaySale/indexDiff"))) {
            try {
                String date=getRequest().getParameter("date");
                String beginTime= DateUtils.getDateStartString(date);
                String endTime= DateUtils.getDateEndString(date);
                m.setBeginTime(beginTime);
                m.setEndTime(endTime);
                Page<LotteryBuyAndTicketDiffReport> reports = service.findLotteryBuyAndTicketDiffByPage(m, page);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("pageSize", page.getPageSize());
                p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list", reports);
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
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/reconciliation/lottery/lotteryBuyAndTicket/index"))){
            try {
                manualProcess.manualByType(form,12);
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
        String url="/system/finance/reconciliation/lottery/funds/LotteryBuyAndTicket";
        Object obj=downLoadProcess.downLoad(date,lotteryBuyAndTicket,url);
        return obj;
    }

    @RequestMapping(value = "downloadDiff", method = {
            RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
    public Object downloadDiff() {
        String date = getRequest().getParameter("date");
        String url="/system/finance/reconciliation/lottery/funds/LotteryBuyAndTicket";
        Object obj=downLoadProcess.downLoad(date,lotteryBuyAndTicketDiff,url);
        return obj;
    }

}

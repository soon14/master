package com.jy.controller.system.finance.statistics.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.exception.DaysBaseException;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.LotteryWaySaleReport;
import com.jy.entity.system.finance.statistics.OrderTicketDiffReport;
import com.jy.entity.system.finance.statistics.OrderTicketReport;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.base.BaseProcess;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.service.system.finance.statistics.lottery.LotteryWaySaleReportService;
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
 * @文件名:LotteryWaySaleController.java
 * @功能描述：
 * @创建日期:2017年3月9日上午11:34:37
 * @创建人：lijunke
 * @Copyright（c） 2017, all rights reserved by days
 */
@Controller
@RequestMapping("/statistics/lotteryWaySale/")
public class LotteryWaySaleReportController extends BaseController<LotteryWaySaleReport> {


    @Autowired
    public LotteryWaySaleReportService service;

    @Value("${download.suffix}")
    private String suffix;

    @Value("${download.lottery.filename}")
    private String lotteryFileName;
    @Autowired
    private DownLoadProcess downLoadProcess;
    @Value("${download.orderTicket.filename}")
    private String orderTicketName;
    @Value("${download.orderTicketDiff.filename}")
    private String orderTicketDiffName;
    @Autowired
    private BaseProcess baseProcess;
    @Autowired
    private ManualProcess manualProcess;

    /**
     * @param model
     * @return String
     * @方法功能描述： 方法作用
     * @author lijunke
     * @创建时间： 2017年3月1日下午7:36:53
     */
    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/statistics/lottery/lotteryWaySaleReport";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping("indexOrderTicket")
    public String indexOrderTicket(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/statistics/lottery/orderTicketReport";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    @RequestMapping("indexDiff")
    public String indexDiff(Model model,String date) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("date", date);
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            return "/system/finance/statistics/lottery/orderTicketDiff";
        }
        return Const.NO_AUTHORIZED_URL;
    }
    /**
     * @return String
     * @方法功能描述： 加载发展客户页面带出查询的数据
     * @author lijunke
     * @创建时间： 2017年3月1日下午6:55:54
     */
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<LotteryWaySaleReport> page, LotteryWaySaleReport m) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/statistics/lotteryWaySale/index"))) {
            try {
                Page<LotteryWaySaleReport> reports = service.findByPage(m, page);
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

    @RequestMapping(value = "findOrderTicketByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findOrderTicketByPage(Page<OrderTicketReport> page, OrderTicketReport m) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/statistics/lotteryWaySale/indexOrderTicket"))) {
            try {
                Page<OrderTicketReport> reports = service.findOrderTicketByPage(m, page);
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

    @RequestMapping(value = "findDiffList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findDiffList(Page<OrderTicketDiffReport> page, OrderTicketDiffReport m) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/statistics/lotteryWaySale/indexDiff"))) {
            try {
                String date=getRequest().getParameter("date");
                String beginTime= DateUtils.getDateStartString(date);
                String endTime= DateUtils.getDateEndString(date);
                m.setBeginTime(beginTime);
                m.setEndTime(endTime);
                Page<OrderTicketDiffReport> reports = service.findOrderTicketDiffByPage(m, page);
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

    /**
     * @return
     * @throws Exception String
     * @方法功能描述：下载报表到本地
     * @author lijunke
     * @创建时间： 2017年3月7日下午1:09:00
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "download", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object exportReport() {
        String date = getRequest().getParameter("date");
        String url="/system/finance/reconciliation/bettingDifferenceRestData";
        Object obj=downLoadProcess.downLoad(date,lotteryFileName,url);
        return obj;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "downloadOrderTicket", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object downloadOrderTicket() {
        String date = getRequest().getParameter("date");
        String url="/system/finance/statistics/lottery/orderTicketReport";
        Object obj=downLoadProcess.downLoad(date,orderTicketName,url);
        return obj;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "downloadOrderTicketDiff", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object downloadOrderTicketDiff() {
        String date = getRequest().getParameter("date");
        String url="/system/finance/statistics/lottery/orderTicketReport";
        Object obj=downLoadProcess.downLoad(date,orderTicketDiffName,url);
        return obj;
    }


    @RequestMapping(value="manual", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes manual(BaseForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/statistics/lotteryWaySale/index"))){
            try {
                manualProcess.manualByType(form,10);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("msg", "数据重置成功!");
                ar.setSucceed(p);
            } catch (DaysBaseException e) {
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



    @RequestMapping(value="manualOrderTicket", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes manualOrderTicket(BaseForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/statistics/lotteryWaySale/indexOrderTicket"))){
            try {
                manualProcess.manualByType(form,11);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("msg", "数据重置成功!");
                ar.setSucceed(p);
            } catch (DaysBaseException e) {
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
}
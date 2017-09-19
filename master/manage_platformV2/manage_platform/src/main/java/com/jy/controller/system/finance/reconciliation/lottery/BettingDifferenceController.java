package com.jy.controller.system.finance.reconciliation.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.exception.DaysBaseException;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.lottery.BettingDifference;
import com.jy.entity.system.finance.reconciliation.lottery.CountSale;
import com.jy.mybatis.Page;
import com.jy.process.system.base.BaseProcess;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.service.system.finance.reconciliation.lottery.BettingDifferenceService;
import com.jy.service.system.finance.reconciliation.lottery.CountSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @文件名:SaleDifferenceController.java
 * @功能描述：
 * @创建日期:2017年3月9日下午8:09:52
 * @创建人：lijunke
 * @Copyright（c） 2017, all rights reserved by days
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/backstage/bettingDifference")
public class BettingDifferenceController extends BaseController<BettingDifference> {

    @Autowired
    public BettingDifferenceService service;

    @Autowired
    public CountSaleService countSaleService;
    @Autowired
    private DownLoadProcess downLoadProcess;


    @Value("${download.suffix}")
    private String suffix;

    @Value("${download.betting.filename}")
    private String bettingFileName;

    @Value("${download.detail.filename}")
    private String detailFileName;

    @Autowired
    private BaseProcess baseProcess;

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
            return "/system/finance/reconciliation/bettingDifference";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping("resetData")
    public String resetData(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/reconciliation/bettingDifferenceRestData";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    /**
     * 手动重置数据
     * @param page
     * @param m
     * @return
     */
    @RequestMapping(value = "restBill", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes restBill(Page<CountSale> page, CountSale m) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/backstage/bettingDifference/resetData"))) {
            try {
                this.service.restTask(DateUtils.parseDate(getRequest().getParameter("beginTime"), "yyyy-MM-dd"),
                        DateUtils.parseDate(getRequest().getParameter("endTime"), "yyyy-MM-dd"));
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("msg", "数据重置成功!");
                ar.setSucceed(p);

            } catch (DaysBaseException e) {
                logger.error(e.toString(), e);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("msg", "投注或者出票数据为空!");
                ar.setSucceed(p);

            } catch (Exception e) {
                logger.error(e.toString(), e);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("msg", "数据重置失败!");
                ar.setSucceed(p);
            }
        }
        return ar;
    }

    /**
     * @return String
     * @方法功能描述： 显示销售汇总数据
     * @author lijunke
     * @创建时间： 2017年3月1日下午6:55:54
     */
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<CountSale> page, CountSale m) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/bettingDifference/index"))) {
            try {
                String date = getRequest().getParameter("beginTime");
                String date1 = getRequest().getParameter("endTime");
                Page<CountSale> reports = service.findAll(date, date1, page);
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
     * @param model
     * @param billDate
     * @return String
     * @方法功能描述： 初始化跳转页面
     * @author lijunke
     * @创建时间： 2017年3月13日下午8:33:03
     */
    @RequestMapping("buillIndex")
    public String buillIndex(Model model, String billDate, String ids,int number) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("billDate", billDate);
            model.addAttribute("ids", ids);
            model.addAttribute("number", number);
            return "/system/finance/reconciliation/childBettingDifference";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * @param page
     * @param billDate
     * @return AjaxRes
     * @方法功能描述： 显示明细对账数据
     * @author lijunke
     * @创建时间： 2017年3月13日下午9:02:21
     */
    @RequestMapping(value = "buill", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes buill(Page<BettingDifference> page, String billDate, String ids,int number) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/backstage/bettingDifference/buillIndex"))) {
            try {
                Page<BettingDifference> reports = this.service.differenceList(billDate, page, ids,number);
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
     * @方法功能描述：下载总账报表
     * @author lijunke
     * @创建时间： 2017年3月7日下午1:09:00
     */
    @RequestMapping(value = "downloadBetting", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object exportReport() {
        String date = getRequest().getParameter("date");
        String name=bettingFileName;
        String url="/system/finance/reconciliation/bettingDifferenceRestData";
        Object obj=downLoadProcess.downLoad(date,name,url);
        return obj;
    }


    /**
     * @return Object
     * @方法功能描述： 下载明细报表
     * @author lijunke
     * @创建时间： 2017年3月16日下午7:19:57
     */

    @RequestMapping(value = "downloadDetail", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object exportReportDetail(String date) {
        String name=detailFileName;
        String url="/system/finance/reconciliation/bettingDifferenceRestData";
        Object obj=downLoadProcess.downLoad(date,name,url);
        return obj;
    }


    @RequestMapping(value = "countAumone", method = RequestMethod.POST)
    @ResponseBody
    public void countAumone(String id, String offlineVolume, String totalAumone) {
        try {
            CountSale cs = new CountSale();
            cs.setId(Integer.parseInt(id));

            if (null != totalAumone) {
                cs.setTotalAumone(new BigDecimal(totalAumone));
            } else {
                cs.setTotalAumone(new BigDecimal(0));
            }

            if (null != offlineVolume) {
                cs.setOfflineVolume(new BigDecimal(offlineVolume));
            } else {
                cs.setOfflineVolume(new BigDecimal(0));
            }
            BigDecimal countAumone = cs.getTotalAumone().add(cs.getOfflineVolume());
            cs.setTotalAumone(countAumone);
            countSaleService.updateCountAumone(cs);
        } catch (NumberFormatException e) {
            logger.info("保存线下手工录入数据失败！");
            e.printStackTrace();
        }
    }

}

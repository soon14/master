package com.jy.controller.system.finance.reconciliation.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.entity.system.finance.reconciliation.lottery.CashPrizeDifference;
import com.jy.mybatis.Page;
import com.jy.process.system.reconciliation.lottery.CashDifferenceProcess;
import com.jy.process.system.reconciliation.lottery.CashPrizeProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @文件名:CashDifferenceController.java
 * @功能描述：
 * @创建日期:2017年3月16日下午1:50:24
 * @创建人：xin
 * @Copyright（c） 2017, all rights reserved by days
 */
@Controller
@RequestMapping("/backstage/cashDifference/")
public class CashDifferenceController extends BaseController<CashDifference> {


    @Autowired
    private CashDifferenceProcess cashDifferenceProcess;

    @Autowired
    private CashPrizeProcess cashPrizeProcess;

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/reconciliation/cashDifference";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * @param page
     * @return AjaxRes
     * @方法功能描述：获取兑奖对账汇总列表
     * @author xin
     * @创建时间： 2017年3月20日上午11:31:35
     */
    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<CashPrizeDifference> page) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/cashDifference/index"))) {
            try {
                String beginTime = getRequest().getParameter("beginTime");
                String endTime = getRequest().getParameter("endTime");
                Page<CashPrizeDifference> reports;
                if (null == beginTime || beginTime.length() == 0 || beginTime == "" ||
                        null == endTime || endTime.length() == 0 || endTime == "") {
                    reports = this.cashPrizeProcess.findAll(null, null, page);
                } else {
                    reports = this.cashPrizeProcess.findAll(DateUtils.StringToDate(beginTime), DateUtils.StringToDate(endTime), page);
                }
                Map<String, Object> p = new HashMap<>();
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
     * @param page
     * @return AjaxRes
     * @方法功能描述:调用对账逻辑，获取兑奖详细明细
     * @author Xin
     * @创建时间： 2017年3月21日下午6:58:45
     */
    @RequestMapping(value = "buill", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes buill(Page<CashDiffDetail> page) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/backstage/cashDifference/buillIndex"))) {
            try {
                CashDiffDetail m = new CashDiffDetail();
                String date = getRequest().getParameter("billDate");
                //改成调用process层
                Page<CashDiffDetail> reports = cashPrizeProcess.findAllDetail(DateUtils.StringToDate(date), page);
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
     * @return String
     * @方法功能描述:初始化跳转页面
     * @author xin
     * @创建时间： 2017年3月21日下午6:58:04
     */
    @RequestMapping("buillIndex")
    public String buillIndex(Model model, String billDate) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("billDate", billDate);
            return "/system/finance/reconciliation/childCashDifference";
        }
        return Const.NO_AUTHORIZED_URL;
    }



    // 依据用户方案编号查询
    @RequestMapping(value = "schemeExtendId", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes schemeExtendId(Page<CashDiffDetail> page) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/backstage/cashDifference/buillIndex"))) {
            try {
                CashDiffDetail m = new CashDiffDetail();
                String schemeExtendId = getRequest().getParameter("schemeExtendId");
                String billDate = getRequest().getParameter("billDate");

                String _schemeExtendId = schemeExtendId.trim();
                Page<CashDiffDetail> reports = null;
                if (_schemeExtendId != null && _schemeExtendId != "") {
                    reports = cashDifferenceProcess.findschemeExtendIdDetails(_schemeExtendId, page);
                } else {
                    reports = cashDifferenceProcess.findCashDiffDetails(billDate, page);
                }

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
     * @return String
     * @方法功能描述:兑奖总差异数据重置跳转
     * @author xin
     * @创建时间： 2017年3月27日下午2:57:49
     */
    @RequestMapping("resetData")
    public String resetData(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/reconciliation/cashDifferenceRestData";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * @param page
     * @param c
     * @return AjaxRes
     * @方法功能描述:重置兑奖总差异数据
     * @author Dingj
     * @创建时间： 2017年3月29日下午12:38:18
     */
    @RequestMapping(value = "restBill", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes restBill(Page<CashDifference> page, CashDifference c) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON, "/backstage/cashDifference/resetData"))) {
            try {
                this.cashDifferenceProcess.restTask(DateUtils.parseDate(getRequest().getParameter("beginTime"), "yyyy-MM-dd"), DateUtils.parseDate(getRequest().getParameter("endTime"), "yyyy-MM-dd"), page);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("msg", "数据重置成功!");
                ar.setSucceed(p);

            } catch (Exception e) {
                logger.error(e.toString(), e);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("msg", "该日期未导入兑奖数据!");
                ar.setSucceed(p);
            }
        }
        return ar;
    }

    /**
     * @return Object
     * @方法功能描述：下载 兑奖总报表
     * @author Dingj
     * @创建时间： 2017年3月29日下午4:08:46
     */
    @RequestMapping(value = "downloadCash", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object exportReport() {
        String date = getRequest().getParameter("date");
        List<Object> list = new ArrayList<Object>();
        try {
            String[] dates = date.split("-");
            StringBuffer sb = new StringBuffer();
            sb.append(dates[0]);
            String filePath = "";
            list = cashDifferenceProcess.download(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "该日期对账文件尚未生成", null, JOptionPane.ERROR_MESSAGE);
            return "/system/finance/reconciliation/cashDifferenceRestData";
        }
        return new ResponseEntity<byte[]>((byte[]) list.get(0), (MultiValueMap<String, String>) list.get(1), HttpStatus.OK);
    }

    /**
     * @param date
     * @return Object
     * @方法功能描述:下载兑奖差异明细数据
     * @author xin
     * @创建时间： 2017年3月29日下午4:28:21
     */
    @RequestMapping(value = "downloadDetail", method = {
            RequestMethod.GET, RequestMethod.POST}, produces = "application/MIME")
    public Object exportReportDetail(String date) {
        // String date = getRequest().getParameter("date");
        String[] dates = date.split("-");
        StringBuffer sb = new StringBuffer();
        sb.append(dates[0]);
        String filePath = "";
        filePath = cashDifferenceProcess.findFilePath();
//		List<SysDict> sysDicts = sysDictService.find(new SysDict());
//		for (SysDict sysDict : sysDicts) {
//			if (sysDict.getParamKey().equals("filePath")) {
//				filePath = sysDict.getParamValue();
//			}
//		}
        String _date = sb.toString();
        List<Object> list = new ArrayList<Object>();
        try {
            list = cashDifferenceProcess.downloadDetail(_date);
//			list = DownloadUtil.download(_date, filePath, cashDifferenceProcess.getCashDetailFileName(), cashDifferenceProcess.getSuffix());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "该日期对账文件尚未生成", null, JOptionPane.ERROR_MESSAGE);
            return "/system/finance/reconciliation/cashDifferenceRestData";
        }

        return new ResponseEntity<byte[]>((byte[]) list.get(0), (MultiValueMap<String, String>) list.get(1), HttpStatus.OK);
    }

    /**
     * 录入线下兑奖金额
     *
     * @param id
     * @param offlineRedeemAmount
     * @param onlineRedeemAmount
     * @return
     */
    @RequestMapping(value = "countAumone", method = RequestMethod.POST)
    @ResponseBody
    public String countAumone(String id, String offlineRedeemAmount, String onlineRedeemAmount) {
        try {
            CashDifference cs = new CashDifference();
            cs.setDate(id);
            if (null != onlineRedeemAmount) {
                cs.setOnlineRedeemAmount((new BigDecimal(onlineRedeemAmount)));
            } else {
                cs.setOnlineRedeemAmount(new BigDecimal(0));
            }

            if (null != offlineRedeemAmount) {
                cs.setOfflineRedeemAmount(new BigDecimal(offlineRedeemAmount));
            } else {
                cs.setOfflineRedeemAmount(new BigDecimal(0));
            }
            CashDifference _cs = cashDifferenceProcess.findOfflineRedeemAmount(id);
            if (_cs != null) {
                cs.setOfflineRedeemAmount(cs.getOfflineRedeemAmount().add(_cs.getOfflineRedeemAmount()));
            }
            BigDecimal countAumone = cs.getOnlineRedeemAmount().add(cs.getOfflineRedeemAmount());
            cs.setTotalRedeemAmount(countAumone);

            cashDifferenceProcess.updateCountAumone(cs);
//			cashDifferenceService.updateCountAumone(cs);

        } catch (NumberFormatException e) {
            logger.info("保存线下手工录入数据失败！");
            e.printStackTrace();
        }
        // TODO
        return "redirect:/backstage/cashDifference/findByPage";
    }

}

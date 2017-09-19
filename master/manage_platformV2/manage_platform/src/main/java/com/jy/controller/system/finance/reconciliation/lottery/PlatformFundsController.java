package com.jy.controller.system.finance.reconciliation.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.lottery.PlatformFunds;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.from.system.finance.reconciliation.lottery.PlatformFundsForm;
import com.jy.from.system.finance.reconciliation.lottery.SalesDifferencesDetailForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.lottery.LotteryFundsProcess;
import com.jy.vo.system.reconciliation.lottery.FundsActivityReportVo;
import com.jy.vo.system.reconciliation.lottery.PlatFormFundsRunningVo;
import com.jy.vo.system.reconciliation.lottery.PlatFormFundsVo;
import com.jy.vo.system.reconciliation.lottery.SalesDifferencesDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backstage/reconciliation/")
public class PlatformFundsController extends BaseController<PlatformFunds> {

 @Autowired
 private LotteryFundsProcess process;
 @Autowired
 private ManualProcess manualProcess;

 /**
  * 查询当前日期平台汇总资金
  *
  * @param model
  * @return
  */
 @RequestMapping("platformFundsIndex")
 public String platformFundsIndex(Model model) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
   return "/system/finance/reconciliation/platformFundsIndex";
  }
  return Const.NO_AUTHORIZED_URL;
 }

 @RequestMapping("activityFundsIndex")
 public String activityFundsIndex(Model model) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
   return "/system/finance/reconciliation/lottery/funds/activityFundsIndex";
  }
  return Const.NO_AUTHORIZED_URL;
 }


 /**
  * 根据日期查询日期平台汇总资金
  *
  * @param page
  * @param form
  * @return
  */
 @RequestMapping(value = "findFundsListByPage", method = RequestMethod.POST)
 @ResponseBody
 public AjaxRes findFundsListByPage(Page<PlatFormFundsVo> page, PlatformFundsForm form) {
  AjaxRes ar = getAjaxRes();
  if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/reconciliation/platformFundsIndex"))) {
   try {
    //參數檢查
    if (!process.findFundsListCheck(form)) {
     ar.setRes(0);
     ar.setFailMsg(Const.PARAMETER_ERROR);
     return ar;
    }
    //數據查詢
    Page<PlatFormFundsVo> list = process.findFundsList(form, page);
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


 /**
  * 活动资金报表
  * @param page
  * @param form
     * @return
     */
 @RequestMapping(value = "findActivityFundsListByPage", method = RequestMethod.POST)
 @ResponseBody
 public AjaxRes findActivityFundsListByPage(Page<FundsActivityReportVo> page, PlatformFundsForm form) {
  AjaxRes ar = getAjaxRes();
  if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/reconciliation/activityFundsIndex"))) {
   try {
    //參數檢查
    if (!process.findFundsListCheck(form)) {
     ar.setRes(0);
     ar.setFailMsg(Const.PARAMETER_ERROR);
     return ar;
    }
    //數據查詢
    Page<FundsActivityReportVo> list = process.findActivityFundsList(form, page);
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

 /**
  * 查询当前日期第三方流水
  *
  * @param model
  * @return
  */
 @RequestMapping("platformFundsRunning")
 public String platformFundsRunning(Model model) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
   return "/system/finance/reconciliation/platformFundsRunning";
  }
  return Const.NO_AUTHORIZED_URL;
 }

 /**
  * 根据日期查询日期平台资金流水汇总对账数据
  *
  * @param page
  * @param form
  * @return
  */
 @RequestMapping(value = "findFundsRunningListByPage", method = RequestMethod.POST)
 @ResponseBody
 public AjaxRes findFundsRunningListByPage(Page<PlatFormFundsRunningVo> page, PlatformFundsForm form) {
  AjaxRes ar = getAjaxRes();
  if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/reconciliation/platformFundsIndex"))) {
   try {
    //參數檢查
    if (!process.findFundsListCheck(form)) {
     ar.setRes(0);
     ar.setFailMsg(Const.PARAMETER_ERROR);
     return ar;
    }
    //數據查詢
    Page<PlatFormFundsRunningVo> funds = process.findRunningList(form, page);
    Map<String, Object> p = new HashMap<String, Object>();
    p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
    p.put("list", funds);
    ar.setSucceed(p);
   } catch (Exception e) {
    logger.error(e.toString(), e);
    ar.setFailMsg(Const.DATA_FAIL);
   }
  }
  return ar;
 }



//------------------------保存,查看平台差异处理结果，开始------------------------------------------------

 @RequestMapping("dealPlatFormFundsDive")
 public String findDivePageForDeal(Model model,String dzDate,Integer type) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   model.addAttribute("dzDate", dzDate);
   model.addAttribute("type", type);
   return "/system/finance/reconciliation/dealPlatFormFundsDive";
  }
  return Const.NO_AUTHORIZED_URL;
 }


 @RequestMapping(value = "saveDive", method = RequestMethod.POST)
 @ResponseBody
 public AjaxRes saveDive( SalesDifferencesDetailForm form) {
  AjaxRes ar = getAjaxRes();
  try {
  process.saveFundsDive(form);
   ar.setSucceedMsg(Const.SAVE_SUCCEED);
  } catch (Exception e) {
   logger.error(e.toString(), e);
   ar.setFailMsg(Const.SAVE_FAIL);
  }
  return ar;
 }


 @RequestMapping("showPlatFormFundsDive")
 public String showPlatFormFundsDive(Model model,String dzDate,Integer type) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   model.addAttribute("dzDate", dzDate);
   model.addAttribute("type", type);
   return "/system/finance/reconciliation/showPlatFormFundsDive";
  }
  return Const.NO_AUTHORIZED_URL;
 }



 //------------------------第三方流水差异对账处理，开始-----------------------------------------------
    @RequestMapping(value = "dealFundsRunningDive", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes dealFundsRunningDive( SalesDifferencesDetailForm form) {
        AjaxRes ar = getAjaxRes();
        try {
         process.saveRunningDive(form);
            ar.setSucceedMsg(Const.SAVE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.SAVE_FAIL);
        }
        return ar;

    }


 @RequestMapping("showFundsRunningDive")
 public String showFundsRunningDive(Model model,String dzDate) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
   model.addAttribute("dzDate", dzDate);
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
   return "/system/finance/reconciliation/showFundsRunningDive";
  }
  return Const.NO_AUTHORIZED_URL;
 }

 @RequestMapping(value = "getRunningDiveList", method = RequestMethod.POST)
 @ResponseBody
 public AjaxRes getRunningDiveList(Page<SalesDifferencesDetailVo> page, SalesDifferencesDetailForm form) {
  AjaxRes ar = getAjaxRes();
  try {
   //數據查詢
   Page<SalesDifferencesDetailVo> list =process.findDiffList(form,page);
   Map<String, Object> p = new HashMap<String, Object>();
   p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   p.put("list", list);
   ar.setSucceed(p);
  } catch (Exception e) {
   logger.error(e.toString(), e);
   ar.setFailMsg(Const.DATA_FAIL);
  }
  return ar;
 }


 /**
  * 第三方对账重置页面
  * @param model
  * @return
     */
 @RequestMapping("resetData")
 public String resetData(Model model){
  if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   return "/system/finance/reconciliation/resetData";
  }
  return Const.NO_AUTHORIZED_URL;
 }


 /**
  * 第三方对账重置
  * @return
     */
 @RequestMapping(value="manual", method= RequestMethod.POST)
 @ResponseBody
 public AjaxRes manual(BaseForm form){
  AjaxRes ar=getAjaxRes();
  if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/reconciliation/resetData"))){
   try {
    manualProcess.manualByType(form,1);
    Map<String, Object> p=new HashMap<String, Object>();
    p.put("msg", "数据重置成功!");
    ar.setSucceed(p);
   }catch (Exception e){
    logger.error(e.toString(),e);
    Map<String, Object> p=new HashMap<String, Object>();
    p.put("msg", "数据重置失败!");
    ar.setSucceed(p);
   }}
  return ar;
 }


 /**
  * 平台资金重置页面
  * @param model
  * @return
     */
 @RequestMapping("platResetData")
 public String platResetData(Model model){
  if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   return "/system/finance/reconciliation/platResetData";
  }
  return Const.NO_AUTHORIZED_URL;
 }


 /**
  * 平台资金重置
  * @return
     */
 @RequestMapping(value="platManual", method= RequestMethod.POST)
 @ResponseBody
 public AjaxRes platManual(BaseForm form){
  AjaxRes ar=getAjaxRes();
  if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/reconciliation/platResetData"))){
   try {
    manualProcess.manualByType(form,2);
    Map<String, Object> p=new HashMap<String, Object>();
    p.put("msg", "数据重置成功!");
    ar.setSucceed(p);
   }catch (Exception e){
    logger.error(e.toString(),e);
    Map<String, Object> p=new HashMap<String, Object>();
    p.put("msg", "数据重置失败!");
    ar.setSucceed(p);
   }}
  return ar;
 }
 @RequestMapping("dealFundsRunningDivePage")
 public String dealFundsRunningDivePage(Model model,String dzDate) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   model.addAttribute("dzDate", dzDate);
   return "/system/finance/reconciliation/dealFundsRunningDive";
  }
  return Const.NO_AUTHORIZED_URL;
 }

//----------------------下载报表---------------------------------------------------------------/



 @RequestMapping(value = "download", method = {
         RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
 public Object download() {
  String date = getRequest().getParameter("date");
  String type=getRequest().getParameter("type");
  Object obj=process.dowload(date,type);
  return obj;
 }

}

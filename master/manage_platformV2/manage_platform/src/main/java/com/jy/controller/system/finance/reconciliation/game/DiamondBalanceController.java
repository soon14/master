package com.jy.controller.system.finance.reconciliation.game;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.game.DiamondBalance;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.FormProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.games.DiamondProcess;
import com.jy.vo.system.reconciliation.games.DiamondVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backstage/reconciliation/diamondBalance")
public class   DiamondBalanceController extends BaseController<DiamondBalance> {

 @Autowired
 private ManualProcess manualProcess;
 @Autowired
 private DiamondProcess process;
 @Autowired
 private FormProcess formProcess;
 /**
  * 查询当前日期平台汇总资金
  *
  * @param model
  * @return
  */
 @RequestMapping("diamondBalance")
 public String diamondBalance(Model model) {
  if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
   return "/system/finance/reconciliation/game/diamondBalance";
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
 @RequestMapping(value = "findByPage", method = RequestMethod.POST)
 @ResponseBody
 public AjaxRes findByPage(Page<DiamondVo> page, BaseForm form) {
  AjaxRes ar = getAjaxRes();
  if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/reconciliation/diamondBalance/diamondBalance"))) {
   try {
    //數據查詢
    formProcess.check(form);
    Page<DiamondVo> list = process.findByPage(form, page);
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
  * 数据重置页面
  * @param model
  * @return
  */
 @RequestMapping("resetData")
 public String resetData(Model model){
  if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
   model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
   return "/system/finance/reconciliation/game/diamondResetData";
  }
  return Const.NO_AUTHORIZED_URL;
 }


 /**
  * 重置
  * @return
  */
 @RequestMapping(value="manual", method= RequestMethod.POST)
 @ResponseBody
 public AjaxRes manual(BaseForm form){
  AjaxRes ar=getAjaxRes();
  if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/reconciliation/diamondBalance/resetData"))){
   try {
    manualProcess.manualByType(form,7);
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

 //----------------------下载报表---------------------------------------------------------------/



 @RequestMapping(value = "download", method = {
         RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
 public Object download() {
  String date = getRequest().getParameter("date");
  Object obj= process.download(date);
  return obj;
 }



}

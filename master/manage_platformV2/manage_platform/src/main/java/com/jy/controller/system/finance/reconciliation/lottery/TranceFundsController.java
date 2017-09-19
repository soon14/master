package com.jy.controller.system.finance.reconciliation.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.lottery.TranceFunds;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.FormProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.lottery.TranceFundsProcess;
import com.jy.vo.system.reconciliation.lottery.TranceFundsVo;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/backstage/reconciliation/lottery/tranceFunds")
public class TranceFundsController extends BaseController<TranceFunds>{

    @Autowired
    private TranceFundsProcess process;
    @Autowired
    private ManualProcess manualProcess;
    @Autowired
    private FormProcess formProcess;

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/reconciliation/lottery/funds/TranceFundsReport";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<TranceFundsVo> page, BaseForm form) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/reconciliation/lottery/tranceFunds/index"))) {
            try {
                formProcess.check(form);
                Page<TranceFundsVo> list = process.findList(form, page);
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


    @RequestMapping("resetData")
    public String resetData(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            return "/system/finance/reconciliation/lottery/funds/tranceFundsResetData";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    @RequestMapping(value="manual", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes manual(BaseForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/reconciliation/lottery/tranceFunds/resetData"))){
            try {
                manualProcess.manualByType(form,9);
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


    @RequestMapping(value = "download", method = {
            RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
    public Object download() {
        String date = getRequest().getParameter("date");
        Object obj=process.dowload(date);
        return obj;
    }

}

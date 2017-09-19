package com.jy.controller.system.finance.statistics.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.statistics.QrCodeSoldDailyReport;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.from.system.finance.statistics.QrCodeSoldDailyReportForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.FormProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.lottery.qrCode.SoldProcess;import com.jy.vo.system.statistics.lottery.QrCodeSoldVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/13.
 */
@Controller
@RequestMapping("/statistics/qrCodeSoldDailyReport/")
public class QrCodeSoldDailyReportController extends BaseController<QrCodeSoldDailyReport> {

    @Autowired
    private SoldProcess process;
    @Autowired
    private ManualProcess manualProcess;
    @Autowired
    private FormProcess formProcess;

    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/statistics/qrcode/QrCodeSoldDailyReport";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    @RequestMapping("addQrCodePage")
    public String addQrCodePage(Model model,String date){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("date",date);
            return "/system/finance/statistics/qrcode/addQrCodePage";
        }
        return Const.NO_AUTHORIZED_URL;
    }


    @RequestMapping(value="findByPage", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<QrCodeSoldVo> page, BaseForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/statistics/qrCodeSoldDailyReport/index"))){
            try {
                formProcess.check(form);
                Page<QrCodeSoldVo> reports=process.findByPage(page,form);
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


    @RequestMapping(value="add", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes add( QrCodeSoldDailyReportForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/statistics/qrCodeSoldDailyReport/addQrCodePage"))){
            try {
                process.add(form);
                ar.setSucceedMsg(Const.SAVE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }


    /**
     * 重置页面
     * @param model
     * @return
     */
    @RequestMapping("resetData")
    public String resetData(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            return "/system/finance/statistics/qrcode/soldResetData";
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
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/statistics/qrCodeDailyReport/resetData"))){
            try {
                manualProcess.manualByType(form,3);
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


    @RequestMapping(value = "exportReport", method = {
            RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
    public Object exportReport() {
        String date = getRequest().getParameter("date");
        Object obj=process.download(date);
        return obj;
    }
}

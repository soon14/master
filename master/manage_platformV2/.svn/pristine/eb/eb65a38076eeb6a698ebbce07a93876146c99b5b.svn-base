package com.jy.controller.system.finance.statistics.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.statistics.QrCodeDetailDailyReport;
import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.FormProcess;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.lottery.qrCode.DetailProcess;
import com.jy.vo.system.statistics.lottery.QrCodeDetailVo;
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
@RequestMapping("/statistics/qrCodeDetailDailyReport/")
public class QrCodeDetailDailyReportController extends BaseController<QrCodeDetailDailyReport> {

    @Autowired
    private DetailProcess process;
    @Autowired
    private ManualProcess manualProcess;
    @Autowired
    private FormProcess formProcess;

    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/finance/statistics/qrcode/QrCodeDetailDailyReport";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping(value="findByPage", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<QrCodeDetailVo> page, BaseForm form){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/statistics/qrCodeDetailDailyReport/index"))){
            try {
                formProcess.check(form);
                Page<QrCodeDetailVo> reports=process.findByPage(page,form);
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


    /**
     * 重置页面
     * @param model
     * @return
     */
    @RequestMapping("resetData")
    public String resetData(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            return "/system/finance/statistics/qrcode/detailResetData";
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
                manualProcess.manualByType(form,5);
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

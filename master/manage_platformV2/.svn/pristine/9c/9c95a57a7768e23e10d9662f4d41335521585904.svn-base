package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.entity.system.finance.UserVo;
import com.jy.entity.system.finance.statistics.SalesCommissionReport;
import com.jy.from.system.request.UserInfoForm;
import com.jy.mybatis.Page;
import com.jy.process.system.channels.SalesVolumeProcess;
import com.jy.service.system.account.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew on 2017/1/19 0005.
 * 商户销量展示信息
 */
@Controller
@RequestMapping("/backstage/salesVolume/")
public class SalesVolumeController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
    private AccountService aService;

    @Autowired
   private SalesVolumeProcess salesVolumeProcess;

    Logger logger = Logger.getLogger(SalesVolumeController.class);


    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            //过去一天
            c.setTime(new Date());
            c.add(Calendar.DATE, - 1);
            Date d = c.getTime();
            String day = format.format(d);
            System.out.println("过去一天："+day);
            //过去一月
            c.setTime(new Date());
            c.add(Calendar.MONTH, -6);
            Date m = c.getTime();
            String mon = format.format(m);
            System.out.println("过去6个月："+mon);
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("dayAgo",day);
            model.addAttribute("monthAgo",mon);
            return "system/channels/salesVolume";
        }
        return Const.NO_AUTHORIZED_URL;
    }
    /**
     * 该部门的渠道商户,标签用户的页面
     *
     * @param model
     * @return
     */
    @RequestMapping("childMerchantAndSignList")
    public String childMerchantList(Model model, String merchantId,String bTime,String eTime) {
//        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {

            String beginTime = getRequest().getParameter("bTime");
            String endTime = getRequest().getParameter("eTime");
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("merchantId", merchantId);
            model.addAttribute("bTime",bTime );
            model.addAttribute("eTime", eTime);
            return "/system/channels/childMerchantAndSignList";
//        }
//        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping("salesUserList")
    public String salesUserList(Model model, String merchantId,String bTime,String eTime) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("merchantId", merchantId);
            model.addAttribute("bTime",bTime );
            model.addAttribute("eTime", eTime);
            return "/system/channels/salesUserList";
        }
        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping("findchildMerchantInit")
    public String findchildMerchant(Model model,String preId,String bTime,String eTime){
        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("preId", preId);
            model.addAttribute("beginTime", bTime);
            model.addAttribute("endTime", eTime);
            return "/system/channels/childMerchantList";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping(value = "findByPage")
    @ResponseBody
    public AjaxRes findByPage(Page<SalesCommissionReport> page, SalesCommissionReport o) {
        AjaxRes ar = new AjaxRes();
        try {
            ar = salesVolumeProcess.findByPage( page,o,getRequest(), getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return null;
    }
    @RequestMapping(value = "findSvByPage")
    @ResponseBody
    public AjaxRes findSvByPage(Page<SalesCommissionReport> page, SalesCommissionReport o) {
        AjaxRes ar = new AjaxRes();
        HttpServletRequest request = getRequest();
        try {
            ar= salesVolumeProcess.findSvByPage(page, o,
                    request, getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }

    /**
     * 查询销量
     * @param page
     * @param
     * @return
     */
    @RequestMapping(value="childMerchantAndSignList", method= RequestMethod.POST)
    @ResponseBody
    AjaxRes childMerchantAndSignList(Page<CpOrderInfo> page,Model model){
        AjaxRes ar=getAjaxRes();
        HttpServletRequest request =  getRequest();
        model.addAttribute("bTime",request.getParameter("bTime"));
        model.addAttribute("eTime",request.getParameter("eTime"));
        model.addAttribute("merchantId",request.getParameter("merchantId"));
//        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/salesVolume/childMerchantAndSignList"))){
            try {
                ar= salesVolumeProcess.childMerchantAndSignList(page, request, getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
        return ar;
    }
  @RequestMapping(value="salesUserList", method= RequestMethod.POST)
    @ResponseBody
    AjaxRes salesUserList(Page<UserVo> page){
        AjaxRes ar=getAjaxRes();
//        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/salesVolume/salesUserList"))){
            try {
                ar =  salesVolumeProcess.salesUserList(page, getRequest(), getPermitBtn(Const.RESOURCES_TYPE_BUTTON)) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
        return ar;
    }
    @RequestMapping(value="childMerchantList", method= RequestMethod.POST)
    @ResponseBody
    AjaxRes childMerchantList(Page<SalesCommissionReport> page, SalesCommissionReport o){
        AjaxRes ar=getAjaxRes();
        HttpServletRequest request =  getRequest();

        try {
            ar =  salesVolumeProcess.findChildMerchantList(page, o,
                    request, getPermitBtn(Const.RESOURCES_TYPE_BUTTON)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }
    @RequestMapping(value="customerSalesInit")
    public String customerSalesInit(Model model){
//        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/customerSales";
//        }
//        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping(value="countCustomerInit")
    public String countCustomerInit(Model model){
//        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/countCustomer";
//        }
//        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping(value="customerSales", method= RequestMethod.POST)
    @ResponseBody
    AjaxRes customerSales(Page<UserInfoForm> page, UserInfoForm o){
        AjaxRes ar=getAjaxRes();
        HttpServletRequest request =  getRequest();
        try {
            ar =  salesVolumeProcess.customerSales(page, o,
                    request, getPermitBtn(Const.RESOURCES_TYPE_BUTTON)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }
    @RequestMapping("findSaleList")
    public String findSaleListInit(Model model, String userId,String bTime,String eTime) {
//        if (doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)) {

        String beginTime = getRequest().getParameter("bTime");
        String endTime = getRequest().getParameter("eTime");
        model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
        model.addAttribute("userId", userId);
        model.addAttribute("bTime",bTime );
        model.addAttribute("eTime", eTime);
        return "/system/channels/findSaleList";
//        }
//        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping(value="findSaleList" , method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes findSaleList(Page<CpOrderInfo> page) {
        AjaxRes ar=getAjaxRes();
        HttpServletRequest request =  getRequest();
        try {
            ar= salesVolumeProcess.findSaleList(page, request, getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }
    @RequestMapping(value="countCustomer", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes countCustomer(Page<UserInfoForm> page, UserInfoForm o){
        AjaxRes ar=getAjaxRes();
            try {
                HttpServletRequest request =  getRequest();
                Page<UserInfoForm> reports=salesVolumeProcess.countCustomer(o, page,request);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list",reports);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }

        return ar;
    }
}

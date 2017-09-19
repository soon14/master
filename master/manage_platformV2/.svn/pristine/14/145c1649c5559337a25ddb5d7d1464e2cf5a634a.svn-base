package com.jy.controller.system.finance.reconciliation.lottery;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.entity.system.finance.SalesSum;
import com.jy.entity.system.finance.vo.SalesSumDifferencesVO;
import com.jy.service.system.finance.reconciliation.lottery.SalesSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
@Controller
@RequestMapping("/backstage/finance/reconciliation/")
public class SalesSumController extends BaseController<Object>  {
    @Autowired
    public SalesSumService service;

    /**
     * 查询销售差异总报表数据
     * @param model
     * @param dzsdvo
     * @return
     */
    @RequestMapping("salesSumDifferences")
    public String salesSumDifferences(Model model,SalesSumDifferencesVO dzsdvo){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/finance/reconciliation/salesSumDifferences")){
            try {
            List<SalesSumDifferencesVO> list=service.findDifference(dzsdvo.getBeginTime(),dzsdvo.getEndTime(),dzsdvo.getDealResultStatus());
            model.addAttribute("list",list);
            } catch (Exception e) {
                logger.error(e.toString(),e);
            }
            return "/system/finance/reconciliation/salesSumDifferences";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * 查询投注系统销售总报表
     * @param model
     * @param dzsdvo
     * @return
     */
    @RequestMapping(value="salesSum", method= RequestMethod.GET)
    public String salesSum(Model model,SalesSumDifferencesVO dzsdvo){
        AjaxRes ar=getAjaxRes();
        List list=new ArrayList();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/finance/reconciliation/salesSumDifferences"))){
            try {
                service.findSalesSum();
                service.findSalesSumDifferences();
                service.findSalesDifferencesDetail();

                SalesSum sumSales=service.findMarket(dzsdvo.getDatetime());
                list.add(sumSales);
                model.addAttribute("sumSales",list);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return "/system/finance/reconciliation/salesSum";
    }

    /**
     * 查询销售差异明细报表
     * @param model
     * @param dzsdvo
     * @return
     */
    @RequestMapping(value="salesDifferencesDetail", method= RequestMethod.GET)
    public String findByPage(Model model,SalesSumDifferencesVO dzsdvo){
        AjaxRes ar=getAjaxRes();
        List list=new ArrayList();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/finance/reconciliation/salesSumDifferences"))){
            try {
                SalesDifferencesDetail salesDifferencesDetail=service.findDifferencesDetail(dzsdvo.getDatetime());
                list.add(salesDifferencesDetail);
                model.addAttribute("salesDetail",list);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return "/system/finance/reconciliation/salesDifferencesDetail";
    }

}

package com.jy.process.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.entity.system.finance.UserVo;
import com.jy.entity.system.finance.statistics.SalesCommissionReport;
import com.jy.entity.system.resources.Resources;
import com.jy.from.system.request.UserInfoForm;
import com.jy.mybatis.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ZQY on 2017/4/14.
 */
public interface SalesVolumeProcess {
    public AjaxRes findSvByPage(Page<SalesCommissionReport> page, SalesCommissionReport o,
                                HttpServletRequest request,List<Resources> permitBtn) throws Exception;
    public AjaxRes findChildMerchantList(Page<SalesCommissionReport> page, SalesCommissionReport o,
                                HttpServletRequest request,List<Resources> permitBtn) throws Exception;
    public AjaxRes childMerchantAndSignList(Page<CpOrderInfo> page, HttpServletRequest request, List<Resources> permitBtn)throws Exception;
    public AjaxRes salesUserList(Page<UserVo> page ,HttpServletRequest request,List<Resources> permitBtn) throws Exception;
    public AjaxRes findByPage(Page<SalesCommissionReport> page, SalesCommissionReport o,
                              HttpServletRequest request,List<Resources> permitBtn) throws Exception;
    public AjaxRes customerSales(Page<UserInfoForm> page, UserInfoForm o,
                              HttpServletRequest request,List<Resources> permitBtn) throws Exception;
    public AjaxRes findSaleList(Page<CpOrderInfo> page, HttpServletRequest request, List<Resources> permitBtn)throws Exception;
    public Page<UserInfoForm> countCustomer(UserInfoForm o, Page<UserInfoForm> page,HttpServletRequest request);

}

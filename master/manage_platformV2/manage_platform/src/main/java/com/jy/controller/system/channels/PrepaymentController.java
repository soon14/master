package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.channels.ChannelOperationLog;
import com.jy.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.Merchant;
import com.jy.service.system.channels.ChannelOperationLogService;
import com.jy.service.system.channels.PrepaymentService;
import com.jy.service.system.channels.MerchantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Matthew on 2017/1/5 0005.
 */
@Controller
@RequestMapping("/backstage/prepayment/")
public class PrepaymentController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
    private PrepaymentService service;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ChannelOperationLogService channelService;

     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     Logger logger = Logger.getLogger(PrepaymentController.class);


    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "system/channels/prepayment";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * 预存款金额展示页
     */
    @RequestMapping(value="findByPage")
    @ResponseBody
    public AjaxRes findByPage(Page<Prepayment> page, Prepayment o){
        AjaxRes ar= new AjaxRes();
        Account a= AccountShiroUtil.getCurrentUser();
        String userId= AccountShiroUtil.getCurrentUser().getAccountId();
        Merchant mer = merchantService.findMerchant(userId);
            try {
                List<Integer> mid=new ArrayList<>();
//                try {
//                    Page<Merchant> pages=new Page<>();
//                    Merchant merchant=new Merchant();
//                    merchant.setUserId(userId);
//                    List<Merchant> byPage=merchantService.findByPage(merchant);
//                    //等于0则代表是非一级二级三级渠道商查看渠道信息。这时则搜索该用户创建的一级渠道
//                    if(byPage.size()==0){
//                        merchant.setJudge(1);
//                        List<Merchant> merchantPage=merchantService.findByPage(merchant);
//                        for(int i=0;i<merchantPage.size();i++){
//                            mid.add(merchantPage.get(i).getmId());
//                        }
//                    }else {
//                        for(int i=0;i<byPage.size();i++){
//                            mid.add(byPage.get(i).getmId());
//                        }
//                    }
//                } catch (Exception e) {
//                    logger.error(e.toString(),e);
//                    ar.setFailMsg(Const.DATA_FAIL);
//                }
                Map m = new HashMap();
                m.put("mName",o.getMerchantName());
                m.put("userIds",userId);
                m.put("isValid","1");
                List<Merchant> merlist = merchantService.findDataAuthorityMerchant(m);
                if(null!=merlist){
                    for (Merchant me:merlist) {
                        mid.add(me.getmId());
                    }
                }
                Map map=new HashMap();
                int rn= (page.getPageNum()-1)*page.getPageSize();
                if(0==mid.size()) {
                    mid.add(0);
                    map.put("mid", mid);
                }else{
                    map.put("mid",mid);
                }
                if("2".equals(userId)||"3e23061b64864e8e811d222a7cd05034".equals(a.getRoleId())||"05fdf829f596437f993eab1c3e6c8bd8".equals(a.getRoleId())){
                    map.put("mid",null);
                }
                map.put("beginTimes",page.getBeginTime());
                map.put("endTimes",page.getEndTime());
                map.put("merchantName",o.getMerchantName());
                map.put("rn",rn);
                map.put("pageSize",page.getPageSize());
                Page<Prepayment> byPage = service.findByPages(map,page);
                Integer count = service.counts(map);
                if(null!=byPage.getResults()){
                    byPage.setTotalRecord(count);
                }
                List<Prepayment> list = byPage.getResults();
                if(list!=null){
                    for(Prepayment vo:list){
                        /**设置余额*/
                        if(vo.getBalance()!=null){
                            vo.setBalance(vo.getBalance());
                        }else{
                            vo.setBalance(vo.getPayMoney());
                        }
                        /**数据来源标识*/
                        if(vo.getDataType()==1){
                            vo.setDataTypeName("线上");
                        }else{
                            vo.setDataTypeName("线下");
                        }
                        if(null!=vo.getPayType()&&!"".equals(vo.getPayType())){
                            if(vo.getPayType().equals("1")){
                                vo.setPayTypeName("终端机额度");
                            }else if(vo.getPayType().equals("2")){
                                vo.setPayTypeName("其他");
                            }
                        }
                    }
                }
                byPage.setResults(list);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list",byPage);
                if(null!=mer){
                    p.put("mLevel",mer.getmLevel());
                }
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
//        }
        return ar;
    }

    /**
     * 废除预存款金额
     */
    @RequestMapping(value="deletePrepayment")
    @ResponseBody
    public AjaxRes deletePrepayment(Prepayment o){
        AjaxRes ar= new AjaxRes();
        try {
            service.update(o);
//            service.updateBC(o);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        Map<String, Object> p=new HashMap<String, Object>();
        p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        ar.setSucceed(p);
        return ar;
    }

    /**
     * 添加预存款金额
     */
    @RequestMapping(value="create")
    @ResponseBody
    public AjaxRes createCommission(Prepayment o){
        AjaxRes ar= new AjaxRes();
        try {
            String userName= AccountShiroUtil.getCurrentUser().getName();
            o.setChangeTime(new Date());
            o.setUserName(userName);
            o.setPpStatus(1);
            service.insert(o);
//            service.updateBC(o);

        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setSucceedMsg("未成功分配!");
            return ar;
        }
        Map<String, Object> p=new HashMap<String, Object>();
        ar.setSucceedMsg("已成功分配!");
        ar.setSucceed(p);
        return ar;
    }
    /**
     * 同步预存款信息
     */
    @RequestMapping(value="synchro")
    @ResponseBody
    public AjaxRes synchroPrepayment(){
        AjaxRes ar= new AjaxRes();
        String userName= AccountShiroUtil.getCurrentUser().getName();
        try {
            List<Prepayment> list =service.findMerchants();
            if(list.size()!=0){
                service.synchroPrepayment(list);
            }
            /**同步线上信息*/
            List<Prepayment> listPre= service.findMerchantPre();
            if(listPre.size()!=0){
                for(Prepayment page:listPre){
                    page.setPayType("2");
                    /**默认预警金额为5000*/
                    page.setWarningMoney(5000.00);
                    page.setUserName(userName);
                    page.setChangeTime(new Date());
                }
                service.synchroOnline(listPre);
            }
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setSucceedMsg("数据同步失败!");
            return ar;
        }
        Map<String, Object> p=new HashMap<String, Object>();
        ar.setSucceedMsg("数据同步成功!");
        ar.setSucceed(p);
        return ar;
    }

    /**
     * 预存款详情
     */
    @RequestMapping(value="find")
    @ResponseBody
    public AjaxRes findPrepayment(Prepayment o){
        AjaxRes ar= new AjaxRes();
        try {
            o.setPpStatus(0);
            List<Prepayment> list = service.find(o);
            Prepayment pre = list.get(0);
            pre.setPayMoney(0.00);
            Map<String, Object> p=new HashMap<String, Object>();
            p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            p.put("list",pre);
            ar.setSucceed(p);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        return ar;
    }

    /**
     * 修改预存款金额
     */
    @RequestMapping(value="update")
    @ResponseBody
    public AjaxRes updatePrepayment(Prepayment o){
        AjaxRes ar= new AjaxRes();
        try {
            String userName= AccountShiroUtil.getCurrentUser().getName();
            o.setChangeTime(new Date());
            o.setUserName(userName);
            List<Prepayment> list = service.find(o);
            Prepayment pre =list.get(0);
            o.setBalance(o.getBalance()+o.getPayMoney());
            o.setRealBalance(pre.getRealBalance()+o.getPayMoney());
            if(o.getWarningMoney()>o.getBalance()){
                ar.setFailMsg("配置的预警金额不能高于商户预存款金额！");
                return ar;
            }
            service.update(o);
            /**记录操作日志*/
            ChannelOperationLog log = new ChannelOperationLog();
            log.setFuncContent("商户"+o.getMerchantName()+"的预存款余额：+"+formatDoubleNumber(o.getPayMoney())+"元");
            log.setRemarks("剩余金额为："+formatDoubleNumber(o.getBalance())+"元");
            log.setFuncNo(1);
            log.setOperationUser(userName);
            log.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            channelService.insertChannelOperationLog(log);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        Map<String, Object> p=new HashMap<String, Object>();
        p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        ar.setSucceed(p);
        return ar;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return null;
    }

    public String formatDoubleNumber(Double value) {
        if(value != null){
            if(value.doubleValue() != 0.00){
                java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
                return df.format(value.doubleValue());
            }else{
                return "0.00";
            }
        }
        return "";
    }
}

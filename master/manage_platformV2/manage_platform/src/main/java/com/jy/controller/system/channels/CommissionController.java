package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.entity.system.org.Org;
import com.jy.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.Commission;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Payable;
import com.jy.repository.system.org.PositionDao;
import com.jy.service.system.channels.CommissionService;
import com.jy.service.system.channels.MerchantService;
import com.jy.service.system.channels.PayableService;
import com.jy.service.system.org.PositionService;
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
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Matthew on 2017/1/5 0005.
 */
@Controller
@RequestMapping("/backstage/commissions/")
public class CommissionController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
    private CommissionService service;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PayableService payableService;
    @Autowired
    public PositionService positionService;
    @Autowired
    public PositionDao positionDao;

     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     Logger logger = Logger.getLogger(CommissionController.class);


    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "system/channels/commission";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * 佣金日报展示页
     */
    @RequestMapping(value="findByPage")
    @ResponseBody
    public AjaxRes findByPage(Page<Commission> page, Commission o){
        AjaxRes ar= new AjaxRes();
        String userId= AccountShiroUtil.getCurrentUser().getAccountId();
        List<String> orgIds = new ArrayList();
        List<Org> orgs = positionDao.findOrgByUserId(userId);
        if(null!=orgs&&orgs.size()!=0){
            for (Org orgId:orgs){
                orgIds.add(orgId.getOrgId());
            }
        }
        String mContactUser= AccountShiroUtil.getCurrentUser().getLoginName();
        Merchant mer = merchantService.findMerchant(userId);
        String idList = getRequest().getParameter("ids");
        List<String> ids = new ArrayList();
        if(!"".equals(idList)&&null!=idList){
            ids = Arrays.asList(idList.split(","));
        }
            try {
                List<Integer> mid=new ArrayList<>();
                try {
                    Page<Merchant> pages=new Page<>();
                    Merchant merchant=new Merchant();
                    merchant.setUserId(userId);

                    List<Merchant> byPage=merchantService.findByPage(merchant);
                    //等于0则代表是非一级二级三级渠道商查看渠道信息。这时则搜索该用户创建的一级渠道
                    if(byPage.size()==0){
                        merchant.setJudge(1);
                        List<Merchant> merchantPage=merchantService.findByPage(merchant);
                        for(int i=0;i<merchantPage.size();i++){
                            mid.add(merchantPage.get(i).getmId());
                        }
                    }else {
                        for(int i=0;i<byPage.size();i++){
                            mid.add(byPage.get(i).getmId());
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.toString(),e);
                    ar.setFailMsg(Const.DATA_FAIL);
                }
                Map map=new HashMap();
                int rn= (page.getPageNum()-1)*page.getPageSize();
                if(0==mid.size()) {
                    map.put("mid", 0);
                }else{
                    map.put("mid",mid);
                }
                if(ids.size()!=0){
                    map.put("mid",null);
                }
                map.put("beginTime",page.getBeginTime());
                map.put("endTime",page.getEndTime());
                map.put("merchantName",o.getMerchantName());
                map.put("rn",rn);
                map.put("ids",ids);
                map.put("pageSize",page.getPageSize());
                map.put("dataType",o.getDataType());
                Page<Commission> byPage = service.findByPage(map,page);
                Integer count = service.counts(map);
                if(null!=byPage.getResults()){
                    byPage.setTotalRecord(count);
                }
                List<Commission> list = byPage.getResults();
                if(list!=null&&list.size()!=0){
                    for(Commission vo:list){
                        /**设置返佣类型*/
                        if("1".equals(vo.getCommissionType())){
                            vo.setCommissionTypeName("天返");
                        }else if("2".equals(vo.getCommissionType())){
                            vo.setCommissionTypeName("周返");
                        }else if("3".equals(vo.getCommissionType())){
                            vo.setCommissionTypeName("月返");
                        }else if("4".equals(vo.getCommissionType())){
                            vo.setCommissionTypeName("季返");
                        }
                        if(null==vo.getUserName()||"".equals(vo.getUserName())){
                            vo.setUserName("System");
                        }
                        /**设置返佣状态*/
                        if("1".equals(vo.getIsOver())){
                            vo.setIsOverStr("未生成");
                        }else if("2".equals(vo.getIsOver())){
                            vo.setIsOverStr("已生成");
                        }else{
                            vo.setIsOverStr("已作废");
                        }
                        /**设置数据类型*/
                        if(1==vo.getDataType()){
                            vo.setDataTypeName("线上");
                        }else if(2==vo.getDataType()){
                            vo.setDataTypeName("线下");
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
                if(orgIds.contains("3")||orgIds.contains("e76467f85b9e49608bed5a99954ba238")){
                    p.put("mLevel",2);
                }
                if(orgIds.contains("25e6bc4ec14d4b7c8cae3935c7e5dd36")||orgIds.contains("cbc9d8e5dc12453a9827e29920bdaa01")){
                    p.put("mLevel",1);
                }
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        return ar;
    }

    @RequestMapping("resetData")
    public String resetData(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/resetData";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * 同步佣金日报数据
     */
    @RequestMapping(value="synchro")
    @ResponseBody
    public AjaxRes synchroCommison(){
        AjaxRes ar= new AjaxRes();
        String startDate = getRequest().getParameter("beginTime");
        String endDate = getRequest().getParameter("endTime");
        if("".equals(startDate)||"".equals(endDate)){
            ar.setFailMsg("请选择日期！");
            return ar;
        }
        try {
            ar =service.synchroCommison(startDate,endDate);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        return ar;

    }

    /**
     * 同步佣金日报数据.线下
     */
    @RequestMapping(value="synchroOffline")
    @ResponseBody
    public AjaxRes synchroCommisonOffline(){
        AjaxRes ar= new AjaxRes();
        try {
            ar =service.synchroCommisonOffline();
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        return ar;

    }

    /**
     * 生成结算单
     */
    @RequestMapping(value="account")
    @ResponseBody
    public AjaxRes account(Commission o){
        AjaxRes ar= new AjaxRes();
        String[] ids = getRequest().getParameterValues("ids[]");

        if(ids==null){
            ar.setFailMsg("请选择要生成的数据！");
            return ar;
        }
        /**当前用户*/
        String userName= AccountShiroUtil.getCurrentUser().getName();

        try {
            List<Commission> list = service.findByIds(Arrays.asList(ids));
            for(int i= 0;i< list.size()-1;i++){
                if(!"1".equals(list.get(i).getIsOver())){
                    ar.setFailMsg("请选择尚未返佣的数据！");
                    return ar;
                }
                for(int j= list.size()-1;j>i;j--){
                    if(!list.get(j).getMerchantId().equals(list.get(i).getMerchantId()))   {
                        ar.setFailMsg("请选择同一商户的数据！");
                        return ar;
                    }
                }
            }
            Date beginValue = list.get(0).getBeginDate();
            Date endValue = beginValue;
            for(int i= 0;i< list.size()-1;i++){
                if(list.get(i).getBeginDate().before(beginValue)){
                    beginValue=list.get(i).getBeginDate();
                }
                if(list.get(i).getBeginDate().after(endValue)){
                    endValue=list.get(i).getBeginDate();
                }
            }

            BigDecimal money = new BigDecimal(0.000);
            for(Commission co: list) {
                money = money.add(co.getCommission());
            }
            Payable pa= new Payable();
            pa.setCommissionId(list.get(0).getId());
            pa.setMerchantId(list.get(0).getMerchantId());
            pa.setMoney(money);
            pa.setUserName(userName);
            pa.setPayDate(new Date());
            pa.setStatus(1);
            pa.setBeginDate(beginValue);
            pa.setEndDate(endValue);
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < ids.length; i++){
                sb. append(ids[i]);
                if(i<ids.length-1){
                    sb.append(",");
                }
            }
            pa.setIds(sb.toString());
            payableService.insert(pa);
            service.updateStatus(Arrays.asList(ids));
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        Map<String, Object> p=new HashMap<String, Object>();
        p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        ar.setSucceed(p);
        ar.setSucceedMsg("操作成功！");
        return ar;
    }

    /**
     * 作废选中佣金数据
     */
    @RequestMapping(value="delete")
    @ResponseBody
    public AjaxRes deleteCommission(Commission o){
        AjaxRes ar= new AjaxRes();
        String id = getRequest().getParameter("id");
        try {
            o.setId(Integer.valueOf(id));
            service.update(o);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        Map<String, Object> p=new HashMap<String, Object>();
        p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        ar.setSucceed(p);
        ar.setSucceedMsg("操作成功！");
        return ar;
    }


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return null;
    }
}

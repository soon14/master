package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.ChannelOperationLog;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.entity.system.org.Org;
import com.jy.mybatis.Page;
import com.jy.service.system.channels.ChannelOperationLogService;
import com.jy.service.system.channels.MerchantService;
import com.jy.service.system.channels.PrepaymentExtendService;
import com.jy.service.system.channels.PrepaymentService;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Matthew on 2017/7/13 0005.
 */
@Controller
@RequestMapping("/backstage/prepaymentExtend/")
public class PrepaymentExtendController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
    private PrepaymentService preService;
    @Autowired
    private PrepaymentExtendService service;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ChannelOperationLogService channelService;
    @Autowired
    public PositionService positionService;

     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     Logger logger = Logger.getLogger(PrepaymentExtendController.class);

    @RequestMapping("index")
    public String index(Model model,String id){
//        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            Prepayment p = new Prepayment();
            if (id != null) {
                p.setId(Integer.parseInt(id));
                List<Prepayment> list = preService.find(p);
                Prepayment pre = list.get(0);
                model.addAttribute("merchantId", pre.getMerchantId());
            }
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "system/channels/prepaymentExtend";
//        }
    }

    /**
     * 预存款.线下二级渠道金额展示页
     */
    @RequestMapping(value="findByPage")
    @ResponseBody
    public AjaxRes findByPage(Page<PrepaymentExtend> page, PrepaymentExtend o){
        AjaxRes ar= new AjaxRes();
        String merchantId =getRequest().getParameter("merchantId");
        String userId= AccountShiroUtil.getCurrentUser().getAccountId();
        Merchant mer = merchantService.findMerchant(userId);
            try {
                Map map=new HashMap();
                int rn= (page.getPageNum()-1)*page.getPageSize();
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
//                Map m = new HashMap();
//                List<Org> l = positionService.findDataAuthority(userId);
//                List userIds = new ArrayList();
//                for (int i = 0; i < l.size(); i++) {
//                    userIds.add(l.get(i).getAccountId());
//                }
//                m.put("mName",o.getMerchantName());
//                m.put("userIds",userId);
//                m.put("isValid","1");
//                List<Merchant> merlist = merchantService.findDataAuthoritySalesManager(m);
//                if(null!=merlist){
//                    for (Merchant me:merlist) {
//                        mid.add(me.getmId());
//                    }
//                }

                if("".equals(merchantId)&&0!=mid.size()){
                    map.put("mid",mid);
                }
                if(0==mid.size()) {
                    map.put("mid", 000);
                }else if(mer.getmLevel()==1){
                    map.put("mid",null);
                }else{
                    map.put("mid",mid);
                }
                map.put("merchantId",merchantId);
                map.put("beginTimes",page.getBeginTime());
                map.put("endTimes",page.getEndTime());
                map.put("merchantName",o.getMerchantName());
                map.put("rn",rn);
                map.put("pageSize",page.getPageSize());

                Page<PrepaymentExtend> byPage = service.findByPage(map,page);
                Integer count = service.counts(map);
                if(null!=byPage.getResults()){
                    byPage.setTotalRecord(count);
                }
                List<PrepaymentExtend> list = byPage.getResults();
                if(list!=null){
                    for(PrepaymentExtend vo:list){
                        /**设置余额*/
                        if(vo.getBalance()!=null){
                            vo.setBalance(vo.getBalance());
                        }else{
                            vo.setBalance(vo.getPayMoney());
                        }
                        /**数据来源标识*/
                        if(vo.getDataType()!=null){
                            if(vo.getDataType()==1){
                                vo.setDataTypeName("线上");
                            }else{
                                vo.setDataTypeName("线下");
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
        return ar;
    }

    /**
     * 预存款.线下二级渠道详情
     */
    @RequestMapping(value="findByMId")
    @ResponseBody
    public AjaxRes findByMId(Page<PrepaymentExtend> page,PrepaymentExtend o){
        AjaxRes ar= new AjaxRes();
        try {
            String userId= AccountShiroUtil.getCurrentUser().getAccountId();
            Merchant mer = merchantService.findMerchant(userId);
            Page<PrepaymentExtend> byPage = service.findByMId(o.getmId().toString(),page);
            List<PrepaymentExtend> list = byPage.getResults();
            if(list!=null){
                for(PrepaymentExtend vo:list){
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
//                    if(null!=vo.getPayType()&&!"".equals(vo.getPayType())){
//                        if(vo.getPayType().equals("1")){
//                            vo.setPayTypeName("终端机额度");
//                        }else if(vo.getPayType().equals("2")){
//                            vo.setPayTypeName("其他");
//                        }
//                    }
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
        return ar;
    }

    /**
     * 预存款详情
     */
    @RequestMapping(value="find")
    @ResponseBody
    public AjaxRes findById(PrepaymentExtend o){
        AjaxRes ar= new AjaxRes();
        try {
            List<PrepaymentExtend> list = service.find(o);
            PrepaymentExtend pre = list.get(0);
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
     * 下级预存款查看.为二级渠道配置预警金额
     */
    @RequestMapping(value="setWarning")
    @ResponseBody
    public AjaxRes setWarning(PrepaymentExtend o){
        AjaxRes ar= new AjaxRes();
        try {
            if(null==o.getWarningMoney()){
                ar.setFailMsg("请输入预警金额！");
            }
            PrepaymentExtend pre = new PrepaymentExtend();
            List<PrepaymentExtend> list = service.find(o);
            PrepaymentExtend prepaymentExtend = list.get(0);
            if(prepaymentExtend.getBalance()==0){
                ar.setFailMsg("请先分配预存款金额！");
                return ar;
            }
            if(o.getWarningMoney()>prepaymentExtend.getBalance()){
                ar.setFailMsg("您配置的预警金额已高于商户预存款金额！");
            }
            String userName= AccountShiroUtil.getCurrentUser().getName();
            pre.setId(o.getId());
            pre.setChangeTime(new Date());
            pre.setCreateUser(userName);
            pre.setWarningMoney(o.getWarningMoney());
            service.update(pre);
            /**记录操作日志*/
            ChannelOperationLog log = new ChannelOperationLog();
            log.setFuncContent("商户"+prepaymentExtend.getMerchantName()+"的预存款线下二级渠道预警金额修改为：+"+formatDoubleNumber(o.getWarningMoney())+"元");
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
        if(ar.getResMsg()==null){
            ar.setSucceedMsg("配置成功！");
        }
        return ar;
    }

    /**
     * 预存款调配.修改渠道金额
     */
    @RequestMapping(value="update")
    @ResponseBody
    public AjaxRes update(PrepaymentExtend o){
        AjaxRes ar= new AjaxRes();
        try {
            if(null==o.getPayMoney()){
                ar.setFailMsg("请输入分配金额！");
                return ar;
            }
            if(o.getPreEId().equals(o.getPreEIds())){
                ar.setFailMsg("本系统禁止一切无聊操作！");
                return ar;
            }
            PrepaymentExtend pre = new PrepaymentExtend();
            String userName= AccountShiroUtil.getCurrentUser().getName();
            pre.setChangeTime(new Date());
            pre.setCreateUser(userName);
            pre.setBalance(o.getPayMoney());
            if(o.getPreEId().contains("parentMerchant")){
                String id = o.getPreEId().substring(15);
                pre.setId(Integer.parseInt(id));
                Prepayment p =new Prepayment();
                p.setId(Integer.parseInt(id));
                List<Prepayment> list = preService.find(p);
                Prepayment prepayment =list.get(0);
                if(prepayment.getRealBalance()<o.getPayMoney()){
                    ar.setFailMsg("余额不足！");
                    return ar;
                }
                service.deployPre(pre);
            }else{
                pre.setId(Integer.parseInt(o.getPreEId()));
                List<PrepaymentExtend> list = service.find(pre);
                PrepaymentExtend prepaymentExtend =list.get(0);
                if(prepaymentExtend.getBalance()<o.getPayMoney()){
                    ar.setFailMsg("余额不足！");
                    return ar;
                }
                service.deploy(pre);
            }
            if(o.getPreEIds().contains("parentMerchant")){
                String id = o.getPreEIds().substring(15);
                pre.setId(Integer.parseInt(id));
                service.deployPres(pre);
            }else{
                pre.setId(Integer.parseInt(o.getPreEIds()));
                service.deploys(pre);
            }
            /**记录操作日志*/
            ChannelOperationLog log = new ChannelOperationLog();
            log.setFuncContent("商户"+o.getMerchantName()+"的预存款线下二级渠道余额：+"+formatDoubleNumber(o.getPayMoney())+"元");
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

    /**
     * 预存款调配.查询线下二级渠道
     */
    @RequestMapping(value="findPreE")
    @ResponseBody
    public AjaxRes findPreE(Page<PrepaymentExtend> page, PrepaymentExtend o){
        AjaxRes ar= new AjaxRes();
        try {
            Page<PrepaymentExtend> byPage =new Page();
            List<PrepaymentExtend> list = service.findPreE(o);
            byPage.setResults(list);
            Map<String, Object> p=new HashMap<String, Object>();
            p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
            p.put("list",byPage);
            ar.setSucceed(p);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
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

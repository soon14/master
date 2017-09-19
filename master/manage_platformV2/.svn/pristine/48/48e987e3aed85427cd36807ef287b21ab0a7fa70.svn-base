package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Payable;
import com.jy.service.system.channels.MerchantService;
import com.jy.service.system.channels.PayableService;
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
@RequestMapping("/backstage/payable/")
public class PayableController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
    private PayableService service;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private PayableService payableService;

     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     Logger logger = Logger.getLogger(PayableController.class);


    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "system/channels/payable";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * 预收款金额展示页
     */
    @RequestMapping(value="findByPage")
    @ResponseBody
    public AjaxRes findByPage(Page<Payable> page, Payable o){
        AjaxRes ar= new AjaxRes();
        String userId= AccountShiroUtil.getCurrentUser().getAccountId();
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
                    map.put("mid", 000);
                }else{
                    map.put("mid",mid);
                }
                map.put("beginTime",page.getBeginTime());
                map.put("endTime",page.getEndTime());
                map.put("merchantName",o.getMerchantName());
                map.put("rn",rn);
                map.put("pageSize",page.getPageSize());
                Page<Payable> byPage = service.findByPage(map,page);
                Integer count = service.counts(map);
                if(null!=byPage.getResults()){
                    byPage.setTotalRecord(count);
                }
                List<Payable> list = byPage.getResults();
                if(list!=null){
                    for(Payable vo:list){
                        /**设置支付状态*/
                        if(1==vo.getStatus()){
                            vo.setStatusName("待付款");
                        }else if(2==vo.getStatus()){
                            vo.setStatusName("付款中");
                        }else{
                            vo.setStatusName("付款成功");
                        }
                        /**设置支付方式*/
                        if("0".equals(vo.getPayWay())){
                            vo.setPayWayStr("-");
                        }else if("1".equals(vo.getPayWay())){
                            vo.setPayWayStr("微信");
                        }else{
                            vo.setPayWayStr("支付宝");
                        }

                    }
                }
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


    /**
     * 对选中数据进行付款操作
     */
    @RequestMapping(value="payment")
    @ResponseBody
    public AjaxRes payment(Payable o){
        AjaxRes ar= new AjaxRes();
        String id = getRequest().getParameter("id");
        /**当前用户*/
        String userName= AccountShiroUtil.getCurrentUser().getName();
        try {
            o.setPayWay("1");
            o.setId(Integer.valueOf(id));
            o.setStatus(2);
            o.setUserName(userName);
            Payable pa = service.findById(id);
            if(2==pa.getStatus()){
                o.setStatus(3);
            }
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
    /**
     * 查看佣金明细
     *
     * @param model
     * @return
     */
    @RequestMapping("commissionList")
    public String childMerchantList(Model model,String ids){
            model.addAttribute("ids", ids);
            return "/system/channels/commission";
    }



    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return null;
    }
}

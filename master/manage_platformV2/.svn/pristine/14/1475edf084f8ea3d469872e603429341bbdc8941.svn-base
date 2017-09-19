package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.PageCalculation;
import com.jy.entity.system.org.Org;
import com.jy.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.Merchant;
import com.jy.service.system.channels.AuditService;
import com.jy.service.system.org.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 * 审核（一次）
 */
@Controller
@RequestMapping("/channels/audit/")
public class AuditController extends BaseController<Object> {

    @Autowired
    private AuditService service;

    @Autowired
    public PositionService positionService;

    /**
     * 审核页面
     * @param model
     * @return
     */
    @RequestMapping("auditIndex")
    public String salesSumDifferences(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/audit";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
     * 审核数据查询(一审)
     * @param page
     * @param o
     * @return
     */
    @RequestMapping(value="findByPage", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<Merchant> page, Merchant o){
        String userId= AccountShiroUtil.getCurrentUser().getAccountId();
        String mContactUser= AccountShiroUtil.getCurrentUser().getLoginName();
        o.setUserId(userId);
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/channels/audit/auditIndex"))){
            try {

                List<Org> l = positionService.findDataAuthority(userId);
                List userIds = new ArrayList();
                for (int i = 0; i < l.size(); i++) {
                    userIds.add(l.get(i).getAccountId());
                }
                Map m = new HashMap();
                m.put("mContactUser",mContactUser);
                m.put("userIds",userIds);
                m.put("mName",o.getmName());
                List<Merchant> list = service.findOneExamineMerchant(m);
                Page<Merchant> byPage = convertPageSize(list, page);
                Map<String, Object> p=new HashMap<String, Object>();
                p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list",byPage);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }


    public Page<Merchant> convertPageSize(List<Merchant> list, Page<Merchant> page) {
        page.setResults(list);
        page.setPageSize(page.getPageSize());
        page.setPageNum(page.getPageNum());
        page = (Page<Merchant>) PageCalculation.getPageCalculation(page);
        if (null != page.getResults()) {
            page.setTotalRecord(list.size());
        }
        return page;
    }

    /**
     * 审核(一审)通过
     * @param
     * @param o
     * @return
     */
    @RequestMapping(value="auditThrough", method=RequestMethod.POST)
    @ResponseBody
    public AjaxRes auditThrough(Merchant o){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                o.setmStatus(1);
                Integer res=service.updateMerchant(o);
                if(res==1) ar.setSucceedMsg("审核成功");
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg("审核失败");
            }
        }
        return ar;
    }

    /**
     * 审核(一审)拒绝
     * @param
     * @param o
     * @return
     */
    @RequestMapping(value="auditRefused", method=RequestMethod.POST)
    @ResponseBody
    public AjaxRes audit(Merchant o){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                o.setmStatus(3);
                Integer res=service.updateMerchant(o);
                if(res==1) ar.setSucceedMsg("拒绝成功");
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg("审核失败");
            }
        }
        return ar;
    }

}

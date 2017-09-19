package com.jy.controller.system.account;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jy.entity.system.org.Position;
import com.jy.service.system.account.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.ajax.AjaxRes;
import com.jy.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.entity.utils.ZNodes;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.account.Account;

@Controller
@RequestMapping("/backstage/account/")
public class AccountController extends BaseController<Account> {

    @Autowired
    private AccountService service;

    @RequestMapping("index")
    public String index(Model model) {
        if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/account/list";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    @RequestMapping(value = "roleTree", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes roleTree() {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/account/index"))) {
            try {
                List<ZNodes> list = service.getRoles();
                ar.setSucceed(list);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "findByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<Account> page, Account o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/account/index"))) {
            try {
                Page<Account> accounts = service.findByPage(o, page);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
                p.put("list", accounts);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes add(Account o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                o.setAccountId(get32UUID());
                int res = service.insertAccount(o);
                if (res == 1){
                    ar.setSucceedMsg(Const.SAVE_SUCCEED);
                } else{
                    ar.setFailMsg("登录名已存在");
                }
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.SAVE_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "delBatch", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes delBatch(String chks) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                service.deleteBatchAccount(chks);
                ar.setSucceedMsg(Const.DEL_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes find(Account o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                List<Account> listAccount = service.find(o);
                List<Account> listRole = service.findRoleById(o.getAccountId());
                Account acount = listAccount.get(0);
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("acount",acount);
                p.put("listRole",listRole);
                ar.setSucceed(p);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes update(Account o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                service.updateRoleId(o);
                o.setUpdateTime(new Date());
                service.update(o);
                ar.setSucceedMsg(Const.UPDATE_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.UPDATE_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes del(Account o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                service.deleteAccount(o);
                ar.setSucceedMsg(Const.DEL_SUCCEED);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "resetPwd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes resetPwd(Account o) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
            try {
                o.setPassword(getPageData().getString("pwd"));
                int res = service.sysResetPwd(o);
                if (res == 1) ar.setSucceedMsg(Const.UPDATE_SUCCEED);
                else ar.setFailMsg(Const.UPDATE_FAIL);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.UPDATE_FAIL);
            }
        }
        return ar;
    }

    @RequestMapping(value = "setSetting", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes setSetting(String skin) {
        AjaxRes ar = getAjaxRes();
        try {
            service.setSetting(skin);
            ar.setSucceedMsg(Const.UPDATE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.UPDATE_FAIL);
        }
        return ar;
    }

    @RequestMapping(value = "getPerData", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes getPerData() {
        AjaxRes ar = getAjaxRes();
        try {
            Account account = service.getPerData();
            ar.setSucceed(account);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        return ar;
    }

    @RequestMapping(value = "setHeadpic", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes setHeadpic(Account o) {
        AjaxRes ar = getAjaxRes();
        try {
            service.setHeadpic(o);
            ar.setSucceedMsg(Const.UPDATE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.UPDATE_FAIL);
        }
        return ar;
    }

    @RequestMapping(value = "setPerData", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes setPerData(Account o) {
        AjaxRes ar = getAjaxRes();
        try {
            service.setPerData(o);
            ar.setSucceedMsg(Const.UPDATE_SUCCEED);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.UPDATE_FAIL);
        }
        return ar;
    }

    @RequestMapping(value = "preResetPWD", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes resetPWD(String opwd, String npwd, String qpwd) {
        AjaxRes ar = getAjaxRes();
        try {
            int res = service.preResetPwd(opwd, npwd, qpwd);
            if (res == 1) ar.setSucceedMsg(Const.UPDATE_SUCCEED);
            else if (res == 2) ar.setFailMsg("密码不正确");
            else if (res == 3) ar.setFailMsg("两次密码不一致");
        } catch (Exception e) {
            logger.error(e.toString(), e);
            ar.setFailMsg(Const.UPDATE_FAIL);
        }
        return ar;
    }

    /**
     * 获取登录账户的部门级别信息
     *
     * @return
     */
    public List<Position> getPoss() {
        List<Position> positions = service.getPerPoss();
        return positions;
    }

    public List<String> getAccountId(List<String> orgIds) {
        List<String> accountIds = service.getAllAccountByOrgId(orgIds);
        return accountIds;
    }

}

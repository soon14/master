package com.jy.controller.system.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jy.service.system.org.OrgService;
import com.jy.service.system.org.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.ajax.AjaxRes;
import com.jy.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.entity.utils.ZNodes;
import com.jy.common.utils.webpage.PageData;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.org.Org;
import com.jy.entity.system.org.Position;

/*
 * 职务管理
 */
@Controller
@RequestMapping("/backstage/org/position/")
public class PositionController extends BaseController<Position> {
	
	@Autowired
	private PositionService service;
	
	@Autowired
	public OrgService orgService;
	
	private static final String SECURITY_URL = "/backstage/org/position/index";
	
	/**
	 * 职务管理首页
	 */
	@RequestMapping("index")
	public String index(Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/org/position/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value = "getOrgAndPositionTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgAndPositionTree() {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				List<ZNodes> r = service.getOrgAndPositionTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "getOrgRoleTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgRoleTree() {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				List<ZNodes> r = service.getOrgRoleTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "getPreOrgTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPreOrgTree() {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				List<ZNodes> r = service.getPreOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "findPos", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPos(Position o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				List<Position> list = service.find(o);
				Position position = list.get(0);
				ar.setSucceed(position);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "updatePos", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePos(Position o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
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
	
	@RequestMapping(value = "delPos", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delPos(Position o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				service.deletePos(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "findArrangeAccByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findArrangeAccByPage(Page<Account> page, Position o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				Page<Account> accs = service.findArrangeAccByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", accs);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "arrangeAcc", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes arrangeAcc(String posId, String chks) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				service.saveAccountPosition(posId, chks);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "findPosByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPosByPage(Page<Account> page, Position o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				o.setId(getPageData().getString("posId"));
				Page<Account> aps = service.findPosByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", aps);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "delAccPos", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delAccPos(String loginName) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				service.deleteAccPosByLoginName(loginName);
				ar.setSucceedMsg("移除成功");
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg("移除失败");
			}
		}
		return ar;
	}
	
	/**
	 * 根据部门ID获取所在部门信息
	 */
	public List<Org> getOrgs(List<String> orgIds) {
		List<Org> orgs = orgService.findOrgsById(orgIds);
		return orgs;
	}
	
	/**
	 * 根据已有的领导部门ID查看子机构部门的ID
	 */
	public List<String> getSubSectorOrgs(List<String> orgIds) {
		List<String> orgIdList = orgService.findSubSectorOrgsById(orgIds);
		return orgIdList;
	}
	
	@RequestMapping(value = "getPosOrgTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPosOrgTree() {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {	
				PageData pd = this.getPageData();
				String posId = pd.getString("id");
				List<ZNodes> orgs = service.getOrgTreesByPos(posId);
				ar.setSucceed(orgs);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
		
	}
	
	@RequestMapping(value = "savePosOrg", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes savePosOrg() {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				PageData pd = this.getPageData();
				String posId = pd.getString("id");
				String orgs = pd.getString("aus");
				service.savePosOrg(posId, orgs);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}
}

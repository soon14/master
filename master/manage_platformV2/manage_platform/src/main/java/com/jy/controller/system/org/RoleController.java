package com.jy.controller.system.org;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.webpage.PageData;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.org.Org;
import com.jy.entity.system.org.Role;
import com.jy.entity.utils.ZNodes;
import com.jy.mybatis.Page;
import com.jy.service.system.org.OrgService;
import com.jy.service.system.org.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;
/*
 * 角色管理
 */
@Controller
@RequestMapping("/backstage/org/role/")
public class RoleController extends BaseController<Role>{
	
	@Autowired
	public OrgService orgService;
	
	@Autowired
	public RoleService roleService;
	
	private static final String SECURITY_URL="/backstage/org/role/index";
	/**
	 * 角色首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/org/role/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<Role> page,Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				Page<Role> roles=roleService.findAllRoleByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",roles);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){		
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				roleService.insert(o);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){		
			try {
				String[] chk =chks.split(",");
				List<Role> list=new ArrayList<Role>();
				for(String s:chk){
					Role sd=new Role();
					sd.setId(s);
					list.add(sd);
					//roleService.deleteRoleOrg(s);
				}
				roleService.deleteBatch(list);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	/**
	 *
	 * @方法功能描述： 角色组织树
	 * @return
	 * AjaxRes
	 * @author duanshixi
	 * @创建时间： 2017年4月12日下午5:27:52
	 */
	@RequestMapping(value = "getRoleOrgTree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getRoleOrgTree() {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, SECURITY_URL))) {
			try {
				PageData pd = this.getPageData();
				String roleId = pd.getString("id");
				List<ZNodes> orgs = roleService.getOrgTreesByRole(roleId);
				ar.setSucceed(orgs);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;

	}
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
			try {
				List<Role> list=roleService.find(o);
				Role role=list.get(0);
				ar.setSucceed(role);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(Role o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				o.setUpdateTime(new Date());
				roleService.update(o);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				//roleService.deleteRoleOrg(o.getId());
				roleService.delete(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="listAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){		
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String layer=pd.getString("layer");
				List<ZNodes> r=roleService.listAuthorized(roleId,layer);
				ar.setSucceed(r);			
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="saveAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/org/role/listAuthorized"))){
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String aus=pd.getString("aus");	
				String layer=pd.getString("layer");	
				roleService.saveAuthorized(roleId,aus,layer);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="getOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<ZNodes> r=orgService.getOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="getPreOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPreOrgTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<ZNodes> r=orgService.getPreOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="addOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes addOrg(Org o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				orgService.insert(o);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="updateOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateOrg(Org o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				o.setUpdateTime(new Date());
				orgService.update(o);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="findOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findOrg(Org o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<Org> list=orgService.find(o);
				Org org=list.get(0);
				ar.setSucceed(org);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="delOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delOrg(Org o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				int res=orgService.delOrg(o);
				if(res==1)ar.setSucceedMsg(Const.DEL_SUCCEED);
				else      ar.setFailMsg("请先删除所有其子组织或子角色");	
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);	
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="orglistAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes orglistAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String layer=pd.getString("layer");
				List<ZNodes> r=orgService.listAuthorized(orgId,layer);
				ar.setSucceed(r);			
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="saveOrgAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveOrgAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String aus=pd.getString("aus");	
				String layer=pd.getString("layer");	
				orgService.saveAuthorized(orgId,aus,layer);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}
}

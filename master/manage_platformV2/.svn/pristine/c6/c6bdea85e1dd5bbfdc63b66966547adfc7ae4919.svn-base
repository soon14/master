package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.PageCalculation;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.channels.BaseCommission;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.org.Org;
import com.jy.mybatis.Page;
import com.jy.service.system.channels.BaseCommissionService;
import com.jy.service.system.channels.MerchantService;
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
 * Created by Matthew on 2017/1/5 0005.
 */
@Controller
@RequestMapping("/backstage/commission/")
public class BaseCommissionController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
    private BaseCommissionService service;
    @Autowired
    private MerchantService merchantService;
	@Autowired
	public PositionService positionService;

     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     Logger logger = Logger.getLogger(BaseCommissionController.class);


    @RequestMapping("index")
    public String index(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "system/channels/baseCommission";
        }
        return Const.NO_AUTHORIZED_URL;
    }
    
	/**
	 * @方法功能描述：新增渠道商户
	 * @param page
	 * @param o
	 * @return
	 */
    @RequestMapping(value="findByPage")
    @ResponseBody
	public AjaxRes findByPage(Page<BaseCommission> page, BaseCommission o) {
        AjaxRes ar= new AjaxRes();
    	  try{

  			String userName = AccountShiroUtil.getCurrentUser().getLoginName();
  			String userId = AccountShiroUtil.getCurrentUser().getAccountId();
			String value = null;
			if (null != getRequest().getParameter("value")) {
				value = new String(getRequest().getParameter("value").getBytes("ISO-8859-1"), "UTF-8");
			}
			
			if (null != value) {
				o.setName(value);
			}
			  Map map = new HashMap();
			  List<Org> l = positionService.findDataAuthority(userId);
			  List userIds = new ArrayList();
			  for (int i = 0; i < l.size(); i++) {
				  userIds.add(l.get(i).getAccountId());
			  }
			  map.put("userIds",userIds);
			  List<Account> a = service.findUserName(map);
			  List id = new ArrayList();
			  for (int i = 0; i < a.size(); i++) {
				  id.add(a.get(i).getLoginName());
			  }
			  Map m = new HashMap();
			  m.put("userIds",id);
			  m.put("name",o.getName());
			  m.put("bcId",o.getBcId());
			  m.put("userName",userName);

			  Page<BaseCommission> byPage = service.findByUserName(page,m);
			  List<BaseCommission> list = byPage.getResults();
				if (list != null) {
					byPage.setTotalRecord(byPage.getResults().size());
					for (BaseCommission vo : list) {
						if (vo.getType() == 1) {
							vo.setTypeName("标签用户");
						} else if (vo.getType() == 2) {
							vo.setTypeName("投注机");
					  }
				  }
				}
    		
    		byPage.setResults(list);
    		byPage = (Page<BaseCommission>) PageCalculation.getPageCalculation(byPage);
              Map<String, Object> p=new HashMap<String, Object>();
    		  p.put("permitBtn", Const.RESOURCES_TYPE_BUTTON);
              p.put("list",byPage);
              ar.setSucceed(p);
          } catch (Exception e) {
              logger.error(e.toString(),e);
              ar.setFailMsg(Const.DATA_FAIL);
          }
      return ar;
    }
    
    @RequestMapping(value="findByPageAll")
    @ResponseBody
	public AjaxRes findByPageAll(Page<BaseCommission> page, BaseCommission o) {
        AjaxRes ar= new AjaxRes();
    	  try{

			  String bcId = getRequest().getParameter("bcId");
			  String bcIdLine = getRequest().getParameter("bcIdLine");

			  String userName = AccountShiroUtil.getCurrentUser().getLoginName();
			  String userId = AccountShiroUtil.getCurrentUser().getAccountId();
			  String value = null;
			  if (null != getRequest().getParameter("value")) {
				  value = new String(getRequest().getParameter("value").getBytes("ISO-8859-1"), "UTF-8");
			  }

			  if (null != value) {
				  o.setName(value);
			  }
			  Map map = new HashMap();
			  List<Org> l = positionService.findDataAuthority(userId);
			  List userIds = new ArrayList();
			  for (int i = 0; i < l.size(); i++) {
				  userIds.add(l.get(i).getAccountId());
			  }
			  map.put("userIds",userIds);
			  List<Account> a = service.findUserName(map);
			  List id = new ArrayList();
			  for (int i = 0; i < a.size(); i++) {
				  id.add(a.get(i).getLoginName());
			  }
			  Map m = new HashMap();
			  m.put("userIds",id);
			  m.put("bcId",bcId);
			  m.put("userName",userName);
			  m.put("bcIdLine",bcIdLine);

  			Page<BaseCommission> byPage = service.findAllCommission(page,m);
  			List<BaseCommission> list = byPage.getResults();
    		if (list != null) {
    			byPage.setTotalRecord(byPage.getResults().size());
    			for (BaseCommission vo : list) {
    				if (vo.getType() == 1) {
						vo.setTypeName("标签用户");
    				} else if (vo.getType() == 2) {
						vo.setTypeName("投注机");
                  }
              }
    		}
    		
    		byPage.setResults(list);
    		byPage = (Page<BaseCommission>) PageCalculation.getPageCalculation(byPage);
              Map<String, Object> p=new HashMap<String, Object>();
    		  p.put("permitBtn", Const.RESOURCES_TYPE_BUTTON);
              p.put("list",byPage);
              ar.setSucceed(p);
          } catch (Exception e) {
              logger.error(e.toString(),e);
              ar.setFailMsg(Const.DATA_FAIL);
          }
      return ar;
    }


	@RequestMapping(value="findById")
	@ResponseBody
	public AjaxRes findById(BaseCommission o) {
		AjaxRes ar= new AjaxRes();
		try{
			String bcId = getRequest().getParameter("bcId");
			String bcIdLine = getRequest().getParameter("bcIdLine");
			Map m = new HashMap();
			m.put("bcId",bcId);
			m.put("bcIdLine",bcIdLine);
			List<BaseCommission> b = service.findById(m);
			Map<String, Object> p=new HashMap<String, Object>();
			p.put("permitBtn", Const.RESOURCES_TYPE_BUTTON);
			p.put("list",b);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		return ar;
	}

    @RequestMapping(value="deleteCommission")
    @ResponseBody
    public AjaxRes deleteCommission(BaseCommission o){
        AjaxRes ar= new AjaxRes();
        try {
			Merchant m = new Merchant();
			m.setBcId(o.getId());
			List<Merchant> byPage = merchantService.findBaseCommission(m);
			if (byPage.size() != 0) {
				ar.setSucceed("1");
				return ar;
			}
			service.deleteBaseCommission(o);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            ar.setFailMsg(Const.DATA_FAIL);
        }
        Map<String, Object> p=new HashMap<String, Object>();
        p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
        ar.setSucceed(p);
        return ar;
    }

	@RequestMapping(value = "create")
	@ResponseBody
	public AjaxRes createCommission(BaseCommission o) {
		AjaxRes ar = new AjaxRes();
		try {
			Merchant merchant = new Merchant();
			merchant.setBcId(o.getId());
			merchant.setmId(o.getMerchantId());
			merchant.setBcFlag(1);
			merchantService.updateMerchant(merchant);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setSucceedMsg("未成功分配!");
			return ar;
		}
		Map<String, Object> p = new HashMap<String, Object>();
		ar.setSucceedMsg("已成功分配!");
		ar.setSucceed(p);
		return ar;
	}

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return null;
    }
	
	@RequestMapping(value = "createBaseCommission")
	@ResponseBody
	public AjaxRes createBaseCommission(BaseCommission o) {
		AjaxRes ar = new AjaxRes();
		try {
			Merchant m = new Merchant();
			m.setBcId(o.getId());
			List<Merchant> byPage = merchantService.findBaseCommission(m);
			if (byPage.size() != 0) {
				ar.setSucceed("1");
				return ar;
			}
			service.updateBaseCommission(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
		ar.setSucceed(p);
		return ar;
	}
	
	@RequestMapping(value = "insertBaseCommission")
	@ResponseBody
	public AjaxRes insertBaseCommission(BaseCommission o) {
		AjaxRes ar = new AjaxRes();
		try {
			String userName = AccountShiroUtil.getCurrentUser().getLoginName();
			String userId = AccountShiroUtil.getCurrentUser().getAccountId();

			Merchant mer = merchantService.findMerchant(userId);
			Integer level = mer.getmLevel();
			if(level==2 || level==3){
				ar.setSucceedMsg("当前用户没有权限创建佣金!");
				return ar;
			}

			o.setChangeDate(new Date());
			o.setCreateUser(userName);
			o.setIsEnable(1);
			service.insertBaseCommission(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
		ar.setSucceed(p);
		return ar;
	}
}

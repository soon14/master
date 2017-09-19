package com.jy.controller.system.channels;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.jy.common.utils.ChannelsUtils;
import com.jy.common.utils.HttpUtil;
import com.jy.common.utils.JsonUtil;
import com.jy.controller.controllerUtils.controllerUtil;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.org.Org;
import com.jy.entity.system.resources.Resources;
import com.jy.entity.utils.ZNodes;
import com.jy.repository.system.channels.MerchantDao;
import com.jy.repository.system.channels.PrepaymentDao;
import com.jy.repository.system.channels.PrepaymentExtendDao;
import com.jy.service.system.account.AccountService;
import com.jy.service.system.channels.AuditService;
import com.jy.service.system.channels.BaseCommissionService;
import com.jy.service.system.channels.MerchantService;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.org.OrgService;
import com.jy.service.system.org.PositionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.ajax.AjaxRes;
import com.jy.mybatis.Page;
import com.jy.common.mybatis.PageCalculation;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.controller.system.account.AccountHelper;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.channels.BaseCommission;
import com.jy.entity.system.channels.Merchant;

import static java.util.regex.Pattern.matches;

/**
 * Created by Administrator on 2016/12/29.
 */
@Controller
@RequestMapping("/channels/")
public class MerchantController extends BaseController<Object> {
	@Value("${secondaryAuditUrl}")
	private String path;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AuditService auditService;
	@Autowired
	private PrepaymentExtendDao prepaymentExtendDao;
	/**
	 * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
	 * **/
	private static final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";

	/**
	 * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709
	 * **/
	private static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";

	/**
	 * 中国移动号码格式验证
	 * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184
	 * ,187,188,147,178,1705
	 *
	 **/
	private static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";

	/** 座机电话格式验证 **/
	private static final String PHONE_CALL_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$";


	/**
	 * 仅手机号格式校验
	 */
	private static final String PHONE_PATTERN=new StringBuilder(300).append(CHINA_MOBILE_PATTERN)
			.append("|")
			.append(CHINA_TELECOM_PATTERN)
			.append("|")
			.append(CHINA_UNICOM_PATTERN)
			.toString();


	private static final String PHONE_TEL_PATTERN=new StringBuilder(350).append(PHONE_PATTERN)
			.append("|")
			.append("(")
			.append(PHONE_CALL_PATTERN)
			.append(")")
			.toString();

	/**
	 * 渠道商
	 * 
	 * @param model
	 * @return
	 */
    @RequestMapping("merchantList")
    public String salesSumDifferences(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/merchant";
        }
        return Const.NO_AUTHORIZED_URL;
    }

    /**
	 * 渠道商数据查询
	 * 
	 * @param page
	 * @param o
	 * @return
	 */
	@RequestMapping(value = {"findByPage"}, method = {RequestMethod.POST })
	@ResponseBody
	public AjaxRes findByPage(Page<Merchant> page, Merchant o) {
		String userId = AccountShiroUtil.getCurrentUser().getAccountId();
		String name = AccountShiroUtil.getCurrentUser().getLoginName();
		String mParentName = getRequest().getParameter("mParentName");
		o.setUserId(userId);
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept("1", "/channels/merchantList"))) {

			Map p = new HashMap();
			try {
				String mName = AccountShiroUtil.getCurrentUser().getLoginName();
				o.setmContactUser(mName);
				Map m = new HashMap();
				m.put("mName",o.getmName());
				m.put("bcId",o.getBcId());
				m.put("mContactUser",o.getmContactUser());
				m.put("userIds",userId);
				m.put("mParentName",mParentName);
				m.put("isValid",o.getIsValid());
				List<Merchant> mer = merchantService.findDataAuthorityMerchant(m);
				Page byPage = convertPageSize(mer,page);
				List<Merchant> list = byPage.getResults();
				if(null!=list){
					for (int i = 0; i < list.size(); i++) {
						int mId = list.get(i).getmId();
						String childMerchantNum = merchantService.findChildMerchant(mId);
						String childCustomerNum = merchantService.findChildCustomer(mId);
						list.get(i).setChildMerchantNum(childMerchantNum);
						list.get(i).setChildCustomerNum(childCustomerNum);
						byPage.setResults(list);
					}
				}
				p.put("list", byPage);
				p.put("mName", mName);
				Merchant merchant = merchantService.findId(userId,"");
				if(null != merchant){
					p.put("level", merchant.getmLevel());
				}else{
					p.put("level", '1');
				}
				p.put("permitBtn", getPermitBtn("3"));
				ar.setSucceed(p);
			} catch (Exception e) {
				this.logger.error(e.toString(), e);
				ar.setFailMsg("数据获取失败");
			}
		}
		return ar;
	}


	@RequestMapping(value = {"findOwn"}, method = {RequestMethod.POST })
	@ResponseBody
	public AjaxRes findOwn(Page<Merchant> page, Merchant o) {
		String userId = AccountShiroUtil.getCurrentUser().getAccountId();
		AjaxRes ar = getAjaxRes();
		Map p = new HashMap();
		try {
			Map m = new HashMap();
			m.put("userId",userId);
			List<Merchant> mer = merchantService.findOwn(m);
			p.put("list", mer);
			p.put("permitBtn", getPermitBtn("3"));
			ar.setSucceed(p);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			ar.setFailMsg("数据获取失败");
		}
		return ar;
	}

	@RequestMapping(value = {"findParentMerchant"}, method = {RequestMethod.POST })
	@ResponseBody
	public AjaxRes findParentMerchant(Page<Merchant> page, Merchant o) {
		String userId = AccountShiroUtil.getCurrentUser().getAccountId();
		o.setUserId(userId);
		AjaxRes ar = getAjaxRes();
		Map p = new HashMap();
		try {
			String mName = AccountShiroUtil.getCurrentUser().getLoginName();
			o.setmContactUser(mName);
			Map m = new HashMap();
			m.put("mContactUser",o.getmContactUser());
			m.put("userIds",userId);

			List<Merchant> mer = merchantService.findParentMerchant(m);
			p.put("list", mer);
			p.put("mName", mName);
			p.put("permitBtn", getPermitBtn("3"));
			ar.setSucceed(p);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			ar.setFailMsg("数据获取失败");
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
	 * 新增商户跳转页面
	 * 
	 * @param model
	 * @return
	 */
    @RequestMapping(value="addMerchant", method= RequestMethod.GET)
    public String addMerchant(Model model){
        return "/system/channels/addMerchant";
    }

    @RequestMapping(value="findChildCustomer", method=RequestMethod.GET)
    public String findChildCustomer(Merchant merchant,Model model,Page<Merchant> page) throws Exception{
		model.addAttribute("mId",merchant.getmId());
        return "/system/channels/findChildCustomer";
    }

	@RequestMapping(value="findChildMerchant", method=RequestMethod.GET)
	public String findChildMerchant(Merchant merchant,Model model,Page<Merchant> page) throws Exception{
		model.addAttribute("mId",merchant.getmId());
		return "/system/channels/findChildMerchant";
	}

	@RequestMapping(value="uploadMLicense", method=RequestMethod.GET)
	public String uploadMLicense(Merchant merchant,Model model,Page<Merchant> page) throws Exception{
		List<Merchant> list=new ArrayList<>();
		if(ChannelsUtils.IsNull(merchant.getmId())){
			return "/system/noAuthorized";
		}
		else {
			list=merchantService.findChannel(merchant);
		}
		model.addAttribute("list",list);
		model.addAttribute("id",merchant.getmId());
		return "/system/channels/uploadMLicense";
	}


    /**
	 * 新增商户
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(Merchant merchant, HttpServletResponse response) throws Exception {
		AjaxRes ar = getAjaxRes();
		if ((doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			String userId = AccountShiroUtil.getCurrentUser().getAccountId();

			//校验手机号是否合法
			if(!isPhoneOrTel(merchant.getmMobile())){
				ar.setSucceedMsg("2");
				return ar;
			}

			//校验手机号是否存在
			if(merchantService.findmMobile(merchant.getmMobile(),"")!=0){
				ar.setSucceedMsg("3");
				return ar;
			}

			//选择二级渠道并且没选上级渠道
			if(merchant.getmLevel()==3 && (merchant.getMParentMerchant().equals("") || merchant.getMParentMerchant().equals("0"))){
				ar.setSucceedMsg("4");
				return ar;
			}

			//校验是否选择渠道来源
			if(merchant.getLine()==null || merchant.getLine().equals("")){
				ar.setSucceedMsg("5");
				return ar;
			}else if(merchant.getLine().length()>1){
				merchant.setLine("0");
			}

			try {
				merchant.setIsValid("1");
				if(merchant.getmLevel()==3){
					ar = cheak(merchant);
				}else{
					Account account=new Account();
					account.setAccountId(get32UUID());
					account.setIsValid(1);
					account.setLoginName(merchant.getmMobile());
					account.setRoleId(merchant.getRoleId());
					account.setName(merchant.getmName());
					accountService.insertAccount(account);
					merchant.setmAccountId(account.getAccountId());
					merchant.setmCpUserId("0");
					merchant.setmStatus(0);
					merchantService.createMerchant(merchant);
					ar.setSucceedMsg("1");
				}
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setSucceedMsg("0");
			}
		}
		return ar;
	}

	public AjaxRes cheak(Merchant merchant){
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("彩票请求接口开始：===========");
			String url = this.path+"?mobile="+merchant.getmMobile()+"&mName="+ URLEncoder.encode(merchant.getmName(), "utf-8");
			logger.info("彩票请求地址为：==========="+url);
			String s=null;
			try {
				s= HttpUtil.get(url,false);
				logger.info("彩票接口请求响应数据为：==========================="+s);
			}catch (Exception e){
				logger.error("接口异常信息============================"+e.getMessage().toString());
				ar.setSucceedMsg("5");
				return ar;
			}
			if(s!=null && s !=""){
				Map map= JsonUtil.jsonToObject(s);
                if (map.get("flag").equals("1") || map.get("flag").equals("2")|| map.get("flag").equals("4")) {
                    merchant.setmCpUserId(map.get("userId").toString());
                    merchant.setmBarcode(map.get("picBUrl").toString());
                }else if(map.get("flag").equals("-1")) {
					ar.setSucceedMsg("6");
					return ar;
				}else if(map.get("flag").equals("3")){
					ar.setSucceedMsg("7");
                    return ar;
                }else {
					ar.setSucceedMsg("8");
					return ar;
				}
			}else{
				ar.setSucceedMsg("8");
				return ar;
			}
			merchant.setmStatus(2);
			Account account=new Account();
			account.setAccountId(get32UUID());
			account.setIsValid(1);
			account.setLoginName(merchant.getmMobile());
			account.setRoleId(merchant.getRoleId());
			account.setName(merchant.getmName());
			accountService.insertAccount(account);
			merchant.setmAccountId(account.getAccountId());
			merchantService.createMerchant(merchant);
			//为二级渠道预备预存款信息
			if(merchant.getLine().equals("2")){
				Merchant mer = merchantService.findId("",merchant.getmMobile());
				PrepaymentExtend pre = new PrepaymentExtend();
				pre.setmId(mer.getmId());
				pre.setBalance(0.00);
				pre.setPayMoney(0.00);
				pre.setWarningMoney(0.00);
				pre.setDataType(2);
				prepaymentExtendDao.insert(pre);
			}
			ar.setSucceedMsg("1");
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setSucceedMsg("8");
		}
		return ar;
	}

	/**
	 * 手机号或座机号校验
	 * @param input
	 * @return
	 */
	public static boolean isPhoneOrTel(String input){
		return matches(PHONE_TEL_PATTERN, input);
	}


	/**
	 * 查看详情
	 * 
	 * @param merchant
	 * @return
	 */
    @RequestMapping(value="findParticulars", method=RequestMethod.POST)
    @ResponseBody
    public AjaxRes findParticulars(Merchant merchant){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {
                merchant=auditService.findMerchantId(merchant);
                if(!ChannelsUtils.IsNull(merchant) && !ChannelsUtils.IsNull(merchant.getmAccountId())){
                    Account account=accountService.findAccountByIdAndName(merchant.getmAccountId());
                    merchant.setmAccountId(account.getLoginName());
                }
                ar.setSucceed(merchant);
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.DATA_FAIL);
            }
        }
        return ar;
    }

	@RequestMapping(value="findChildCustomerByMId", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findChildCustomerByMId(Page<Merchant> page,Merchant merchant){
		AjaxRes ar=getAjaxRes();
		Map p = new HashMap();
		try {
			List<Merchant> mer = merchantService.findChildCustomerByMId(merchant);
			Page byPage = convertPageSize(mer,page);
			p.put("list", byPage);
			ar.setSucceed(p);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			ar.setFailMsg("数据获取失败");
		}
		return ar;
	}

	@RequestMapping(value="findChildMerchantByMId", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findChildMerchantByMId(Page<Merchant> page,Merchant merchant){
		AjaxRes ar=getAjaxRes();
		Map p = new HashMap();
		try {
			List<Merchant> mer = merchantService.findChildMerchantByMId(merchant);
			Page byPage = convertPageSize(mer,page);
			List<Merchant> list = byPage.getResults();
			if(null!=list){
				for (int i = 0; i < list.size(); i++) {
					int mId = list.get(i).getmId();
					String childCustomerNum = merchantService.findChildCustomer(mId);
					list.get(i).setChildCustomerNum(childCustomerNum);
					byPage.setResults(list);
				}
			}
			p.put("list", byPage);
			ar.setSucceed(p);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			ar.setFailMsg("数据获取失败");
		}
		return ar;
	}


    /**
	 * 修改
	 * 
	 * @param merchant
	 * @return
	 */
    @RequestMapping(value="updateMerchant", method=RequestMethod.POST)
    @ResponseBody
    public AjaxRes updateMerchant(Merchant merchant){
        AjaxRes ar=getAjaxRes();
        if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){
            try {

            	//非未审批状态不可修改
            	if(merchant.getmStatus()!=0){
					ar.setSucceedMsg("3");
					return ar;
				}

				//校验手机号是否合法
				if(!isPhoneOrTel(merchant.getmMobile())){
					ar.setSucceedMsg("2");
					return ar;
				}

				//校验手机号是否存在
				if(merchantService.findmMobile(merchant.getmMobile(),merchant.getmId().toString())!=0){
					ar.setSucceedMsg("4");
					return ar;
				}

				if (null != merchant.getBcId()) {
					if (merchant.getBcId() == 1) {
						merchant.setBcId(null);
					}
				}
				if (null != merchant.getBcIdLine()) {
					if (merchant.getBcIdLine() == 1) {
						merchant.setBcIdLine(null);
					}
				}
				merchantService.updateMerchant(merchant);
				ar.setSucceedMsg("1");
            } catch (Exception e) {
                logger.error(e.toString(),e);
                ar.setFailMsg(Const.UPDATE_FAIL);
            }
        }
        return ar;
    }


	@RequestMapping(value="updateMLicense", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateMLicense(Merchant merchant){
		AjaxRes ar=getAjaxRes();
		try {
			Integer res=merchantService.updateMLicense(merchant);
			if(res==1){
				ar.setSucceedMsg("1");
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(Const.UPDATE_FAIL);
		}
		return ar;
	}


	/**
	 * 删除
	 * 
	 * @param chks
	 * @return
	 */
    @RequestMapping(value = "delMerchant", method = RequestMethod.POST)
    @ResponseBody
    public AjaxRes delMerchant(String chks) {
        AjaxRes ar = getAjaxRes();
        if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
            try {
                List<Integer> listId=new ArrayList<>();
                Integer integer=0;
                List<Integer> listIds=new ArrayList<>();
                List<Integer> integers=new ArrayList<>();
                integers.add(1);
                integers.add(2);
                if (StringUtils.isNotBlank(chks)) {
                    String[] chk = chks.split(",");
                    List<Integer> list = new ArrayList<Integer>();
                    for (String s : chk) list.add(Integer.parseInt(s));
                    List<Merchant> merchant=merchantService.findMerchantId(list);
                    for(Merchant merchant1:merchant){
						if (integers.contains(merchant1.getmStatus()))
							listIds.add(merchant1.getmId()); // 不可删除
						else listId.add(merchant1.getmId()); // 可以删除
                    }
                    integer=merchantService.deleteMerchant(listId);
                }
				if (integer > 0) ar.setSucceedMsg("删除成功！ 已删除条数:" + integer + "   未成功删除条数:" + listIds.size());
            } catch (Exception e) {
                logger.error(e.toString(), e);
                ar.setFailMsg(Const.DEL_FAIL);
            }
        }
        return ar;
    }

	@RequestMapping(value = "checkBaseCommission", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes checkBaseCommission(BaseCommission b) {
		AjaxRes ar = getAjaxRes();
		Merchant o = new Merchant();
		o.setBcId(b.getId());
		List<Merchant> byPage = merchantService.findBaseCommission(o);
		if (byPage.size() == 0) {
			ar.setSucceed("0");
		} else {
			ar.setSucceed("1");
		}
		return ar;
	}

	@RequestMapping(value = {"findUserLevel"}, method = {RequestMethod.POST })
	@ResponseBody
	public AjaxRes findUserLevel(Page<Merchant> page, Merchant o) {
		String userId = AccountShiroUtil.getCurrentUser().getAccountId();
		AjaxRes ar = getAjaxRes();
		Map p = new HashMap();
		try {
			Map m = new HashMap();
			m.put("userId",userId);
			Map m1 = new HashMap();
			m1.put("mId",o.getmId());
			List<Merchant> mer = merchantService.findOwn(m);
			List<Merchant> mers = merchantService.findUserLevel(m1);

			p.put("list", mer);
			p.put("lists", mers);
			p.put("permitBtn", getPermitBtn("3"));
			ar.setSucceed(p);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			ar.setFailMsg("数据获取失败");
		}
		return ar;
	}
	
}

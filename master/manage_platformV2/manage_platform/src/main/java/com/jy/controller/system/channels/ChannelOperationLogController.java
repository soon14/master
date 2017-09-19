package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.ChannelsUtils;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.channels.BaseCommission;
import com.jy.entity.system.channels.ChannelOperationLog;
import com.jy.entity.system.channels.Merchant;
import com.jy.mybatis.Page;
import com.jy.service.system.account.AccountService;
import com.jy.service.system.channels.AuditService;
import com.jy.service.system.channels.BaseCommissionService;
import com.jy.service.system.channels.ChannelOperationLogService;
import com.jy.service.system.channels.MerchantService;
import com.jy.service.system.org.OrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/channels/channelOperationLog/")
public class ChannelOperationLogController extends BaseController<Object> {

    @Autowired
    private ChannelOperationLogService service;

    @RequestMapping("index")
    public String salesSumDifferences(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/channelOperationLog";
        }
        return Const.NO_AUTHORIZED_URL;
    }

	@RequestMapping(value = {"findByPage"}, method = {RequestMethod.POST })
    @ResponseBody
	public AjaxRes findByPage(Page<ChannelOperationLog> page, ChannelOperationLog o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept("1", "/channels/channelOperationLog/index"))) {
			Map p = new HashMap();
			List<String> orgIdList = null;
			try {
				String beginTime = getRequest().getParameter("beginTime");
				String endTime = getRequest().getParameter("endTime");
				o.setDate(beginTime);
				o.setEndDate(endTime);
				Page byPage = this.service.findByPage(o, page);
				p.put("list", byPage);
				p.put("permitBtn", getPermitBtn("3"));
				ar.setSucceed(p);
			} catch (Exception e) {
				this.logger.error(e.toString(), e);
				ar.setFailMsg("数据获取失败");
			}
		}
		return ar;
	}

	@RequestMapping(value = {"findFuncNo"}, method = {RequestMethod.POST })
	@ResponseBody
	public AjaxRes findFuncNo() {

		AjaxRes ar = getAjaxRes();
		Map p = new HashMap();
		List<String> orgIdList = null;
		try {
			List<ChannelOperationLog> list = this.service.findFuncNo();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			ar.setFailMsg("数据获取失败");
		}

		return ar;
	}


	
}

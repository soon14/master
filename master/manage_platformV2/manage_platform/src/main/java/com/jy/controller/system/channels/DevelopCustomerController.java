package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.Merchant;
import com.jy.mybatis.Page;
import com.jy.process.system.base.BaseProcess;
import com.jy.service.system.channels.DevelopCustomerService;
import com.jy.service.system.dict.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @文件名:DevelopCustomerController.java
 * @功能描述：
 * @创建日期:2017年3月1日下午6:51:32
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */
@Controller
@RequestMapping("/channels/develop/")
public class DevelopCustomerController extends BaseController<Object> {
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private DevelopCustomerService developCustomerService;
	
	@Value("${download.suffix}")
	private String suffix;
	
	@Value("${download.developcustomer.filename}")
	private String developFileName;

	@Autowired
	private BaseProcess baseProcess;
	
	/**
	 * @方法功能描述： 方法作用
	 * @param model
	 * @return String
	 * @author lijunke
	 * @创建时间： 2017年3月1日下午7:36:53
	 */
	@RequestMapping("index")
	public String index(Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/channels/developCoustomerList";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	/**
	 * @方法功能描述： 加载发展客户页面带出查询的数据
	 * @return String
	 * @author lijunke
	 * @创建时间： 2017年3月1日下午6:55:54
	 */
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<Merchant> page, Merchant m) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/channels/develop/index"))) {
			try {
				String date = getRequest().getParameter("beginTime");
				if (date != "") {
					m.setmCreateTime(date);
				}
				Page<Merchant> reports = developCustomerService.developCustomerList(m, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("pageSize", page.getPageSize());
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", reports);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	/**
	 * @方法功能描述：下载报表到本地
	 * @return
	 * @throws Exception String
	 * @author lijunke
	 * @创建时间： 2017年3月7日下午1:09:00
	 */
    @RequestMapping(value = "download", method = {
	        RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
	public Object exportReport() {
		String date = getRequest().getParameter("date");
		String pagePaht = "/system/finance/reconciliation/bettingDifferenceRestData";
		return baseProcess.getDownLoad(date,pagePaht,developFileName);
	}


}
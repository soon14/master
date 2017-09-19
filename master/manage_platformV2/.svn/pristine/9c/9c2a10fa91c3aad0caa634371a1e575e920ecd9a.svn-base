package com.jy.controller.system.finance.reconciliation.lottery;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.mybatis.PageCalculation;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.finance.reconciliation.lottery.WithDrawDifference;
import com.jy.mybatis.Page;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.reconciliation.lottery.WithDrawDifferenceService;


@Controller
@RequestMapping("/backstage/withDrawDifference")
public class WithDrawDifferenceController extends BaseController<WithDrawDifference> {
	
	@Autowired
	public WithDrawDifferenceService service;
	
	@Autowired
	public SysDictService dictService;
	
	@RequestMapping("index")
	public String index(Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/finance/reconciliation/withDrawDifference";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<WithDrawDifference> page, WithDrawDifference m) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/withDrawDifference/index"))) {
			try {
				String tDate = getRequest().getParameter("beginTime");
				String tFlowNo = getRequest().getParameter("tFlowNo");
				List<WithDrawDifference> list = service.withDrawDifference(tDate, tFlowNo);
				Page<WithDrawDifference> reports = convertPageSize(list, page);
				
				double sumTmoney = 0.00;
				double sumMoney = 0.00;
				for (int i = 0; i < list.size(); i++) {
					if (null != list.get(i).getTMoney()) {
						sumTmoney += Double.parseDouble(list.get(i).getTMoney());
					}
					if (null != list.get(i).getMoney()) {
						sumMoney += Double.parseDouble(list.get(i).getMoney());
					}
				}
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("pageSize", page.getPageSize());
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", reports);
				p.put("sumTmoney", sumTmoney);
				p.put("sumMoney", sumMoney);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	public Page<WithDrawDifference> convertPageSize(List<WithDrawDifference> list, Page<WithDrawDifference> page) {
		page.setResults(list);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<WithDrawDifference>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(list.size());
		}
		return page;
	}
	
	@RequestMapping(value = "exportReport", method = {
	        RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
	public Object exportReport() {
		String date = getRequest().getParameter("date");
		if (null == date || date.equals("")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date d = cal.getTime();
			date = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		String fileName = "提现汇总日报" + date + ".xls";
		byte[] bytes = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			String localFileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
			SysDict o = new SysDict();
			o.setParamKey("filePath");
			List<SysDict> dictList = dictService.find(o);
			SysDict obj = dictList.get(0);
			String day = date.replace("-", "");
			String filePath = obj.getParamValue() + day + "/";
			File dirFile = new File(filePath + fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", localFileName);
			bytes = FileUtils.readFileToByteArray(dirFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "该日期对账文件尚未生成", null, JOptionPane.ERROR_MESSAGE);
			return "/system/finance/reconciliation/withDrawDifference";
		}
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		
	}
}

package com.jy.controller.system.finance.reconciliation.lottery;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.jy.entity.system.finance.reconciliation.lottery.TicketDifference;
import com.jy.mybatis.Page;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.reconciliation.lottery.TicketDifferenceService;



@Controller
@RequestMapping("/backstage/ticketDifference")
public class TicketDifferenceController extends BaseController<TicketDifference> {
	
	@Autowired
	public SysDictService dictService;
	
	@Autowired
	public TicketDifferenceService service;
	
	@RequestMapping("index")
	public String index(Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/finance/reconciliation/ticketDifference";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping("childIndex")
	public String childIndex(Model model, String VStart) {
		model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
		model.addAttribute("VStart", VStart);
		return "/system/finance/reconciliation/childTicketDifference";
	}
	
	@RequestMapping(value = "dataReset", method = RequestMethod.POST)
	@ResponseBody
	public void dataReset() {
		List<TicketDifference> list = service.findTicketDifference();// insert券对账差异
		service.insertTicketDifference(list);
	}
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<TicketDifference> page, TicketDifference m) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/ticketDifference/index"))) {
			try {
				String beginTime = getRequest().getParameter("beginTime");
				String endTime = getRequest().getParameter("endTime");
				List<TicketDifference> list = service.findDifference(beginTime, endTime);
				List<TicketDifference> tkList = reconciliation(list);
				Page<TicketDifference> reports = convertPageSize(tkList, page);
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
	
	public List<TicketDifference> reconciliation(List<TicketDifference> list) {
		List<TicketDifference> tkList = new ArrayList<TicketDifference>();
		TicketDifference lists = null;
		List day = new ArrayList();
		
		for (int i = 0; i < list.size(); i++) {
			String vstart = list.get(i).getVStart();
			String vend = list.get(i).getVEnd();
			day.add(vstart + "," + vend);
			
		}
		
		List<String> listTemp = new ArrayList<String>();
		Iterator<String> it = day.iterator();
		while (it.hasNext()) {
			String a = it.next();
			if (listTemp.contains(a)) {
				it.remove();
			} else {
				listTemp.add(a);
			}
		}
		
		for (int j = 0; j < day.size(); j++) {
			lists = new TicketDifference();
			String[] days = day.get(j).toString().split(",");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getVStart().equals(days[0]) && list.get(i).getVEnd().equals(days[1])) {
					lists.setVStart(list.get(i).getVStart());
					lists.setVEnd(list.get(i).getVEnd());
					if (list.get(i).getVoucher().equals("体彩")) {
						if (list.get(i).getStatus().equals("1")) {
							lists.setUsedTSMoney(list.get(i).getMoney());
						} else if (list.get(i).getStatus().equals("2")) {
							lists.setUnUsedTSMoney(list.get(i).getMoney());
						} else if (list.get(i).getStatus().equals("3")) {
							lists.setOverTSMoney(list.get(i).getMoney());
						}
					} else if (list.get(i).getVoucher().equals("投注")) {
						if (list.get(i).getStatus().equals("1")) {
							lists.setUsedDCMoney(list.get(i).getMoney());
						} else if (list.get(i).getStatus().equals("2")) {
							lists.setUnUsedDCMoney(list.get(i).getMoney());
						} else if (list.get(i).getStatus().equals("3")) {
							lists.setOverDCMoney(list.get(i).getMoney());
						}
					} else if (list.get(i).getVoucher().equals("差异")) {
						lists.setdNumber(list.get(i).getStatus());
					}
				}
			}
			tkList.add(lists);
		}
		return tkList;
	}
	
	@RequestMapping(value = "childDiffByBatchNo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes childDiffByBatchNo(Page<TicketDifference> page, String VStart) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/ticketDifference/index"))) {
			try {
				String[] dates = VStart.split(",");
				String VBegin = dates[0];
				String VEnd = dates[1];
				String usedDate = getRequest().getParameter("beginTime");
				String VNo = getRequest().getParameter("VNo");
				List<TicketDifference> list = service.childDiffByBatchNo(usedDate, VBegin, VEnd, VNo);
				Page<TicketDifference> reports = convertPageSize(list, page);
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
	
	@RequestMapping(value = "loadExcel", method = {
	        RequestMethod.GET, RequestMethod.POST }, produces = "application/MIME")
	public Object loadExcel() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date d = cal.getTime();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
		String fileName = "券汇总报表.xls";
		//String localFileName = null;
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
			return "/system/finance/reconciliation/ticketDifference";
		}
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		
	}

	public Page<TicketDifference> convertPageSize(List<TicketDifference> list, Page<TicketDifference> page) {
		page.setResults(list);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<TicketDifference>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(list.size());
		}
		return page;
	}

}

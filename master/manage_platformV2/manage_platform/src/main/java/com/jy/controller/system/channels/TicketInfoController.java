package com.jy.controller.system.channels;

import static com.jy.common.utils.FileUtil.readDaysPass;
import static com.jy.common.utils.FileUtil.readExcel;
import static com.jy.common.utils.FileUtil.readUnionPay;
import static com.jy.common.utils.FileUtil.readWeChat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jy.common.ajax.AjaxRes;
import com.jy.mybatis.Page;
import com.jy.common.utils.FileUtil;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.channels.FilePO;
import com.jy.entity.system.channels.ThirdPayInfo;
import com.jy.entity.system.channels.TicketImportRecordsVO;
import com.jy.entity.system.channels.TicketInfoPO;
import com.jy.entity.system.channels.VoucherInfo;
import com.jy.entity.system.dict.SysDict;
import com.jy.service.system.channels.TicketImportRecordsService;
import com.jy.service.system.channels.TicketInfoService;
import com.jy.service.system.dict.SysDictService;

/**
 * Created by Matthew on 2017/1/5 0005.
 */
@Controller
@RequestMapping("/backstage/ticketInfo/")
public class TicketInfoController extends BaseController implements HandlerExceptionResolver {

	@Autowired
	private TicketImportRecordsService service;

	@Autowired
	public SysDictService sysDictService;

	@Resource
	private TicketInfoService ticketInfoService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	Logger logger = Logger.getLogger(TicketInfoController.class);

	@RequestMapping("index")
	public String index(Model model) {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "system/channels/ticketImportRecords";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	@RequestMapping(value = "findByPage")
	@ResponseBody
	public AjaxRes findByPage(Page<TicketImportRecordsVO> page, TicketImportRecordsVO o) {
		AjaxRes ar = new AjaxRes();
		// if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/channels/index"))){
		try {
			if (null != page.getBeginTime() && null != page.getEndTime()) {
				o.setBeginTimes(sdf.format(page.getBeginTime()));
				o.setEndTimes(sdf.format(page.getEndTime()));
			}
			Page<TicketImportRecordsVO> cashes = service.findByPage(o, page);
			List<TicketImportRecordsVO> list = cashes.getResults();
			if (list != null) {
				for (TicketImportRecordsVO vo : list) {

					/** 交易状态设置 */
					if (vo.getIsValid().equals("1")) {
						vo.setTransStatusName("在使用");
					} else {
						vo.setTransStatusName("已作废");
					}

					/** 交易类型设置 */
					if (vo.getTransType().equals("0")) {
						vo.setTransTypeName("出票");
					} else if (vo.getTransType().equals("1")) {
						vo.setTransTypeName("微信");
					} else if (vo.getTransType().equals("3")) {
						vo.setTransTypeName("银联");
					} else if (vo.getTransType().equals("2")) {
						vo.setTransTypeName("得仕通");
					} else if (vo.getTransType().equals("4")) {
						vo.setTransTypeName("兑奖");
					} else if (vo.getTransType().equals("5")) {
						vo.setTransTypeName("体彩券");
					}
				}
			}
			cashes.setResults(list);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
			p.put("list", cashes);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		// }
		return ar;
	}

	/**
	 * 出票明细导入
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "import")
	public AjaxRes ticketImport(HttpServletRequest request, @RequestParam(value = "uploadfile", required = false) MultipartFile ticketfile, HttpServletResponse response) {
		String payWay = request.getParameter("payWay");
		AjaxRes ar = new AjaxRes();
		if ("".equals(payWay)) {
			ar.setFailMsg("请选择文件交易类型！");
			return ar;
		}
		if (null == ticketfile) {
			ar.setFailMsg("请选择文件！");
			return ar;
		}
		/** 获取用户的Name **/
		String userName = AccountShiroUtil.getCurrentUser().getName();
		try {
			/** 上传字符格式设置 **/
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			/** 统计当日导入的次数 **/
			Integer count = service.findImportCount(sdf.format(new Date()), payWay) + 1;
			String importCount;
			if (count < 10) {
				importCount = "0" + count.toString();
			} else {
				importCount = count.toString();
			}

			// 得到上传文件的保存目录
			// String filePath = "G:\\mt\\" + sdf.format(new Date());
			// Map<String,String> uploadMap= PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
			// String filePath =uploadMap.get("filePath")+sdf.format(new Date());//上传文件路径
			/** 保存目录(生产路径) */
			String filePath = "";
			List<SysDict> sysDicts = sysDictService.find(new SysDict());
			for (SysDict sysDict : sysDicts) {
				if (sysDict.getParamKey().equals("filePath")) {
					filePath = sysDict.getParamValue() + sdf.format(new Date());
				}
			}
			File file = new File(filePath);
			// 判断上传文件的保存目录是否存在
			FileUtil.createFile(file);
			// 上传文件
			FilePO vo = FileUtil.uploadFile(payWay, filePath, ticketfile, importCount);
			if (null == vo) {
				ar.setFailMsg("上传文件失败!请选择文件！");
				return ar;
			}
			synchronized (userName){
				// 解析文件得到要导入的数据
				if ("0".equals(payWay)) {
					List<TicketInfoPO> list = readExcel(vo,userName);
					ticketInfoService.ticketImport(list);
				} else {
					List<ThirdPayInfo> list = new ArrayList<ThirdPayInfo>();
					List<CashInfo> cashlist = new ArrayList<CashInfo>();
					List<VoucherInfo> voucherList = new ArrayList<VoucherInfo>();


					if ("1".equals(payWay)) {
						list = readWeChat(vo.getFileName(), vo.getSuffix(), payWay, vo.getBatchNo());
						ticketInfoService.thirdImport(list);
					} else if ("3".equals(payWay)) {
						list = readUnionPay(vo.getFileName(), vo.getSuffix(), payWay, vo.getBatchNo());
						ticketInfoService.thirdImport(list);
					} else if ("2".equals(payWay)) {
						list = readDaysPass(vo.getFileName(), vo.getSuffix(), payWay, vo.getBatchNo());
						ticketInfoService.thirdImport(list);
					} else if ("4".equals(payWay)) {
						cashlist = FileUtil.readCashPay(vo,userName,payWay);
						ticketInfoService.cashInfoImport(cashlist);
					} else if ("5".equals(payWay)) {
						voucherList = FileUtil.readVoucherInfo(vo.getBatchNo(), vo.getFileName(), vo.getSuffix(), userName);
						ticketInfoService.voucherInfoImport(voucherList);
					}

				}
			}
			// 生成导入记录
			CommonsMultipartFile cf = (CommonsMultipartFile)ticketfile;
			FileItem item =  cf.getFileItem();
			/**原文件名*/
			String originalName =item.getName();
			TicketImportRecordsVO importVo = new TicketImportRecordsVO();
			importVo.setOriginalName(originalName);
			importVo.setFileName(vo.getBatchNo());
			importVo.setCreateTime(new Date());
			importVo.setCreateDate(sdf.format(new Date()));
			importVo.setTransStatus("1");
			importVo.setTransType(payWay);
			importVo.setUsername(userName);
			service.insert(importVo);
			ar.setSucceedMsg("导入成功！");
		} catch (Exception e) {
			logger.error("本次导入失败！");
			logger.error(e);
			e.printStackTrace();
			ar.setFailMsg("本次导入失败！");
		}
		return ar;
	}

	@RequestMapping(value = "deleteImport")
	@ResponseBody
	public AjaxRes deleteImport(Page<TicketImportRecordsVO> page, TicketImportRecordsVO o) {
		AjaxRes ar = new AjaxRes();
		try {
			String str = o.getFileName();
			String[] data = str.split(",");
			o.setFileName(data[0]);
			o.setTransType(data[1]);
			service.update(o);
			TicketInfoPO po = new TicketInfoPO();
			po.setBatchNo(o.getFileName());

			if ("0".equals(o.getTransType())) {
				ticketInfoService.delete(po);
			} else if ("4".equals(o.getTransType())) {
				CashInfo cashinfo = new CashInfo();
				cashinfo.setBatchNo(o.getFileName());
				ticketInfoService.deleteCash(cashinfo);
			} else if ("5".equals(o.getTransType())) {
				VoucherInfo voucherInfo = new VoucherInfo();
				voucherInfo.setBatchNo(o.getFileName());
				ticketInfoService.deleteVoucher(voucherInfo);
			} else {
				ThirdPayInfo tp = new ThirdPayInfo();
				tp.setBatchNo(o.getFileName());
				ticketInfoService.deleteThird(tp);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
		ar.setSucceed(p);
		return ar;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
		return null;
	}
}
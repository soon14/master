package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.common.mybatis.PageCalculation;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.DownloadUtil;
import com.jy.common.utils.StringDateUtils;
import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.finance.reconciliation.lottery.CashDiffDetail;
import com.jy.entity.system.finance.reconciliation.lottery.CashDifference;
import com.jy.entity.system.finance.reconciliation.lottery.SendPrizeInfo;
import com.jy.mybatis.Page;
import com.jy.repository.system.dict.SysDictDao;
import com.jy.repository.system.finance.reconciliation.lottery.CashDifferenceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.CashDifferenceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @文件名：: CashDifferenceServiceImpl
 * @功能描述:
 * @创建时间: 2017年3月29日 下午7:51:33 <br/>
 * @author Dingj
 * @version
 */
@SuppressWarnings("unchecked")
@Service("cashDifferenceService")
public class CashDifferenceServiceImpl extends BaseServiceImp<CashDifference> implements CashDifferenceService {

	@Autowired
	public CashDifferenceDao cashDifferenceDao;

//	@Autowired
//	public PropertiesServiceImpl propertiesServiceImpl;

//	@Autowired
//	public SysDictService sysDictService;

	@Autowired
	public SysDictDao sysDictDao;


//	@Value("${cashDownload.path}")
//	public String filePath;

	@Value("${cashDownload.suffix}")
	public String suffix;

	@Value("${download.cashTotal.filename}")
	public String cashTotalFileName;

	@Value("${download.cashDetail.filename}")
	public String cashDetailFileName;

	@Override
	public Page<CashDifference> cashDifferenceDate(String date, Page<CashDifference> page) {

		CashDifference CashDifference = new CashDifference();
		CashDifference.setDate(date);
		List<CashDifference> list = this.cashDifferenceDao.findByDate(CashDifference);
		page.setResults(list);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<CashDifference>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(list.size());
		}
		return page;
	}

	/**
	 * @方法功能描述:保存兑奖差异明细数据
	 * @param date void
	 * @author xin
	 * @创建时间： 2017年3月21日下午1:39:19
	 */
	@SuppressWarnings("unused")
	public List<CashDiffDetail> saveCashDifferenceDetail(String date) {
		// TODO
		List<CashDiffDetail> SendList = cashDifferenceDao.findSendDiffBySchemeId(date);
		List<CashDiffDetail> CashList = cashDifferenceDao.findCashDiffBySchemeId(date);
		List<CashDiffDetail> tList = new ArrayList<CashDiffDetail>();
		// 定义装兑奖订单的集合
		for (int i = 0; i < SendList.size(); i++) {
			List<String> cList = new ArrayList<String>();
			BigDecimal sendPrize = SendList.get(i).getSendPrize();
			if (null == sendPrize) {
				sendPrize = new BigDecimal(0);
			}
			for (int j = 0; j < CashList.size(); j++) {
				cList.add(CashList.get(j).getTicketNo());
				BigDecimal ticketPrize = CashList.get(j).getTicketPrize();
				if (null == ticketPrize) {
					ticketPrize = new BigDecimal(0);
				}
				String sNum = SendList.get(i).getTicketNo();
				String cNum = CashList.get(j).getTicketNo();
				if (SendList.get(i).getTicketNo().equals(CashList.get(j).getTicketNo()) && sendPrize.equals(ticketPrize)) {
					continue;
				}
				if (SendList.get(i).getTicketNo().equals(CashList.get(j).getTicketNo()) && !sendPrize.equals(ticketPrize)) {
					CashDiffDetail cashDiffDetail = new CashDiffDetail();
					// try {
					// cashDiffDetail.setNormalDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
					// } catch (ParseException e) {
					// e.printStackTrace();
					// }
					SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date cParse = null;
					Date sParse = null;
					try {
						cParse = _sdf.parse(_sdf.format(CashList.get(j).getNormalDate()));
						sParse = _sdf.parse(_sdf.format(SendList.get(i).getPrizeTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					cashDiffDetail.setNormalDate(cParse);
					cashDiffDetail.setPrizeTime(sParse);
					cashDiffDetail.setTicketNo(SendList.get(i).getTicketNo());// 设置订单号
					cashDiffDetail.setSendPrize(sendPrize);
					cashDiffDetail.setTicketPrize(ticketPrize);
					cashDiffDetail.setDiffMoney(cashDiffDetail.getSendPrize().subtract(cashDiffDetail.getTicketPrize()));
					cashDiffDetail.setDfInitDate(new Date());
					tList.add(cashDiffDetail);
				}

			}
			if (!cList.contains(SendList.get(i).getTicketNo())) {
				CashDiffDetail cashDiffDetail = new CashDiffDetail();
				// try {
				// cashDiffDetail.setNormalDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
				// } catch (ParseException e) {
				// e.printStackTrace();
				// }

				SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sParse = null;
				try {
					sParse = _sdf.parse(_sdf.format(SendList.get(i).getPrizeTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cashDiffDetail.setPrizeTime(sParse);
				cashDiffDetail.setTicketNo(SendList.get(i).getTicketNo());// 设置订单号

				cashDiffDetail.setSendPrize(sendPrize);

				cashDiffDetail.setTicketPrize(new BigDecimal(0));

				cashDiffDetail.setDiffMoney(cashDiffDetail.getSendPrize().subtract(cashDiffDetail.getTicketPrize()));
				cashDiffDetail.setDfInitDate(new Date());
				tList.add(cashDiffDetail);
			}
		}
		for (int i = 0; i < CashList.size(); i++) {
			BigDecimal ticketPrize = CashList.get(i).getTicketPrize();
			if (null == ticketPrize) {
				ticketPrize = new BigDecimal(0);
			}
			List<String> sList = new ArrayList<String>();
			for (int j = 0; j < SendList.size(); j++) {
				sList.add(SendList.get(j).getTicketNo());
			}
			// 吞票
			if (null == CashList.get(i).getTicketNo()) {
				CashDiffDetail cashDiffDetail = new CashDiffDetail();
				SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = null;
				try {
					parse = _sdf.parse(_sdf.format(CashList.get(i).getNormalDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				cashDiffDetail.setNormalDate(parse);
				cashDiffDetail.setTicketNo("");
				cashDiffDetail.setTicketPrize(ticketPrize);
				cashDiffDetail.setSendPrize(new BigDecimal(0));
				cashDiffDetail.setDiffMoney(new BigDecimal(0).subtract(ticketPrize));
				cashDiffDetail.setDfInitDate(new Date());
				cashDiffDetail.setDfProcessInfo("票机吞票");
				tList.add(cashDiffDetail);
			}
			if (CashList.get(i).getTicketNo()!=null && !sList.contains(CashList.get(i).getTicketNo())) {
				CashDiffDetail cashDiffDetail = new CashDiffDetail();
				SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = null;
				try {
					parse = _sdf.parse(_sdf.format(CashList.get(i).getNormalDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cashDiffDetail.setNormalDate(parse);

				cashDiffDetail.setTicketNo(CashList.get(i).getTicketNo());

				cashDiffDetail.setTicketPrize(ticketPrize);
				cashDiffDetail.setSendPrize(new BigDecimal(0));
				cashDiffDetail.setDiffMoney(ticketPrize);
				cashDiffDetail.setDfInitDate(new Date());
				tList.add(cashDiffDetail);
			}
		}
		Map<String, Object> arguMap = new HashMap<String, Object>();
		arguMap.put("_startDate", date);
		arguMap.put("_endDate", date);
		arguMap.put("_tList", tList);
		// 若该日期已经跑批过,删除该跑批日期所有的明细
		if (tList.size() != 0) {
			cashDifferenceDao.deleteByTicketNo(arguMap);
			cashDifferenceDao.saveCashDiffDetail(tList);
		}
		return tList;

	}

	/**
	 * @功能描述：保存该时间段内的兑奖差异数据到汇总表中,并返回该列表
	 * @author Xin
	 * @DATE 2017年3月20日下午1:58:01
	 */
	@Override
	public List<CashDifference> saveCashDifferenceInfo(String date) {
		// 设置日期为跑批日期所在的年和月日期
		String[] _temp = date.split("-");
		StringBuffer sb = new StringBuffer();
		sb.append(_temp[0]).append("-").append(_temp[1]);

		BigDecimal cashTotalAccount = cashDifferenceDao.findCashSumbyDates(date);
		BigDecimal sendPrizeSumByDates = cashDifferenceDao.findSendPrizeSumByDates(date);
		List<CashDifference> cashDifferenceList = new ArrayList<CashDifference>();
		CashDifference cashDifference = new CashDifference();

		if (null == cashTotalAccount) {
			cashTotalAccount = new BigDecimal(0);
		}
		if (null == sendPrizeSumByDates) {
			sendPrizeSumByDates = new BigDecimal(0);
		}
		cashDifference.setOnlineRedeemAmount(cashTotalAccount);
		cashDifference.setOnlineSentAmount(sendPrizeSumByDates);
		cashDifference.setRedeemSentGap(sendPrizeSumByDates.subtract(cashTotalAccount));
		// 依据当前日期-1获取期末差异额,封装为该日期下的期初差异额
		// String sDate = cashDifference.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		Date parseDate = null;
		try {
			parseDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 获取该日期所在月份的上个月的年和月日期
		Date d = DateUtils.getPreMonthStart(parseDate);
		String bTemp = sdf.format(d);
		String[] bDate = bTemp.split("-");
		StringBuffer bsb = new StringBuffer();
		bsb.append(bDate[0]).append("-").append(bDate[1]);
		// 查询上个月的差异
		BigDecimal findEndGap = cashDifferenceDao.findEndGap(bsb.toString());

		if (findEndGap == null) {
			BigDecimal temp = new BigDecimal(0);
			findEndGap = temp;
		}
		cashDifference.setStartGap(findEndGap);
		// 封装期末差异额:期初差异额+本日的差异额
		cashDifference.setEndGap(cashDifference.getStartGap().add(cashDifference.getRedeemSentGap()));

		// 封装总兑奖金额：线上兑奖+线下兑奖
		BigDecimal offlineRedeemAmount = cashDifference.getOfflineRedeemAmount();
		if (offlineRedeemAmount == null) {
			BigDecimal temp = new BigDecimal(0);
			offlineRedeemAmount = temp;
		}
		cashDifference.setOfflineRedeemAmount(offlineRedeemAmount);
		cashDifference.setTotalRedeemAmount(cashTotalAccount.add(offlineRedeemAmount));
		cashDifference.setDate(sb.toString());// 兑奖月份日期
		// cashDifference.setDate(date);
		// cashDifference.setCreateUser(AccountShiroUtil.getCurrentUser().getLoginName());// 创建人
		CashDifference _cashDifference = cashDifferenceDao.findOfflineRedeemAmount(sb.toString());
		if (_cashDifference!=null){
			cashDifference.setOfflineRedeemAmount(cashDifference.getOfflineRedeemAmount().add(_cashDifference.getOfflineRedeemAmount()));
		}
		cashDifferenceList.add(cashDifference);
		// 先删除该日的跑批数据
		cashDifferenceDao.deleteCashDiffByDate(cashDifference);
		cashDifferenceDao.saveCashDifferenceList(cashDifferenceList);
		return cashDifferenceList;
	}

	/**
	 * @功能描述： 生成兑奖派奖的差异的汇总报表
	 * @author xin
	 * @DATE 2017年3月21日上午10:11:56
	 */
	@SuppressWarnings("resource")
	@Override
	public void GenerateCashCountReport() {
		logger.info("进入兑奖差异总报生成excle的方法！");
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("兑奖差异总报表");
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -1);
		String dates = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 昨天数据放入List
		List<CashDifference> list = new ArrayList<CashDifference>();

		try {
			// 调用本类的方法
			String date = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
			list = this.saveCashDifferenceInfo(date);
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		Object[][] data = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getDate();
			BigDecimal startGap = list.get(i).getStartGap();

			if (null == list.get(i).getStartGap()) {
				list.get(i).setStartGap(new BigDecimal(0));
			}

			BigDecimal onlineRedeemAmount = list.get(i).getOnlineRedeemAmount();// 线上兑奖总金额

			if (null == list.get(i).getOnlineRedeemAmount()) {
				list.get(i).setOnlineRedeemAmount(new BigDecimal(0));
			}

			BigDecimal bigAmount = list.get(i).getBigAmount();
			if (null == bigAmount) {
				bigAmount = new BigDecimal(0);
			}
			list.get(i).setBigAmount(bigAmount);
			BigDecimal onlineSentAmount = list.get(i).getOnlineSentAmount();

			if (null == list.get(i).getOnlineSentAmount()) {
				list.get(i).setOnlineSentAmount(new BigDecimal(0));
			}

			BigDecimal redeemSentGap = list.get(i).getRedeemSentGap();

			if (null == list.get(i).getRedeemSentGap()) {
				list.get(i).setRedeemSentGap(new BigDecimal(0));
			}

			BigDecimal endGap = list.get(i).getEndGap();

			if (null == list.get(i).getEndGap()) {
				list.get(i).setEndGap(new BigDecimal(0));
			}

			BigDecimal offlineRedeemAmount = list.get(i).getOfflineRedeemAmount();
			if (null == offlineRedeemAmount) {
				// list.get(i).setOfflineRedeemAmount(new BigDecimal(0));
				offlineRedeemAmount = new BigDecimal(0);
			}
			list.get(i).setOfflineRedeemAmount(offlineRedeemAmount);
			BigDecimal totalRedeemAmount = list.get(i).getTotalRedeemAmount();

			if (null == list.get(i).getTotalRedeemAmount()) {
				list.get(i).setTotalRedeemAmount(new BigDecimal(0));
			}

			// Date createDate = list.get(i).getCreateDate();
			// String createUser = list.get(i).getCreateUser();
			data[i] = new Object[] {
					date, startGap, onlineRedeemAmount, bigAmount, onlineSentAmount, redeemSentGap, endGap, offlineRedeemAmount, totalRedeemAmount };
		}
		// 显示标题
		Row title_row = sheet.createRow(0);
		title_row.setHeight((short) (40 * 20));
		Cell title_cell = title_row.createCell(0);
		String headers[] = new String[] {
				"日期", "期初差异额", "线上兑奖总额", "大额兑奖", "线上派奖金额", "线上兑奖和派奖的差异", "期末差异额", "线下兑奖总额", "总的兑奖金额 " };
		Row header_row = sheet.createRow(1);
		header_row.setHeight((short) (20 * 24));
		// 创建单元格的 显示样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
		title_cell.setCellStyle(style);
		title_cell.setCellValue("兑奖差异总报表");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
		for (int i = 0; i < headers.length; i++) {
			// 设置列宽 基数为256
			sheet.setColumnWidth(i, 20 * 256);
			Cell cell = header_row.createCell(i);
			// 应用样式到 单元格上
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		for (int i = 0; i < data.length; i++) {
			Row row = sheet.createRow(i + 2);
			row.setHeight((short) (20 * 20)); // 设置行高 基数为20
			for (int j = 0; j < data[i].length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(data[i][j] + "");
			}
		}
		FileOutputStream fileOut;
		// 查询数据字典得到路径
//		String filePath = "";
		SysDict _sysDict = sysDictDao.findFilePath("filePath");
		String filePath = _sysDict.getParamValue();
//		List<SysDict> sysDicts = sysDictService.find(new SysDict());
//		for (SysDict sysDict : sysDicts) {
//			if (sysDict.getParamKey().equals("filePath")) {
//				filePath = sysDict.getParamValue();
//			}
//		}
		try {
			fileOut = new FileOutputStream(filePath + cashTotalFileName + dates + "(跑批产生的)" + suffix);
			wb.write(fileOut);
			fileOut.close();
			logger.info("跑批生成销售差异总报表成功" + dates);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("跑批生成销售差异总报表失败" + e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("跑批生成销售差异总报表失败" + e);
		}
	}

	/**
	 * @功能描述:查询兑奖详细明细的方法
	 * @author Xin
	 * @DATE 2017年3月21日下午6:53:50
	 */
	@Override
	public Page<CashDiffDetail> findCashDiffDetails(String date, Page page) {
		List<CashDiffDetail> blist = cashDifferenceDao.findCashDiffDetails(date);
		page.setResults(blist);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<CashDiffDetail>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(blist.size());
		}
		return page;
	}

	@SuppressWarnings("resource")
	@Override
	public void GenerateDetailedReport() {
		logger.info("进入兑奖差异明细报表生成excle类！");
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("兑奖差异明细报表");
		Calendar cald = Calendar.getInstance();
		// cald.add(Calendar.DATE, -1);
		String gettime = new SimpleDateFormat("yyyy-MM-dd").format(cald.getTime());

		List<CashDiffDetail> list = new ArrayList<CashDiffDetail>();

		list = this.saveCashDifferenceDetail(gettime);

		if (list.size() == 0) {

			throw new NullPointerException();

		}

		Object[][] data = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date normalDate = list.get(i).getNormalDate();
			Date PrizeTime = list.get(i).getPrizeTime();
			String cashDate = "";
			if (normalDate != null) {

				cashDate = sdf.format(normalDate);
			}
			String prizeDate = "";
			if (PrizeTime != null) {

				prizeDate = sdf.format(PrizeTime);
			}
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// Date normalDate = list.get(i).getNormalDate();
			// String date = sdf.format(normalDate);
			CashDiffDetail cashDiffDetail = list.get(i);
			String ticketNo = list.get(i).getTicketNo();
			BigDecimal sendPrize = list.get(i).getSendPrize();
			BigDecimal ticketPrize = list.get(i).getTicketPrize();
			BigDecimal diffMoney = list.get(i).getDiffMoney();

			if (null == list.get(i).getSendPrize()) {
				list.get(i).setSendPrize(new BigDecimal(0));
			}
			if (null == list.get(i).getTicketPrize()) {
				list.get(i).setTicketPrize(new BigDecimal(0));
			}
			if (null == list.get(i).getDiffMoney()) {
				list.get(i).setDiffMoney(new BigDecimal(0));
			}
			data[i] = new Object[] {
					cashDate, prizeDate, ticketNo, sendPrize, ticketPrize, diffMoney };
		}
		// 显示标题
		Row title_row = sheet.createRow(0);
		title_row.setHeight((short) (40 * 20));
		Cell title_cell = title_row.createCell(0);
		String headers[] = new String[] {
				"兑奖日期", "派奖日期", "编号", "线上派奖金额", "线上兑奖金额", "差异金额" };
		Row header_row = sheet.createRow(1);
		header_row.setHeight((short) (20 * 24));
		// 创建单元格的 显示样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
		title_cell.setCellStyle(style);
		title_cell.setCellValue("兑奖差异明细报表");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
		for (int i = 0; i < headers.length; i++) {
			// 设置列宽 基数为256
			sheet.setColumnWidth(i, 20 * 256);
			Cell cell = header_row.createCell(i);
			// 应用样式到 单元格上
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		for (int i = 0; i < data.length; i++) {
			Row row = sheet.createRow(i + 2);
			row.setHeight((short) (20 * 20)); // 设置行高 基数为20
			for (int j = 0; j < data[i].length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(data[i][j] + "");
			}
		}
		FileOutputStream fileOut;
		// 查询数据字典得到路径
		SysDict _sysDict = sysDictDao.findFilePath("filePath");
		String filePath = _sysDict.getParamValue();
//		String filePath = "";
//		List<SysDict> sysDicts = sysDictService.find(new SysDict());
//		for (SysDict sysDict : sysDicts) {
//			if (sysDict.getParamKey().equals("filePath")) {
//				filePath = sysDict.getParamValue();
//			}
//		}
		try {
			fileOut = new FileOutputStream(filePath + cashDetailFileName + gettime + "(跑批产生的)" + suffix);
			wb.write(fileOut);
			fileOut.close();
			logger.info("跑批生成兑奖差异明细报表成功" + gettime);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("跑批生成兑奖差异明细报表失败" + e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("跑批生成兑奖差异明细报表失败" + e);
		}

	}

	/**
	 * @功能描述:重置该时间段内的兑奖差异数据
	 * @author xin
	 * @DATE 2017年3月29日下午1:22:49
	 */
	@Override
	public Page<CashDifference> restTask(Date startDate, Date endDate, Page<CashDifference> page) {
		logger.info("执行手动重置方法，调用跑批程序，生成数据!");
		// this.dateRestTaskDetail(startDate, endDate);
		// TODO
		// 重置总表数据
		this.dateRestTask(startDate, endDate);
		// 重置差异明细数据
		this.dateRestDetailTask(startDate, endDate);

		return page;
	}

	@Override
	public List<CashDifference> executeRest(Date startDate, Date endDate) {
		// 查询该段时间内的每个月的兑奖信息(时间如果不跨月,只得到当月)
		List<CashInfo> cashReList = cashDifferenceDao.findCashByRedates(startDate, endDate);
		// 查询得到该段时间内的每个月的派奖信息(时间如果不跨月,只得到当月)
		List<SendPrizeInfo> sendReList = cashDifferenceDao.findsendByRedates(startDate, endDate);

		CashDifference cashDifference = null;

		if (cashReList.size() == 0 || sendReList.size() == 0) {
			logger.info("要重置的兑奖或者派奖数据为空!");
			throw new NullPointerException();
		}

		if (null != cashReList && null != sendReList) {

			for (int i = 0; i < sendReList.size(); i++) {
				// 定义字符串集合
				List<String> cList = new ArrayList<String>();
				String sTime = DateUtils.formatDate(sendReList.get(i).getPrizeTime());
				String _sTime = StringDateUtils.subDateString(sTime);
				for (int j = 0; j < cashReList.size(); j++) {
					// 依据年和月份日期相等比较
					String cTime = DateUtils.formatDate(cashReList.get(j).getPrizeDate());
					String _cTime = StringDateUtils.subDateString(cTime);
					// 把每个兑奖的年月日期放入集合
					cList.add(_cTime);
					if (_sTime.equals(_cTime)) {
						cashDifference = new CashDifference();
						BigDecimal sendPrizeTotalAccount = sendReList.get(i).getSendPrizeTotalAccount();
						BigDecimal cashTotalAccount = cashReList.get(j).getCashTotalAccount();
						if (sendPrizeTotalAccount == null) {
							sendPrizeTotalAccount = new BigDecimal(0);
						}
						cashDifference.setOnlineSentAmount(sendPrizeTotalAccount);
						if (cashTotalAccount == null) {
							cashTotalAccount = new BigDecimal(0);
						}
						cashDifference.setOnlineRedeemAmount(cashTotalAccount);
						cashDifference.setRedeemSentGap(sendPrizeTotalAccount.subtract(cashTotalAccount));// 设定派奖和兑奖差异
						// 得到该循环的日期的上个月的年和月的日期
						SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
						Date parse = null;
						try {
							parse = sdf.parse(sTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						Date d = DateUtils.getPreMonthStart(parse);
						String bTemp = sdf.format(d);
						String[] bDate = bTemp.split("-");
						StringBuffer bsb = new StringBuffer();
						bsb.append(bDate[0]).append("-").append(bDate[1]);
						// 查询上个月的差异
						BigDecimal findEndGap = cashDifferenceDao.findEndGap(bsb.toString());
						// String tempDate = DateUtils.getPreDateByDay(sendReList.get(i).getPrizeTime(), "-1");
						// BigDecimal findEndGap = cashDifferenceDao.findEndGap(tempDate);
						if (null == findEndGap) {
							findEndGap = new BigDecimal(0);
						}
						cashDifference.setStartGap(findEndGap);
						// 封装期末差异额:期初差异额+本日的差异额
						cashDifference.setEndGap(cashDifference.getStartGap().add(cashDifference.getRedeemSentGap()));
						cashDifference.setDate(sTime);// 对账时间
						// 查询该日期的差异得到线下录入的兑奖金额
						List<CashDifference> tempList = cashDifferenceDao.findByDate(cashDifference);
						if (tempList.size() == 0) {
							cashDifference.setOfflineRedeemAmount(new BigDecimal(0));
						} else {
							BigDecimal offlineRedeemAmount = tempList.get(0).getOfflineRedeemAmount();
							// 封装总兑奖金额：线上兑奖+线下兑奖
							if (offlineRedeemAmount == null) {
								BigDecimal temp = new BigDecimal(0);
								offlineRedeemAmount = temp;
							}
							cashDifference.setOfflineRedeemAmount(offlineRedeemAmount);
						}
						cashDifference.setTotalRedeemAmount(cashTotalAccount.add(cashDifference.getOfflineRedeemAmount()));
						// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						// String str = sdf.format(sendReList.get(i).getPrizeTime());
						// cashDifference.setCreateUser(AccountShiroUtil.getCurrentUser().getLoginName());// 创建人
						List<CashDifference> list = new ArrayList<CashDifference>();
						cashDifference.setDate(_sTime);
						cashDifference.setCreateDate(new Date());
						cashDifferenceDao.deleteCashDiffByDate(cashDifference);
						list.add(cashDifference);
						cashDifferenceDao.saveCashDifferenceList(list);
					}
				}
				// 判定兑奖日期中是否包含该派奖日期
				if (!cList.contains(_sTime)) {
					// 不包含
					cashDifference = new CashDifference();
					BigDecimal sendPrizeTotalAccount = sendReList.get(i).getSendPrizeTotalAccount();
					if (sendPrizeTotalAccount == null) {
						sendPrizeTotalAccount = new BigDecimal(0);
					}
					cashDifference.setOnlineSentAmount(sendPrizeTotalAccount);// 派奖总和
					cashDifference.setOnlineRedeemAmount(new BigDecimal(0));// 兑奖总和
					cashDifference.setRedeemSentGap(sendPrizeTotalAccount.subtract(new BigDecimal(0)));// 设定派奖和兑奖差异
					// 得到该循环的日期的上个月的年和月的日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
					Date parse = null;
					try {
						parse = sdf.parse(sTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Date d = DateUtils.getPreMonthStart(parse);
					String bTemp = sdf.format(d);
					String[] bDate = bTemp.split("-");
					StringBuffer bsb = new StringBuffer();
					bsb.append(bDate[0]).append("-").append(bDate[1]);
					// 查询上个月的差异
					BigDecimal findEndGap = cashDifferenceDao.findEndGap(bsb.toString());
					// String tempDate = DateUtils.getPreDateByDay(sendReList.get(i).getPrizeTime(), "-1");
					// BigDecimal findEndGap = cashDifferenceDao.findEndGap(tempDate);
					if (null == findEndGap) {
						findEndGap = new BigDecimal(0);
					}
					cashDifference.setStartGap(findEndGap);
					// 封装期末差异额:期初差异额+本月的差异额
					cashDifference.setEndGap(cashDifference.getStartGap().add(cashDifference.getRedeemSentGap()));

					cashDifference.setDate(sTime);// 对账时间
					// 查询该日期的差异得到线下录入的兑奖金额
					List<CashDifference> tempList = cashDifferenceDao.findByDate(cashDifference);
					BigDecimal offlineRedeemAmount = tempList.get(0).getOfflineRedeemAmount();
					// 封装总兑奖金额：线上兑奖+线下兑奖
					if (offlineRedeemAmount == null) {
						BigDecimal temp = new BigDecimal(0);
						offlineRedeemAmount = temp;
					}
					cashDifference.setOfflineRedeemAmount(offlineRedeemAmount);
					cashDifference.setTotalRedeemAmount(cashDifference.getTotalRedeemAmount().add(offlineRedeemAmount));
					List<CashDifference> list = new ArrayList<CashDifference>();
					cashDifference.setDate(_sTime);
					cashDifference.setCreateDate(new Date());
					cashDifferenceDao.deleteCashDiffByDate(cashDifference);
					list.add(cashDifference);
					cashDifferenceDao.saveCashDifferenceList(list);
				}
			}
			// 对调遍历对比
			for (int i = 0; i < cashReList.size(); i++) {
				// 定义字符串集合
				List<String> sList = new ArrayList<String>();
				String cTime = DateUtils.formatDate(cashReList.get(i).getPrizeDate());
				String _cTime = StringDateUtils.subDateString(cTime);
				for (int j = 0; j < sendReList.size(); j++) {
					String sTime = DateUtils.formatDate(sendReList.get(j).getPrizeTime());
					String _sTime = StringDateUtils.subDateString(sTime);
					// 把每个兑奖的年月日期放入集合
					sList.add(_cTime);
				}
				if (!sList.contains(_cTime)) {
					cashDifference = new CashDifference();
					BigDecimal cashTotalAccount = cashReList.get(i).getCashTotalAccount();
					if (cashTotalAccount == null) {
						cashTotalAccount = new BigDecimal(0);
					}
					cashDifference.setOnlineRedeemAmount(cashTotalAccount);// 兑奖总和
					cashDifference.setOnlineSentAmount(new BigDecimal(0));// 派奖总和
					cashDifference.setRedeemSentGap(new BigDecimal(0).subtract(cashTotalAccount));// 设定派奖和兑奖差异
					// 得到该循环的日期的上个月的年和月的日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
					Date parse = null;
					try {
						parse = sdf.parse(cTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Date d = DateUtils.getPreMonthStart(parse);
					String bTemp = sdf.format(d);
					String[] bDate = bTemp.split("-");
					StringBuffer bsb = new StringBuffer();
					bsb.append(bDate[0]).append("-").append(bDate[1]);
					// 查询上个月的差异
					BigDecimal findEndGap = cashDifferenceDao.findEndGap(bsb.toString());
					if (null == findEndGap) {
						findEndGap = new BigDecimal(0);
					}
					cashDifference.setStartGap(findEndGap);
					// 封装期末差异额:期初差异额+本月的差异额
					cashDifference.setEndGap(cashDifference.getStartGap().add(cashDifference.getRedeemSentGap()));

					cashDifference.setDate(cTime);// 对账时间
					// 查询该日期的差异得到线下录入的兑奖金额
					List<CashDifference> tempList = cashDifferenceDao.findByDate(cashDifference);
					BigDecimal offlineRedeemAmount = tempList.get(0).getOfflineRedeemAmount();
					// 封装总兑奖金额：线上兑奖+线下兑奖
					if (offlineRedeemAmount == null) {
						BigDecimal temp = new BigDecimal(0);
						offlineRedeemAmount = temp;
					}
					cashDifference.setOfflineRedeemAmount(offlineRedeemAmount);
					cashDifference.setTotalRedeemAmount(cashDifference.getTotalRedeemAmount().add(offlineRedeemAmount));
					List<CashDifference> list = new ArrayList<CashDifference>();
					cashDifference.setDate(_cTime);
					cashDifference.setCreateDate(new Date());
					cashDifferenceDao.deleteCashDiffByDate(cashDifference);
					list.add(cashDifference);

					cashDifferenceDao.saveCashDifferenceList(list);
				}
			}

		}
		// 查询该月的所有差异数据
		// cashDifferenceDao.saveCashDifferenceList(list);
		List<CashDifference> allDiffList = cashDifferenceDao.findAllCashDiffByMonth(startDate);

		return allDiffList;
	}

	/**
	 * @功能描述： 生成兑奖派奖的差异的汇总报表
	 * @author xin
	 * @DATE 2017年3月21日上午10:11:56
	 */
	@SuppressWarnings("resource")
	@Override
	public void dateRestTask(Date startTime, Date endTime) {
		logger.info("进入重置兑奖差异总报生成excle的方法！");
		// String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");
		// String str[] = currentDate.split("-");
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("重置兑奖差异总报表");

		List<CashDifference> list = null;

		try {

			list = this.executeRest(startTime, endTime);
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		Object[][] data = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getDate();
			BigDecimal startGap = list.get(i).getStartGap();

			if (null == list.get(i).getStartGap()) {
				list.get(i).setStartGap(new BigDecimal(0));
			}

			BigDecimal onlineRedeemAmount = list.get(i).getOnlineRedeemAmount();// 线上兑奖总金额

			if (null == list.get(i).getOnlineRedeemAmount()) {
				list.get(i).setOnlineRedeemAmount(new BigDecimal(0));
			}

			BigDecimal bigAmount = list.get(i).getBigAmount();
			if (null == list.get(i).getBigAmount()) {
				list.get(i).setBigAmount(new BigDecimal(0));
			}

			BigDecimal onlineSentAmount = list.get(i).getOnlineSentAmount();

			if (null == list.get(i).getOnlineSentAmount()) {
				list.get(i).setOnlineSentAmount(new BigDecimal(0));
			}

			BigDecimal redeemSentGap = list.get(i).getRedeemSentGap();

			if (null == list.get(i).getRedeemSentGap()) {
				list.get(i).setRedeemSentGap(new BigDecimal(0));
			}

			BigDecimal endGap = list.get(i).getEndGap();

			if (null == list.get(i).getEndGap()) {
				list.get(i).setEndGap(new BigDecimal(0));
			}

			BigDecimal offlineRedeemAmount = list.get(i).getOfflineRedeemAmount();
			if (null == list.get(i).getOfflineRedeemAmount()) {
				list.get(i).setOfflineRedeemAmount(new BigDecimal(0));
			}
			BigDecimal totalRedeemAmount = list.get(i).getTotalRedeemAmount();

			if (null == list.get(i).getTotalRedeemAmount()) {
				list.get(i).setTotalRedeemAmount(new BigDecimal(0));
			}

			// Date createDate = list.get(i).getCreateDate();
			// String createUser = list.get(i).getCreateUser();
			data[i] = new Object[] {
					date, startGap, onlineRedeemAmount, bigAmount, onlineSentAmount, redeemSentGap, endGap, offlineRedeemAmount, totalRedeemAmount };
		}
		// 显示标题
		Row title_row = sheet.createRow(0);
		title_row.setHeight((short) (40 * 20));
		Cell title_cell = title_row.createCell(0);
		String headers[] = new String[] {
				"日期", "期初差异额", "线上兑奖总额", "大额兑奖", "线上派奖金额", "线上兑奖和派奖的差异", "期末差异额", "线下兑奖总额", "总的兑奖金额 " };
		Row header_row = sheet.createRow(1);
		header_row.setHeight((short) (20 * 24));
		// 创建单元格的 显示样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
		title_cell.setCellStyle(style);
		title_cell.setCellValue("重置兑奖差异总报表");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
		for (int i = 0; i < headers.length; i++) {
			// 设置列宽 基数为256
			sheet.setColumnWidth(i, 20 * 256);
			Cell cell = header_row.createCell(i);
			// 应用样式到 单元格上
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		for (int i = 0; i < data.length; i++) {
			Row row = sheet.createRow(i + 2);
			row.setHeight((short) (20 * 20)); // 设置行高 基数为20
			for (int j = 0; j < data[i].length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(data[i][j] + "");
			}
		}
		FileOutputStream fileOut;
		// 查询数据字典得到路径
		SysDict _sysDict = sysDictDao.findFilePath("filePath");
		String filePath = _sysDict.getParamValue();
//		String filePath = "";
//		List<SysDict> sysDicts = sysDictService.find(new SysDict());
//		for (SysDict sysDict : sysDicts) {
//			if (sysDict.getParamKey().equals("filePath")) {
//				filePath = sysDict.getParamValue();
//			}
//		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String temDate = sdf.format(startTime);
			String[] temp = temDate.split("-");
			StringBuffer sb = new StringBuffer();
			sb.append(temp[0]);
			String _date = sb.toString();
			fileOut = new FileOutputStream(filePath + cashTotalFileName + _date + suffix);
			wb.write(fileOut);
			fileOut.close();
			logger.info("重置生成兑奖差异总报表成功" + temDate);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("重置生成兑奖差异总报表失败" + e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("重置生成兑奖差异总报表失败" + e);
		}
	}

	/**
	 * @功能描述:保存线下兑奖录入数据
	 * @author Dingj
	 * @DATE 2017年3月29日下午5:30:12
	 */
	@Override
	public void updateCountAumone(CashDifference cs) {
		cashDifferenceDao.updateCountAumone(cs);
	}

	@SuppressWarnings("resource")
	@Override
	public void dateRestDetailTask(Date startDate, Date endDate) {
		logger.info("进入重置兑奖差异明细报表生成excle方法！");
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("生成重置兑奖差异明细报表");
		// Calendar cald = Calendar.getInstance();
		// cald.add(Calendar.DATE, -1);
		// String gettime = new SimpleDateFormat("yyyy-MM-dd").format(cald.getTime());

		List<CashDiffDetail> list = new ArrayList<CashDiffDetail>();

		list = this.executeRestDetail(startDate, endDate);

		Object[][] data = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date normalDate = list.get(i).getNormalDate();
			Date PrizeTime = list.get(i).getPrizeTime();
			String cashDate = "";
			if (normalDate != null) {

				cashDate = sdf.format(normalDate);
			}
			String prizeDate = "";
			if (PrizeTime != null) {

				prizeDate = sdf.format(PrizeTime);
			}
			String ticketNo = list.get(i).getTicketNo();
			BigDecimal sendPrize = list.get(i).getSendPrize();
			BigDecimal ticketPrize = list.get(i).getTicketPrize();
			BigDecimal diffMoney = list.get(i).getDiffMoney();

			if (null == list.get(i).getSendPrize()) {
				list.get(i).setSendPrize(new BigDecimal(0));
			}
			if (null == list.get(i).getTicketPrize()) {
				list.get(i).setTicketPrize(new BigDecimal(0));
			}
			if (null == list.get(i).getDiffMoney()) {
				list.get(i).setDiffMoney(new BigDecimal(0));
			}
			data[i] = new Object[] {
					cashDate, prizeDate, ticketNo, sendPrize, ticketPrize, diffMoney };
		}
		// 显示标题
		Row title_row = sheet.createRow(0);
		title_row.setHeight((short) (40 * 20));
		Cell title_cell = title_row.createCell(0);
		String headers[] = new String[] {
				"计奖日期", "派奖日期", "编号", "线上派奖金额", "线上兑奖金额", "差异金额" };
		Row header_row = sheet.createRow(1);
		header_row.setHeight((short) (20 * 24));
		// 创建单元格的 显示样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
		title_cell.setCellStyle(style);
		title_cell.setCellValue("重置兑奖差异明细报表");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
		for (int i = 0; i < headers.length; i++) {
			// 设置列宽 基数为256
			sheet.setColumnWidth(i, 20 * 256);
			Cell cell = header_row.createCell(i);
			// 应用样式到 单元格上
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		for (int i = 0; i < data.length; i++) {
			Row row = sheet.createRow(i + 2);
			row.setHeight((short) (20 * 20)); // 设置行高 基数为20
			for (int j = 0; j < data[i].length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(data[i][j] + "");
			}
		}
		FileOutputStream fileOut;
		SysDict _sysDict = sysDictDao.findFilePath("filePath");
		String filePath = _sysDict.getParamValue();
//		String filePath = "";
//		List<SysDict> sysDicts = sysDictService.find(new SysDict());
//		for (SysDict sysDict : sysDicts) {
//			if (sysDict.getParamKey().equals("filePath")) {
//				filePath = sysDict.getParamValue();
//			}
//		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String temDate = sdf.format(startDate);
			String[] dates = temDate.split("-");
			StringBuffer sb = new StringBuffer();
			sb.append(dates[0]);
			String _date = sb.toString();

			fileOut = new FileOutputStream(filePath + cashDetailFileName + _date + suffix);
			wb.write(fileOut);
			fileOut.close();
			logger.info("生成重置兑奖差异明细报表成功" + _date);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("生成重置兑奖差异明细报表失败" + e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("生成重置兑奖差异明细报表失败" + e);
		}

	}

	/**
	 * @方法功能描述:执行具体的重置差异明细逻辑
	 * @param startDate
	 * @param endDate
	 * @return List<CashDiffDetail>
	 * @author Dingj
	 * @创建时间： 2017年3月29日下午7:51:37
	 */
	public List<CashDiffDetail> executeRestDetail(Date startDate, Date endDate) {
		// 得到该段时间内的所有派奖的订单(schemeExtendId)金额讯息
		List<CashDiffDetail> SendList = cashDifferenceDao.refindSendDiffBySchemeId(startDate, endDate);
		// 得到该段时间内的所有兑奖的订单(schemeExtendId)金额讯息
		List<CashDiffDetail> CashList = cashDifferenceDao.refindCashDiffBySchemeId(startDate, endDate);

		List<CashDiffDetail> tList = new ArrayList<CashDiffDetail>();
		for (int i = 0; i < SendList.size(); i++) {
			List<String> cList = new ArrayList<String>();
			BigDecimal sendPrize = SendList.get(i).getSendPrize();
			if (null == sendPrize) {
				sendPrize = new BigDecimal(0);
			}
			for (int j = 0; j < CashList.size(); j++) {
				cList.add(CashList.get(j).getTicketNo());
				BigDecimal ticketPrize = CashList.get(j).getTicketPrize();
				if (null == ticketPrize) {
					ticketPrize = new BigDecimal(0);
				}
				String sNum = SendList.get(i).getTicketNo();
				String cNum = CashList.get(j).getTicketNo();
				// 兑奖.派奖的时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sendT = sdf.format(SendList.get(i).getPrizeTime());
				String cashT = sdf.format(CashList.get(j).getNormalDate());
				String _sendDate = StringDateUtils.subDateString(sendT);
				String _cashDate = StringDateUtils.subDateString(cashT);
				// && _cashDate.equals(_sendDate)
				if (SendList.get(i).getTicketNo().equals(CashList.get(j).getTicketNo()) && sendPrize.equals(ticketPrize)) {
					continue;
				}
				// && _cashDate.equals(_sendDate)
				if (SendList.get(i).getTicketNo().equals(CashList.get(j).getTicketNo()) && !sendPrize.equals(ticketPrize)) {
					CashDiffDetail cashDiffDetail = new CashDiffDetail();
					cashDiffDetail.setPrizeTime(SendList.get(i).getPrizeTime());// 派奖时间
					cashDiffDetail.setNormalDate(CashList.get(j).getNormalDate());// 兑奖时间
					cashDiffDetail.setTicketNo(SendList.get(i).getTicketNo());// 设置订单号
					cashDiffDetail.setSendPrize(sendPrize);
					cashDiffDetail.setTicketPrize(ticketPrize);
					cashDiffDetail.setDiffMoney(cashDiffDetail.getSendPrize().subtract(cashDiffDetail.getTicketPrize()));
					cashDiffDetail.setDfInitDate(new Date());
					tList.add(cashDiffDetail);

				}
				// 订单相等,兑奖和派奖不在同一个月份(该订单分别放入每个月的差异中)
				// if (SendList.get(i).getTicketNo().equals(CashList.get(j).getTicketNo()) && !_cashDate.equals(_sendDate)) {
				// CashDiffDetail cashDiffDetail = new CashDiffDetail();
				// cashDiffDetail.setPrizeTime(SendList.get(i).getPrizeTime());//派奖时间
				// cashDiffDetail.setTicketNo(SendList.get(i).getTicketNo());
				// cashDiffDetail.setSendPrize(sendPrize);
				// cashDiffDetail.setTicketPrize(new BigDecimal(0));
				// cashDiffDetail.setDiffMoney(sendPrize.subtract(new BigDecimal(0)));
				// cashDiffDetail.setDfInitDate(new Date());
				// tList.add(cashDiffDetail);
				//
				// CashDiffDetail _cashDiffDetail = new CashDiffDetail();
				// _cashDiffDetail.setNormalDate(CashList.get(j).getNormalDate());
				// _cashDiffDetail.setTicketNo(CashList.get(j).getTicketNo());
				// _cashDiffDetail.setTicketPrize(ticketPrize);
				// _cashDiffDetail.setSendPrize(new BigDecimal(0));
				// _cashDiffDetail.setDiffMoney(new BigDecimal(0).subtract(ticketPrize));
				// _cashDiffDetail.setDfInitDate(new Date());
				// tList.add(_cashDiffDetail);
				// }
			}
			int size = cList.size();
			if (!cList.contains(SendList.get(i).getTicketNo())) {
				CashDiffDetail cashDiffDetail = new CashDiffDetail();
				cashDiffDetail.setPrizeTime(SendList.get(i).getPrizeTime());// 派奖时间

				cashDiffDetail.setTicketNo(SendList.get(i).getTicketNo());// 设置订单号

				cashDiffDetail.setSendPrize(sendPrize);

				cashDiffDetail.setTicketPrize(new BigDecimal(0));

				cashDiffDetail.setDiffMoney(cashDiffDetail.getSendPrize().subtract(cashDiffDetail.getTicketPrize()));
				cashDiffDetail.setDfInitDate(new Date());
				tList.add(cashDiffDetail);
			}
		}

		for (int i = 0; i < CashList.size(); i++) {
			BigDecimal ticketPrize = CashList.get(i).getTicketPrize();
			if (null == ticketPrize) {
				ticketPrize = new BigDecimal(0);
			}
			List<String> sList = new ArrayList<String>();
			for (int j = 0; j < SendList.size(); j++) {
				sList.add(SendList.get(j).getTicketNo());
			}
			// 吞票
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (null == CashList.get(i).getTicketNo()) {
				CashDiffDetail cashDiffDetail = new CashDiffDetail();
				cashDiffDetail.setNormalDate(CashList.get(i).getNormalDate());
				cashDiffDetail.setTicketNo("");
				cashDiffDetail.setTicketPrize(ticketPrize);
				cashDiffDetail.setSendPrize(new BigDecimal(0));
				cashDiffDetail.setDiffMoney(new BigDecimal(0).subtract(ticketPrize));
				cashDiffDetail.setDfInitDate(new Date());
				cashDiffDetail.setDfProcessInfo("票机吞票");
				tList.add(cashDiffDetail);
			}

			if (null != CashList.get(i).getTicketNo() && !sList.contains(CashList.get(i).getTicketNo())) {
				CashDiffDetail cashDiffDetail = new CashDiffDetail();
				cashDiffDetail.setNormalDate(CashList.get(i).getNormalDate());

				cashDiffDetail.setTicketNo(CashList.get(i).getTicketNo());

				cashDiffDetail.setTicketPrize(ticketPrize);
				cashDiffDetail.setSendPrize(new BigDecimal(0));
				cashDiffDetail.setDiffMoney(new BigDecimal(0).subtract(ticketPrize));
				cashDiffDetail.setDfInitDate(new Date());
				tList.add(cashDiffDetail);
			}
		}
		Map<String, Object> arguMap = new HashMap<String, Object>();
		arguMap.put("_startDate", startDate);
		arguMap.put("_endDate", endDate);
		arguMap.put("_tList", tList);
		cashDifferenceDao.deleteByTicketNo(arguMap);
		cashDifferenceDao.reSavaCashDetail(tList);
		List<CashDiffDetail> allCashDetialDiffList = cashDifferenceDao.findAllCashDetialDiff(startDate);
		return allCashDetialDiffList;
	}

	/**
	 * @功能描述： 查询该用户方案编号的所有明细
	 * @author Dingj
	 * @DATE 2017年4月17日下午3:27:58
	 */
	@Override
	public Page<CashDiffDetail> findschemeExtendIdDetails(String schemeExtendId,Page<CashDiffDetail> page) {
		List<CashDiffDetail> blist = cashDifferenceDao.findschemeExtendIdDetails(schemeExtendId);
		page.setResults(blist);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<CashDiffDetail>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(blist.size());
		}
		return page;
	}

    /**
     * 下载兑奖总报表
     * @param s
     * @return
     */
	@Override
	public List<Object> download(String s) {
		SysDict _sysDict = sysDictDao.findFilePath("filePath");
		String filePath = _sysDict.getParamValue();
		List<Object> list = new ArrayList<Object>();
		try {
			list = DownloadUtil.download(s, filePath, cashTotalFileName,suffix);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

    /**
     * 下载兑奖明细报表
     * @param date
     * @return
     */
    @Override
    public List<Object> downloadDetail(String date) {
        SysDict _sysDict = sysDictDao.findFilePath("filePath");
        String filePath = _sysDict.getParamValue();
        List<Object> list = new ArrayList<Object>();
        try {
            list = DownloadUtil.download(date, filePath, cashDetailFileName,suffix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CashDifference findOfflineRedeemAmount(String id) {
        return cashDifferenceDao.findOfflineRedeemAmount(id);
    }

}

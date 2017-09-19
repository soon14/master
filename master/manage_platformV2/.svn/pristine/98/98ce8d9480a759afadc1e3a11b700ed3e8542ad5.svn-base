package com.jy.service.impl.system.finance.reconciliation.lottery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.entity.system.finance.reconciliation.lottery.TicketDifference;
import com.jy.repository.system.finance.reconciliation.lottery.TicketDifferenceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.TicketDifferenceService;


@SuppressWarnings("unchecked")
@Service("TicketDifferenceService")
public class TicketDifferenceServiceImpl extends BaseServiceImp<TicketDifference> implements TicketDifferenceService {
	
	
	@Autowired
	private TicketDifferenceDao dao;
	
	@Override
	public List<TicketDifference> findDifference(String vstart, String vend) {
		List<TicketDifference> list = dao.findDifference(vstart, vend);
		return list;
	}
	
	@Override
	public List<TicketDifference> findTicketDifference() {
		List<TicketDifference> list = dao.findTicketDifference();
		return list;
	}
	
	@Override
	public void insertTicketDifference(List<TicketDifference> list) {
		dao.deleteTicketDifference();
		if (list.size() > 10000) {
			List<List<TicketDifference>> aa = averageAssign(list, 20);
			
			for (int i = 0; i < aa.size(); i++) {
				dao.insertTicketDifference(aa.get(i));
			}
		
		} else {
			dao.insertTicketDifference(list);
		}
	}
	
	public static <T> List<List<T>> averageAssign(List<T> source, int n) {
		List<List<T>> result = new ArrayList<List<T>>();
		int remaider = source.size() % n; // (先计算出余数)
		int number = source.size() / n; // 然后是商
		int offset = 0;// 偏移量
		for (int i = 0; i < n; i++) {
			List<T> value = null;
			if (remaider > 0) {
				value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
				remaider--;
				offset++;
			} else {
				value = source.subList(i * number + offset, (i + 1) * number + offset);
			}
			result.add(value);
		}
		return result;
	}
	
	@Override
	public List<TicketDifference> childDiffByBatchNo(String usedDate, String VStart, String VEnd, String VNo) {
		List<TicketDifference> list = dao.childDiffByBatchNo(usedDate, VStart, VEnd, VNo);
		return list;
	}
	
	public String isNull(String str) {
		if (null == str) { return ""; }
		return str;
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
	
	@Override
	public void findExportReport(String path) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date d = cal.getTime();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
		
		List<TicketDifference> list = findDifference(null, null);
		List<TicketDifference> tkList = reconciliation(list);
		
		Object[][] data = new Object[tkList.size()][tkList.size()];
		for (int i = 0; i < tkList.size(); i++) {
			String vStart = isNull(tkList.get(i).getVStart());
			String vEnd = isNull(tkList.get(i).getVEnd());
			String usedDCMoney = isNull(tkList.get(i).getUsedDCMoney());
			String usedTSMoney = isNull(tkList.get(i).getUsedTSMoney());
			String unUsedDCMoney = isNull(tkList.get(i).getUnUsedDCMoney());
			String unUsedTSMoney = isNull(tkList.get(i).getUnUsedTSMoney());
			String overDCMoney = isNull(tkList.get(i).getOverDCMoney());
			String overTSMoney = isNull(tkList.get(i).getOverTSMoney());
			String dNumber = isNull(tkList.get(i).getdNumber());
			data[i] = new Object[] {
			        vStart + "~" + vEnd, usedDCMoney, usedTSMoney, unUsedDCMoney, unUsedTSMoney, overDCMoney, overTSMoney, dNumber };
		}
		
		List<TicketDifference> childList = childDiffByBatchNo(null, null, null, null);
		Object[][] childData = new Object[childList.size()][childList.size()];
		for (int i = 0; i < childList.size(); i++) {
			String vStart = isNull(childList.get(i).getVStart());
			String vEnd = isNull(childList.get(i).getVEnd());
			String usedDate = isNull(childList.get(i).getUsedDate());
			String vNo = isNull(childList.get(i).getVNo());
			String vstatus = isNull(childList.get(i).getVstatus());
			String status = isNull(childList.get(i).getStatus());
			String vMoney = isNull(childList.get(i).getVmoney());
			String money = isNull(childList.get(i).getMoney());
			childData[i] = new Object[] {
			        vStart + "~" + vEnd, usedDate, vNo, vstatus, status, vMoney, money };
		}
		
		Workbook wb = new HSSFWorkbook();
		String[] sheets = {
		        "券汇总明细", "券差异明细" };
		// 创建工作表
		for (int y = 0; y < sheets.length; y++) {
			Sheet sheet = wb.createSheet(sheets[y]);
			// 显示标题
			Row title_row = sheet.createRow(0);
			title_row.setHeight((short) (40 * 20));
			Cell title_cell = title_row.createCell(0);
			
			Row header_row = sheet.createRow(1);
			header_row.setHeight((short) (20 * 24));
			// 创建单元格的 显示样式
			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
			title_cell.setCellStyle(style);
			String headers[] = null;
			if (sheets[y].equals("券汇总明细")) {
				headers = new String[] {
				        "批次", "已用投注总额", "已用体彩总额", "未用投注总额", "未用体彩总额", "过期投注总额", "过期体彩总额", "差异笔数" };
				title_cell.setCellValue("券汇总明细");
				for (int i = 0; i < data.length; i++) {
					Row row = sheet.createRow(i + 2);
					row.setHeight((short) (20 * 20)); // 设置行高 基数为20
					for (int j = 0; j < data[i].length; j++) {
						Cell cell = row.createCell(j);
						cell.setCellValue(data[i][j].toString());
					}
				}
			} else if (sheets[y].equals("券差异明细")) {
				headers = new String[] {
				        "批次", "日期", "券号", "体彩状态", "投注状态", "体彩金额", "投注金额" };
				title_cell.setCellValue("券差异明细");
				for (int i = 0; i < childData.length; i++) {
					Row row = sheet.createRow(i + 2);
					row.setHeight((short) (20 * 20)); // 设置行高 基数为20
					for (int j = 0; j < childData[i].length; j++) {
						Cell cell = row.createCell(j);
						cell.setCellValue(childData[i][j].toString());
					}
				}
			}
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
			for (int i = 0; i < headers.length; i++) {
				// 设置列宽 基数为256
				sheet.setColumnWidth(i, 20 * 256);
				Cell cell = header_row.createCell(i);
				// 应用样式到 单元格上
				cell.setCellStyle(style);
				cell.setCellValue(headers[i]);
			}
		}
		
		FileOutputStream fileOut;
		try {
			
			String day = date.replace("-", "");
			String filePath = path + day + "/";
			File dirFile = new File(filePath);
			if (!dirFile.exists()) {// 文件路径不存在时，自动创建目录
				dirFile.mkdir();
			}
			fileOut = new FileOutputStream(filePath + "券汇总报表" + ".xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

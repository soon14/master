package com.jy.service.impl.system.channels;

import com.jy.common.mybatis.PageCalculation;
import com.jy.common.utils.DateUtils;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.dict.SysDict;
import com.jy.mybatis.Page;
import com.jy.repository.system.channels.CPUserInfoDao;
import com.jy.repository.system.channels.MerchantDao;
import com.jy.repository.system.dict.SysDictDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.DevelopCustomerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @文件名:DevelopCustomerServiceImpl.java
 * @功能描述：查询发展客户页面数据
 * @创建日期:2017年3月2日上午9:51:03
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */
@Service("developCustomerService")
public class DevelopCustomerServiceImpl extends BaseServiceImp<Merchant> implements DevelopCustomerService {

	private static final Logger log = LoggerFactory.getLogger(DevelopCustomerServiceImpl.class);

	@Autowired
	private SysDictDao sysDictDao;

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private CPUserInfoDao cpUserInfoDao;

	@Value("${download.path}")
	private String filePath;

	@Value("${download.suffix}")
	private String suffix;

	@Value("${download.developcustomer.filename}")
	private String developFileName;

	@SuppressWarnings("unchecked")
	@Override
	public Page<Merchant> developCustomerList(Merchant m, Page<Merchant> page) {
		List<Merchant> list = this.countUserDay(m);
		page.setResults(list);
		page.setPageSize(page.getPageSize());
		page.setPageNum(page.getPageNum());
		page = (Page<Merchant>) PageCalculation.getPageCalculation(page);
		if (null != page.getResults()) {
			page.setTotalRecord(list.size());
		}
		return page;
	}

	public List<Merchant> countUserDay(Merchant m) {

		/**
		 * 获取期初 截止到5号之前所有的数据 5号的期初， 5号之前加上5号本身是 5号的期末，5号的期末就是6号的期初。 也就是新增数据 level 1 渠道 2 渠道 3标签 处理方式相同
		 */
		String oneDay = null;
		String toDay = null;
		String date = null;

		if (null == m.getmCreateTime() || ("").equals(m.getmCreateTime())) {

			// 默认为空 是当前时间 - 1 -2
			oneDay = DateUtils.getPreDate(-1);
			toDay = DateUtils.getPreDate(-2);
			date = DateUtils.format(new Date(), "yyyy-MM-dd");
		} else {
			// 指定时间 -1 -2
			oneDay = DateUtils.getSpecifiedDayBefore(m.getmCreateTime(), 1);
			toDay = DateUtils.getSpecifiedDayBefore(m.getmCreateTime(), 2);
			date = m.getmCreateTime();
		}
		// 所有数据进行遍历
		List<Merchant> merchantListBeforeAll = merchantDao.queryMerchantByIdAndDate(oneDay);
		List<Merchant> merchantListNowAll = merchantDao.queryMerchantByIdAndDatenow(toDay);
		for (int i = 0; i < merchantListBeforeAll.size(); i++) {
			for (int j = 0; j < merchantListNowAll.size(); j++) {
				if (merchantListBeforeAll.get(i).getmId() == merchantListNowAll.get(j).getmId()) {
					merchantListBeforeAll.get(i).setDevelopCustomerToday(merchantListNowAll.get(j).getDevelopCustomerToday());
				}
			}
			merchantListBeforeAll.get(i).setmCreateTime(date);
		}
		return merchantListBeforeAll;
	}

	/**
	 * @功能描述： 生成excel
	 * @author lijunke
	 * @DATE 2017年3月3日上午9:33:51
	 */
	@SuppressWarnings("resource")
	@Override
	public void GenerateReport() {
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("发展客户日报");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date d = cal.getTime();
		String dates = new SimpleDateFormat("yyyy-MM-dd").format(d);
		Merchant m = new Merchant();
		List<Merchant> list = this.countUserDay(m);
		Object[][] data = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getmName();
			String ContactUser = list.get(i).getmContactUser();
			String mobile = list.get(i).getmMobile();
			String createDate = list.get(i).getmCreateTime();
			Integer fistDay = list.get(i).getDevelopCustomerNumber();
			Integer toDay = list.get(i).getDevelopCustomerToday();
			data[i] = new Object[] {
					createDate, name, ContactUser, mobile, fistDay, toDay, fistDay + toDay };
		}
		// 显示标题
		Row title_row = sheet.createRow(0);
		title_row.setHeight((short) (40 * 20));
		Cell title_cell = title_row.createCell(0);
		String headers[] = new String[] {
				"日期", "渠道/客户名称", "联系人", "联系方式", "初期客户数", "新增天数", "合计" };
		Row header_row = sheet.createRow(1);
		header_row.setHeight((short) (20 * 24));
		// 创建单元格的 显示样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
		title_cell.setCellStyle(style);
		title_cell.setCellValue("发展客户日报");
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
		try {
			StringBuffer sb = new StringBuffer();
			SysDict sysDict1 = new SysDict();
	        sysDict1.setParamKey("filePath");
	        String falseStr = sysDictDao.find(sysDict1).get(0).getParamValue();
			sb.append(falseStr).append(developFileName).append(dates).append(suffix);
			fileOut = new FileOutputStream(sb.toString());
			wb.write(fileOut);
			fileOut.close();
			log.info("生成发展客户日报成功" + dates);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("生成发展客户日报失败" + e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("生成发展客户日报失败" + e);
		}

	}

}

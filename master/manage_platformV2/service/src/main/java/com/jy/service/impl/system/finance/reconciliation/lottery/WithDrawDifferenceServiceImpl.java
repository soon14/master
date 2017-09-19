package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.days.webservice.direct.CompareWebService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jy.common.utils.DateUtils;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.finance.reconciliation.lottery.WithDrawDifference;
import com.jy.repository.system.finance.reconciliation.lottery.WithDrawDifferenceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.reconciliation.lottery.WithDrawDifferenceService;
import com.jy.vo.webService.FileWithdrawInfoDTO;
import com.jy.vo.webService.ResultData;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



@SuppressWarnings("unchecked")
@Service("WithDrawDifferenceService")
public class WithDrawDifferenceServiceImpl extends BaseServiceImp<WithDrawDifference> implements WithDrawDifferenceService {
	
	private static final Logger log = LoggerFactory.getLogger(WithDrawDifferenceServiceImpl.class);

	@Value("${webservice.client.compareInfo}")
	public String withDrawUrl;//提現信息webservice接口

	@Autowired
	public SysDictService dictService;
	
	@Autowired
	private WithDrawDifferenceDao dao;
	
	@Override
	public List<WithDrawDifference> withDrawDifference(String tDate, String tFlowNo) {
		List<WithDrawDifference> list = dao.findByTDate(tDate, tFlowNo);
		return list;
	}
	
	
	@Override
	public void findExportReport() {
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("提现汇总日报");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date d = cal.getTime();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
		List<WithDrawDifference> list = dao.findByTDate(date, null);
		Object[][] data = new Object[list.size()][list.size()];
		for (int i = 0; i < list.size(); i++) {
			
			String tDate = " ";
			String tFlowNo = " ";
			String tStatus = " ";
			String status = " ";
			double tMoney = 0.00;
			double money = 0.00;
			double cyMoney = 0.00;
			DecimalFormat df = new DecimalFormat("#.00");
			
			if (null != list.get(i).getTdate()) {
				tDate = list.get(i).getTdate();
			}
			if (null != list.get(i).getTFlowNo()) {
				tFlowNo = list.get(i).getTFlowNo();
			}
			if (null != list.get(i).getTStatus()) {
				if (list.get(i).getTStatus().equals("1")) {
					tStatus = "成功";
				} else if (list.get(i).getTStatus().equals("2")) {
					tStatus = "失败";
				} else if (list.get(i).getTStatus().equals("3")) {
					tStatus = "处理中";
				} else if (list.get(i).getTStatus().equals("4")) {
					tStatus = "其它";
				}
			}
			if (null != list.get(i).getStatus()) {
				if (list.get(i).getStatus().equals("1")) {
					status = "成功";
				} else if (list.get(i).getStatus().equals("2")) {
					status = "失败";
				} else if (list.get(i).getStatus().equals("3")) {
					status = "处理中";
				} else if (list.get(i).getStatus().equals("4")) {
					status = "其它";
				}
			}
			
			if (null != list.get(i).getTMoney()) {
				tMoney = Double.parseDouble(list.get(i).getTMoney());
			}
			if (null != list.get(i).getMoney()) {
				money = Double.parseDouble(list.get(i).getMoney());
			}
			if (null != list.get(i).getTMoney() && null != list.get(i).getMoney()) {
				cyMoney = tMoney - money;
			} else if (null != list.get(i).getTMoney() && null == list.get(i).getMoney()) {
				cyMoney = tMoney - 0;
			} else if (null == list.get(i).getTMoney() && null != list.get(i).getMoney()) {
				cyMoney = 0 - money;
			}
			if (cyMoney == 0) {
				cyMoney = 0.00;
			} else {
				cyMoney = Double.parseDouble(df.format(cyMoney));
			}
			data[i] = new Object[] {
			        tDate, tFlowNo, tStatus, status, tMoney, money, cyMoney };
					
		}
		// 显示标题
		Row title_row = sheet.createRow(0);
		title_row.setHeight((short) (40 * 20));
		Cell title_cell = title_row.createCell(0);
		String headers[] = new String[] {
		        "日期", "流水号", "第三方用状态", "数据中心使用状态", "第三方金额", "数据中心金额", "差异金额" };
		Row header_row = sheet.createRow(1);
		header_row.setHeight((short) (20 * 24));
		// 创建单元格的 显示样式
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
		title_cell.setCellStyle(style);
		title_cell.setCellValue("提现汇总日报");
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
				cell.setCellValue(data[i][j].toString());
			}
		}
		FileOutputStream fileOut;
		try {
			SysDict o = new SysDict();
			o.setParamKey("filePath");
			List<SysDict> dictList = dictService.find(o);
			SysDict obj = dictList.get(0);
			String day = date.replace("-", "");
			String filePath = obj.getParamValue() + day + "/";
			File dirFile = new File(filePath);
			if (!dirFile.exists()) {// 文件路径不存在时，自动创建目录
				dirFile.mkdir();
			}
			fileOut = new FileOutputStream(filePath + "提现汇总日报" + date + ".xls");
			wb.write(fileOut);
			fileOut.close();
			log.info("生成提现汇总日报成功" + date);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("生成提现汇总日报失败" + e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("生成提现汇总日报失败" + e);
		}
	}

	/**
	 * 获取数据入库
	 */
	@Override
	public void updateData()
	{
		try{
		JaxWsProxyFactoryBean wsFactory = new JaxWsProxyFactoryBean();
		wsFactory.setAddress(withDrawUrl);
		wsFactory.setServiceClass(CompareWebService.class);
		CompareWebService compareWebService =(CompareWebService)wsFactory.create();
		//获取昨天时间参数
		String getPreDate= DateUtils.getPreDate();
		ResultData<FileWithdrawInfoDTO> resultData= compareWebService.doQueryCompareInfo(getPreDate,"3");
        if(resultData.isSuccess()){
			Gson gson=new Gson();
			String result=gson.toJson(resultData.getRoot());
			List<WithDrawDifference> list= gson.fromJson(result, new TypeToken<List<WithDrawDifference>>() {}.getType());
			//筛选掉流水号为空的数据
			for (WithDrawDifference item:list )
			{
				if (item.getTTradeNo().indexOf(".")<0)
				{
					WithDrawDifference withDraw=dao.findByTradeNo(item.getTTradeNo());
					item.setTRecordDate(DateUtils.getDate("yyyy-mm-dd HH:MM:ss"));
					if(withDraw==null){
                     dao.save(item);
					}else{
						if (item.getTStatus().equals("3")){//如果库里面有状态为3的未处理数据，数量进行添加，时间修改
                            item.setTimes(withDraw.getTimes()+1);
						}
					 dao.updateByTradeNo(item);
					}
				}
			}
			log.info("提现数据更新成功");
        }else{
			log.error("提现获取数据更新失败" + resultData.getSyserr());
		}}catch (Exception e){
			e.printStackTrace();
			log.error("提现获取数据更新失败" + e);
		}

	}
	
}

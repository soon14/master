package com.jy.common.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.jy.common.massert.MAssert;
import com.jy.common.msg.consts.MsgConst;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
@Component("exportExcel")
public class ExportExcelUtil {

//	protected HttpServletResponse response;
//	@ModelAttribute
//	public void setReqAndRes( HttpServletResponse response){
//		this.response = response;
//	}
	/**
	 * 描述：根据文件路径获取项目中的文件
	 * @param fileDir 文件路径
	 * @return
	 * @throws Exception
	 */
	public  File getExcelDemoFile(String fileDir) throws Exception{
		String classDir = null;
		String fileBaseDir = null;
		File file = null;
		classDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		fileBaseDir = classDir.substring(0, classDir.lastIndexOf("classes"));
		
		file = new File(fileDir);
		if(!file.exists()){
			throw new Exception("模板文件不存在！");
		}
		return file;
	}
	
	public  Workbook writeNewExcel(File file,String sheetName,List<String []> lis) throws Exception{
		Workbook wb = null;
		Row row = null; 
		Cell cell = null;
		
		FileInputStream fis = new FileInputStream(file);
		wb = new ImportExcelUtil().getWorkbook(fis, file.getName());	//获取工作薄
		Sheet sheet = wb.getSheet(sheetName);
		
		//循环插入数据
		int lastRow = sheet.getLastRowNum()+1;    //插入数据的数据ROW
		CellStyle cs = setSimpleCellStyle(wb);    //Excel单元格样式
		for (int i = 0; i < lis.size(); i++) {
			row = sheet.createRow(lastRow+i); //创建新的ROW，用于数据插入
			//按项目实际需求，在该处将对象数据插入到Excel中
			String [] vo  = lis.get(i);
			if(null==vo){
				break;
			}
			//Cell赋值开始
			for(int j=0;j<vo.length;j++){
				cell = row.createCell(j);
				cell.setCellValue(vo[j]);
				cell.setCellStyle(cs);
			}
			
		}
		return wb;
	}
	
	/**
	 * 描述：设置简单的Cell样式
	 * @return
	 */
	public  CellStyle setSimpleCellStyle(Workbook wb){
		CellStyle cs = wb.createCellStyle();
		
		cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框
		cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框
		cs.setBorderTop(CellStyle.BORDER_THIN);//上边框
		cs.setBorderRight(CellStyle.BORDER_THIN);//右边框

		cs.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		
		return cs;
	}

	/**
	 *
	 * @param response
	 * @param arrList 写入excel的数据集合
	 * @param templatePath 模板存放路径
	 * @param fileName 导出文件名称
	 * @param sheetName 模板sheet名称
	 * @return
	 * @throws Exception
	 */
	public String downloadExcel(HttpServletResponse response,List<String []> arrList,String templatePath,String fileName,String sheetName) throws Exception{

		if (arrList.size()>0 || arrList !=null){
			MAssert.massertNotNull(arrList, MsgConst.User.ERR_PHONE_ERR);
		}

		OutputStream os = null;
		Workbook wb = null;    //工作薄
		//TODO 异常处理
			//导出Excel文件数据
			File file =getExcelDemoFile(templatePath);
			wb = writeNewExcel(file, sheetName,arrList);
			 fileName=fileName+".xlsx";
		    response.setContentType("application/vnd.ms-excel");
		    response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
		    os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
			wb.close();
		return null;
	}
}

package com.jy.test;

import com.jy.common.utils.excel.ExcelReport;
import com.jy.entity.system.channels.Merchant;
import com.jy.repository.system.channels.MerchantDao;
import com.jy.service.impl.help.ExcelUtil;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shixi on 2017/4/24.
 */
public class excelUtilTest extends BaseJunit4Test
{
    @Resource
    private ExcelUtil excelUtil;
    @Autowired
    private MerchantDao service;

    @Test
        public void Excel() {
            String fileName="Test2017s-saasSAS5";

            List<Merchant> merchants=service.findMerchantListPre();
            String [] attrs = new String[]
                    {"judge","findCount","person","enterprise","findDepId"};
            JSONArray datas= JSONArray.fromObject(merchants);
            //复杂表头
            String [] headers=new String[]
                    {"一级渠道,sasa/as","统计次级渠道数,asa/asa","统计个人数","统计企业数","部门ID","发展的客户T+1天的数量","商户地址"};

            //简单表头
            String [] headers2=new String[]
                    {"一级渠道","统计次级渠道数","统计个人数","统计企业数","部门ID","发展的客户T+1天的数量","商户地址"};
            List<ExcelReport> a=new ArrayList<>();

            ExcelReport excelReport=new ExcelReport();
            excelReport.setHeaderType(1);//设置类型为1
            excelReport.setHeader(headers);
            excelReport.setAttrs(attrs);
            excelReport.setData(datas);
            excelReport.setTitle("Test");
            excelReport.setFileName(fileName);
            excelReport.setSuffix(".xls");
            a.add(excelReport);

            ExcelReport excelReport2=new ExcelReport();
            excelReport2.setHeader(headers2);
            excelReport2.setAttrs(attrs);
            excelReport2.setData(datas);
            excelReport2.setTitle("qweqwe");
            excelReport2.setFileName(fileName);
            a.add(excelReport2);

            ExcelReport excelReports=new ExcelReport();
            excelReports.setSheet(true);
            excelReports.setFileName(fileName);
            excelReports.setList(a);
            try {
                excelUtil.CreateManySheet(excelReports);
     	/*JSONArray json=excelUtil.readExcel(fileName,".xls",attrs);
         System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
         List<Merchant> list = (List<Merchant>)JSONArray.toCollection(json, Merchant.class);
           for (Merchant merchant : list) {
	        System.out.println(merchant.getJudge());
	        System.out.println(merchant.getFindCount());
	        System.out.println(merchant.getPerson());
	        System.out.println(merchant.getEnterprise());
	        System.out.println(merchant.getmAddress());
            }*/
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
}

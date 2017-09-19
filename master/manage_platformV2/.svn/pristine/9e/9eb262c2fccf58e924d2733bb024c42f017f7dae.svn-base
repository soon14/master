package com.jy.controller.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.excel.ExportExcelUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.OutLineDataInfo;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.from.system.request.OutLineDataForm;
import com.jy.from.system.request.TerminalForm;
import com.jy.mybatis.Page;
import com.jy.process.system.channels.OutLineDataProcess;
import com.jy.process.system.channels.TerminalProcess;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matthew on 2017/1/19 0005.
 * 商户销量展示信息
 */
@Controller
@RequestMapping("/channels/terminal/")
public class TerminalController extends BaseController implements
        HandlerExceptionResolver {

    @Autowired
   private TerminalProcess terminalProcess;
    @Autowired
    private ExportExcelUtil exportExcel ;
    Logger logger = Logger.getLogger(TerminalController.class);
    @RequestMapping("downloadExcel")
    public void downloadExcel(HttpServletResponse response){
        try {
            //模拟数据库取值
            List<String []> loarray = new ArrayList<String []>();
            for(int i=0;i<15;i++){
                String [] arr = new String[5];
                arr[0]="yi"+i;
                arr[1]="er"+i;
                arr[2]="san"+i;
                arr[3]="si"+i;
                arr[4]="wu"+i;
                loarray.add(arr);
            }
            String fileName = "test";
            String sheetName = "sheet1";
            String templetePath = "E:/excel/test.xlsx";
            exportExcel.downloadExcel(response,loarray,templetePath,fileName,sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("findTerminalInit")
    public String findOutLineDataInit(Model model){
        if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/terminal";
        }
        return Const.NO_AUTHORIZED_URL;
    }
    @RequestMapping("addTerminalInit")
    public String addTerminalInit(Model model){
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            return "/system/channels/addTerminal";
    }
    @RequestMapping("updateTerminalInit")
    public String updateTerminalInit(Model model,HttpServletRequest request) {
        try {
            Map map = new HashMap();
            String id= request.getParameter("id");
            map.put("id",id);
            TerminalForm terminalForm = terminalProcess.findTerminal(map);
            model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
            model.addAttribute("id",id);
            model.addAttribute("machineNum",terminalForm.getMachineNum());
            model.addAttribute("merchantName",terminalForm.getMerchantName());
            model.addAttribute("merchantId",terminalForm.getMerchantId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/system/channels/updateTerminal";
    }

    @RequestMapping(value="addTerminal", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes add(TerminalForm terminalForm){
        AjaxRes ar = getAjaxRes();
        try {
            if ((doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
                String id[] = terminalForm.getMerchantName().split(";");
                terminalForm.setMerchantId(id[0]);
                terminalForm.setMerchantName(id[1]);
                terminalProcess.addTerminal(terminalForm);
                ar.setSucceedMsg("success");
            }
        } catch (Exception e) {
            e.printStackTrace();ar.setSucceedMsg("fail");
        }
        return ar;
    }
    @RequestMapping(value="delTerminal", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes delTerminal(TerminalForm terminalForm, HttpServletRequest request){
        AjaxRes ar = getAjaxRes();
        String terminalId= request.getParameter("id");
        terminalForm.setId(terminalId);
        try {
//            if ((doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
                terminalProcess.delTerminal(terminalForm);
                ar.setSucceedMsg("success");
//            }
        } catch (Exception e) {
            e.printStackTrace();ar.setSucceedMsg("fail");
        }
        return ar;
    }
    @RequestMapping(value="updateTerminal", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes updateTerminal(TerminalForm terminalForm, HttpServletRequest request){
        AjaxRes ar = getAjaxRes();
        try {
                String id[] = terminalForm.getMerchantName().split(";");
                terminalForm.setMerchantId(id[0]);
                terminalForm.setMerchantName(id[1]);

                terminalProcess.updateTerminal(terminalForm);
                ar.setSucceedMsg("success");
        } catch (Exception e) {
            e.printStackTrace();ar.setSucceedMsg("fail");
        }
        return ar;
    }
    @RequestMapping(value="findTerminal", method= RequestMethod.POST)
    @ResponseBody
    public AjaxRes findByPage(Page<TerminalForm> page, TerminalForm o) {
        AjaxRes ar = getAjaxRes();
        Page<TerminalForm> reports=new Page<TerminalForm>();
        Map map = new HashMap();
        map.put("pageNum",(page.getPageNum()-1)*page.getPageSize());
        map.put("pageSize",page.getPageSize());
        if(!o.getMachineNum().equals("")){
            map.put("machineNum",o.getMachineNum());
        }
        if(!o.getMerchantName().equals("")){
            map.put("merchantName",o.getMerchantName());
        }
        if(!o.getPhone().equals("")){
            map.put("phone",o.getPhone());
        }
        if(!o.getBeginTime().equals("") && !o.getEndTime().equals("")){
            map.put("beginTime",o.getBeginTime()+" 00:00:00");
            map.put("endTime",o.getEndTime()+" 23:59:59");
        }
        List<TerminalForm> listTotal = new ArrayList<TerminalForm>();
        List<TerminalForm> list = new ArrayList<TerminalForm>();
        try {
            listTotal = terminalProcess.findTerminalForm(map,1);
            list = terminalProcess.findTerminalForm(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int total=listTotal.size();
        int totalPage = total/page.getPageSize();
        if(total % page.getPageSize()>0){
            totalPage++;
        }
        reports.setTotalPage(totalPage);
        reports.setPageNum(page.getPageNum());
        reports.setPageSize(page.getPageSize());
        reports.setTotalRecord(total);
        reports.setResults(list);
        Map<String, Object> p=new HashMap<String, Object>();
        p.put("permitBtn", getPermitBtn("3"));
        p.put("list", reports);
        ar.setSucceed(p);
        return ar;
    }
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return null;
    }

}

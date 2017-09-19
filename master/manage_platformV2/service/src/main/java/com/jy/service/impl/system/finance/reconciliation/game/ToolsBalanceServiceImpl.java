package com.jy.service.impl.system.finance.reconciliation.game;

import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.excel.ExcelReport;
import com.jy.common.utils.game.DiamondOrGoldToMoneyUtils;
import com.jy.entity.system.finance.reconciliation.game.ToolsBalance;
import com.jy.repository.system.finance.reconciliation.game.ToolsBalanceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.impl.help.ExcelUtil;
import com.jy.service.system.finance.reconciliation.game.ToolsBalanceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service("ToolsBalanceService")
public class ToolsBalanceServiceImpl extends BaseServiceImp<ToolsBalance> implements ToolsBalanceService {

	@Autowired
	private ToolsBalanceDao toolsBalanceDao;
	@Value("${download.game.toolsReport.filename}")
	private String toolsReportFileName;
	@Autowired
	private ExcelUtil excelutil;
	/**
	 * 道具数据入库
	 * @param auto
	 * @param date
	 */
	@Override
	public  void execToolsBalanceTask(String auto, String date){
		List<ToolsBalance> list= _installToolsListByDate(date);
		if(list!=null&&list.size()!=0&&!list.isEmpty()){
			_checkAndSave(list,auto,date);
		}
	}



	private List<ToolsBalance>  _installToolsListByDate(String date) {
		Date dDate= DateUtils.parseDate(date);
		String dateMax=DateUtils.getPreDateByDay(dDate,"1");
		List<ToolsBalance> list=toolsBalanceDao.findToolsBetweenDate(date,dateMax);
		ToolsBalance obj=_installAllObject(date,dateMax);
		if(list!=null&&list.size()!=0&&!list.isEmpty()){
			_installEachToolsValue(list,date,dateMax,obj);
		}
		return list;

	}

	/**
	 * 期初总价值，消耗总价值，新增总价值，期末总价值的obj
	 * @param date
	 * @param dateMax
	 * @return
	 */
	private ToolsBalance _installAllObject(String date, String dateMax) {
		ToolsBalance obj=new ToolsBalance();
		ToolsBalance allQc=_findQllQcTools(date,dateMax);
		Integer allQcTools=_checkAndGetValue(allQc.getQcTools());
		Double allQcMoney=_CalToolsMoney(allQc);
		obj.setAllQcTools(allQcTools);
		obj.setAllQcMoney(allQcMoney);
		ToolsBalance addTool=_findAllAddOrConsumeBetweenDate(date,dateMax,2);
		Integer allAddTools=_checkAndGetValue(addTool.getAllAddTools());
		Double allAddMoney=_CalToolsMoney(addTool);
		obj.setAllAddTools(allAddTools);
		obj.setAllAddMoney(allAddMoney);
		ToolsBalance consumeTool=_findAllAddOrConsumeBetweenDate(date,dateMax,1);
		Integer allConsumeTools=_checkAndGetValue(consumeTool.getAllConsumeTools());
		Double allConsumeMoney=_CalToolsMoney(consumeTool);
		obj.setAllConsumeTools(allConsumeTools);
		obj.setAllConsumeMoney(allConsumeMoney);
		Integer allQmTools=_CalAllQmTools(allQcTools,allAddTools,allConsumeTools);
		Double allQmMoney=_CalAllQmMoney(allQcMoney,allAddMoney,allConsumeMoney);
		obj.setAllQmTools(allQmTools);
		obj.setAllQmMoney(allQmMoney);
		return obj;
	}

	private ToolsBalance _findAllAddOrConsumeBetweenDate(String date, String dateMax, int i) {
		ToolsBalance tool=toolsBalanceDao.findAllAddOrConsumeBetweenDate(date,dateMax,i);
		if(tool==null){
			return new ToolsBalance();
		}
		return tool;
	}

	/**
	 * 计算得出道具期末总价值
	 * @param allQcMoney
	 * @param allAddMoney
	 * @param allConsumeMoney
	 * @return
	 */
	private Double _CalAllQmMoney(Double allQcMoney, Double allAddMoney, Double allConsumeMoney) {
		Double a=Double.valueOf(CalculationUtils.add(allQcMoney,allAddMoney));
		return Double.valueOf(CalculationUtils.sub(a,allConsumeMoney));
	}

	/**
	 * 计算得出期末道具总数
	 * @param allQcTools
	 * @param allAddTools
	 * @param allConsumeTools
	 * @return
	 */
	private Integer _CalAllQmTools(Integer allQcTools, Integer allAddTools, Integer allConsumeTools) {
		return allQcTools+allAddTools-allConsumeTools;
	}



	private void  _installEachToolsValue(List<ToolsBalance> list,String date,String dateMax,ToolsBalance obj) {
		for(ToolsBalance balance:list){
			balance.setAllQcTools(obj.getAllQcTools());
			balance.setAllQcMoney(obj.getAllQcMoney());
			balance.setAllAddTools(obj.getAllAddTools());
			balance.setAllAddMoney(obj.getAllAddMoney());
			balance.setAllConsumeTools(obj.getAllConsumeTools());
			balance.setAllConsumeMoney(obj.getAllConsumeMoney());
			balance.setAllQmTools(obj.getAllQmTools());
			balance.setAllQmMoney(obj.getAllQmMoney());
			_installQcValue(balance,date);
			_installAddValue(balance,date,dateMax);
			_installConsumeValue(balance,date,dateMax);
			_installQmValue(balance);
		}
	}


	/**
	 * 计算期初道具价值
	 * @param tool
	 * @return
	 */
	private Double _CalToolsMoney(ToolsBalance tool) {
		Integer diamond=tool.getDiamondValueBalance();
		Integer gold=tool.getGoldValueBalance();
		Double diamondMoney= DiamondOrGoldToMoneyUtils.turnDiamondToMoney(diamond);
		Double goldMoney=DiamondOrGoldToMoneyUtils.turnGoldToMoney(gold);
		return Double.valueOf(CalculationUtils.add(diamondMoney,goldMoney));


	}

	private ToolsBalance _findQllQcTools(String date, String dateMax) {
		ToolsBalance tool=toolsBalanceDao.findQllQcTools(date,dateMax);
		if(tool==null){
			return new ToolsBalance();
		}
		return tool;
	}


	private void _installQmValue(ToolsBalance balance) {
//		Date dDate= DateUtils.parseDate(balance.getDate());
//		String date=DateUtils.getPreDateByDay(dDate,"1");
//		String dateMax=DateUtils.getPreDateByDay(dDate,"2");
//		List<ToolsBalance> list=toolsBalanceDao.findToolsBetweenDate(date,dateMax);
		Integer  qmTools=_findQmTools(balance);
		balance.setQmTools(qmTools);
		Double qmMoney=_findQmMoney(balance);
		balance.setQmMoney(qmMoney);
		Double  qmAverageTools=_findQmAverageTools(qmMoney,qmTools);
		balance.setQmAverageTools(qmAverageTools);
	}

	private void _installConsumeValue(ToolsBalance balance, String date, String dateMax) {
		Integer id=balance.getToolsId();
		ToolsBalance consumeTools=_findAddOrConsumeToolsBetweenDate(date,dateMax,1,id);
		Integer count=_checkAndGetValue(consumeTools.getConsumeTools());
		balance.setConsumeTools(count);
		Double  consumeMoney=_CalToolsMoney(consumeTools);
		balance.setConsumeMoney(consumeMoney);
	}

	private Integer _checkAndGetValue(Integer consumeTools) {
		return consumeTools==null?0:consumeTools;
	}

	private void _installAddValue(ToolsBalance balance, String date, String dateMax) {
		Integer id=balance.getToolsId();
		ToolsBalance addTools=_findAddOrConsumeToolsBetweenDate(date,dateMax,2,id);
		balance.setAddTools(_checkAndGetValue(addTools.getAddTools()));
		Double addMoney=_CalToolsMoney(addTools);
		balance.setAddMoney(addMoney);
		Integer  consumeDiamond=_checkAndGetValue(addTools.getDiamondValueBalance());
		balance.setConsumeDiamond(consumeDiamond);
		Double  averageTools=_findAverageTools(balance);
		balance.setAverageTools(averageTools);
	}

	private void _installQcValue(ToolsBalance balance, String date) {
		balance.setDate(date);
		Double qcMoney=_CalToolsMoney(balance);
		balance.setQcMoney(qcMoney);
	}

//	/**
//	 * 获取某日道具id列表
//	 * @param date
//	 * @param dateMax
//     * @return
//     */
//	private List<String> _findToolsIdListBetweenDate(String date, String dateMax) {
//		return toolsBalanceDao.findToolsIdListBetweenDate(date,dateMax);
//	}

	/**
	 * 期末结余货币价值=期初结余货币价值+本期有价道具购买价值-本期有价道具消费价值
	 * @param balance
	 * @return
	 */
	private Double _findQmMoney(ToolsBalance balance) {
		Double qcMoney=balance.getQcMoney();
		Double addMoney=balance.getAddMoney();
		Double consumeMoney=balance.getConsumeMoney();
		Double money1=Double.valueOf(CalculationUtils.add(qcMoney,addMoney));
		return Double.valueOf(CalculationUtils.sub(money1,consumeMoney));
	}


	/**
	 * 期初结余有价道具数+本期有价道具购买数-本期有价道具消耗数
	 * @param balance
	 * @return
	 */
	private Integer _findQmTools(ToolsBalance balance) {
		Integer qcTools=balance.getQcTools();
		Integer addTools=balance.getAddTools();
		Integer consumeTools=balance.getConsumeTools();
		return qcTools+addTools-consumeTools;
	}

	/**
	 * 期末道具货币单价（加权平均）=期末结余货币价值/期末充值道具数量
	 * @param qmMoney
	 * @param qmTools
	 * @return
	 */
	private Double _findQmAverageTools(Double qmMoney, Integer qmTools) {
		if(qmMoney.compareTo(0.0)==0){
			return 0.0;
		}else {
			return Double.valueOf(CalculationUtils.div(qmMoney, qmTools, 2));
		}
	}


	/**
	 * 有价道具货币单价（加权平均）=(本期有价道具购买价值+期初结余货币价值)/(本期有价道具购买数+期初结余有价道具数)
	 * @param balance
	 * @return
	 */
	private Double _findAverageTools(ToolsBalance balance) {
		Double money1=Double.valueOf(CalculationUtils.add(balance.getAddMoney(),balance.getQcMoney()));
		Integer addTools=_checkAndGetValue(balance.getAddTools());
		Integer qcTools=_checkAndGetValue(balance.getQcTools());
		Double money2=Double.valueOf(CalculationUtils.add(addTools,qcTools));
		if(money1.compareTo(0.0)==0){
			return 0.0;
		}else {
			Double result = Double.valueOf(CalculationUtils.div(money1, money2, 6));
			return result;
		}
	}


	/**
	 * 消费道具（1），充值道具（2）
	 * @param date
	 * @param dateMax
	 * @param id
	 * @return
	 */
	private ToolsBalance  _findAddOrConsumeToolsBetweenDate(String date, String dateMax,int type, Integer id) {
		ToolsBalance balance=null;
		if(type==1){
			balance=toolsBalanceDao.findConsumeToolsBetweenDate( date,  dateMax, id);
		}else{
			balance=toolsBalanceDao.findAddToolsBetweenDate( date,  dateMax, id);
		}
		if(balance==null){
			balance=new ToolsBalance();
		}
		return balance;

	}



	private void _checkAndSave( List<ToolsBalance> list, String auto, String date) {
		List<ToolsBalance>  isExitList=toolsBalanceDao.findIsExitByDate(date);
		if(isExitList.size()==0||isExitList==null||isExitList.isEmpty()){
			toolsBalanceDao.insertList(list);
		}else {
//			if(auto.equals("manual")){
				toolsBalanceDao.deleteByDate(date);
				toolsBalanceDao.insertList(list);
//			}
		}
	}

//------------------------------------生成道具报表---------------------------------------------
	/**
	 * 生成道具报表
	 * @param auto
	 * @param date
	 */
	@Override
	public  void generateToolsBalanceReport(String auto, String date){

		ExcelReport ep=_installExcelObj(date);
		try {
			excelutil.GenerateExcel(ep);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ExcelReport _installExcelObj(String date) {
		String month=date.substring(0,7);
		date=DateUtils.getDateStartString(month+"-01");
		String dateMax=DateUtils.getDateEndString(month+"-31");
		ExcelReport ep = new ExcelReport();
		StringBuffer sbf = new StringBuffer();
		String fileName = toolsReportFileName;
		ep.setTitle(fileName);
		ep.setFileName(sbf.append(fileName).append(month).toString());
		List<String> headList =toolsBalanceDao.findToolsIdListBetweenDate(date,dateMax);
		String headers[] =_installHeadArr(headList);
		List<ToolsBalance> dateList=toolsBalanceDao.findDateInMonth(month);
		String attrs[] =_installAttr();
		JSONArray data=_installExcelData(dateList);
		ep.setProcess("prop");
		ep.setHeader(headers);
		ep.setAttrs(attrs);
		ep.setTotalCountAttrs(_installTotalAttr());
		ep.setData(data);
		ep.setCheckAttr("toolsId");
		return ep;
	}

	private JSONArray _installExcelData(List<ToolsBalance> dateList) {
		Integer rowSize=dateList.size();
		JSONArray data=new JSONArray();
		for(int i=0;i<rowSize;i++){
			JSONObject object=new JSONObject();
			List<ToolsBalance> eachRowDataList =toolsBalanceDao.findIsExitByDate(dateList.get(i).getDate());
			object.put(dateList.get(i).getDate(),eachRowDataList);
			data.add(object);
		}

		return data;
	}

	private String[] _installAttr() {
		String attrs[] = new String[] {
				"qcTools", "qcMoney", "addTools", "addMoney", "consumeDiamond","averageTools",
				"consumeTools","consumeMoney","qmTools","qmMoney","qmAverageTools"};
		return attrs;

	}
	private String[] _installTotalAttr () {

		String attrs[] = new String[] {
				"allQcTools", "allQcMoney",
				"allAddTools","allAddMoney",
				"allConsumeTools","allConsumeMoney",
				"allQmTools","allQmMoney"};
		return attrs;

	}


	private String[] _installHeadArr(List<String> list) {
		List<String> arrList=new ArrayList<>();
		arrList.add("日期");
		for(int i=0;i<list.size();i++){
			String toolId=list.get(i);
			arrList.add("期初结余有价道具"+toolId+"数量");
			arrList.add("期初结余有价道具"+toolId+"价值");
			arrList.add("本期购买有价道具"+toolId+"数量");
			arrList.add("本期购买有价道具"+toolId+"价值");
			arrList.add("本期购买有价道具"+toolId+"消耗钻石数量");
			arrList.add("有价道具"+toolId+"货币单价（加权平均）");
			arrList.add("本期消耗有价道具"+toolId+"数量");
			arrList.add("本期消耗有价道具"+toolId+"价值");
			arrList.add("期末结余有价道具"+toolId+"数量");
			arrList.add("期末结余有价道具"+toolId+"价值");
			arrList.add("期末结余有价道具"+toolId+"货币单价（加权平均）");
		}
		arrList.add("期初结余有价道具数量（合计）");
		arrList.add("期初结余有价道具货币价值（合计）");
		arrList.add("本期购买有价道具数量（合计）");
		arrList.add("本期购买有价道具货币价值（合计）");
		arrList.add("本期消耗有价道具数量（合计）");
		arrList.add("本期消耗有价道具货币价值（合计）");
		arrList.add("期末结余有价道具数量（合计）");
		arrList.add("期末结余有价道具货币价值（合计）");

		String[] headersArr = (String[])arrList.toArray(new String[arrList.size()]);
		return headersArr;
	}



}

package com.jy.service.impl.system.finance.reconciliation.game;

import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.excel.ExcelReport;
import com.jy.common.utils.game.DiamondOrGoldToMoneyUtils;
import com.jy.entity.system.finance.reconciliation.game.DiamondBalance;
import com.jy.repository.system.finance.reconciliation.game.DiamondBalanceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.impl.help.ExcelUtil;
import com.jy.service.system.finance.reconciliation.game.DiamondBalanceService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;



@Service("diamondBalanceService")
public class DiamondBalanceServiceImpl extends BaseServiceImp<DiamondBalance> implements DiamondBalanceService {

	@Autowired
	private DiamondBalanceDao diamondBalanceDao;
	@Value("${download.game.diamondReport.filename}")
	private String diamondReportFileName;
	@Autowired
	private ExcelUtil excelutil;

	/**
	 * 钻石数据入库
	 * @param
	 * @param date
	 */
	@Override
	public void execDiamondBalanceTask(String auto, String date){
		DiamondBalance balance=new DiamondBalance();
		_installDiamond(balance,date);
		_checkAndSave(balance,auto,date);


	}

	private void _checkAndSave(DiamondBalance balance, String auto, String date) {
		List<DiamondBalance> isExit=diamondBalanceDao.findIsExitByDate(date);
		if(isExit==null||isExit.size()==0||isExit.isEmpty()){
			diamondBalanceDao.insert(balance);
		}else {
//			if(auto.equals("manual")){
				diamondBalanceDao.deleteList(isExit);
				diamondBalanceDao.insert(balance);
//			}
		}
	}

	private void _installDiamond(DiamondBalance balance,String date) {
		balance.setDate(date);
		date=DateUtils.getDateStartString(date);
		String dateMax=DateUtils.getDateEndString(date);
		Integer qcDiamond=_findQcDiamond(date,dateMax);
		balance.setQcDiamond(qcDiamond);
		Double qcMoney=_turnDiamondToMoney(qcDiamond);
		balance.setQcMoney(qcMoney);
		Integer addDiamond= _getAddOrConsumeDiamondBetweenDate(date,dateMax,2);
		balance.setAddDiamond(addDiamond);
		Double addMoney=_turnDiamondToMoney(addDiamond);
		balance.setAddMoney(addMoney);
		Double averageDiamond=_getAverageDiamond(balance);
		balance.setAverageDiamond(averageDiamond);
		Integer consumeDiamond=_getAddOrConsumeDiamondBetweenDate(date,dateMax,1);
		balance.setConsumeDiamond(consumeDiamond);
		Double addDiamondToMoney=_getAddDiamondToMoney(averageDiamond,consumeDiamond);
		balance.setAddDiamondToMoney(addDiamondToMoney);
		//T-1日期末=T日期初
//		String date2=DateUtils.getPreDateByDay(dDate,"2");
//		Integer qmDiamond=_findQcDiamond(dateMax,date2);
		Integer qmDiamond=_CalQmDiamond(qcDiamond,addDiamond,consumeDiamond);
		balance.setQmDiamond(qmDiamond);
//		Double qmMoney=_turnDiamondToMoney(qmDiamond);
		Double qmMoney=_CalQmMoney(qcMoney,addMoney,addDiamondToMoney);
		balance.setQmMoney(qmMoney);
		Double qmAverageDiamond=_getQmAverageDiamond(qmMoney,qmDiamond);
		balance.setQmAverageDiamond(qmAverageDiamond);

	}

	/**
	 * 计算期末结余充值金额=期初+新增-本期充值钻石结转价值
	 * @param qcMoney
	 * @param addMoney
	 * @param addDiamondToMoney
	 * @return
	 */
	private Double _CalQmMoney(Double qcMoney, Double addMoney, Double addDiamondToMoney) {
		Double a=Double.valueOf(CalculationUtils.add(qcMoney,addMoney));
		return Double.valueOf(CalculationUtils.sub(a,addDiamondToMoney));
	}

	/**
	 * 计算得出的期末=期初+新增-消费
	 * @param qcDiamond
	 * @param addDiamond
	 * @param consumeDiamond
	 * @return
	 */
	private Integer _CalQmDiamond(Integer qcDiamond, Integer addDiamond, Integer consumeDiamond) {
		return qcDiamond+addDiamond-consumeDiamond;
	}


	/**
	 * 期末钻石货币单价（加权平均）=期末结余充值金额/本期结余充值钻石数量
	 * @param qmMoney
	 * @param qmDiamond
	 * @return
	 */
	private Double _getQmAverageDiamond(Double qmMoney, Integer qmDiamond) {
		if(qmMoney.compareTo(0.0)==0){
			return 0.0;
		}else {
			Double money = Double.valueOf(CalculationUtils.div(qmMoney, qmDiamond, 2));
			return money;
		}
	}

	/**
	 * 本期充值钻石结转价值=钻石货币单价（加权平均）*本期充值钻石消耗数
	 * @param averageDiamond
	 * @param consumeDiamond
	 * @return
	 */
	private Double _getAddDiamondToMoney(Double averageDiamond, Integer consumeDiamond) {
		Double money=Double.valueOf(CalculationUtils.mul(averageDiamond,consumeDiamond));
		return money;
	}

	/**
	 * 钻石货币单价（加权平均）=(期初结余充值金额+本期充值金额)/(期初结余充值钻石数+本期充值钻石数),保留6位
	 * @param balance
	 * @return
	 */
	private Double _getAverageDiamond(DiamondBalance balance) {
		Double money1=Double.valueOf(CalculationUtils.add(balance.getQcMoney(),balance.getAddMoney()));
		Double money2=Double.valueOf(CalculationUtils.add(balance.getQcDiamond(),balance.getAddDiamond()));
		if(money1.compareTo(0.0)==0){
			return 0.0;
		}else{
			Double result=Double.valueOf(CalculationUtils.div(money1,money2,6));
			return result;
		}
	}

	/**
	 *充值钻石（2），消费钻石（1）
	 * @param date
	 * @param dateMax
	 * @param i
	 * @return
	 */
	private Integer _getAddOrConsumeDiamondBetweenDate(String date, String dateMax, int i) {
		Double count=0.0;
		if(i==1){
			count=CalculationUtils.fomatMoney(diamondBalanceDao.findConsumeDiamondBetweenDate(date,dateMax));
		}else {
			count=CalculationUtils.fomatMoney(diamondBalanceDao.findAddDiamondBetweenDate(date,dateMax));

		}
		return DiamondOrGoldToMoneyUtils.parseDoubleToInt(count);
	}



	/**
	 *钻石-》现金
	 * @param qcDiamond
	 * @return
	 */
	private Double _turnDiamondToMoney(Integer qcDiamond) {
		return DiamondOrGoldToMoneyUtils.turnDiamondToMoney(qcDiamond);
	}

	/**
	 * 某段时间期初钻石额
	 * @param date
	 * @param dateMax
	 * @return
	 */
	private Integer _findQcDiamond(String date, String dateMax) {
		Integer count=diamondBalanceDao.findQcDiamondBetweenDate(date,dateMax);
		return count==null?0:count;
	}

//---------------------------------------生成钻石报表------------------------------------------
	/**
	 * 生成钻石报表
	 * @param auto
	 * @param month
	 */
	@Override
	public  void generateDiamondBalanceReport(String auto, String month){
		ExcelReport ep=_installExcelObj(month);
		try {
			excelutil.GenerateExcel(ep);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private ExcelReport _installExcelObj(String month) {
		ExcelReport ep = new ExcelReport();
		StringBuffer sbf = new StringBuffer();
		String fileName = diamondReportFileName;
		ep.setTitle(fileName);
		ep.setFileName(sbf.append(fileName).append(month).toString());
		String headers[] = new String[] {"日期","期初结余充值钻石数","期初结余充值金额","本期充值钻石数","本期充值金额","钻石货币单价（加权平均）","本期充值钻石消耗数","本期充值钻石结转价值","本期结余充值钻石数量","期末结余充值金额","期末钻石货币单价（加权平均）"};
		String attrs[] = new String[] {
				"date", "qcDiamond", "qcMoney", "addDiamond", "addMoney", "averageDiamond", "consumeDiamond", "addDiamondToMoney","qmDiamond","qmMoney","qmAverageDiamond"};
		List<DiamondBalance> list = diamondBalanceDao.findMonth(month);
		JSONArray data= JSONArray.fromObject(list);
		ep.setHeader(headers);
		ep.setAttrs(attrs);
		ep.setData(data);
		return ep;
	}


}

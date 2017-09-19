package com.jy.service.impl.system.finance.reconciliation.game;

import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.excel.ExcelReport;
import com.jy.common.utils.game.DiamondOrGoldToMoneyUtils;
import com.jy.entity.system.finance.reconciliation.game.GoldBalance;
import com.jy.repository.system.finance.reconciliation.game.GoldBalanceDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.impl.help.ExcelUtil;
import com.jy.service.system.finance.reconciliation.game.GoldBalanceService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service("GoldBalanceService")
public class GoldBalanceServiceImpl extends BaseServiceImp<GoldBalance> implements GoldBalanceService {

	@Autowired
	private GoldBalanceDao goldBalanceDao;
	@Value("${download.game.goldReport.filename}")
	private String goldReportFileName;
	@Autowired
	private ExcelUtil excelutil;
	/**
	 * 金币数据入库
	 * @param auto
	 * @param date
	 */
	@Override
	public  void execGoldBalanceTask(String auto, String date){
		GoldBalance balance=new GoldBalance();
		_installGoldValue(balance,date);
		_checkAndSave(balance,auto,date);
	}



	private void _installGoldValue(GoldBalance balance, String date) {
		balance.setDate(date);
		date=DateUtils.getDateStartString(date);
		String dateMax=DateUtils.getDateEndString(date);
		Integer qcGold=_findQcGoldBetweenDate(date,dateMax);
		balance.setQcGold(qcGold);
		Double qcMoney=DiamondOrGoldToMoneyUtils.turnGoldToMoney(qcGold);
		balance.setQcMoney(qcMoney);
		Integer addGold=_getAddOrConsumeGoldBetweenDate(date,dateMax,2);
		balance.setAddGold(addGold);
		Double addMoney=DiamondOrGoldToMoneyUtils.turnGoldToMoney(addGold);
		balance.setAddMoney(addMoney);
		Integer  consumeDiamond=_getConsumeDiamondForBuyGold(date,dateMax);
		balance.setConsumeDiamond(consumeDiamond);
		Double  averageGold=_getAverageGold(balance);
		balance.setAverageGold(averageGold);
		Integer  consumeGold=_getAddOrConsumeGoldBetweenDate(date,dateMax,1);
		balance.setConsumeGold(consumeGold);
		Double  addGoldToMoney=_getAddGoldToMoney(averageGold,consumeGold);
		balance.setAddGoldToMoney(addGoldToMoney);
		Integer  qmGold=_getQmGold(qcGold,addGold,consumeGold);
		//T-1日期末=T日期初
//		String date2=DateUtils.getPreDateByDay(dDate,"2");
//		Integer qmGold=_findQcGoldBetweenDate(dateMax,date2);
		balance.setQmGold(qmGold);
		Double qmMoney=_getQmMoney(qcMoney,addMoney,addGoldToMoney);
//		Double qmMoney=_turnGoldToMoney(qmGold);
		balance.setQmMoney(qmMoney);
		Double  qmAverageGold=_getQmAverageGold(qmMoney,qmGold);
		balance.setQmAverageGold(qmAverageGold);

	}

	/**
	 * 期末结余货币价值=期初结余货币价值+本期有价金币购买价值-本期有价金币结转价值
	 * @param qcMoney
	 * @param addMoney
	 * @param addGoldToMoney
	 * @return
	 */
	private Double _getQmMoney(Double qcMoney, Double addMoney, Double addGoldToMoney) {
		Double money1=Double.valueOf(CalculationUtils.add(qcMoney,addMoney));
		return Double.valueOf(CalculationUtils.sub(money1,addGoldToMoney));
	}


	/**
	 * 期初结余有价金币数+本期有价金币购买数-本期有价金币消耗数
	 * @param qcGold
	 * @param addGold
	 * @param consumeGold
	 * @return
	 */
	private Integer _getQmGold(Integer qcGold, Integer addGold, Integer consumeGold) {
		return qcGold+addGold-consumeGold;
	}

	/**
	 * 期末金币货币单价（加权平均）=期末结余货币价值/期末充值金币数量
	 * @param qmMoney
	 * @param qmGold
	 * @return
	 */
	private Double _getQmAverageGold(Double qmMoney, Integer qmGold) {
		if(qmMoney.compareTo(0.0)==0||qmGold==0){
			return 0.0;
		}else {
			return Double.valueOf(CalculationUtils.div(qmMoney, qmGold, 2));
		}
	}

	/**
	 * 本期有价金币结转价值=有价金币货币单价（加权平均）*本期有价金币消耗数
	 * @param averageGold
	 * @param consumeGold
	 * @return
	 */
	private Double _getAddGoldToMoney(Double averageGold, Integer consumeGold) {
		return Double.valueOf(CalculationUtils.mul(averageGold,consumeGold));
	}

	/**
	 * 有价金币货币单价（加权平均）=(本期有价金币购买价值+期初结余货币价值)/(本期有价金币购买数+期初结余有价金币数)
	 * @param balance
	 * @return
	 */
	private Double _getAverageGold(GoldBalance balance) {
		Double money1=Double.valueOf(CalculationUtils.add(balance.getAddMoney(),balance.getQcMoney()));
		Double money2=Double.valueOf(CalculationUtils.add(balance.getAddGold(),balance.getQcGold()));
		if(money1.compareTo(0.0)==0){
			return 0.0;
		}else {
			Double result = Double.valueOf(CalculationUtils.div(money1, money2, 6));
			return result;
		}
	}

	/**
	 * 购买金币消耗钻石数量
	 * @param date
	 * @param dateMax
	 * @return
	 */
	private Integer _getConsumeDiamondForBuyGold(String date, String dateMax) {
		Double money=CalculationUtils.fomatMoney(goldBalanceDao.findConsumeDiamondForBuyGold(date,dateMax));
		return DiamondOrGoldToMoneyUtils.parseDoubleToInt(money);
	}

	/**
	 * 消费金币（1），充值金币（2）
	 * @param date
	 * @param dateMax
	 * @param i
	 * @return
	 */
	private Integer _getAddOrConsumeGoldBetweenDate(String date, String dateMax, int i) {

		Double count=0.0;
		if(i==1){
			count=CalculationUtils.fomatMoney(goldBalanceDao.findConsumeGoldBetweenDate(date,dateMax));
		}else {
			count=CalculationUtils.fomatMoney(goldBalanceDao.findAddGoldBetweenDate(date,dateMax));

		}
		return DiamondOrGoldToMoneyUtils.parseDoubleToInt(count);

	}




	/**
	 * 期初余额
	 * @param date
	 * @param dateMax
	 * @return
	 */
	private Integer _findQcGoldBetweenDate(String date, String dateMax) {
		Double money=CalculationUtils.fomatMoney(goldBalanceDao.findQcGoldBetweenDate(date,dateMax));
		return DiamondOrGoldToMoneyUtils.parseDoubleToInt(money);

	}

	private void _checkAndSave(GoldBalance balance, String auto, String date) {
		List<GoldBalance> isExit=goldBalanceDao.findIsExitByDate(date);
		if(isExit==null||isExit.size()==0||isExit.isEmpty()){
			goldBalanceDao.insert(balance);
		}else {
//			if(auto.equals("manual")){
				goldBalanceDao.deleteList(isExit);
				goldBalanceDao.insert(balance);
//			}
		}
	}

//------------------------------------生成金币报表---------------------------------------------
	/**
	 * 生成金币报表
	 * @param auto
	 * @param month
	 */
	@Override
	public  void generateGoldBalanceReport(String auto, String month) {
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
		String fileName = goldReportFileName;
		ep.setTitle(fileName);
		ep.setFileName(sbf.append(fileName).append(month).toString());
		String headers[] = new String[] {"日期","期初结余有价金币数","期初结余货币价值","本期有价金币购买数","本期有价金币购买价值","购买金币消耗钻石数量","有价金币货币单价（加权平均）","本期有价金币消耗数","本期有价金币结转价值","期末充值金币数量","期末结余货币价值","期末金币货币单价（加权平均）"};
		String attrs[] = new String[] {
				"date", "qcGold", "qcMoney", "addGold", "addMoney","consumeDiamond", "averageGold", "consumeGold", "addGoldToMoney","qmGold","qmMoney","qmAverageGold"};
		List<GoldBalance> list = goldBalanceDao.findMonth(month);
		JSONArray data= JSONArray.fromObject(list);
		ep.setHeader(headers);
		ep.setAttrs(attrs);
		ep.setData(data);
		return ep;
	}




}

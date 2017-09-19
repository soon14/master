package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.common.utils.CalculationUtils;
import com.jy.entity.system.channels.TicketInfoDetailPO;
import com.jy.entity.system.channels.TicketInfoPO;
import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.entity.system.finance.SalesSum;
import com.jy.entity.system.finance.SalesSumDifferences;
import com.jy.entity.system.finance.vo.SalesSumDifferencesVO;
import com.jy.mybatis.Page;
import com.jy.repository.system.channels.TicketInfoDao;
import com.jy.repository.system.finance.reconciliation.lottery.SalesSumDifferencesDao;
import com.jy.repository.system.finance.reconciliation.lottery.SalesDifferencesDetailDao;
import com.jy.repository.system.finance.reconciliation.lottery.SalesSumDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.reconciliation.lottery.SalesSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service("SalesSumServiceImp")
public class SalesSumServiceImp extends BaseServiceImp<SalesSumDifferences> implements SalesSumService {
	@Autowired
	private SalesSumDifferencesDao salesSumDifferencesDao;
	@Autowired
	private SalesSumDao salesSumDao;
	@Autowired
	private SalesDifferencesDetailDao salesDifferencesDetailDao;
	@Autowired
	private TicketInfoDao ticketInfoDao;

	/**
	 * 查询销售差异总报表数据
	 * @param date		开始时间
	 * @param time		截止时间
	 * @param dealResultStatus		处理状态
	 * @return
	 */
	public List<SalesSumDifferencesVO> findDifference(String date, String time, String dealResultStatus){
		List list=new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(dealResultStatus!=null && !dealResultStatus.equals("")){
				switch (Integer.parseInt(dealResultStatus)){
					case 0:
						dealResultStatus="未处理";
						break;
					case 1:
						dealResultStatus="处理中";
						break;
					case 2:
						dealResultStatus="已处理";
						break;
					case 3:
						dealResultStatus="不用处理";
				}
			}
			List<SalesSumDifferencesVO> dzSumDifferences= salesSumDifferencesDao.findDifference(date,time,dealResultStatus);
			for(SalesSumDifferencesVO difference:dzSumDifferences){
				difference.setBetDate(sdf.format(sdf.parse(difference.getBetDate())));
				list.add(difference);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询投注系统销售总报表,总金额明细
	 * @param date		日期
	 * @return
	 */
	public SalesSum findMarket(String date){
		SalesSum dzSumDifferences=new SalesSum();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			dzSumDifferences=salesSumDao.findMarket(date);
			dzSumDifferences.setmDate(sdf.format(sdf.parse(dzSumDifferences.getmDate())));
		}catch (Exception e){
			logger.error(e.toString(),e);
		}
		return dzSumDifferences;
	}

	/**
	 * 查询销售差异明细报表
	 * @param date	日期
	 * @return
	 */
	public SalesDifferencesDetail findDifferencesDetail(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SalesDifferencesDetail salesDifferencesDetail=new SalesDifferencesDetail();
		try {
			salesDifferencesDetail=salesDifferencesDetailDao.findDifferenceDetail(date);
			salesDifferencesDetail.setdDate(sdf.format(sdf.parse(salesDifferencesDetail.getdDate())));
		}catch (Exception e){
			logger.error(e.toString(),e);
		}
		return salesDifferencesDetail;
	}



	public List<TicketInfoDetailPO> findSalesSum(){
		SalesSum salesSum=new SalesSum();
		Double aDouble=0.00;
		List<TicketInfoDetailPO> infoDetail=salesSumDao.findTicketInfoDetail("2017-02-19","2017-02-20");
		for(int i=0;i<infoDetail.size();i++){
			aDouble=Double.valueOf(CalculationUtils.add(aDouble,infoDetail.get(i).getMoney()));
		}
		for(TicketInfoDetailPO detailPO:infoDetail){
			switch (detailPO.getLotteryName()){
				case "11选5":
					salesSum.setmSyxw(detailPO.getMoney().toString());
					break;
				case "超级大乐透":
					salesSum.setmDlt(detailPO.getMoney().toString());
					break;
				case "竞彩足球":
					salesSum.setmJczq(detailPO.getMoney().toString());
					break;
				case "竞彩篮球":
					salesSum.setmJclq(detailPO.getMoney().toString());
					break;
				case "排列3/5":
					salesSum.setmPlsw(detailPO.getMoney().toString());
					break;
				case "七星彩":
					salesSum.setmQxc(detailPO.getMoney().toString());
					break;
				case "任选九场":
					salesSum.setmRxjq(detailPO.getMoney().toString());
					break;
				case "胜负彩":
					salesSum.setmSfc(detailPO.getMoney().toString());
			}
		}
		salesSum.setmSum(aDouble.toString());	//总额
		salesSum.setmDate("2017-02-19");

//		salesSumDao.
		return infoDetail;
	}


	@Override
	public void findSalesSumDifferences() {
		SalesSumDifferences sumDifferences=new SalesSumDifferences();
		Double aDouble=0.00;
		//统计投注系统总金额和笔数
		SalesSum salesSum=salesSumDao.findSumMoney("2017-02-19","2017-02-20");
		//统计出票系统总金额和笔数
		TicketInfoPO ticketInfoPO=ticketInfoDao.findSumTicketInfo("2017-01-03","2017-01-04");
		System.out.print("");
	}

	@Override
	public void findSalesDifferencesDetail() {

	}



	/**
	 * 获取type类型的某日差异处理结果
	 * ，1：平台总资金差异 2：第三方流水差异
	 * @param form
	 * @return
	 */
	@Override
	public List<SalesDifferencesDetail> findDetailByDateAndType(SalesDifferencesDetail form){
		Page<SalesDifferencesDetail> page=new Page<SalesDifferencesDetail>();
		return salesDifferencesDetailDao.findDetailByDateAndType(form, page);
	}

}

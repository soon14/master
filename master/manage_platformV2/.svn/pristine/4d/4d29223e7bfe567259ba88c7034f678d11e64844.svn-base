package com.jy.service.impl.system.finance.reconciliation.game;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.reconciliation.game.GameUserPaymentInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.HttpClientUtil;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.finance.reconciliation.game.GameUserPaymentInfo;
import com.jy.entity.system.finance.reconciliation.game.form.GameParamReq;
import com.jy.entity.system.finance.reconciliation.game.vo.GamePageInfo;
import com.jy.entity.system.finance.reconciliation.game.vo.GameUserPaymentInfoVO;
import com.jy.repository.system.finance.reconciliation.game.GameUserPaymentInfoDao;


/**
 * @文件名:GameUserPaymentInfoServiceImpl.java
 * @功能描述：
 * @创建日期:2017年3月30日下午2:21:34
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days
 */

@Service("gameUserPaymentInfoService")
public class GameUserPaymentInfoServiceImpl extends BaseServiceImp<GameUserPaymentInfo> implements GameUserPaymentInfoService {
	
	@Autowired
	public GameUserPaymentInfoDao gameUserPaymentInfoDao;
	
	@Autowired
	public SysDictService dictService;
	
	@Value("${synchronous.data.path}")
	public String url;
	
	@Value("${user.pay}")
	public String method;
	
	/**
	 * 同步数据开关
	 */
	@Override
	public void switchData() {
		boolean flag = false;
		SysDict sysDict1 = new SysDict();
		sysDict1.setParamKey("DateSwitch");
		String falseStr = dictService.find(sysDict1).get(0).getParamValue();
		
		if (falseStr.equals("off")) {
			this.bySwitch(flag);
		} else {
			flag = true;
			this.bySwitch(flag);
		}
		
	}
	
	@Override
	public void synchronousData(String startDate, String endDate, int pageSize, int currentPage) throws Exception {
		logger.info("进入请求用户充值明细接口！");
		// GameParamReq gp = new GameParamReq("fish.getUserPayInfo","20170327","20170327",5,1);
		try {
			int countPage = 0;
			List<GameUserPaymentInfo> userPaymentInfoList = new ArrayList<GameUserPaymentInfo>();
			String bat = this.returnBatchId();
			do {
				currentPage++;
				GameParamReq gp = new GameParamReq(method, startDate, endDate, pageSize, currentPage);
				String jsonString = JSON.toJSONString(gp);
				String response = HttpClientUtil.post(jsonString, url);
				Gson gson = new Gson();
				Type type = new TypeToken<GamePageInfo<List<GameUserPaymentInfoVO>>>() {}.getType();
				GamePageInfo<List<GameUserPaymentInfoVO>> userListResult = gson.fromJson(response, type);
				countPage = (userListResult.getTotalRecords() + pageSize - 1) / pageSize;
				// 拿到数据集
				List<GameUserPaymentInfoVO> userPaymentInfoVoList = userListResult.getList();
				
				if (userPaymentInfoVoList.size() > 0) {
					
					for (GameUserPaymentInfoVO gameUserPaymentInfoVO : userPaymentInfoVoList) {
						GameUserPaymentInfo userPaymentInfo = new GameUserPaymentInfo();
						userPaymentInfo.setBatchId(bat);
						userPaymentInfo.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH-mm-ss"));
						BeanUtils.copyProperties(gameUserPaymentInfoVO, userPaymentInfo);
						userPaymentInfoList.add(userPaymentInfo);
					}
					
				} else {
					logger.info("当页数据为空！");
				}
			} while (currentPage != countPage && countPage != 0);
			if (userPaymentInfoList.size() > 0) {
				logger.info("保存用户充值数据！");
				this.gameUserPaymentInfoDao.save(userPaymentInfoList);
			}
		} catch (Exception e) {
			logger.info("保存用户充值数据失败！");
			e.printStackTrace();
		}
	}
	
	private String returnBatchId() {
		String userBatchId = null;
		// 查询今天的批次是否存在 创建时间最大的 是不是今天 不是今天生成今天批次的 否则就是拿到这个批次号加1
		GameUserPaymentInfo info = this.gameUserPaymentInfoDao.findByBatchId();
		if (null == info) {
			// 第一次创建
			userBatchId = generateBatchId(false, null);
		} else {
			if (DateUtils.compare_date(info.getCreateTime(), DateUtils.getDate("yyyy-MM-dd")) == 0) {
				// 拿到这个批次号+1
				userBatchId = generateBatchId(true, info.getBatchId());
				
			} else if (DateUtils.compare_date(info.getCreateTime(), DateUtils.getDate("yyyy-MM-dd")) == -1) {
				// 第一次创建
				userBatchId = generateBatchId(false, null);
			}
		}
		return userBatchId;
	}
	
	// 生成批次号的方法
	public String generateBatchId(boolean flag, String batchid) {
		
		// 生成批次规则 “date” 拼接 “_0” 拼接“ 1 ” 如果一天中有第二次生成相同数据的 1++
		String date = DateUtils.format(new Date(), "yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		if (!flag) {
			// 当天批次的第1次
			int strip = 1;
			sb.append(date).append("_0").append(strip);
		} else {
			// 当天的批次号次数加1
			String[] batchs = batchid.split("_");
			sb.append(date).append("_0").append(Integer.parseInt(batchs[1]) + 1);
		}
		return sb.toString();
	}
	
	// 根据开关，拿到对应日期天数的数据
	private void bySwitch(boolean flag) {
		try {
			SysDict sysDict1 = new SysDict();
			sysDict1.setParamKey("pageSize");
			SysDict sysDict2 = new SysDict();
			sysDict2.setParamKey("currentPage");
			int pageSize = Integer.parseInt(dictService.find(sysDict1).get(0).getParamValue());
			int currentPage = Integer.parseInt(dictService.find(sysDict2).get(0).getParamValue());
			
			if (flag) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				String dates = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
				this.synchronousData(dates, dates, pageSize, currentPage);
			} else {
				sysDict1 = new SysDict();
				sysDict1.setParamKey("startTime");
				sysDict2 = new SysDict();
				sysDict2.setParamKey("endTime");
				String startTime = dictService.find(sysDict1).get(0).getParamValue();
				String endTime = dictService.find(sysDict2).get(0).getParamValue();
				this.synchronousData(startTime, endTime, pageSize, currentPage);
			}
		} catch (Exception e) {
			logger.info("获取用户交易明细数据失败！");
			e.printStackTrace();
		}
	}
}

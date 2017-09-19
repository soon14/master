package com.jy.service.impl.system.finance.reconciliation.lottery;

import com.jy.service.system.finance.reconciliation.lottery.PropertiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @文件名:PropertiesServiceImpl.java
 * @功能描述：
 * @创建日期:2017年4月5日上午8:49:13
 * @创建人：Dingj
 * @Copyright（c） 2017,all rights reserved by days
 */
@Service("propertiesServiceImpl")
public class PropertiesServiceImpl implements PropertiesService {
	
	@Value("${cashDownload.path}")
	public String filePath;
	
	@Value("${cashDownload.suffix}")
	public String suffix;
	
	@Value("${download.cashTotal.filename}")
	public String cashTotalFileName;
	
	@Value("${download.cashDetail.filename}")
	public String cashDetailFileName;
}

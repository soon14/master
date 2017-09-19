package com.jy.service.system.channels;

import com.jy.mybatis.Page;
import com.jy.entity.system.channels.Merchant;
import com.jy.service.base.BaseService;


/**
 * @文件名:DevelopCustomerService.java
 * @功能描述：
 * @创建日期:2017年3月1日下午6:54:41
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
public interface DevelopCustomerService extends BaseService<Merchant>
{

	/**
	 *
	 * @方法功能描述： 发展客户页面加载数据查询
	 * @param m
	 * @param page
	 * @return
	 * Page<Merchant>
	 * @author lijunke
	 * @创建时间： 2017年3月3日下午1:08:56
	 */
	Page<Merchant> developCustomerList(Merchant m, Page<Merchant> page);


	/**
	 *
	 * @方法功能描述： 生成后excel表格方法
	 * void
	 * @author lijunke
	 * @创建时间： 2017年3月3日下午1:09:42
	 */
	void GenerateReport();


}

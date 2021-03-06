package com.jy.repository.system.channels;

import com.jy.entity.system.channels.CPUserInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * @文件名:CPUserinfo.java 
 * @功能描述：
 * @创建日期:2017年3月8日下午1:21:32 
 * @创建人：lijunke
 * @Copyright（c） 2017,all rights reserved by days 
 */
@JYBatis
public interface CPUserInfoDao extends BaseDao<CPUserInfo> {
	
	/**
	 * 
	 * @方法功能描述： 根据渠道userid和时间查询标签用户 
	 * @return
	 * int
	 * @author lijunke
	 * @创建时间： 2017年3月8日下午1:23:19
	 */
	int queryUserInfoByIdAndDate(@Param("uId") String getmCpUserId, @Param("marketTime") String dateTwo);

/*	*//**
	 * 保存所有记录
	 * @param
	 * @param charset
	 *//*
	void save(List<CPUserInfo> cpUserInfos);*/

	int save(@Param("fileName") String filePath, @Param("currentDate") Date currentDate,@Param("charset") String charset);

	int count(@Param("searchDate") Date searchDate);
}

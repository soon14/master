package com.jy.repository.system.channels;

import com.jy.entity.system.channels.TradeOnlineDetail;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@JYBatis
public interface TradeOnlineDetailDao extends BaseDao<TradeOnlineDetail> {

	/**
	 * 保存所有记录
	 * @param list
     */
	Integer save(@Param("list")List<TradeOnlineDetail> list);

	void clean(@Param("date")String date);

	int save(@Param("fileName") String filePath, @Param("currentDate")Date currentDate, @Param("charset") String charset);
}

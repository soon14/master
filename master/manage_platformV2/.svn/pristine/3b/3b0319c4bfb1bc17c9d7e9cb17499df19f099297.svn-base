package com.jy.repository.system.channels;

import com.jy.entity.system.channels.CpVoucherInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@JYBatis
public interface CpVoucherInfoDao extends BaseDao<CpVoucherInfo> {

	/**
	 * 兑换券接口
	 * @param cpVoucherInfo
     */
	int voucherSave(@Param("list")List<CpVoucherInfo> cpVoucherInfo);

	void voucherClean(@Param("date")String date);

	int save(@Param("fileName") String filePath, @Param("currentDate")Date currentDate,@Param("charset") String charset);
}

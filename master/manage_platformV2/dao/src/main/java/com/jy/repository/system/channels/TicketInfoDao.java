package com.jy.repository.system.channels;

/**
 * Created by Matthew on 2017/1/5 0005.
 */

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.channels.ThirdPayInfo;
import com.jy.entity.system.channels.TicketInfoPO;
import com.jy.entity.system.channels.VoucherInfo;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;

/**
 * 出票/兑奖信息数据层
 */
@JYBatis
public interface TicketInfoDao extends BaseDao<TicketInfoPO> {
	
	public void ticketImport(List<TicketInfoPO> list);
	
	/**
	 * @方法功能描述： 方法作用 导入该批次的兑奖数据
	 * 
	 * @param cashlist void
	 * @author Xin @创建时间： 2017年3月16日上午9:38:12
	 */
	public void cashInfoImport(List<CashInfo> cashlist);
	
	public void thirdImport(List<ThirdPayInfo> list);
	
	public void deleteThird(ThirdPayInfo vo);
	
	public TicketInfoPO findSumTicketInfo(@Param("ticketTime") String ticketTime, @Param("ticketDate") String ticketDate);
	
	/**
	 * 某段时间内XX类型的交易流水
	 * 
	 * @param params
	 * @return
	 */
	public List<ThirdPayInfo> findThirdListByParam(Map<String, Object> params);
	
	/**
	 * 查询某条流水记录
	 * 
	 * @param payFlowNo
	 * @param thirdType
	 * @return
	 */
	public ThirdPayInfo findObjByPayFlowNo(String payFlowNo, Integer thirdType);

	
	/**
	 * @方法功能描述： 方法作用 删除该批次导入的兑奖数据
	 * 
	 * @param cashinfo void
	 * @author Dingj @创建时间： 2017年3月16日上午9:37:15
	 */
	public void deleteCash(CashInfo cashinfo);
	
	
	 /**
	 * @方法功能描述： 统计总条数和总金额
	 * 
	 * @return TicketInfoPO
	 * @author lijunke @创建时间： 2017年3月10日上午9:42:50
	 * @param date
	 */
	public TicketInfoPO queryTotal(@Param("date") String date);

	/**
	 * @方法功能描述：根据批次号查询对应的数据
	 * @param batchId void
	 * @author lijunke @创建时间： 2017年3月10日上午10:11:44
	 * @param date
	 * @return
	 */
	public TicketInfoPO queryByNo(@Param("ticketNo") String ticketNo, @Param("date") String date);

	/**
	 * @方法功能描述： 拿到所有的出票条数
	 * 
	 * @return List<TicketInfoPO>
	 * @author lijunke @创建时间： 2017年3月10日上午11:46:06
	 * @param date
	 */
	public List<TicketInfoPO> findAll(@Param("date") String date);

	/**
	 * @方法功能描述： 根据日期分组，获取每天总条数，总金额
	 * 
	 * @return List<TicketInfoPO>
	 * @author lijunke @创建时间： 2017年3月13日上午11:36:27
	 * @param date
	 */
	public List<TicketInfoPO> queryByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
	public void voucherInfoImport(List<VoucherInfo> voucherlist);

	public void deleteVoucher(VoucherInfo voucher);
	
}

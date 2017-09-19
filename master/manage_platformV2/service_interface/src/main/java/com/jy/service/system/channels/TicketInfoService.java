package com.jy.service.system.channels;

import java.util.List;

import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.channels.ThirdPayInfo;
import com.jy.entity.system.channels.TicketInfoPO;
import com.jy.entity.system.channels.VoucherInfo;
import com.jy.service.base.BaseService;

/**
 * Created by Matthew on 2017/1/5 0005.
 */
public interface TicketInfoService extends BaseService<TicketInfoPO>
{

	public void ticketImport(List<TicketInfoPO> list);

	public void thirdImport(List<ThirdPayInfo> list);

	public void deleteThird(ThirdPayInfo vo);

	public void cashInfoImport(List<CashInfo> cashlist);

	/**
	 * @方法功能描述： 方法作用 删除该批次导入的兑奖数据
	 *
	 * @param cashinfo void
	 * @author Dingj @创建时间： 2017年3月16日上午9:35:51
	 */
	public void deleteCash(CashInfo cashinfo);

	public void voucherInfoImport(List<VoucherInfo> voucherlist);

	public void deleteVoucher(VoucherInfo voucher);

}

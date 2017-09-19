package com.jy.service.impl.system.channels;

import java.util.List;

import com.jy.service.impl.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.entity.system.channels.CashInfo;
import com.jy.entity.system.channels.ThirdPayInfo;
import com.jy.entity.system.channels.TicketInfoPO;
import com.jy.entity.system.channels.VoucherInfo;
import com.jy.repository.system.channels.TicketInfoDao;
import com.jy.service.system.channels.TicketInfoService;

/**
 * Created by Matthew on 2017/1/5 0005.
 */
@Service("ticketInfoService")
public class TicketInfoServiceImpl extends BaseServiceImp<TicketInfoPO> implements TicketInfoService {

	@Autowired
	private TicketInfoDao ticketInfoDao;

	@Transactional
	public void ticketImport(List<TicketInfoPO> list) {
		if(list.size()>1000){
			int limit = 1000;
			int size = list.size()/limit;
			for(int i=0;i<size;i++) {
				List<TicketInfoPO> listPage = list.subList(0, limit);
				ticketInfoDao.ticketImport(listPage);
				list.subList(0, limit).clear();
				if(list.size()<1000){
					ticketInfoDao.ticketImport(list);
				}
			}
		}else{
			ticketInfoDao.ticketImport(list);
		}

	}

	@Transactional
	public void thirdImport(List<ThirdPayInfo> list) {
		ticketInfoDao.thirdImport(list);

	}

	@Transactional
	public void deleteThird(ThirdPayInfo vo) {
		ticketInfoDao.deleteThird(vo);
	}

	@Override
	public void cashInfoImport(List<CashInfo> cashlist) {
		ticketInfoDao.cashInfoImport(cashlist);
	}

	@Override
	public void deleteCash(CashInfo cashinfo) {
		ticketInfoDao.deleteCash(cashinfo);
	}

	@Override
	public void voucherInfoImport(List<VoucherInfo> list) {
		if (list.size() > 2000) {
			int limit = 1000;
			int size = list.size() / limit;
			for (int i = 0; i < size; i++) {
				List<VoucherInfo> listPage = list.subList(0, limit);
				ticketInfoDao.voucherInfoImport(listPage);
				list.subList(0, limit).clear();
				if (list.size() < 1000) {
					ticketInfoDao.voucherInfoImport(list);
				}
			}
		} else {
			ticketInfoDao.voucherInfoImport(list);
		}
	}

	@Override
	public void deleteVoucher(VoucherInfo voucher) {
		ticketInfoDao.deleteVoucher(voucher);
	}

}

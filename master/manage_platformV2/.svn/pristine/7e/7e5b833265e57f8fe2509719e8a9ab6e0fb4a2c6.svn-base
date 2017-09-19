package com.jy.service.impl.system.channels;

import com.jy.entity.system.channels.TicketImportRecordsVO;
import com.jy.repository.system.channels.TicketImportRecordsDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.TicketImportRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("TicketImportRecordsService")
public class TicketImportRecordsServiceImpl extends BaseServiceImp<TicketImportRecordsVO> implements TicketImportRecordsService {

	@Autowired
	private TicketImportRecordsDao ticketImportRecordsDao;

	/**
	 * 查找出当天导入次数
	 * @param
	 * @return
	 */
	@Override
	public Integer findImportCount(String createDate,String payWay) {
		return  ticketImportRecordsDao.findImportCount(createDate,payWay);
	}
}

package com.days.quartz;

import java.util.Date;

import com.jy.process.inter.system.reconciliation.games.GamesProcess;
import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;

public class GetGameDataQuartz {
	private static Logger m_logger = Logger.getLogger(GetGameDataQuartz.class);

	@Autowired
	private GamesProcess gamesProcess;
	public void run(){
		m_logger.info("Start quartz run:" + new Date());
		try {
			//gamesProcess.insertUserGamesInfo();
			gamesProcess.insertUserDayBalanceinfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	
}

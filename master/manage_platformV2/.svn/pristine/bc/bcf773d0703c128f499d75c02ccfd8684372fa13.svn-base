package com.jy.service.impl.system.reconciliation.games;

import com.jy.repository.system.reconciliation.games.GamesDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.reconciliation.games.GamesService;
import com.jy.vo.system.reconciliation.games.GamesUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by ZQY on 2017/3/24.
 */
@Service("gamesService")
public class GamesServiceImpl extends BaseServiceImp<GamesUserVO> implements GamesService{
    @Autowired
    private GamesDao gamesDao;


    public void insertUserGamesInfo(Map map) throws Exception{
            gamesDao.insertUserGamesInfo(map);
    }
    @Transactional
    public void  insertUserDayBalanceinfo(Map balanceMap,Map propBalance) throws Exception{
            gamesDao.insertUserDayBalanceInfo(balanceMap);
//        List list =gamesDao.selectUserDayPropBalanceInfo();
            //int x = Integer.parseInt("df");
            if(propBalance.size()>0){
                gamesDao.insertUserDayPropBalanceInfo(propBalance);
            }
    }
}

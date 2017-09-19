package com.jy.repository.system.reconciliation.games;

import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import com.jy.vo.system.reconciliation.games.GamesUserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ZQY on 2017/3/24.
 */
@JYBatis
public interface GamesDao extends BaseDao<GamesUserVO> {
    public void insertUserGamesInfo(Map map) throws Exception;
    public void insertUserDayBalanceInfo(Map map) throws Exception;
    public void insertUserDayPropBalanceInfo(Map map) throws Exception;
    public List selectUserDayPropBalanceInfo() throws Exception;
}

package com.jy.process.system.reconciliation.games;

/**
 * Created by yutao on 2017/4/12.
 */
public interface GamesProcess {
    void insertUserGamesInfo();

    void insertUserDayBalanceinfo();

    String JsonStrToJsonObj(String jsonString);

    int countPage(String jsonString);

    String paramJson(String method, int currentPage);
}

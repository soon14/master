package com.jy.common.utils.games;


/**
 * Created by ZQY on 2016/12/1.
 */
public class Constants {
    /**
     * 游戏接口数据采集请求url
     */
    public final static String GAME_GETDATA_URL = "http://192.168.193.10:8085/api.go";
    /**
     * 游戏接口数据采集请求pageSize
     */
    public final static int  GAME_GETDATA_PAGESIZE = 500;
    /**
     * 游戏接口数据采集请求接口方法名称
     */
    public final static String GAME_GETDATA_METHOD_GETUSERINFO = "fish.getUserInfo";
    /**
     * 游戏接口数据采集请求接口方法名称UserDayBalanceinfo
     */
    public final static String GAME_GETDATA_METHOD_GETUSERBALANCEINFO = "fish.getUserDayBalanceinfo";

}

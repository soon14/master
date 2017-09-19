package com.jy.common.CpSystem;

/**
 * Created by Administrator on 2017/7/4.
 */
public class GameConst {


    //游戏充值交易渠道(1:微信 2:得仕通 3:银联）
    public final static Integer RECHARGECHANNEL_WECHAT =1;
    public final static Integer RECHARGECHANNEL_DAYSPAY =2;
    public final static Integer RECHARGECHANNEL_UNIONPAY =3;

    //兑换券增加减少
    public final static Integer VOUCHER_ADD =1;//增加
    public final static Integer VOUCHER_SUB=2;//减少


    //新增兑换券类型
    public final static Integer VOUCHER_ADD_GAME =1;//游戏
    public final static Integer VOUCHER_ADD_TURNTABLE =2;//转盘
    public final static Integer VOUCHER_ADD_SHARE =3;//分享

    //消耗兑换券类型
    public final static Integer VOUCHER_SUB_TOOL=1;//兑换道具
    public final static Integer VOUCHER_SUB_PRIZE =2;//兑换实物奖品

    //捕鱼
    public final static Integer GAME_TYPE_FISH =1;
    //棋牌
    public final static Integer GAME_TYPE_CARD=2;
}

package com.jy.common.CpSystem;

/**
 * Created by Administrator on 2017/7/4.
 */
public class CpConst {
    /**
     * 非活动用户，排除活动，优惠卷等,用于资金报表
     */
    public final static Integer ISCOMMON =1;

    public final static Integer COMMONORDER =1;

    public final static Integer TRANCEORDER =2;
    /**
     * 提现失败退款
     */
    public final static Integer REFUNDWITHDRAW =6;
    /**
     *出票失败退款
     */
    public final static Integer REFUNDTICKETFAIL =5;
    /**
     * 返佣
     */
    public final static Integer COMMISSION=7;
    /**
     * 优惠卷
     */
    public final static Integer COUPON=6;

    //彩票充值交易渠道(1:微信 2:得仕通 3:银联）
    public final static Integer RECHARGECHANNEL_WECHAT =1;
    public final static Integer RECHARGECHANNEL_DAYSPAY =2;
    public final static Integer RECHARGECHANNEL_UNIONPAY =3;

    //新增积分类型
    public final static Integer INTEGRAL_ADD_SIGN =0;//签到
    public final static Integer INTEGRAL_ADD_REGISTER =1;//注册
    public final static Integer INTEGRAL_ADD_RECHARGE =2;//充值
    public final static Integer INTEGRAL_ADD_FIRSTSENDPACKET =3;//首次发红包
    public final static Integer INTEGRAL_ADD_BUY=4;//购彩
    public final static Integer INTEGRAL_ADD_IMPROVEDATA =5;//完善个人信息
    public final static Integer INTEGRAL_ADD_INVITATION =6;//邀请好友注册
    public final static Integer INTEGRAL_ADD_SENDPACKET =7;//发彩包

    //消耗积分类型
    public final static Integer INTEGRAL_CONSUME_EXCHANGE=10;//彩券
    public final static Integer INTEGRAL_CONSUME_GAME =11;//游戏

}

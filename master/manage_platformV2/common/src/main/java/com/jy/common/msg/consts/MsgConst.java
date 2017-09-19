package com.jy.common.msg.consts;

import com.jy.common.msg.RetMsg;
import com.jy.common.utils.base.Const;

/**
 * Created by yutao on 2017/4/20.
 */
public class MsgConst {
    public final static RetMsg SUCCESS = new RetMsg(Const.SUCCEED,"成功！",MsgType.MESSAGE);
    public final static RetMsg FAIL = new RetMsg(Const.FAIL,"失败！",MsgType.ERROR);

    public static class Srv{
        public final static RetMsg ERR_VALICODE_SEND_FAIL = new RetMsg(1001,"短信发送失败！",MsgType.ERROR);
    }
    public static class User{

        public final static RetMsg ERR_PHONE_ERR = new RetMsg(2001,"无效的手机号码！",MsgType.ERROR);
        public final static RetMsg ERR_PHONE_EXIST_ERR = new RetMsg(2002,"手机号已经存在！",MsgType.ERROR);
        public final static RetMsg ERR_USER_EXIST_ERR = new RetMsg(2002,"用户已经存在！",MsgType.ERROR);
        public final static RetMsg NO_AUTHORIZED = new RetMsg(Const.NO_AUTHORIZED,Const.NO_AUTHORIZED_MSG,MsgType.ERROR);
    }
}

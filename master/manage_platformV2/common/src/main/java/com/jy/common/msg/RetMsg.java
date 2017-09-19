package com.jy.common.msg;

import com.jy.common.msg.consts.MsgType;
import com.jy.common.utils.base.Const;

import java.util.Map;

/**
 * Created by yutao on 2017/2/17.
 */
public class RetMsg {
    /**
     * 消息编码
     */
    int code;

    /**
     * 消息内容
     */
    String msg;

    /**
     * 消息类别
     * 从常量MsgType中取
     * 默认值为"Error";
     */
    String type;

    /**
     * 扩展数据，用于传递一些附属对象，一般用不到
     */
    Map<String,Object> map;

    public RetMsg(int code, String msg, String type) {
        this.code = code;
        this.msg = msg;
        this.type = type;
    }
    public RetMsg(int code, String msg, String type, Map<String,Object> map) {
        this.code = code;
        this.msg = msg;
        this.type = type;
    }
    public RetMsg(int code, Map<String,Object> map) {
        this.code = code;
        this.msg = "";
        this.type = MsgType.ERROR;
        this.map = map;
    }

    public RetMsg(String msg) {
        this.code = Const.FAIL;
        this.msg = msg;
        this.type = MsgType.ERROR;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}

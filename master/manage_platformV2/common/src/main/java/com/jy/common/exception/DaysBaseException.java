package com.jy.common.exception;

import com.jy.common.msg.RetMsg;

import java.util.Map;

/**
 * Created by yutao on 2017/4/20.
 */
public class DaysBaseException extends Exception{
    private RetMsg retMsg;
    public DaysBaseException(){

    }

    public DaysBaseException(RetMsg retMsg){
        this.retMsg = retMsg;
    }

    public DaysBaseException(RetMsg retMsg, Map<String,Object> map){
        this.retMsg = retMsg;
        retMsg.setMap(map);
    }

    public RetMsg getRegMsg(){
        return retMsg;
    }
}

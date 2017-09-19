package com.jy.common.massert;

import com.jy.common.exception.DaysBaseException;
import com.jy.common.msg.RetMsg;

/**
 * Created by yutao on 2017/4/20.
 */
public class MAssert {

    public static void massertIsNull(Object obj, RetMsg retMsg) throws DaysBaseException{
        if(obj != null){
            throw new DaysBaseException(retMsg);
        }
    }

    public static void massertNotNull(Object obj, RetMsg retMsg) throws DaysBaseException{
        if(obj == null){
            throw new DaysBaseException(retMsg);
        }
    }

    public static void massertEqual(Object obj, Object obj2,RetMsg retMsg) throws DaysBaseException{
        if(!obj.equals(obj2)){
            throw new DaysBaseException(retMsg);
        }
    }

    public static void massertNotEqual(Object obj, Object obj2,RetMsg retMsg) throws DaysBaseException{
        if(obj.equals(obj2)){
            throw new DaysBaseException(retMsg);
        }
    }

    public static void massertTrue(Boolean b, RetMsg retMsg) throws DaysBaseException{
        if(!b){
            throw new DaysBaseException(retMsg);
        }
    }

}

package com.jy.common.utils.game;

import com.jy.common.utils.CalculationUtils;

/**
 * Created by Administrator on 2017/3/30.
 */
public class DiamondOrGoldToMoneyUtils {


    /**
     * 钻石：现金=1:1
     * @param diamond
     * @return
     */
    public final static Double turnDiamondToMoney(Integer diamond){
        if(diamond==null){
            return 0.0;
        }
        String money=diamond.toString();
        return Double.valueOf(money);

    }


    public final static Double turnGoldToMoney(Integer gold){
        if(gold==null){
            return 0.0;
        }
        Integer diamond=turnGoldToDiamond(gold);
        return turnDiamondToMoney(diamond);

    }


    public final static Integer turnDiamondToGold(Integer diamond){
        return Integer.parseInt(CalculationUtils.mul(diamond,1000));
    }

    public final static Integer turnGoldToDiamond(Integer gold){
        if(gold==0||gold==null){
            return 0;
        }
        String val=CalculationUtils.div(gold,1000,1);
        return parseDoubleToInt(Double.valueOf(val));
    }

    public final static Integer parseDoubleToInt(Double money) {
        if(money.compareTo(0.0)==0||money==null){
            return 0;
        }else {
            String s=money.toString();
            return Integer.parseInt(s.substring(0,s.indexOf(".")));
        }
    }
}

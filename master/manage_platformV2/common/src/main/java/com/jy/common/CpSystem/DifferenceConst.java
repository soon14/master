package com.jy.common.CpSystem;

/**
 *SalesDifferencesDetail对应常量
 * Created by Dingj on 2017-01-15.
 */
public class DifferenceConst {

    /**
     * 差异原因，金额不对
     */
    public final static String DIFFERENCE_REASON_MONEY = "third交易流水号payFlowNo差异金额是thirdMoney";

    /**
     * 差异原因，状态不对
     */
    public final static String DIFFERENCE_REASON_STATUS = "third交易流水号payFlowNo有,我方无流水交易或交易未成功";
    public final static String DIFFERENCE_REASON_STATUS_US = "我方交易流水号payFlowNo有,us无流水交易或交易未成功";
    /**
     * 差异原因，时间差
     */
    public final static String DIFFERENCE_REASON_DATE = "third交易流水号payFlowNo的交易时间是thirdDate";



    public final static Integer DIFFERENCE_RESULT_NO = 0;//未处理
    public final static Integer DIFFERENCE_RESULT_OK = 1;//已处理
    public final static Integer DIFFERENCE_RESULT_ING = 2;//处理中
    public final static Integer DIFFERENCE_RESULT_UNDEAL = 3;//不用处理

    public final static Integer DIFFERENCE_TYPE_1 =1;//彩票系统有，第三方无
    public final static Integer DIFFERENCE_TYPE_2 =2;////彩票系统无，第三方有
    public final static Integer DIFFERENCE_TYPE_3 =3;//金额差异
    public final static Integer DIFFERENCE_TYPE_4 =4;//时间差异
    /***
     *  类型，1：平台总资金差异 2：第三方流水差异
     */
    public final static Integer TYPE_1 = 1;
    public final static Integer TYPE_2 = 2;



    public final static String getResult(String dResult) {
        String type="";
        if(dResult.equals(DifferenceConst.DIFFERENCE_RESULT_NO+"")){
            type="未处理";
        }else if(dResult.equals(DifferenceConst.DIFFERENCE_RESULT_OK+"")){
            type="已处理";
        }else if(dResult.equals(DifferenceConst.DIFFERENCE_RESULT_ING+"")){
            type="处理中";
        }else if(dResult.equals(DifferenceConst.DIFFERENCE_RESULT_UNDEAL+"")){
            type="不用处理";
        }
        return type;
    }

    public final static String getType(String dDifferenceType) {
        String type=dDifferenceType;
        if(dDifferenceType.equals(DifferenceConst.DIFFERENCE_TYPE_1+"")){
            type="彩票系统有，第三方无";
        }else if(dDifferenceType.equals(DifferenceConst.DIFFERENCE_TYPE_2+"")){
            type="彩票系统无，第三方有";
        }else if(dDifferenceType.equals(DifferenceConst.DIFFERENCE_TYPE_3+"")){
            type="金额差异";
        }else if(dDifferenceType.equals(DifferenceConst.DIFFERENCE_TYPE_4+"")){
            type="时间差异";
        }
        return type;
    }
/***
 *  总差异详情报表
 */

    //方案和票差异
    public final static Integer LOTTERYSALESDIFF_TYPE_1 = 1;
    //购彩和出票差异
    public final static Integer LOTTERYSALESDIFF_TYPE_2 = 2;
    //出票系统和彩票机差异
    public final static Integer LOTTERYSALESDIFF_TYPE_3 = 3;
    //销售汇总差异
    public final static Integer LOTTERYSALESDIFF_TYPE_4 = 4;
}

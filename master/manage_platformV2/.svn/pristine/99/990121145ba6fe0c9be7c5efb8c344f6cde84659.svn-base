package com.jy.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金额计算类
 */
public class CalculationUtils {
    private static DecimalFormat df = new DecimalFormat("#.00");

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static String add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return df.format(b1.add(b2).doubleValue());
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static String sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return df.format(b1.subtract(b2).doubleValue());
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static String mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return df.format(b1.multiply(b2).doubleValue());
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return df.format(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    /**
     * 将字符串转化为double类型的金额
     *
     * @param amt
     * @return
     */
    public static double fomatAmt(String amt) {
        return Double.parseDouble(amt);
    }

    public static String formatString(double amount) {
        return Double.toString(amount);
    }

    public static double fomatMoney(Double amt) {
        if(amt==null){
            return 0.0;
        }
        return fomatAmt(df.format(amt));
    }

}

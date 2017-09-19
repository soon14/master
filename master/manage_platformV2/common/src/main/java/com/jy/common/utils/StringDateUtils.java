package com.jy.common.utils;

/**
 * @文件名:StringDateUtils.java
 * @功能描述：
 * @创建日期:2017年4月13日下午2:29:36
 * @创建人：Dingj
 * @Copyright（c） 2017,all rights reserved by days
 */
public class StringDateUtils {
	
	public static String subDateString(String str) {
		StringBuffer sb = new StringBuffer();
		String[] temp = str.split("-");
		
		return sb.append(temp[0]).append("-").append(temp[1]).toString();
		
	}
	
}
